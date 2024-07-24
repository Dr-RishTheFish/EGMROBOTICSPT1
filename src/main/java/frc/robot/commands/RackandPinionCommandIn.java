package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import frc.robot.Constants;
import frc.robot.subsystems.RackandPinionSubsystem;

public class RackandPinionCommandIn extends CommandBase{
    private final RackandPinionSubsystem m_rackandPinionSubsystem;

    public RackandPinionCommandIn(RackandPinionSubsystem rackandPinionSubsystem)
    {
        m_rackandPinionSubsystem = rackandPinionSubsystem;

        addRequirements(rackandPinionSubsystem);
    }

    @Override
    public void execute() {
        m_rackandPinionSubsystem.rack_and_pinion.set(TalonFXControlMode.PercentOutput, .3);
    }

    @Override
    public void end(boolean interrupted) {
        m_rackandPinionSubsystem.rack_and_pinion.set(TalonFXControlMode.PercentOutput, 0);
    }

}