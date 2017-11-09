package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBaseline extends CommandGroup {

    public AutoBaseline() {   	
    	// drive forward
    	addSequential(new DeadReckon2(RobotConstants.AUTO_MHOOK_D1_TIME));
    }
}
