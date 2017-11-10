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
	public static double AUTO_SPEED = 15/60.0;
	
	// multiply value in inches by this constant to obtain encoder value
	public static double INCHES_TO_ENCODER = 1.0/9.185185;

	public static double AUTO_DRIVE_TOLERANCE = 0.5;
	public static double AUTO_DRIVE_P = 0.1;
	public static double AUTO_DRIVE_I = 0;
	public static double AUTO_DRIVE_D = 0;
	
	public static double AUTO_DRIVESTRAIGHT_P = 0.05;
	
	public static int AUTO_TURN_TOLERANCE = 2;
	public static double AUTO_TURN_P = 0.1;
	public static double AUTO_TURN_I = 0;
	public static double AUTO_TURN_D = 0;
	
//	// NEW DEAD RECKON AUTO \\
	// Use AUTO_SPEED for speed from normal autonomous ^
	
	public static double AUTO_MHOOK_D1_TIME = 1.2;
	public static double AUTO_MHOOK_D2_TIME = 0.5;
	
	// used for both left and right hook
	// distance to move forward in inches
	public static double AUTO_LHOOK_D1 = 64;
	public static double AUTO_LHOOK_D2 = 80;
	
//	// SMOOTH AUTONOMOUS \\
////	public static double LEFT_HOOK_TURNING_RADIUS = 130.198;
//	public static double LEFT_HOOK_TURNING_RADIUS = 77;
//	public static double LEFT_HOOK_APPROACH_TIME = 7;
////	public static double LEFT_HOOK_TURNING_ANGLE = 72;
//	public static double LEFT_HOOK_TURNING_ANGLE = 70;
//	
//	public static double MIDDLE_DISTANCE = 114.207;
//	public static double MIDDLE_HOOK_TIME = 5;
	
	//to set emergency timeout for DeadReckonTurn in case gyro fails
	//Multiply angle by this constant and add one and a half seconds to get timeout
	public static double ANGLE_TO_TIME_DEAD_RECKON_TURN = 1/30;
	
	// FUEL \\
	public static double FLYWHEEL_SPEED = 0;
	
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