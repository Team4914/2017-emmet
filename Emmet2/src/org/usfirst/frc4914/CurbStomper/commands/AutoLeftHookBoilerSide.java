package org.usfirst.frc4914.CurbStomper.commands;

import org.usfirst.frc4914.CurbStomper.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLeftHookBoilerSide extends Command {
	
	// 2D array containing pairs containing right and left drive velocities (respectively)
	// at each time step, defined as 0.1s (update time of the rio)
	// This array is generated off-board using FalconPathPlanning courtesy of FRC 2186's Kevin Harrilal
	double VelocityProfile[][] = {{0.0, 0.0},
			{1.4408539060809495, 1.4421820089093071},
			{2.152385271967151, 2.1549693165721786},
			{2.554258060563282, 2.558380038000669},
			{2.780679435601657, 2.786787021279931},
			{2.9074754193540073, 2.916229385527776},
			{2.977381850011753, 2.989773522042259},
			{3.014382279572549, 3.031806336089775},
			{3.0318008348426098, 3.056142142679614},
			{3.0368373541777327, 3.070645137894384},
			{3.0330082204317894, 3.0799426638741463},
			{3.0216805579475876, 3.08672103857589},
			{3.00289729984304, 3.0925429550803565},
			{2.9757176579217606, 3.098455497651565},
			{2.937967266472988, 3.1057398120611364},
			{2.8868602629986793, 3.115328567380387},
			{2.819369608847375, 3.127777772647139},
			{2.7321916591409243, 3.143775974208397},
			{2.620182590497849, 3.166085097936014},
			{2.4794016927309235, 3.196059669350935},
			{2.309875466028065, 3.232217830375015},
			{2.117051169302278, 3.2707632945586953},
			{1.9050502658535942, 3.3135614080376494},
			{1.69158403570194, 3.3506777570132678},
			{1.5177276501787718, 3.3526391404755844},
			{1.4371843464871417, 3.281569378775511},
			{1.4384923667109066, 3.16428183829871},
			{1.49267505068272, 3.0322186230900168},
			{1.5781970649837684, 2.9012988171619947},
			{1.68417949584523, 2.7742511990643255},
			{1.791521839931138, 2.6620275309895125},
			{1.888004113834524, 2.5700195993740884},
			{1.9699008872217743, 2.4971352218992067},
			{2.0385732136231596, 2.4389099312266604},
			{2.0937801961637286, 2.393584542229892},
			{2.1363712404985606, 2.3591826875155624},
			{2.1682922360385954, 2.333099854041407},
			{2.1917479421388086, 2.312502753181392},
			{2.207652751392185, 2.295454412605098},
			{2.216265298731123, 2.2798756874128263},
			{2.217005525533436, 2.2629955940284243},
			{2.2077802234632773, 2.240785222940906},
			{2.183657138297649, 2.2071521060891612},
			{2.1352550207092964, 2.1518397514335814},
			{2.0455230457287414, 2.05705936212192},
			{1.883923030100313, 1.8916576272086345},
			{1.596087715921419, 1.6008691971888962},
			{1.0856925113674307, 1.088057308568556},
			{0.0, 0.0}};
	
	double timeStep = 0.1; // period of control loop on Rio, seconds
	double currentTime = 0; // elapsed time, seconds

    public AutoLeftHookBoilerSide() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    try {
	    	Robot.drivetrain.tankDrive(VelocityProfile[(int)currentTime*10][1]/5.0, VelocityProfile[(int)currentTime*10][0]/5.0, false, false);
	    	// increments current time by one time step
	    	currentTime += timeStep;
	    } catch (IndexOutOfBoundsException e) {
	    	// ends command when no more values are left in the velocity profile
	    	end();
	    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
