// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.frcteam3255.joystick.SN_XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.constControllers;
import frc.robot.RobotMap.mapControllers;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.StateMachine.RobotState;

import edu.wpi.first.epilogue.Logged;

@Logged
public class RobotContainer {

  private final SN_XboxController conDriver = new SN_XboxController(mapControllers.DRIVER_USB);

  private final Drivetrain subDrivetrain = new Drivetrain();
  private final StateMachine subStateMachine = new StateMachine(subDrivetrain);

  // Commands
  Command TRY_NONE = Commands.deferredProxy(
      () -> subStateMachine.tryState(RobotState.NONE));

  public RobotContainer() {
    conDriver.setLeftDeadband(constControllers.DRIVER_LEFT_STICK_DEADBAND);

    subDrivetrain
        .setDefaultCommand(
            new DriveManual(subDrivetrain, conDriver.axis_LeftY,
                conDriver.axis_RightX, conDriver.btn_RightBumper));

    configDriverBindings();
    configOperatorBindings();
  }

  private void configDriverBindings() {
  }

  public Command getAutonomousCommand() {
    return TRY_NONE;
  }

  private void configOperatorBindings() {
    // Add operator bindings here if needed
  }

  public RobotState getRobotState() {
    return subStateMachine.getRobotState();
  }
}
