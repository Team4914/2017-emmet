package org.usfirst.frc4914.CurbStomper;

public class RobotConstants {
	
	public static boolean isTestingEnvironment = false;
	
	// DRIVE \\
	public static boolean isInverted = true;
	public static boolean isTrigger = false;
	public static double CO_DRIVE_PERCENT = 30; // unused
	
	// AUTONOMOUS \\
	public static boolean isBaselineAuto = false;
	// divide real-world velocity in inches per second by 60 to obtain motor output
	public static double AUTO_SPEED = 10/60.0;
	public static double INCHES_TO_ENCODER = 1.0/9.185185;

	public static double AUTO_DRIVE_TOLERANCE = 0.5;
	public static double AUTO_DRIVE_RHOOK_D1 = 67.9;
	public static double AUTO_DRIVE_RHOOK_D2 = 67.0;
	public static double AUTO_DRIVE_LHOOK_D1 = 68.8;
	public static double AUTO_DRIVE_LHOOK_D2 = 65.3;
	public static double AUTO_DRIVE_MHOOK_D1 = 10; // originally 72
	public static double AUTO_DRIVE_P = 0.1;
	public static double AUTO_DRIVE_I = 0;
	public static double AUTO_DRIVE_D = 0;
	
	public static double AUTO_DRIVESTRAIGHT_P = 0.05;
	
	public static int AUTO_TURN_TOLERANCE = 2;
	public static int AUTO_TURN_RHOOK_SETPOINT = 60;
	public static int AUTO_TURN_LHOOK_SETPOINT = 60;
	public static double AUTO_TURN_P = 0.1;
	public static double AUTO_TURN_I = 0;
	public static double AUTO_TURN_D = 0;
	
	// NEW DEAD RECKON AUTO \\
	//Use AUTO_SPEED for speed from normal autonomous ^
	public static double AUTO_RHOOK_D1_TIME = 7.6/2;
	public static double AUTO_RHOOK_INITIAL_ROTATION_TIME = 10.825;
	public static double AUTO_RHOOK_D2_TIME = 4.5/2;
	public static double AUTO_RHOOK_D3_TIME = 2.5/2;
	
	public static double AUTO_LHOOK_D1_TIME = 7.6/2;
	public static double AUTO_LHOOK_INITIAL_ROTATION_TIME = 10.825;
	public static double AUTO_LHOOK_D2_TIME = 4.5/2;
	public static double AUTO_LHOOK_D3_TIME = 2.5/2;
	
	public static double AUTO_MHOOK_D1_TIME = 7.9/2;
	public static double AUTO_MHOOK_D2_TIME = 4.5/2;
	
	// FUEL \\
	public static double FLYWHEEL_SPEED = 0.9;
	public static boolean isShoot = false;
	
	// VISION \\
	public static int VISION_TOLERANCE = 2;
	public static int VISION_X_SETPOINT = 0;
	public static int VISION_Y_SETPOINT = 0;
	public static double VISION_P = 0.001;
	public static double VISION_I = 1;
	public static double VISION_D = 1;
	
	// GEAR \\
	public static double GEAR_TOLERANCE = 10;
	public static double GEAR_INIT_SETPOINT = 0;
	public static double GEAR_FINAL_SETPOINT = 750;
	public static double GEAR_P = 0.1;
	public static double GEAR_I = 1;
	public static double GEAR_D = 1;
}