package org.usfirst.frc4914.CurbStomper.subsystems;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotMap;
import org.usfirst.frc4914.CurbStomper.commands.ClawRetract2;

import com.ctre.MotorControl.*;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem {

	private final CANTalon claw = RobotMap.claw;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClawRetract2());
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