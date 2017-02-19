package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class TurnCCW extends Command {
		
	private double initialBearing;
	private double finalBearing;
	private double relativeAngle;
	private static final double EPSILON = Robot.driveTrain.ANGLE_EPSILON;

    public TurnCCW(double relativeAngle) {
    	this.relativeAngle = relativeAngle;
    	setTimeout(3);
    }

    protected void initialize() {
    	Robot.driveTrain.resetGyro();
    	initialBearing = Robot.driveTrain.getGyroBearing();
    	finalBearing = initialBearing - relativeAngle;
    	
    	initialBearing += 360;
     	initialBearing %= 360;
     	finalBearing += 360;
     	finalBearing %= 360;
    }

    protected void execute() {
    	Robot.driveTrain.tankDrive(DriveTrain.AUTO_SPEED, -DriveTrain.AUTO_SPEED);
    }

    protected boolean isFinished() {
        return isTimedOut() || 
        		(Robot.driveTrain.getGyroBearing() > finalBearing - EPSILON && 
        		Robot.driveTrain.getGyroBearing() < finalBearing + EPSILON);
    }

    protected void end() {
    	Robot.driveTrain.stop();
    }

    protected void interrupted() {
    	end();
    }
}