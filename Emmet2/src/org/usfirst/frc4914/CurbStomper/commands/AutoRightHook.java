package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

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
    	addSequential(new ClawExtend2());
    	// back away from airship
    	addSequential(new DriveForward(-RobotConstants.AUTO_DRIVE_RHOOK_D2));
    	addParallel(new ClawRetract2());
    	// turn and head down field
    	addSequential(new TurnCW(RobotConstants.AUTO_TURN_RHOOK_SETPOINT));
    	addSequential(new DriveForward(-60));
    }
}
