package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeadReckon2 extends Command {
	
	double distance = 0;
	double bearing = 0;

    public DeadReckon2(double encoderSetpoint) {
    	setTimeout(Math.abs(encoderSetpoint) + 1);
    	distance = encoderSetpoint;
    }

    protected void initialize() {
    	// resets sensors
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.resetEncoder();
    	
    	// inverts speed if distance negative
    	if (distance < 0) RobotConstants.AUTO_SPEED *= -1;
    }

    protected void execute() {
    	// variable updates
    	bearing = Robot.drivetrain.getRawGyroBearing();
    	
		Robot.drivetrain.tankDrive(RobotConstants.AUTO_SPEED + bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P, 
				RobotConstants.AUTO_SPEED - bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P, false, false);
    	System.out.println(Robot.drivetrain.getRawGyroBearing());
    }

    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getEncoderPosition()) >= Math.abs(distance) || isTimedOut();
    }

    protected void end() {
    	Robot.drivetrain.stop();
    	if (distance < 0) RobotConstants.AUTO_SPEED *= -1;
    }

    protected void interrupted() {
    	end();
    }
}
