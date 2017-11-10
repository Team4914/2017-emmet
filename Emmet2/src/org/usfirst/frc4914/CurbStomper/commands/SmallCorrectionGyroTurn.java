package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;
import edu.wpi.first.wpilibj.command.Command;

public class SmallCorrectionGyroTurn extends Command {
	double speed = RobotConstants.AUTO_SPEED*0.75; // speed to turn drivetrain
	double currentAngle = 0; //current angle
	boolean TurnCW; // direction to turn
	boolean overTurned; // whether the robot has gone past the angle specified or is under the angle specified
	double angleToTurnTo = 0; // the angle to turn to
	boolean actionDone = false; // is true when the correction is complete
	
	//CW boolean true for CW, false for CCW
	public SmallCorrectionGyroTurn(double angle) {
		// set timeout to 7 seconds as an emergency stop
    	setTimeout(7);
    	
    	// turn clockwise if the angle is positive
    	TurnCW = angle > 0;
    	// angle to turn to is in absolute value, which is why there is a boolean for turn direction
    	angleToTurnTo = Math.abs(angle);
    	
    	// removed due to this statement repeating in initialize()
//    	change polarity of speed if moving counter clockwise
//    	if (!TurnCW) speed = -speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// reset sensors - don't reset gyro as the program needs the existing gyro state to correct to the specified angle
    	Robot.drivetrain.resetEncoder();
    	
    	// set current angle
    	currentAngle = Robot.drivetrain.getRawGyroBearing();
    	
    	// print out current angle
    	System.out.println(currentAngle);
    	
    	// if the current angle is more than the angle to turn to, it has overturned, and needs to move in the
    	// opposite direction of the original movement in DeadReckonTurn
    	if (Math.abs(currentAngle) > angleToTurnTo) overTurned = true;
    	else overTurned = false;
    	
    	//change polarity of speed if correction needs to move CW
    	if (TurnCW && !overTurned) speed = -speed;
    	if (!TurnCW && overTurned) speed = -speed;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// update current angle
    	currentAngle = Robot.drivetrain.getRawGyroBearing();
    	
    	// run drivetrain
    	Robot.drivetrain.tankDrive(speed, -speed, false, false);
    	
    	// check if the correction is complete
    	if (TurnCW && overTurned) {
    		actionDone = Math.abs(Robot.drivetrain.getRawGyroBearing()) <= angleToTurnTo;
    	}
    	else if (TurnCW && !overTurned){
    		actionDone = Math.abs(Robot.drivetrain.getRawGyroBearing()) >= angleToTurnTo;
    	}
    	else if (!TurnCW && overTurned) {
    		actionDone = Math.abs(Robot.drivetrain.getRawGyroBearing()) <= angleToTurnTo;
    		System.out.println("1");
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
    	System.out.println("done " + Robot.drivetrain.getRawGyroBearing() + actionDone);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
