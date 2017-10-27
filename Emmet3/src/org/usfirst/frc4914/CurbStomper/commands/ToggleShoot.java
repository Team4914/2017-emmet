package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleShoot extends Command {
	public ToggleShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotConstants.isShoot = !RobotConstants.isShoot;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
