package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class SmoothMiddleHookApproach extends Command {
	double initialTime;
	double currentTime;
	double timeElapsed;
	double totalTime;
	double distanceToMove;
	double peakSpeed;
	double instantaneousSpeed;
	
	public SmoothMiddleHookApproach(double time, double distance) {
		totalTime = time;
		distanceToMove = distance;
		
		//peak speed = 2 * ((distance from alliance station wall to airship - (half the hook length + half the robot length)) / total time interval)
		peakSpeed = 2*((distanceToMove - (8.5 + (38/2)))/totalTime);
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.stop();
    	setTimeout(totalTime);
    	//set to current time in seconds
    	initialTime = System.nanoTime()/Math.pow(10,9);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//calculate current time in seconds
    	currentTime = System.nanoTime()/Math.pow(10,9);
    	timeElapsed = currentTime - initialTime;
    	
    	//If the time is still within half the total time interval, keep accelerating to peak speed
    	if (timeElapsed <= (totalTime/2)) {
    		instantaneousSpeed = (peakSpeed/(totalTime/2))*timeElapsed;
    	}
    	//If the time is past the halfway point in the total time interval, decelerate to a stop
    	else {
    		instantaneousSpeed = ((-peakSpeed/(totalTime/2))*timeElapsed) + (2*peakSpeed);
    	}
    	
    	Robot.drivetrain.tankDrive(instantaneousSpeed, instantaneousSpeed, true, false);
    	System.out.println("Time Elapsed: " + timeElapsed);
    	System.out.println("Instantaneous Speed: " + instantaneousSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//End command once total time interval has elapsed or drivetrain speed is less than zero
        return isTimedOut() || instantaneousSpeed <= 0;
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
