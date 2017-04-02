package org.usfirst.frc4914.CurbStomper.subsystems;

import org.usfirst.frc4914.CurbStomper.RobotConstants;
import org.usfirst.frc4914.CurbStomper.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	private final CANTalon CIMRearLeft = RobotMap.drivetrainCIMRearLeft;
    private final CANTalon CIMFrontLeft = RobotMap.drivetrainCIMFrontRight;
    private final CANTalon CIMRearRight = RobotMap.drivetrainCIMRearRight;
    private final CANTalon CIMFrontRight = RobotMap.drivetrainCIMFrontRight;
	private final RobotDrive robotDrive41 = RobotMap.drivetrainRobotDrive41;
	
	private final ADXRS450_Gyro gyro = RobotMap.gyro;
	// private final BuiltInAccelerometer accel = RobotMap.accel;
	private final Encoder enc = RobotMap.enc;

	private double additionalLeftInput = 0;
	private double additionalRightInput = 0;
	
    public void initDefaultCommand() {	}
    
    /**
     * Operates drivetrain in tank drive
     * 
     * @param leftInput
     * @param rightInput
     * @param isRealSpeed true if input is in inches per second, false for raw input
     * @param isInverted true if inverted, false otherwise
     */
    public void tankDrive(double leftInput, double rightInput, 
    		boolean isRealSpeed, boolean isInverted) {
    	// real speed conversion
    	if (isRealSpeed) {
    		leftInput /= 60;
    		rightInput /= 60;
    	}
    	// inversion
    	if (isInverted) {
    		// reverses polarity
    		leftInput *= -1;
    		rightInput *= -1;
    		// swaps two values
    		double t = leftInput;
    		leftInput = rightInput;
    		rightInput = t;
    	}
    	// additional inputs
    	leftInput += additionalLeftInput;
    	rightInput += additionalRightInput;
    	
    	// safety for left side values
    	if (leftInput > 1) leftInput = 1;
    	else if (leftInput < -1) leftInput = -1;
    	
    	// safety for right side values
    	if (rightInput > 1) rightInput = 1;
    	else if (rightInput < -1) rightInput = -1;
    	
    	// drive command
    	robotDrive41.tankDrive(-leftInput, -rightInput, false);
    }
    
    /**
     * Operates drivetrain in trigger drive
     * 
     * @param turnChannel input for left/right turning
     * @param forwardChannel input for forward speed
     * @param backwardChannel input for backward speed
     */
    /*
    public void triggerDrive(double turnChannel, double forwardChannel, double backwardChannel) {
    	// left and right input speeds
    	double leftSpeed = 0;
    	double rightSpeed = 0;
    	
    	if (forwardChannel <= 0.001) {
    		leftSpeed = -backwardChannel;
    		rightSpeed = -backwardChannel;
    	} else {
    		leftSpeed = forwardChannel;
    		rightSpeed = forwardChannel;
    	}
    	
    	if (turnChannel > 0) {
    		leftSpeed *= (1 - Math.abs(turnChannel));
    	} else {
    		rightSpeed *= (1 - Math.abs(turnChannel));
    	}
    	
    	tankDrive(leftSpeed, rightSpeed, false, RobotConstants.isInverted);
    }
    */
    
    /**
     * Operates drivetrain in trigger drive using built-in arcade drive
     * 
     * @param turnChannel value for turning (-1 to 1)
     * @param forwardChannel value for driving forward (-1 to 1)
     * @param backwardChannel value for driving backward (-1 to 1)
     */
    public void triggerDrive(double turnChannel, double forwardChannel, double backwardChannel, boolean isInverted) {
		// Moves robot using both trigger axes
    	double moveValue = 0;
    	if (isInverted) {
    		moveValue = backwardChannel - forwardChannel;
    	} else {
    		moveValue = forwardChannel - backwardChannel;
    	}
    		
		// Rotates robot left and right on a single axis
		double rotateValue = turnChannel;
		
		// Drives robot at provided move and rotate values
		robotDrive41.arcadeDrive(moveValue, rotateValue, true);
	} // End of method
    
    /**
     * Sets additional left and right values for drive train
     * Ranges from -1 to 1
     */
    public void setAdditionalInput(double addLeftInput, double addRightInput) {
    	additionalLeftInput = addLeftInput;
    	additionalRightInput = addRightInput;
    }
    public void setAdditionalInput(double addInput) {
    	additionalLeftInput = addInput;
    	additionalRightInput = addInput;
    }
    
    /**
     * Stops drivetrain
     */
    public void stop() {
    	setAdditionalInput(0);
    	robotDrive41.stopMotor();
    }
    
    /**
     * Returns gyrometer bearing
     * 
     * @return gyro bearing in degrees
     */
    public double getGyroBearing() {
    	return (gyro.getAngle() + 3600) % 360;
    }
    
    /**
     * Resets gyro bearing
     */
    public void resetGyro() {
    	gyro.reset();
    }
    
    /**
     * Returns accelerometer reading
     * 
     * @return accel reading
     */
    /*
    public double[] getAccelReading() {
    	double a[] = new double[3];
    	a[0] = accel.getX();
    	a[1] = accel.getY();
    	a[2] = accel.getZ();
    	return a;
    } */
    
    /**
     * Returns drive encoder position
     * 
     * @return raw encoder position
     */
    public double getEncoderPosition() {
    	// return CIMRearLeft.getEncPosition();
    	return enc.getDistance();
    }
    
    /**
     * Resets encoder to a value of 0
     */
    public void resetEncoder() {
    	// CIMRearLeft.setPosition(0);
    	enc.reset();
    }
    
}

