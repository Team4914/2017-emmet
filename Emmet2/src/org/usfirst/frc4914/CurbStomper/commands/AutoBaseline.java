package org.usfirst.frc4914.CurbStomper.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBaseline extends CommandGroup {

    public AutoBaseline() {
    	// strategic delay
    	// addSequential(new AutoDelay(Robot.getAutoDelay()));
    	
    	// drive forward
    	addSequential(new DeadReckon2(8));
    }
}
