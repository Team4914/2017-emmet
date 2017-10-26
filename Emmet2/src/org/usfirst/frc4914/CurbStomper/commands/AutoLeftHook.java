package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftHook extends CommandGroup {

	// AKA LOADING STATION HOOK
	double voltage = DriverStation.getInstance().getBatteryVoltage();
	//turn speed = 10.825 [initial value for voltage] + [voltage * slope of turnTime and voltage]
	double turnTime = RobotConstants.AUTO_INITIAL_LHOOK_ROTATION_TIME + (voltage*(-0.75));
	
    public AutoLeftHook() {
    	//First drive forward
    	addSequential(new DeadReckon2(RobotConstants.AUTO_LHOOK_D1_TIME));
    	//Turn CCW
    	addSequential(new DeadReckonTurn(turnTime, false));
    	//First approach to hook
    	addSequential(new DeadReckon2(RobotConstants.AUTO_LHOOK_D2_TIME));
    	//Extend Claw
    	addSequential(new ClawExtend2());
    	//Drive Back
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_LHOOK_D3_TIME));
    	//Retract Claw
    	addSequential(new ClawRetract2());
    	//Adjust trajectory by turning CW a bit
    	addSequential(new DeadReckonTurn(0.2, true));
    	//Second approach to hook
    	addSequential(new DeadReckon2(RobotConstants.AUTO_LHOOK_D3_TIME));
    	//Extend Claw
    	addSequential(new ClawExtend2());
    	//Drive Back
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_LHOOK_D3_TIME));
    	//Retract Claw
    	addParallel(new ClawRetract2());
    	//Adjust trajectory
    	addSequential(new DeadReckonTurn(0.2, true));
    	//Third approach to hook
    	addSequential(new DeadReckon2(RobotConstants.AUTO_LHOOK_D3_TIME));
    	//Extend claw
    	addSequential(new ClawExtend2());
    	//Drive back
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_LHOOK_D3_TIME));
    	//Retract claw
    	addSequential(new ClawRetract2());
    }
}
