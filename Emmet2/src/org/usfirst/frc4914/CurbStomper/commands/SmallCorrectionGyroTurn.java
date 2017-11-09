package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;
import edu.wpi.first.wpilibj.command.Command;

public class SmallCorrectionGyroTurn extends Command {
	double speed = RobotConstants.AUTO_SPEED/10;
	//angle that is tested for to end the function
	double currentAngle = 0;
	//local variable for turning direction
	boolean TurnCW;
	boolean overTurned;
	double angleToTurnTo = 0;
	boolean actionDone = false;
	
	//CW boolean true for CW, false for CCW
	public SmallCorrectionGyroTurn(double angle) {
		// set timeout to 7 seconds as an emergency stop
    	setTimeout(7);
    	
    	// set local variables for this object
    	TurnCW = angle < 0;
    	// stores absoulute value of the angle to turn to
    	angleToTurnTo = Math.abs(angle);
    	
    	//change polarity of speed if moving counter clockwise
    	if (TurnCW) speed = -speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//reset sensors
    	Robot.drivetrain.resetEncoder();
    	
    	//set current angle
    	currentAngle = Robot.drivetrain.getRawGyroBearing();
    	
    	if (Math.abs(currentAngle) > angleToTurnTo) overTurned = true;
    	else overTurned = false;
    	
    	//change polarity of speed
    	if (TurnCW && !overTurned) speed = -speed;
    	if (!TurnCW && overTurned) speed = -speed;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.drivetrain.getRawGyroBearing();
    	
    	//run drivetrain
    	Robot.drivetrain.tankDrive(speed, -speed, false, false);
    	
    	//update realAngleTurned
    	if (TurnCW && overTurned) {
    		actionDone = Math.abs(Robot.drivetrain.getRawGyroBearing()) <= angleToTurnTo;
    	}
    	else if (TurnCW && !overTurned){
    		actionDone = Math.abs(Robot.drivetrain.getRawGyroBearing()) >= angleToTurnTo;
    	}
    	else if (!TurnCW && overTurned) {
    		actionDone = Math.abs(Robot.drivetrain.getRawGyroBearing()) <= angleToTurnTo;
    	}
    	else if (!TurnCW && !overTurned) {
    		actionDone = Math.abs(Robot.drivetrain.getRawGyroBearing()) >= angleToTurnTo;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//command ends when angle turned reaches the desired angle with a timeout as an emergency to stop loops
        return actionDone || isTimedOut();
        
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
