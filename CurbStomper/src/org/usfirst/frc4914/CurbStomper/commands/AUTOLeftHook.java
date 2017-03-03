// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4914.CurbStomper.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc4914.CurbStomper.Robot;

/**
 * 
 */
public class AUTOLeftHook extends CommandGroup {

    public AUTOLeftHook() {
    	requires(Robot.driveTrain);
    	addSequential(new DriveBackward(), 0.8);
    	addSequential(new TurnCW(40));
    	addSequential(new TrackTarget());
    	addSequential(new AUTODelay(), 3);
    	addSequential(new DriveForward(), 0.5);
    	addSequential(new TrackTarget());
    	addSequential(new DriveForward(), 0.7);
    }
}