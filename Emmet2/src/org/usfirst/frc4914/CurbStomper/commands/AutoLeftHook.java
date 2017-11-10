package org.usfirst.frc4914.CurbStomper.commands;

//import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftHook extends CommandGroup {
	
	//Position with bumper 13.5" into the loading station (past the inside edge of the blue line) or flush with boiler
    public AutoLeftHook() {
    	// move forward
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D1, RobotConstants.AUTO_SPEED));
    	// turn roughly 60° CW
    	addSequential(new DeadReckonTurn(60));
    	// fine adjust to 57°
    	addSequential(new SmallCorrectionGyroTurn(57));
    	// go forward towards hook at a slower speed
    	addSequential(new DeadReckon3(RobotConstants.AUTO_LHOOK_D2, RobotConstants.AUTO_SPEED*0.8));
    	// release gear
    	addSequential(new ClawExtend2());
    	// drive backwards
    	addSequential(new DeadReckon2(-0.5));
    	// retract claw
    	addSequential(new ClawRetract2());
    }
}
