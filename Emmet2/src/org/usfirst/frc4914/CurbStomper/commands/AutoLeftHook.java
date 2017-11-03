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
    	//smooth drive up to hook
    	addSequential(new SmoothLeftHookApproach(RobotConstants.LEFT_APPROACH_TIME, RobotConstants.LEFT_TURNING_RADIUS));
    }
}
