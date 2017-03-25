package org.usfirst.frc.team4914.Emmet;

public class RobotConstants {
	
	public static boolean isTestingEnvironment = true;
	
	// DRIVE \\
	public static boolean isInverted = true;
	public static boolean isTrigger = false;
	public static double CO_DRIVE_PERCENT = 30; // unused
	
	// AUTONOMOUS \\
	public static double AUTO_SPEED = 24;

	public static double AUTO_DRIVE_TOLERANCE = 0.5;
	public static double AUTO_DRIVE_RHOOK_SETPOINT = 0;
	public static double AUTO_DRIVE_LHOOK_SETPOINT = 0;
	public static double AUTO_DRIVE_MHOOK_SETPOINT = 0;
	public static double AUTO_DRIVE_P = 0.1;
	public static double AUTO_DRIVE_I = 1;
	public static double AUTO_DRIVE_D = 1;
	
	public static double AUTO_TURN_TOLERANCE = 2;
	public static double AUTO_TURN_RHOOK_SETPOINT = 60;
	public static double AUTO_TURN_LHOOK_SETPOINT = 60;
	public static double AUTO_TURN_P = 0.5;
	public static double AUTO_TURN_I = 1;
	public static double AUTO_TURN_D = 1;
	
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
	public static double GEAR_TOLERANCE = 0.01;
	public static double GEAR_INIT_SETPOINT = 0;
	public static double GEAR_FINAL_SETPOINT = 0;
	public static double GEAR_P = 0.001;
	public static double GEAR_I = 1;
	public static double GEAR_D = 1;
}