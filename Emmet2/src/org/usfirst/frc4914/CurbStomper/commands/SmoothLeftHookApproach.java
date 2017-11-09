package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;

public class SmoothLeftHookApproach extends Command {
	
	/* SmoothLeftHookApproach accelerates the robot through an arc of a given radius and angle in a given time. 
	 * The time only affects the acceleration and peak speeds, but not the distance moved.
	 * When tweaking the arc to fit the field, make sure battery is at least above 12.8V ON DRIVER STATION and
	 * only change the radius, time and angle values in RobotConstants.java and nothing else. */
	
	double pi = 3.14159265359;
	double initialTime;
	double currentTime;
	double timeElapsed;
	double totalTime;
	double angleToTurn;
	double leftPeakSpeed;
	double rightPeakSpeed;
	double leftInstantaneousSpeed;
	double rightInstantaneousSpeed;
	double radiusOfTurn;
	double properAngle;
	double currentAngle;
	double angleDeviation;
	double angleCorrectionAmplitude;
	
	
	public SmoothLeftHookApproach(double time, double radius, double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		
		//set local variables for time interval, turning radius, and how much of a circle to turn in degrees
		this.totalTime = time;
		this.radiusOfTurn = radius;
		this.angleToTurn = angle;
    	
    	//calculate left and right peak speeds
    	//peak speed = (length of an arc of the angle provided of a circle with radius "radiusOfTurn" +/- width of the robot)/time interval
		this.leftPeakSpeed = (angleToTurn * pi * (radiusOfTurn+(27.5/2)) * 2)/(180 * totalTime);
		this.rightPeakSpeed = (angleToTurn * pi * (radiusOfTurn-(27.5/2)) * 2)/(180 * totalTime);
    }
	
	// Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(totalTime);
    	
    	//set to current time in seconds
    	initialTime = System.nanoTime()/Math.pow(10,9);
    	
    	//reset sensors
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.resetEncoder();
    	
    	System.out.println("<<BEGIN VALUE LOG>>");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//calculate current time in seconds
    	currentTime = System.nanoTime()/Math.pow(10,9);
    	timeElapsed = currentTime - initialTime;
    	
    	//calculate optimal angle for current time: y = 0.017x6 - 0.2842x5 + 1.4966x4 - 2.5517x3 + 2.3368x2 - 0.865x + 0.0606
    	properAngle = (0.017*(Math.pow(timeElapsed, 6))) - (0.2842*(Math.pow(timeElapsed, 5))) + (1.4966*(Math.pow(timeElapsed, 4)))
    			- (2.5517*(Math.pow(timeElapsed, 3))) + (2.3368*(Math.pow(timeElapsed, 2))) - (0.865*timeElapsed) + 0.0606;
    	
    	//sample gyro
    	currentAngle = Robot.drivetrain.getGyroBearing();
    	
    	//calculate deviation from optimal angle
    	angleDeviation = currentAngle - properAngle;
    	
    	//If the time is still within half the total time interval, keep accelerating to peak speed
    	if (timeElapsed <= (totalTime/2)) {
    		leftInstantaneousSpeed = (((2*leftPeakSpeed)/totalTime)*timeElapsed);
    		rightInstantaneousSpeed = (((2*rightPeakSpeed)/totalTime)*timeElapsed);
    	}
    	//If the time is past the halfway point in the total time interval, decelerate to a stop
    	else {
    		leftInstantaneousSpeed = (((-2*leftPeakSpeed)/totalTime)*timeElapsed) + (2*leftPeakSpeed);
    		rightInstantaneousSpeed = (((-2*rightPeakSpeed)/totalTime)*timeElapsed) + (2*rightPeakSpeed);
    	}
    	
    	//calculate amount needed for angle correction
    	angleCorrectionAmplitude = (3/(10.0/60)) * ((leftInstantaneousSpeed + rightInstantaneousSpeed)/2);
    	
    	Robot.drivetrain.tankDrive(rightInstantaneousSpeed + angleDeviation*angleCorrectionAmplitude, 
    			leftInstantaneousSpeed - angleDeviation*angleCorrectionAmplitude, true, false);
//    	System.out.println("Time Elapsed: " + timeElapsed);
//    	System.out.println("Left Instantaneous Speed: " + leftInstantaneousSpeed + " Right Instantaneous Speed: " + rightInstantaneousSpeed);
//    	System.out.println("Raw Gyro Bearing: " + Robot.drivetrain.getRawGyroBearing());
    	
    	//log values
    	System.out.println(timeElapsed + " " + leftInstantaneousSpeed + " " + rightInstantaneousSpeed + " " + Robot.drivetrain.getRawGyroBearing());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//End command once total time interval has elapsed or either side of drivetrain have a speed of zero or below
        return isTimedOut() || leftInstantaneousSpeed <= 0 || rightInstantaneousSpeed <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("<<END VALUE LOG>>");
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
