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
  private final SparkMax topLeftFrontMotor;
  private final SparkMax topRightFrontMotor;
  
  private final SparkMax bottomLeftFrontMotor;
  private final SparkMax bottomRightFrontMotor;
  private final SparkMax topLeftBackMotor;
  private final SparkMax topRightBackMotor;
  private final SparkMax bottomLeftBackMotor;
  private final SparkMax bottomRightBackMotor;

  private final DifferentialDrive drive;

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
    bottomLeftFrontMotor.configure(blfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    bottomRightFrontMotor.configure(brfConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    topLeftBackMotor.configure(tlbConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    topRightBackMotor.configure(trbConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    bottomLeftBackMotor.configure(blbConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    bottomRightBackMotor.configure(brbConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void drive(double speed, double rotation) {
    boolean allowTurnInPlace = (Math.abs(speed) < 0.05);

    drive.curvatureDrive(
        speed * Constants.MaxDriveSpeed, 
        rotation * Constants.MaxTurnSpeed, 
        allowTurnInPlace
    );
  }

  public void stop(){
    drive.stopMotor();
  }

  @Override
  public void periodic() {
  }
}
