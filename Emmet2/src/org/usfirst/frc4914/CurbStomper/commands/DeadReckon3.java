package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot in a straight line using encoders 
 * to measure distance and gyro readings to stay straight
 */

public class DeadReckon3 extends Command {
	
	double distance = 0; // distance to drive, in inches
	double speed = 0; // drive speed, in inches per second
	double currentEncoderValue = 0; // current encoder output
	double encoderValueToReach = 0; // encoder value to reach
	
	public DeadReckon3(double distance, double speed) {
		this.distance = distance;
		this.speed = speed;
		
	 	// calculates timeout in seconds given distance and speed and adds two seconds as emergency stop
	 	double timeout = Math.abs(distance/speed) + 2;
	 	setTimeout(timeout);
	 	
	 	// converts distance in inches to an encoder value
	 	encoderValueToReach = RobotConstants.INCHES_TO_ENCODER * distance;
    }

    protected void initialize() {
    	// resets sensors
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.resetEncoder();
    	
    	// inverts speed if distance negative
    	if (distance < 0) this.speed *= -1;
    }

    protected void execute() {
    	//samples gyro
    	double bearing = Robot.drivetrain.getRawGyroBearing();
    	
    	//samples encoder
    	currentEncoderValue = Robot.drivetrain.getEncoderPosition();
    	
    	// Drive robot in a straight line by adding/subtracting gyrometer offsets
		Robot.drivetrain.tankDrive((RobotConstants.AUTO_SPEED + bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P), 
				(RobotConstants.AUTO_SPEED - bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P), false, false);
		
		// Logs raw gyrometer readings as console output
    	// System.out.println(Robot.drivetrain.getRawGyroBearing());
    	
    }

    protected boolean isFinished() {
        //ends command if timed out
    	return isTimedOut() || Math.abs(currentEncoderValue) >= Math.abs(encoderValueToReach);
    }

    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    	end();
    }
}
