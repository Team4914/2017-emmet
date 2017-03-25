package org.usfirst.frc.team4914.Emmet.subsystems;

import org.usfirst.frc.team4914.Emmet.Robot;
import org.usfirst.frc.team4914.Emmet.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Fuel extends Subsystem {

	private final Talon shooter = RobotMap.shooter;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setShooter(double speed) {
    	speed = Robot.limit(speed);
    	shooter.set(speed);
    }
    
    public void stopShooter() {
    	setShooter(0);
    }
    
    public void stopAll() {
    	stopShooter();
    }
}

