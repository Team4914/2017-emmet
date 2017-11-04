package org.usfirst.frc4914.CurbStomper.commands;

//import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftHook extends CommandGroup {
	
	//Position with bumper 8" from loading station or 12" from boiler
    public AutoLeftHook() {
    	//smooth drive up to hook
    	addSequential(new SmoothLeftHookApproach(RobotConstants.LEFT_HOOK_APPROACH_TIME, RobotConstants.LEFT_HOOK_TURNING_RADIUS, 
    			RobotConstants.LEFT_HOOK_TURNING_ANGLE));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon2(-2));
    	addSequential(new ClawRetract2());
    }
}
