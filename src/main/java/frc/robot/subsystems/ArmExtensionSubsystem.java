
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets; 
import edu.wpi.first.networktables.NetworkTableEntry;
import java.util.*;


public class ArmExtensionSubsystem extends SubsystemBase {
    public TalonFX climber_wench_motor_left = new TalonFX(Constants.CLIMBER_WENCH_MOTOR_LEFT);
    public TalonFX climber_wench_motor_right = new TalonFX(Constants.CLIMBER_WENCH_MOTOR_RIGHT);

    public ArmExtensionSubsystem() {

    }

    @Override
    public void periodic() {
       
    }
}