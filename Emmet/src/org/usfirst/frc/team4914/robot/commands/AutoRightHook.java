package org.usfirst.frc.team4914.robot.commands;

import org.usfirst.frc.team4914.Emmet.Robot;
import org.usfirst.frc.team4914.Emmet.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightHook extends CommandGroup {

    public AutoRightHook() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new AutoDelay(Robot.getAutoDelay()));
    	addSequential(new DriveBackward(RobotConstants.AUTO_SPEED));
    	addSequential(new TurnCCW(RobotConstants.AUTO_TURN);
    	addSequential(new TrackTarget());
    	addSequential(new clawExtend());
    	addSequential(new DriveForward(0));
    	addSequential(new TurnCW(RobotConstants.AUTO_TURN;
    	addParallel(new clawRetract());
    	AddSequential(new DriveBackward(3));
    }
}
