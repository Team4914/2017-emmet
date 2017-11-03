package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SmoothLeftHookApproach extends Command {
	
	double pi = 3.14159265359;
	double initialTime;
	double currentTime;
	double timeElapsed;
	double totalTime;
	double leftPeakSpeed;
	double rightPeakSpeed;
	double leftInstantaneousSpeed;
	double rightInstantaneousSpeed;
	double radiusOfTurn;
	
	public SmoothLeftHookApproach(double time, double radius) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		
		//set local variables for time interval and turning radius
    	totalTime = time;
    	radiusOfTurn = radius;
    	
    	//calculate left and right peak speeds
    	//peak speed = (1/6 of a circle with radius "radiusOfTurn" +/- width of the robot)/time interval
    	leftPeakSpeed = (2*pi*(radiusOfTurn+(27.5/2)))/(3*totalTime);
    	rightPeakSpeed = (2*pi*(radiusOfTurn-(27.5/2)))/(3*totalTime);
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
    		leftInstantaneousSpeed = (leftPeakSpeed/(totalTime/2))*timeElapsed;
    		rightInstantaneousSpeed = (rightPeakSpeed/(totalTime/2))*timeElapsed;
    	}
    	//If the time is past the halfway point in the total time interval, decelerate to a stop
    	else {
    		leftInstantaneousSpeed = ((-leftPeakSpeed/(totalTime/2))*timeElapsed) + (2*leftPeakSpeed);
    		rightInstantaneousSpeed = ((-rightPeakSpeed/(totalTime/2))*timeElapsed) + (2*rightPeakSpeed);
    	}
    	
    	Robot.drivetrain.tankDrive(rightInstantaneousSpeed, leftInstantaneousSpeed, true, false);
    	System.out.println("Time Elapsed: " + timeElapsed);
    	System.out.println("Left Instantaneous Speed: " + leftInstantaneousSpeed + " Right Instantaneous Speed: " + rightInstantaneousSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//End command once total time interval has elapsed or either side of drivetrain have a speed of zero or below
        return isTimedOut() || leftInstantaneousSpeed <= 0 || rightInstantaneousSpeed <= 0;
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
