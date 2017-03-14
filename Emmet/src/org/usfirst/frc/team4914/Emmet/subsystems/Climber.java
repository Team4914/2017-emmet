package org.usfirst.frc.team4914.Emmet.subsystems;

import org.usfirst.frc.team4914.Emmet.RobotMap;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    private final TalonSRX climber = RobotMap.climber;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed) {
    	climber.set(speed);
    }
    
    public void stop() {
    	setSpeed(0);
    }
}