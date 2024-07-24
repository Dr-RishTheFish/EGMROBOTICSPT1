package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.InputCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.SetShooterSpeed;
import frc.robot.subsystems.IndexerSubsystem;

public class ShootCommand extends CommandBase{
    private final ShooterSubsystem m_shooterSubsystem;
    private final IndexerSubsystem m_indexerSubsystem;
    private final SetShooterSpeed speedShooter; 
    private final InputCommand input;

    public ShootCommand(ShooterSubsystem shooterSubsystem, IndexerSubsystem indexerSubsystem)
    {
        m_shooterSubsystem = shooterSubsystem;
        m_indexerSubsystem = indexerSubsystem;
        addRequirements(shooterSubsystem);
        speedShooter = new SetShooterSpeed(m_shooterSubsystem);
        input = new InputCommand(m_indexerSubsystem);
    }


    @Override
    public void execute() {
        speedShooter.execute();
        if(speedShooter.isFinished()){
            input.initialize(); 
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.shooter_right.set(TalonFXControlMode.PercentOutput, 0);
    }

}
