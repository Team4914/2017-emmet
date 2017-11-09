package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot in a straight line given a desired distance.
 * Adjustments to left and right sides are made based on deviations from the initial gyrometer reading.
 */

public class DeadReckon2 extends Command {
	
	double distance = 0; // distance to drive, in inches
	double speed = 0; // drive speed, in inches per second
	
	public DeadReckon2(double distance) {
		this.distance = distance;
		this.speed = RobotConstants.AUTO_SPEED;
	 	// calculates timeout in seconds given distance and speed
	 	double timeout = Math.abs(distance/speed);
	 	setTimeout(timeout);
    }

    protected void initialize() {
    	// resets sensors
    	Robot.drivetrain.resetGyro();
    	
    	// inverts speed if distance negative
    	if (distance < 0) RobotConstants.AUTO_SPEED *= -1;
    }

    protected void execute() {
    	//samples gyro
    	double bearing = Robot.drivetrain.getRawGyroBearing();
    	
    	// Drive robot in a straight line by adding/subtracting gyrometer offsets
		Robot.drivetrain.tankDrive((RobotConstants.AUTO_SPEED + bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P), 
				(RobotConstants.AUTO_SPEED - bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P), false, false);
		
		// Logs raw gyroscope readings as console output
    	// System.out.println(Robot.drivetrain.getRawGyroBearing());
    	
    }

    protected boolean isFinished() {
        //ends command if timed out
    	return isTimedOut();
    }

    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    	end();
    }
}
