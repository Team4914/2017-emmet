package org.usfirst.frc4914.CurbStomper.commands;

//import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightHook extends CommandGroup {
	
	// AKA BOILER SIDE HOOK
    public AutoRightHook() {
    	// go forward
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D1+2, RobotConstants.AUTO_SPEED));
    	// turn roughly 60° CCW
    	addSequential(new DeadReckonTurn(-60));
    	// slowly adjust to -58°
    	addSequential(new SmallCorrectionGyroTurn(-58));
    	// go forward towards hook
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D2, RobotConstants.AUTO_SPEED*0.8));
    	// release gear
    	addSequential(new ClawExtend2());
    	// drive back
    	addSequential(new DeadReckon2(-0.5));
    	// retract claw
    	addSequential(new ClawRetract2());
    }
}
