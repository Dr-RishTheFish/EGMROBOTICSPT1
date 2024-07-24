package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.InputCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.SetShooterSpeed;
import frc.robot.subsystems.ArmRotationSubsystem;
import frc.robot.subsystems.IndexerSubsystem;

public class ArmRotationCommand extends CommandBase{
    private final ArmRotationSubsystem m_armRotationCommand;

    public ArmRotationCommand(ArmRotationSubsystem armRotationCommandSubsystem)
    {
        m_armRotationCommand = armRotationCommandSubsystem;
        addRequirements(armRotationCommandSubsystem);

    }

    @Override
    public void execute() {
        m_armRotationCommand.climber_rotation_motor.set(TalonFXControlMode.Velocity, 2000 * Constants.RPMtoEncoderConstant);
    }

    @Override
    public void end(boolean interrupted) {
        m_armRotationCommand.climber_rotation_motor.set(TalonFXControlMode.PercentOutput, 0);

    }

}
