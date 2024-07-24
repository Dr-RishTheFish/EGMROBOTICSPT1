
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
import frc.robot.commands.InputCommand;
import frc.robot.commands.OutputCommand;
import frc.robot.subsystems.IndexerSubsystem;

import java.util.*;

public class RackandPinionSubsystem extends SubsystemBase{
    public TalonFX rack_and_pinion = new TalonFX(Constants.RACK_AND_PINION_MOTOR);
    public double initial_position = rack_and_pinion.getSelectedSensorPosition();

    public RackandPinionSubsystem(){

        //Motor Values
        rack_and_pinion.setSensorPhase(true);
        rack_and_pinion.setInverted(true);
        rack_and_pinion.configForwardSoftLimitThreshold(15000); 
        rack_and_pinion.configForwardSoftLimitEnable(true, 0);
        
    }

    public void periodic() {

        
        //System.out.println(initial_position);
       
    }
    
}
