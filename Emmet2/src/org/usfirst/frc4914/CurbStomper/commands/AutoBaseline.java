package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

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
    	
    	// addSequential(new DeadReckon(3));
    	// addSequential(new ClawExtend2());
    	// addSequential(new DeadReckonBackward(2));
    	// addSequential(new ClawRetract2());
    	// turn and head down field
    	// addSequential(new TurnCCW(90));
    }
}
