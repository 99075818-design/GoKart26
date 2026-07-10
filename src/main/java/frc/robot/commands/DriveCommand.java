// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DriveCommand extends Command {
  private final Drivetrain drivetrain;
  private final Joystick wheelcontroller;

  public DriveCommand(Drivetrain drivetrain, Joystick wheelcontroller) {
    this.drivetrain = drivetrain;
    this.wheelcontroller = wheelcontroller;
    
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double baseSpeed = 0.0;

    if (wheelcontroller.getRawButton(Constants.kForwardButtonY)) {
      baseSpeed = 1.0; 
    } else if (wheelcontroller.getRawButton(Constants.kReverseButtonB)) {
      baseSpeed = -1.0; 
    }

    double rotation = wheelcontroller.getX();

    double leftSpeed = baseSpeed;
    double rightSpeed = baseSpeed;

    if (rotation > 0) {
      rightSpeed = baseSpeed * (1.0 - rotation);
    } else if (rotation < 0) {
      leftSpeed = baseSpeed * (1.0 - Math.abs(rotation));
    }

    drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.stop(); 
  }

  @Override
  public boolean isFinished() {
    return false; 
  }
}
