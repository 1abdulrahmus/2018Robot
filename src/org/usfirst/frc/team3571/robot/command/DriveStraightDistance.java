/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot.command;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3571.robot.Robot;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class DriveStraightDistance extends Command {
	private PIDController m_pid;
	private double speed;
	private double targetDistance;

	public DriveStraightDistance(double distance) {
		requires(Robot.m_drivetrain);
		this.targetDistance = distance;
		
		/*m_pid = new PIDController(4, 0, 0, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			@Override
			public double pidGet() {
				return Robot.m_drivetrain.getDistance();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, d -> Robot.m_drivetrain.drive(speed*d, speed*d));

		m_pid.setAbsoluteTolerance(0.01);
		m_pid.setSetpoint(distance);*/
	}
	
	public DriveStraightDistance(double distance, double speed) {
		this(distance);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		// Get everything in a safe starting state.
		Robot.m_drivetrain.reset();
		//m_pid.reset();
		//m_pid.enable();
		this.speed = SmartDashboard.getNumber("AutoSpeed", 0.75);
		//drive
		Robot.m_drivetrain.drive(speed, speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		double distance = Math.abs(Robot.m_drivetrain.getDistance());
		if(distance>=targetDistance)
			return true;
		return false;
				
				/*m_pid.onTarget()*/
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// Stop PID and the wheels
		//m_pid.disable();
		Robot.m_drivetrain.reset();
		Robot.m_drivetrain.drive(0, 0);
	}
}
