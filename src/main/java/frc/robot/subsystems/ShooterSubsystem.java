
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets; 
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.networktables.NetworkTableEntry;
import java.util.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShooterSubsystem extends SubsystemBase {
    
    public TalonFX shooter_left = new TalonFX(Constants.SHOOTER_MOTER_LEFT);
    public TalonFX shooter_right = new TalonFX(Constants.SHOOTER_MOTER_RIGHT);
    ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
    SupplyCurrentLimitConfiguration currentLimit = new SupplyCurrentLimitConfiguration(); 

    public ShooterSubsystem() {

        //shooter_left.configFactoryDefault(); 

        //Change the direction 
        shooter_left.setInverted(true);
        shooter_right.setInverted(false);


        //PID control
        double kP = .15; 
        shooter_right.config_kF(0, .05, 30); 
        shooter_right.config_kP(0, kP); 
        shooter_right.config_kI(0, 0);
        shooter_right.config_kD(0, 10*kP); 
        shooter_right.selectProfileSlot(0, Constants.SHOOTER_MOTER_LEFT);
        //shooter.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 5, 25, 0.1));
        
        //Shuffleboard (?)
        SmartDashboard.putNumber("Shooter", 0);
        SmartDashboard.putNumber("#Shooter", 0);
    }

    @Override
    public void periodic() {
        double shooterInput = SmartDashboard.getNumber("Top", 0.0);
        SmartDashboard.putNumber("#Feeder", shooterInput);
        shooter_left.follow(shooter_right);
    
    }
    
}