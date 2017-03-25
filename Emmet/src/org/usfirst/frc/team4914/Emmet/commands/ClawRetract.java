package org.usfirst.frc.team4914.Emmet.commands;

import org.usfirst.frc.team4914.Emmet.Robot;
import org.usfirst.frc.team4914.Emmet.RobotConstants;

import edu.wpi.first.wpilibj.command.PIDCommand;


public class ClawRetract extends PIDCommand {

    public ClawRetract() {

    	super("DriveForward", RobotConstants.GEAR_P, 
    			RobotConstants.GEAR_I, RobotConstants.GEAR_D);
    	getPIDController().setSetpoint(RobotConstants.GEAR_INIT_SETPOINT);
        
    	getPIDController().setContinuous(false);
        getPIDController().setAbsoluteTolerance(RobotConstants.GEAR_TOLERANCE);
        getPIDController().setOutputRange(-0.1, 0.1);
        
        requires(Robot.gear);
    }

    protected double returnPIDInput() {
    	return Robot.gear.getEncoderPosition();
    }

    protected void usePIDOutput(double output) {
    	Robot.gear.setClaw(output);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// sets timeout
    	setTimeout(3);
    	// resets all sensors
    	Robot.gear.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return getPIDController().onTarget();
    	return getPIDController().onTarget() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	getPIDController().disable();
    	getPIDController().free();
    	Robot.gear.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}
