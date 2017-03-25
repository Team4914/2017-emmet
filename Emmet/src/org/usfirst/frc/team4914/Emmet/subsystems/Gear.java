package org.usfirst.frc.team4914.Emmet.subsystems;

import org.usfirst.frc.team4914.Emmet.Robot;
import org.usfirst.frc.team4914.Emmet.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem {

	private final CANTalon claw = RobotMap.claw;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setClaw(double speed) {
    	speed = Robot.limit(speed);
    	claw.set(speed);
    }
    
    public void stop() {
    	setClaw(0);
    }
    
    public double getEncoderPosition() {
    	return claw.getEncPosition();
    }
    
    public void resetEncoder() {
    	claw.setEncPosition(0);
    }
}