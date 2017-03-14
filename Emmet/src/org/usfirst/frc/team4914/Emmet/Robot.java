
package org.usfirst.frc.team4914.Emmet;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4914.robot.commands.*;
import org.usfirst.frc.team4914.robot.subsystems.*;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drivetrain;
	public static Climber climber;
	public static Fuel fuel;

	static CameraServer server;
	
	Command autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		
		drivetrain = new Drivetrain();
		climber = new Climber();
		fuel = new Fuel();
		
		oi = new OI();
		
		server = CameraServer.getInstance();
		cameraInit();
		
		autoChooser.addDefault("Middle Hook", new AutoMiddleHook());
		autoChooser.addObject("Left Hook", new AutoLeftHook());
		autoChooser.addObject("Right Hook", new AutoRightHook());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", autoChooser);
		
		SmartDashboard.putNumber("Auto delay", 0);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.drivetrain.stop();
		Robot.climber.stop();
		Robot.fuel.stopAll();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		// DEBUG CODE HERE \\
		
		// *************** \\
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = (Command) autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// primary
		if (RobotConstants.isTrigger) {
			Robot.drivetrain.arcadeDrive(Robot.oi.getPrimaryLJ_H(), Robot.oi.getPrimaryRT(), Robot.oi.getPrimaryLT());
		} else {
			Robot.drivetrain.tankDrive(Robot.oi.getPrimaryLJ_V(), Robot.oi.getPrimaryRJ_V(), false, RobotConstants.isInverted);
		}
		
		// co turn
    	if (Math.abs(Robot.oi.getCoZ()) > 0.25) {
    		Robot.drivetrain.setAdditionalInput(-Robot.oi.getCoZ() * RobotConstants.CO_DRIVE_PERCENT / 100
    			, Robot.oi.getCoZ() * RobotConstants.CO_DRIVE_PERCENT / 100);
    	}
    	
    	// co straight
    	if (RobotConstants.isInverted) {
	    	Robot.drivetrain.setAdditionalInput(-Robot.oi.getCoY() * RobotConstants.CO_DRIVE_PERCENT / 100
	    		, -Robot.oi.getCoY() * RobotConstants.CO_DRIVE_PERCENT / 100);
    	} else {
	    	Robot.drivetrain.setAdditionalInput(Robot.oi.getCoY() * RobotConstants.CO_DRIVE_PERCENT / 100
	    		, Robot.oi.getCoY() * RobotConstants.CO_DRIVE_PERCENT / 100);
    	}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	/**
	 * Returns auto delay, in seconds, from SmartDashboard
	 */
	public static double getAutoDelay() {
		return SmartDashboard.getNumber("Auto delay", 0);
	}
    
    /**
     * Camera switcher initialization
     */
    private void cameraInit() {

        // camera switching code
        Thread t = new Thread(() -> {
    		
    		boolean allowCam1 = false;
    		
    		UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
            camera1.setResolution(320, 240);
            camera1.setFPS(30);
            UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
            camera2.setResolution(320, 240);
            camera2.setFPS(30);
            
            CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
            CvSink cvSink2 = CameraServer.getInstance().getVideo(camera2);
            CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", 320, 240);
            
            Mat image = new Mat();            
            Mat grey = new Mat();
            
            while(!Thread.interrupted()) {
            	
            	if (Robot.oi.getPrimaryJoystick().getRawButton(4)) { allowCam1 = !allowCam1; }
            	
                if(allowCam1){
                  cvSink2.setEnabled(false);
                  cvSink1.setEnabled(true);
                  cvSink1.grabFrame(image);
                } else{
                  cvSink1.setEnabled(false);
                  cvSink2.setEnabled(true);
                  cvSink2.grabFrame(image);     
                }
                
                Imgproc.cvtColor(image, grey, Imgproc.COLOR_BGR2GRAY);
                
                outputStream.putFrame(grey);
            }
            
        });
        t.start();
    }
}
