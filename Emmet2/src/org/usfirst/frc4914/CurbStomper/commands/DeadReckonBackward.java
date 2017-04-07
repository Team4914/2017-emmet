package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeadReckonBackward extends Command {

    public DeadReckonBackward(double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Robot.drivetrain.triggerDrive(Robot.drivetrain.getRawGyroBearing()*0.03, -RobotConstants.AUTO_SPEED/60.0, 0, false);
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive(-10/60.0 + Robot.drivetrain.getRawGyroBearing()*0.05, -10/60.0, false, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
