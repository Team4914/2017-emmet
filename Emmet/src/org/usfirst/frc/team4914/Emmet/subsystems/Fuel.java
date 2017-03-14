package org.usfirst.frc.team4914.Emmet.subsystems;

import org.usfirst.frc.team4914.Emmet.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Fuel extends Subsystem {

	private final TalonSRX intake = RobotMap.intake;
	private final Talon shooter = RobotMap.shooter;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntake(double speed) {
    	intake.set(speed);
    }
    
    public void setShooter(double speed) {
    	shooter.set(speed);
    }
    
    public void stopIntake() {
    	setIntake(0);
    }
    
    public void stopShooter() {
    	setShooter(0);
    }
    
    public void stopAll() {
    	stopIntake();
    	stopShooter();
    }
}

