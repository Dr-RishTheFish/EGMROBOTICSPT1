package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class SetShooterSpeed extends CommandBase{
    private final ShooterSubsystem m_shooterSubsystem;

    public SetShooterSpeed(ShooterSubsystem shooterSubsystem)
    {
        m_shooterSubsystem = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        m_shooterSubsystem.shooter_right.set(TalonFXControlMode.Velocity, 500 * Constants.RPMtoEncoderConstant);
        //m_shooterSubsystem.shooter_right.set(TalonFXControlMode.PercentOutput, .1);
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.shooter_right.set(TalonFXControlMode.PercentOutput, 0);
    }

    public boolean isFinished(){
        
        if(m_shooterSubsystem.shooter_right.getSelectedSensorVelocity()>3400){
            System.out.println("is finished");
            return true;
        } 
        return false;
    }
}