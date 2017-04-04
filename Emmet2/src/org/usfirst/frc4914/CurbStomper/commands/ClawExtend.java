package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.PIDCommand;


public class ClawExtend extends PIDCommand {

    public ClawExtend() {

    	super("DriveForward", RobotConstants.GEAR_P, 
    			RobotConstants.GEAR_I, RobotConstants.GEAR_D, 0.001);
    	getPIDController().setSetpoint(RobotConstants.GEAR_FINAL_SETPOINT);
        
    	getPIDController().setContinuous(false);
        getPIDController().setAbsoluteTolerance(RobotConstants.GEAR_TOLERANCE);
        getPIDController().setOutputRange(-0.1, 0.1);
        
        requires(Robot.gear);
    }

    protected double returnPIDInput() {
    	return Robot.gear.getEncoderPosition();
    }

    protected void usePIDOutput(double output) {
    	Robot.gear.setClaw(output/10.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// sets timeout
    	setTimeout(3);
    	// resets all sensors
    	// Robot.gear.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { System.out.println("ClawExtend"); }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return getPIDController().onTarget();
    	return getPIDController().onTarget() || isTimedOut();
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
