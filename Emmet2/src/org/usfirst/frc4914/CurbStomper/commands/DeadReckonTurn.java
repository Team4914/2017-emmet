package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

public class DeadReckonTurn extends Command{
	
	double speed = RobotConstants.AUTO_SPEED;
	//angle that is tested for to end the function
	double realAngleTurned;
	//local variable for turning direction
	boolean TurnCW;
	double angleToTurn;
	
	//CW boolean true for CW, false for CCW
	public DeadReckonTurn(double angle, boolean CW) {
		//set timeout to 1.5 second more than it would take if it was time-based dead reckoning
    	setTimeout((angle*RobotConstants.ANGLE_TO_TIME_DEAD_RECKON_TURN) + 1.5);
    	
    	//change polarity of speed
    	if (CW) speed = -speed;
    	
    	//set local variables for this object
    	TurnCW = CW;
    	angleToTurn = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//reset sensors
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//run drivetrain
    	Robot.drivetrain.tankDrive(speed, -speed, false, false);
    	//update realAngleTurned
    	if (TurnCW) {
    		realAngleTurned = Robot.drivetrain.getRawGyroBearing();
    	}
    	else {
    		realAngleTurned = 360 - Robot.drivetrain.getRawGyroBearing();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//command ends when angle turned reaches the desired angle with a timeout as an emergency to stop loops
        return realAngleTurned >= angleToTurn || isTimedOut();
        
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
