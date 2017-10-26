package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMiddleHook extends CommandGroup {

    public AutoMiddleHook() {
    	//first approach to hook
    	addSequential(new DeadReckon2(RobotConstants.AUTO_MHOOK_D1_TIME));
    	//extend claw
    	addSequential(new ClawExtend2());
    	//Drive back
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_MHOOK_D2_TIME));
    	//Retract claw
    	addSequential(new ClawRetract2());
    	//Adjust trajectory
    	addSequential(new DeadReckonTurn(0.2, true));
    	//Second approach to hook
    	addSequential(new DeadReckon2(RobotConstants.AUTO_MHOOK_D2_TIME));
    	//Extend claw
    	addSequential(new ClawExtend2());
    	//Drive back
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_MHOOK_D2_TIME));
    	//Retract claw
    	addSequential(new ClawRetract2());
    	//Adjust trajectory
    	addSequential(new DeadReckonTurn(0.4, false));
    	//Third approach to hook
    	addSequential(new DeadReckon2(RobotConstants.AUTO_MHOOK_D2_TIME));
    	//Extend claw
    	addSequential(new ClawExtend2());
    	//Drive back
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_MHOOK_D2_TIME));
    	//Retract claw
    	addSequential(new ClawRetract2());
    	
    }
}
