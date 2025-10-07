// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private Talon frontRightMotor;
  private Talon frontLeftMotor;
  private Talon backRightMotor;
  private Talon backLeftMotor;

  public Drivetrain() {
    frontRightMotor = new Talon(RobotMap.mapDrivetrain.FRONT_RIGHT_MOTOR);
    backRightMotor = new Talon(RobotMap.mapDrivetrain.BACK_RIGHT_MOTOR);
    frontLeftMotor = new Talon(RobotMap.mapDrivetrain.FRONT_LEFT_MOTOR);
    backLeftMotor = new Talon(RobotMap.mapDrivetrain.BACK_LEFT_MOTOR);
  }

  /**
   * Sets the velocity of the drivetrain motors.
   * 
   * @param forwardVelocity The velocity to set for the forward movement of the
   *                        drivetrain.
   * @param rotationSpeed   The rotation speed to apply to the drivetrain.
   * @param slowModeActivated A boolean indicating whether slow mode is activated or not.
   *                          If true, the speed will be multiplied by the slow mode multiplier.
   */
  public void setDrivetrainSpeed(double forwardVelocity, double rotationSpeed, boolean slowModeActivated) {
    // Divide speed in half if slow mode is activated

    if (slowModeActivated) {
      forwardVelocity = forwardVelocity * Constants.constDrivetrain.SLOW_MODE_MULTIPLIER;
      rotationSpeed = rotationSpeed * Constants.constDrivetrain.SLOW_MODE_MULTIPLIER;
    }

    // Set right velocity
    frontRightMotor.set(forwardVelocity - rotationSpeed);
    backRightMotor.set(forwardVelocity - rotationSpeed);
    // Set left velocity
    frontLeftMotor.set(forwardVelocity - rotationSpeed);
    backLeftMotor.set(forwardVelocity - rotationSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}