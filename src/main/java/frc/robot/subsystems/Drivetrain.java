// WE ARE 8060
package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private SparkMax topLeftFrontMotor;
  private SparkMax topRightFrontMotor;
  private SparkMax bottomLeftFrontMotor;
  private SparkMax bottomRightFrontMotor;
  private SparkMax topLeftBackMotor;
  private SparkMax topRightBackMotor;
  private SparkMax bottomLeftBackMotor;
  private SparkMax bottomRightBackMotor;

  private DifferentialDrive drive;

  public Drivetrain() {
    topLeftFrontMotor = new SparkMax(Constants.TLFMID, MotorType.kBrushed);
    topRightFrontMotor = new SparkMax(Constants.TRFMID, MotorType.kBrushed);
    bottomLeftFrontMotor = new SparkMax(Constants.BLFMID, MotorType.kBrushed);
    bottomRightFrontMotor = new SparkMax(Constants.BRFMID, MotorType.kBrushed);
    topLeftBackMotor = new SparkMax(Constants.TLBMID, MotorType.kBrushed);
    topRightBackMotor = new SparkMax(Constants.TRBMID, MotorType.kBrushed);
    bottomLeftBackMotor = new SparkMax(Constants.BLBMID, MotorType.kBrushed);
    bottomRightBackMotor = new SparkMax(Constants.BRBMID, MotorType.kBrushed);
    
    drive = new DifferentialDrive(topLeftFrontMotor, topRightFrontMotor);

    SparkBaseConfig trfConfig = new SparkMaxConfig();
    SparkBaseConfig blfConfig = new SparkMaxConfig();
    SparkBaseConfig brfConfig = new SparkMaxConfig();
    SparkBaseConfig tlbConfig = new SparkMaxConfig();
    SparkBaseConfig trbConfig = new SparkMaxConfig();
    SparkBaseConfig blbConfig = new SparkMaxConfig();
    SparkBaseConfig brbConfig = new SparkMaxConfig();

    trfConfig.inverted(true);
    brfConfig.inverted(true);
    trbConfig.inverted(true);
    brbConfig.inverted(true);

    blfConfig.follow(topLeftFrontMotor);
    brfConfig.follow(topRightFrontMotor);
    tlbConfig.follow(topLeftFrontMotor);
    trbConfig.follow(topRightFrontMotor);
    blbConfig.follow(topLeftFrontMotor);
    brbConfig.follow(topRightFrontMotor);

    topRightFrontMotor.configure(trfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    bottomLeftFrontMotor.configure(trfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    bottomRightFrontMotor.configure(trfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    topLeftBackMotor.configure(trfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    topRightBackMotor.configure(trfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    bottomLeftBackMotor.configure(trfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    bottomRightBackMotor.configure(trfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void drive(double speed, double rotation){
    drive.arcadeDrive(speed * Constants.MaxDriveSpeed, rotation * Constants.MaxTurnSpeed);
  }

  public void stop(){
    drive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
