package org.usfirst.frc.team4914.Emmet.commands;

import org.usfirst.frc.team4914.Emmet.Robot;
import org.usfirst.frc.team4914.Emmet.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMiddleHook extends CommandGroup {

    public AutoMiddleHook() {
    	// strategic delay
    	addSequential(new AutoDelay(Robot.getAutoDelay()));
    	
    	// drive forward
    	addSequential(new DriveForward(RobotConstants.AUTO_DRIVE_MHOOK_D1));
    	// extend claws and deposit gear
    	addSequential(new ClawExtend());
    	// back away from airship
    	addSequential(new DriveForward(-RobotConstants.AUTO_DRIVE_MHOOK_D1/2));
    	addParallel(new ClawRetract());
    	// turn and head down field
    	addSequential(new TurnCCW(90));
    }
}
