package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawExtend2 extends Command {

    public ClawExtend2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(0.2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gear.setClaw(0.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return (Robot.gear.getEncoderPosition() > 600);
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gear.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
