package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMiddleHook extends CommandGroup {

    public AutoMiddleHook() {    	
    	// drive forward
    	addSequential(new DeadReckon2(RobotConstants.AUTO_MHOOK_D1_TIME));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_MHOOK_D2_TIME));
    	addParallel(new ClawRetract2());
    	
    	//correct left and second attempt
    	addSequential(new DeadReckonTurn(0.2, true));
    	addSequential(new DeadReckon2(RobotConstants.AUTO_MHOOK_D2_TIME));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_MHOOK_D2_TIME));
    	addParallel(new ClawRetract2());
    	
    	//correct right and third attempt
    	addSequential(new DeadReckonTurn(0.4, false));
    	addSequential(new DeadReckon2(RobotConstants.AUTO_MHOOK_D2_TIME));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_MHOOK_D2_TIME));
    	addParallel(new ClawRetract2());
    }
}
