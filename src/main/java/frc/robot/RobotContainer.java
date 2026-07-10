// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain m_Drivetrain;
  private final Joystick m_driverController = new Joystick(Constants.kDriverControllerPort);

  public RobotContainer() {
    m_Drivetrain = new Drivetrain();
    
    m_Drivetrain.setDefaultCommand(
        new DriveCommand(m_Drivetrain, m_driverController)
    );

    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return m_Drivetrain.runOnce(m_Drivetrain::stop);
  }
}
