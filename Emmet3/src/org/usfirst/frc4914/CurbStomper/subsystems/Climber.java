package org.usfirst.frc4914.CurbStomper.subsystems;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    private final Talon climber = RobotMap.climber;
    private final Talon climber2 = RobotMap.climber2;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed) {
    	speed = Robot.limit(speed);
    	speed *= 0.9;
    	climber.set(speed);
    	climber2.set(speed);
    }
    
    public void stop() {
    	setSpeed(0);
    }
}