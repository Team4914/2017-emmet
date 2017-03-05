package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveBackward extends Command {
    public DriveBackward() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// TODO reset gyro orientation
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double gyroAngle = Robot.driveTrain.getGyroBearing();
    	double left = 0, right = 0;
    	
    	if(gyroAngle < 0) {
    		right = DriveTrain.AUTO_SPEED - (gyroAngle/30)*0.3;
    	} else if (gyroAngle > 0){ 
    		left = DriveTrain.AUTO_SPEED - (gyroAngle/30)*0.3;
    	}
    	
    	Robot.driveTrain.tankDrive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
