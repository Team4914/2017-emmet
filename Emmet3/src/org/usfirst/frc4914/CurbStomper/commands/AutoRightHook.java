package org.usfirst.frc4914.CurbStomper.commands;

//import org.usfirst.frc4914.CurbStomper.Robot;
import org.usfirst.frc4914.CurbStomper.RobotConstants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightHook extends CommandGroup {

	double voltage = DriverStation.getInstance().getBatteryVoltage();
	double turnSpeed = RobotConstants.AUTO_RHOOK_INITIAL_ROTATION_TIME + (voltage * -0.75);
	
	// AKA BOILER SIDE HOOK
    public AutoRightHook() {
    	//cross baseline
    	addSequential(new DeadReckon2(RobotConstants.AUTO_RHOOK_D1_TIME));
    	//turn towards hook
    	addSequential(new DeadReckonTurn (turnSpeed, true));
    	//first attempt
    	addSequential(new DeadReckon2(RobotConstants.AUTO_RHOOK_D2_TIME));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_RHOOK_D3_TIME));
    	addParallel(new ClawRetract2());
    	//correct and second attempt
    	addSequential(new DeadReckonTurn(0.2, false));
    	addSequential(new DeadReckon2(RobotConstants.AUTO_RHOOK_D3_TIME));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_RHOOK_D3_TIME));
    	addParallel(new ClawRetract2());
    	//correct and third attempt
    	addSequential(new DeadReckonTurn(0.2, false));
    	addSequential(new DeadReckon2(RobotConstants.AUTO_RHOOK_D3_TIME));
    	addSequential(new ClawExtend2());
    	addSequential(new DeadReckon2(-RobotConstants.AUTO_RHOOK_D3_TIME));
    	addParallel(new ClawRetract2());
    }
}
