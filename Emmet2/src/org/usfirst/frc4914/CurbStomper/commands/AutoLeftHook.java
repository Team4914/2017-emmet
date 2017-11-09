package org.usfirst.frc4914.CurbStomper.commands;

//import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftHook extends CommandGroup {
	
	//Position with bumper 4" into the loading station (past the blue line) or flush with boiler
    public AutoLeftHook() {
//    	//smooth drive up to hook
//    	addSequential(new SmoothLeftHookApproach(RobotConstants.LEFT_HOOK_APPROACH_TIME, RobotConstants.LEFT_HOOK_TURNING_RADIUS, 
//    			RobotConstants.LEFT_HOOK_TURNING_ANGLE));
//    	addSequential(new ClawExtend2());
//    	addSequential(new DeadReckon2(-2));
//    	addSequential(new ClawRetract2());
    	
//    	addSequential(new DeadReckon2(0.75));
//    	addSequential(new DeadReckonTurn(58, true));
//    	addSequential(new DeadReckon2(0.8));
//    	addSequential(new DeadReckon2(0.5));
//    	addSequential(new ClawExtend2());
//    	addSequential(new DeadReckon2(-0.5));
//    	addSequential(new ClawRetract2());
    	
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D1, RobotConstants.AUTO_SPEED));
    	addSequential(new DeadReckonTurn(60, true));
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D2, RobotConstants.AUTO_SPEED));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D3, RobotConstants.AUTO_SPEED));
    	addSequential(new ClawRetract2());
    }
}
