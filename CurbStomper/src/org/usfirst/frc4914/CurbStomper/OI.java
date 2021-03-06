// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4914.CurbStomper;

import org.usfirst.frc4914.CurbStomper.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton rBumper;
    public JoystickButton lBumper;
    public JoystickButton X;
    public JoystickButton CIRCLE;
    public JoystickButton SQUARE;
    public JoystickButton TRIANGLE;
    public JoystickButton START;
    public JoystickButton SELECT;
    public Joystick primaryJoystick;
    
    public JoystickButton TRIGGER;
    public JoystickButton THUMB;
    public JoystickButton LTHUMB;
    public JoystickButton RTHUMB;
    public Joystick coJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        primaryJoystick = new Joystick(0);
        coJoystick = new Joystick(1);
        
        X = new JoystickButton(primaryJoystick, 1);
        CIRCLE = new JoystickButton(primaryJoystick, 2);
        SQUARE = new JoystickButton(primaryJoystick, 3);
        rBumper = new JoystickButton(primaryJoystick, 6);
        lBumper = new JoystickButton(primaryJoystick, 5);
        START = new JoystickButton(primaryJoystick, 8);
        SELECT = new JoystickButton(primaryJoystick, 7);
        
        lBumper.whenPressed(new InvertDrive());
        SQUARE.whenPressed(new StraightDrive());
        CIRCLE.whileHeld(new RunReverseIntake());
        rBumper.whileHeld(new RunIntake());
        // lBumper.whileHeld(new RunReverseIntake());
        START.whenPressed(new TrackTarget());
        SELECT.cancelWhenPressed(new TrackTarget());
        
        TRIGGER = new JoystickButton(coJoystick, 1);
        THUMB = new JoystickButton(coJoystick, 2);
        LTHUMB = new JoystickButton(coJoystick, 3);
        RTHUMB = new JoystickButton(coJoystick, 4);
        
        TRIGGER.whenPressed(new ToggleShooter());
        THUMB.whenPressed(new TrackTarget());
        LTHUMB.whenPressed(new AdjustShootSpeed(-0.025));
        RTHUMB.whenPressed(new AdjustShootSpeed(0.025));


        // SmartDashboard Buttons
        SmartDashboard.putData("Track Target", new TrackTarget());
        SmartDashboard.putData("test", new Test());
        SmartDashboard.putNumber("Gyro", Robot.driveTrain.getGyroBearing());
        SmartDashboard.putNumber("Ultrasonic", Robot.driveTrain.getUltra());
        SmartDashboard.putNumber("Goal X", Robot.getGoalX());
        SmartDashboard.putNumber("Goal Y", Robot.getGoalY());
        
    }

    public Joystick getPrimaryJoystick() {
        return primaryJoystick;
    }
    
    public Joystick getCoJoystick() {
    	return coJoystick;
    }
    
    public double getPrimaryLJ() {
    	return primaryJoystick.getRawAxis(1);
    }
    
    public double getPrimaryRJ() {
    	return primaryJoystick.getRawAxis(5);
    }
    
    public double getPrimaryLJ_H() {
    	return primaryJoystick.getRawAxis(0);
    }
    
    public double getPrimaryRJ_H() {
    	return primaryJoystick.getRawAxis(4);
    }
    
    public double getPrimaryRT() {
    	return primaryJoystick.getRawAxis(3);
    }
    
    public double getPrimaryLT() {
    	return primaryJoystick.getRawAxis(2);
    }
    
    public double getCoX() { return coJoystick.getRawAxis(0); }
    public double getCoY() { return coJoystick.getRawAxis(1); }
    public double getCoZ() { return coJoystick.getRawAxis(2); }
    
}

