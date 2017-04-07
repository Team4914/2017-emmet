package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeadReckon extends Command {
	
	double distance = 0;
	double bearing = 0;

    public DeadReckon(double encoderSetpoint) {
    	setTimeout(2);
    	distance = encoderSetpoint;
    }

    protected void initialize() {
    	// resets sensors
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.resetEncoder();
    	
    	// resets baseline auto boolean
    	RobotConstants.isBaselineAuto = false;
    	
    	// inverts speed if distance negative
    	if (distance < 0) RobotConstants.AUTO_SPEED *= -1;
    }

    protected void execute() {
    	// variable updates
    	RobotConstants.isBaselineAuto = (isTimedOut() && Robot.drivetrain.getEncoderPosition() < 1);
    	bearing = Robot.drivetrain.getRawGyroBearing();
    	
    	// drive control
    	if (RobotConstants.isBaselineAuto) {
    		Robot.drivetrain.tankDrive(-RobotConstants.AUTO_SPEED + bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P, 
    				-RobotConstants.AUTO_SPEED - bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P, false, false);
    	} else {
    		Robot.drivetrain.tankDrive(RobotConstants.AUTO_SPEED + bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P, 
    				RobotConstants.AUTO_SPEED - bearing*RobotConstants.AUTO_DRIVESTRAIGHT_P, false, false);
    	}
    }

    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getEncoderPosition()) >= Math.abs(distance);
    }

    protected void end() {
    	Robot.drivetrain.stop();
    	if (distance < 0) RobotConstants.AUTO_SPEED *= -1;
    	if (RobotConstants.isBaselineAuto) {
    		while (true) { boolean a = true; }
    	}
    }

    protected void interrupted() {
    	end();
    }
}
