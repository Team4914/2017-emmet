package org.usfirst.frc.team4914.Emmet.commands;

import org.usfirst.frc.team4914.Emmet.Robot;
import org.usfirst.frc.team4914.Emmet.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightHook extends CommandGroup {

	// AKA BOILER SIDE HOOK
    public AutoRightHook() {
    	// strategic delay
    	addSequential(new AutoDelay(Robot.getAutoDelay()));
    	
    	// first leg of path
    	addSequential(new DriveForward(RobotConstants.AUTO_DRIVE_RHOOK_D1));
    	// turn toward airship
    	addSequential(new TurnCCW(RobotConstants.AUTO_TURN_RHOOK_SETPOINT));
    	// second leg of path
    	addSequential(new DriveForward(RobotConstants.AUTO_DRIVE_RHOOK_D2));
    	// extend claws and deposit gear
    	addSequential(new ClawExtend());
    	// back away from airship
    	addSequential(new DriveForward(-RobotConstants.AUTO_DRIVE_RHOOK_D2));
    	addParallel(new ClawRetract());
    	// turn and head down field
    	addSequential(new TurnCW(RobotConstants.AUTO_TURN_RHOOK_SETPOINT));
    	addSequential(new DriveForward(60));
    }
}
