package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.PIDCommand;


public class DriveForward extends PIDCommand {

    public DriveForward(double distance) {

    	super("DriveForward", RobotConstants.AUTO_DRIVE_P, 
    			RobotConstants.AUTO_DRIVE_I, RobotConstants.AUTO_DRIVE_D);
    	
    	distance *= RobotConstants.INCHES_TO_ENCODER;
    	
    	getPIDController().setSetpoint(distance);
        
    	getPIDController().setContinuous(false);
        getPIDController().setAbsoluteTolerance(RobotConstants.AUTO_DRIVE_TOLERANCE);
        getPIDController().setOutputRange(-0.5, 0.5);
    }

    protected double returnPIDInput() {
    	return Robot.drivetrain.getEncoderPosition();
    }

    protected void usePIDOutput(double output) {
    	Robot.drivetrain.triggerDrive(Robot.drivetrain.getGyroBearing()*0.03, output, 0, false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// sets timeout
    	setTimeout(10);
    	// resets all sensors
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.resetGyro();
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
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}
