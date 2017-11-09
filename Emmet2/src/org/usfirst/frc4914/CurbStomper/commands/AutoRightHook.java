package org.usfirst.frc4914.CurbStomper.commands;

//import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightHook extends CommandGroup {
	
	// AKA BOILER SIDE HOOK
    public AutoRightHook() {
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D1, RobotConstants.AUTO_SPEED));
    	addSequential(new DeadReckonTurn(-60));
    	addSequential(new SmallCorrectionGyroTurn(-60));
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D2, RobotConstants.AUTO_SPEED));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D3, RobotConstants.AUTO_SPEED));
    	addSequential(new ClawRetract2());
    }
}
