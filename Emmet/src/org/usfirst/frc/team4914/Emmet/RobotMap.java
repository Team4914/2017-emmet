package org.usfirst.frc.team4914.Emmet;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class RobotMap {
	
	// DRIVETRAIN MOTORS \\
	public static CANTalon drivetrainCIMRearLeft;
	public static CANTalon drivetrainCIMFrontLeft;
	public static CANTalon drivetrainCIMRearRight;
	public static CANTalon drivetrainCIMFrontRight;
	public static RobotDrive drivetrainRobotDrive41;
	
	// DRIVE SENSORS \\
	public static ADXRS450_Gyro gyro;
	public static BuiltInAccelerometer accel;
	
	// CLIMB \\
	public static TalonSRX climber;
	
	// GEAR \\
	public static CANTalon claw;
	
	// FUEL \\
	public static TalonSRX intake;
	public static Talon shooter;
	
	// DIAGNOSTIC \\
	public static PowerDistributionPanel pdp;
	
	public static void init() {
		// DRIVETRAIN MOTORS \\
		drivetrainCIMRearLeft = new CANTalon(2);
		drivetrainCIMFrontLeft = new CANTalon(12);
		drivetrainCIMRearRight = new CANTalon(1);
		drivetrainCIMFrontRight = new CANTalon(11);
		LiveWindow.addActuator("Drivetrain", "RearLeft(2)", drivetrainCIMRearLeft);
		LiveWindow.addActuator("Drivetrain", "FrontLeft(12)", drivetrainCIMFrontLeft);
		LiveWindow.addActuator("Drivetrain", "RearRight(1)", drivetrainCIMRearRight);
		LiveWindow.addActuator("Drivetrain", "FrontRight(11)", drivetrainCIMFrontRight);
		
		// DRIVE \\
		drivetrainRobotDrive41 = new RobotDrive(drivetrainCIMRearLeft, drivetrainCIMFrontLeft, 
    			drivetrainCIMRearRight, drivetrainCIMFrontRight);
	
		// DRIVE SENSORS \\
		gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
		accel = new BuiltInAccelerometer();
		LiveWindow.addSensor("Drive Sensors", "Gyro", gyro);
		LiveWindow.addSensor("Drive Sensors", "Accelerometer", accel);
		
		// CLIMB \\
		climber = new TalonSRX(0);
		LiveWindow.addActuator("Climber", "Climber", climber);
		
		// GEAR \\
		claw = new CANTalon(3);
		LiveWindow.addActuator("Gear", "Claw", claw);
		
		// FUEL \\
		intake = new TalonSRX(5);
		shooter = new Talon(6);
		LiveWindow.addActuator("Fuel", "Intake", intake);
		LiveWindow.addActuator("Fuel", "Shooter", shooter);
		
		// DIAGNOSTIC \\
		pdp = new PowerDistributionPanel(0);
		LiveWindow.addSensor("PDP", "Power Distribution Panel", pdp);
		
	}
}
