package org.usfirst.frc.team4914.robot.subsystems;

import org.usfirst.frc.team4914.Emmet.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private final ADXRS450_Gyro gyro = RobotMap.gyro;
	private final BuiltInAccelerometer accel = RobotMap.accel;
	private final CANTalon CIMRearLeft = RobotMap.drivetrainCIMRearLeft;
    private final CANTalon CIMFrontLeft = RobotMap.drivetrainCIMFrontRight;
    private final CANTalon CIMRearRight = RobotMap.drivetrainCIMRearRight;
    private final CANTalon CIMFrontRight = RobotMap.drivetrainCIMFrontRight;
	private final RobotDrive robotDrive41 = RobotMap.drivetrainRobotDrive41;

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
     * Sets additional left and right values for drive train
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
    public double[] getAccelReading() {
    	double a[] = new double[3];
    	a[0] = accel.getX();
    	a[1] = accel.getY();
    	a[2] = accel.getZ();
    	return a;
    }
    
}

