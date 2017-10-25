
package org.usfirst.frc4914.CurbStomper;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc4914.CurbStomper.commands.*;
import org.usfirst.frc4914.CurbStomper.subsystems.*;

public class Robot extends IterativeRobot {

	public static OI oi;
    public static PDP pDP;
	public static Drivetrain drivetrain;
	public static Climber climber;
	// public static Fuel fuel;
	public static Gear gear;

	static CameraServer server;
	
	Command autonomousCommand;
	// SendableChooser<CommandGroup> autoChooser;

	// @Override
	public void robotInit() {
			
		RobotMap.init();
		
		drivetrain = new Drivetrain();
		climber = new Climber();
		// fuel = new Fuel();
		gear = new Gear();
		
		oi = new OI();
		
		// initializes camera server
		server = CameraServer.getInstance();
		// cameraInit();
		// server.startAutomaticCapture(0);
		
		// autonomousCommand = (Command) new AutoMiddleHook();
//		autonomousCommand = (Command) new AutoBaseline();
		autonomousCommand = (Command) new AutoRightHook();
		
		// resets all sensors
		resetAllSensors();

		if (RobotConstants.isTestingEnvironment) updateTestingEnvironment();
		updateSensorDisplay();
	}

	// @Override
	public void disabledInit() {
		Robot.drivetrain.stop();
		Robot.climber.stop();
		// Robot.fuel.stopAll();
		Robot.gear.stop();
	}

	// @Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		// DEBUG CODE HERE \\
		updateSensorDisplay();
		
		// *************** \\
	}

	// @Override
	public void autonomousInit() {

		// DEBUG \\
		if (RobotConstants.isTestingEnvironment) updateTestingEnvironment();
		
		// resets sensors
		resetAllSensors();
		
		// autonomousCommand = (Command) autoChooser.getSelected();
		
		if (autonomousCommand != null) autonomousCommand.start();
	}
	
	// @Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		// DEBUG \\
		if (RobotConstants.isTestingEnvironment) readTestingEnvironment();
		updateSensorDisplay();
	}

	// @Override
	public void teleopInit() {
		RobotConstants.isBaselineAuto = false;
		// stops auto
		if (autonomousCommand != null) autonomousCommand.cancel();
		
		RobotConstants.isInverted = !RobotConstants.isInverted;
		
		// DEBUG \\
		if (RobotConstants.isTestingEnvironment) updateTestingEnvironment();
		updateSensorDisplay();
	}

	/**
	 * This function is called periodically during operator control
	 */
	// @Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		// DEBUG \\
		if (RobotConstants.isTestingEnvironment) readTestingEnvironment();
		updateSensorDisplay();
		
		System.out.println(Robot.drivetrain.getEncoderPosition());
		
		// drive control
		drive();
		// climb control
		climb();
	}

	/**
	 * This function is called periodically during test mode
	 */
	// @Override
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
    
    private void drive() {
    	char override = 'p'; // which controller to take control
    	double p = 0; // total primary input
    	double c = 0; // total co input

    	// calculates total primary input
    	/*if (RobotConstants.isTrigger) {*/
    		p += Math.abs(Robot.oi.getPrimaryRT());
    		p += Math.abs(Robot.oi.getPrimaryLT());
    		p += Math.abs(Robot.oi.getPrimaryRJ_V());
    		p += Math.abs(Robot.oi.getPrimaryLJ_V());/*
    	} else {
    		p += Math.abs(Robot.oi.getPrimaryLJ_V());
    		p += Math.abs(Robot.oi.getPrimaryRJ_V());
    	}*/
    	
    	// calculates total co input
    	c += Math.abs(Robot.oi.getCoLJ_V());
    	c += Math.abs(Robot.oi.getCoRJ_V());
    	
    	// finds overriding input
    	if (p*1.5 > c) {
    		override = 'p';
    	} else {
    		override = 'c';
    	}
    	
    	// primary drive inputs
    	if (override == 'p') {
    		/* if (RobotConstants.isTrigger) {
    			Robot.drivetrain.triggerDrive(Robot.oi.getPrimaryLJ_H(), Robot.oi.getPrimaryRT(), Robot.oi.getPrimaryLT(), RobotConstants.isInverted);
    		} else {
    			Robot.drivetrain.tankDrive(Robot.oi.getPrimaryLJ_V(), Robot.oi.getPrimaryRJ_V(), false, RobotConstants.isInverted);
    		} */
    		Robot.drivetrain.hybridDrive(Robot.oi.getPrimaryJoystick(), RobotConstants.isInverted);
    	}
    	
    	// co drive inputs
    	if (override == 'c') {
    		Robot.drivetrain.tankDrive(Robot.oi.getCoLJ_V()*0.35, Robot.oi.getCoRJ_V()*0.35, false, !RobotConstants.isInverted);
    	}
    }
    
    private void climb() {
    	// Robot.climber.setSpeed(Robot.oi.getCoRT() - Robot.oi.getCoLT());
    	Robot.climber.setSpeed(Robot.oi.getCoLT()*-0.2 + Robot.oi.getCoRT()*-0.7);
    }
    
    public void resetAllSensors() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.resetGyro();
    	Robot.gear.resetEncoder();
    }
    
    /**
     * starts up smartdashboard variable changing
     */
    public void updateTestingEnvironment() {
    	// CONSTANTS \\
    	/*SmartDashboard.putBoolean("isInverted", RobotConstants.isInverted);
    	SmartDashboard.putBoolean("isTrigger", RobotConstants.isTrigger);*/
    	
    	SmartDashboard.putNumber("AUTO_SPEED", RobotConstants.AUTO_SPEED);
    	SmartDashboard.putNumber("INCHES_TO_ENCODER", RobotConstants.INCHES_TO_ENCODER);
    	
    	SmartDashboard.putNumber("AUTO_DRIVE_TOLERANCE", RobotConstants.AUTO_DRIVE_TOLERANCE);
    	SmartDashboard.putNumber("AUTO_DRIVE_RHOOK_D1", RobotConstants.AUTO_DRIVE_RHOOK_D1);
    	SmartDashboard.putNumber("AUTO_DRIVE_RHOOK_D2", RobotConstants.AUTO_DRIVE_RHOOK_D2);
    	SmartDashboard.putNumber("AUTO_DRIVE_LHOOK_D1", RobotConstants.AUTO_DRIVE_LHOOK_D1);
    	SmartDashboard.putNumber("AUTO_DRIVE_LHOOK_D2", RobotConstants.AUTO_DRIVE_LHOOK_D2);
    	SmartDashboard.putNumber("AUTO_DRIVE_MHOOK_D1", RobotConstants.AUTO_DRIVE_MHOOK_D1);
    	SmartDashboard.putNumber("AUTO_DRIVE_P", RobotConstants.AUTO_DRIVE_P);
    	SmartDashboard.putNumber("AUTO_DRIVE_I", RobotConstants.AUTO_DRIVE_I);
    	SmartDashboard.putNumber("AUTO_DRIVE_D", RobotConstants.AUTO_DRIVE_D);
    	
    	SmartDashboard.putNumber("AUTO_TURN_TOLERANCE", RobotConstants.AUTO_TURN_TOLERANCE);
    	SmartDashboard.putNumber("AUTO_TURN_RHOOK_SETPOINT", RobotConstants.AUTO_TURN_RHOOK_SETPOINT);
    	SmartDashboard.putNumber("AUTO_TURN_LHOOK_SETPOINT", RobotConstants.AUTO_TURN_LHOOK_SETPOINT);
    	SmartDashboard.putNumber("AUTO_TURN_P", RobotConstants.AUTO_TURN_P);
    	SmartDashboard.putNumber("AUTO_TURN_I", RobotConstants.AUTO_TURN_I);
    	SmartDashboard.putNumber("AUTO_TURN_D", RobotConstants.AUTO_TURN_D);
    	
    	/*SmartDashboard.putNumber("FLYWHEEL_SPEED", RobotConstants.FLYWHEEL_SPEED);
    	
    	SmartDashboard.putNumber("VISION_TOLERANCE", RobotConstants.VISION_TOLERANCE);
    	SmartDashboard.putNumber("VISION_X_SETPOINT", RobotConstants.VISION_X_SETPOINT);
    	SmartDashboard.putNumber("VISION_Y_SETPOINT", RobotConstants.VISION_Y_SETPOINT);
    	SmartDashboard.putNumber("VISION_P", RobotConstants.VISION_P);
    	SmartDashboard.putNumber("VISION_I", RobotConstants.VISION_I);
    	SmartDashboard.putNumber("VISION_D", RobotConstants.VISION_D);
    	
    	SmartDashboard.putNumber("GEAR_TOLERANCE", RobotConstants.GEAR_TOLERANCE);
    	SmartDashboard.putNumber("GEAR_INIT_SETPOINT", RobotConstants.GEAR_INIT_SETPOINT);
    	SmartDashboard.putNumber("GEAR_FINAL_SETPOINT", RobotConstants.GEAR_FINAL_SETPOINT);
    	SmartDashboard.putNumber("GEAR_P", RobotConstants.GEAR_P);
    	SmartDashboard.putNumber("GEAR_I", RobotConstants.GEAR_I);
    	SmartDashboard.putNumber("GEAR_D", RobotConstants.GEAR_D);*/
    }
    
    public void updateSensorDisplay() {
    	// SENSORS \\
    	SmartDashboard.putNumber("Drive Encoder", Robot.drivetrain.getEncoderPosition());
    	SmartDashboard.putNumber("Gyro", Robot.drivetrain.getGyroBearing());
    	SmartDashboard.putNumber("Gear Encoder", Robot.gear.getEncoderPosition());
    	
    }
    
    /**
     * grabs values from testing environment
     */
    public void readTestingEnvironment() {
    	/*RobotConstants.isInverted = SmartDashboard.getBoolean("isInverted", false);
		RobotConstants.isTrigger = SmartDashboard.getBoolean("isTrigger", false);*/
		
		RobotConstants.AUTO_SPEED = SmartDashboard.getNumber("AUTO_SPEED", 0);
		RobotConstants.AUTO_SPEED = SmartDashboard.getNumber("INCHES_TO_ENCODER", 0);
		
		RobotConstants.AUTO_DRIVE_TOLERANCE = SmartDashboard.getNumber("AUTO_DRIVE_TOLERANCE", 0);
		RobotConstants.AUTO_DRIVE_RHOOK_D1 = SmartDashboard.getNumber("AUTO_DRIVE_RHOOK_D1", 0);
		RobotConstants.AUTO_DRIVE_RHOOK_D2 = SmartDashboard.getNumber("AUTO_DRIVE_RHOOK_D2", 0);
		RobotConstants.AUTO_DRIVE_LHOOK_D1 = SmartDashboard.getNumber("AUTO_DRIVE_LHOOK_D1", 0);
		RobotConstants.AUTO_DRIVE_LHOOK_D2 = SmartDashboard.getNumber("AUTO_DRIVE_LHOOK_D2", 0);
		RobotConstants.AUTO_DRIVE_MHOOK_D1 = SmartDashboard.getNumber("AUTO_DRIVE_MHOOK_D1", 0);
		RobotConstants.AUTO_DRIVE_P = SmartDashboard.getNumber("AUTO_DRIVE_P", 0);
		RobotConstants.AUTO_DRIVE_I = SmartDashboard.getNumber("AUTO_DRIVE_I", 0);
		RobotConstants.AUTO_DRIVE_D = SmartDashboard.getNumber("AUTO_DRIVE_D", 0);
		
		RobotConstants.AUTO_TURN_TOLERANCE = (int) SmartDashboard.getNumber("AUTO_TURN_TOLERANCE", 0);
		RobotConstants.AUTO_TURN_RHOOK_SETPOINT = (int) SmartDashboard.getNumber("AUTO_TURN_RHOOK_SETPOINT", 0);
		RobotConstants.AUTO_TURN_LHOOK_SETPOINT = (int) SmartDashboard.getNumber("AUTO_TURN_LHOOK_SETPOINT", 0);
		RobotConstants.AUTO_TURN_P = SmartDashboard.getNumber("AUTO_TURN_P", 0);
		RobotConstants.AUTO_TURN_I = SmartDashboard.getNumber("AUTO_TURN_I", 0);
		RobotConstants.AUTO_TURN_D = SmartDashboard.getNumber("AUTO_TURN_D", 0);
		
		/*RobotConstants.FLYWHEEL_SPEED = SmartDashboard.getNumber("FLYWHEEL_SPEED", 0);
		
		RobotConstants.VISION_TOLERANCE = (int) SmartDashboard.getNumber("VISION_TOLERANCE", 0);
		RobotConstants.VISION_X_SETPOINT = (int) SmartDashboard.getNumber("VISION_X_SETPOINT", 0);
		RobotConstants.VISION_Y_SETPOINT = (int) SmartDashboard.getNumber("VISION_Y_SETPOINT", 0);
		RobotConstants.VISION_P = SmartDashboard.getNumber("VISION_P", 0);
		RobotConstants.VISION_I = SmartDashboard.getNumber("VISION_I", 0);
		RobotConstants.VISION_D = SmartDashboard.getNumber("VISION_D", 0);
		
		RobotConstants.GEAR_TOLERANCE = SmartDashboard.getNumber("GEAR_TOLERANCE", 0);
		RobotConstants.GEAR_INIT_SETPOINT = SmartDashboard.getNumber("GEAR_INIT_SETPOINT", 0);
		RobotConstants.GEAR_FINAL_SETPOINT = SmartDashboard.getNumber("GEAR_FINAL_SETPOINT", 0);
		RobotConstants.GEAR_P = SmartDashboard.getNumber("GEAR_P", 0);
		RobotConstants.GEAR_I = SmartDashboard.getNumber("GEAR_I", 0);
		RobotConstants.GEAR_D = SmartDashboard.getNumber("GEAR_D", 0);*/
    }
    
    /**
     * Limits speed to values between 1 and -1
     * @param speed raw input speed
     * @return returns limited speed between 1 and -1
     */
    public static double limit(double speed) {
    	if (speed > 1) {
    		return 1;
    	} else if (speed < -1) {
    		return -1;
    	} else {
    		return speed;
    	}
    }
}
