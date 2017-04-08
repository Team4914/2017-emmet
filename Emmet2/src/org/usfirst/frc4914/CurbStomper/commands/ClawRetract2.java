package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawRetract2 extends Command {

    public ClawRetract2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gear.getEncoderPosition() > 1 && !Robot.oi.getPrimaryJoystick().getRawButton(6) && !Robot.oi.getCoJoystick().getRawButton(5) && !Robot.oi.getCoJoystick().getRawButton(6)) {
    		Robot.gear.setClaw(-0.2);
    	} else {
    		Robot.gear.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
