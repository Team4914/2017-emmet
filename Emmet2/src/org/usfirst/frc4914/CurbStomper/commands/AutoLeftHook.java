package org.usfirst.frc4914.CurbStomper.commands;

//import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftHook extends CommandGroup {
	
	// AKA LOADING STATION HOOK
    public AutoLeftHook() {
    	//drive forward past baseline
    	addSequential(new DeadReckon2(RobotConstants.AUTO_LHOOK_D1_TIME));
    	//turn towards hook
    	addSequential(new DeadReckonTurn(RobotConstants.AUTO_LHOOK_ROTATION_ANGLE, false));
    	//first attempt
    	addSequential(new DeadReckon2(RobotConstants.AUTO_LHOOK_D2_TIME));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_LHOOK_D3_TIME));
    	addParallel(new ClawRetract2());
    }
}
