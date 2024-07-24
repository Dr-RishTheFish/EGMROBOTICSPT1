package frc.robot.subsystems;  
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
import edu.wpi.first.networktables.NetworkTable; 
import edu.wpi.first.networktables.NetworkTableEntry; 
import edu.wpi.first.networktables.NetworkTableInstance; 
import frc.robot.subsystems.VisionSubsystem; 
import edu.wpi.first.wpilibj2.command.SubsystemBase; 
import frc.robot.Constants; 
import edu.wpi.first.wpilibj.GenericHID; 
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard; 
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab; 
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;  
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts; 
import java.util.*; 
public class VisionSubsystem extends SubsystemBase { 

	ShuffleboardTab tab = Shuffleboard.getTab("Limelight"); 
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight"); 
    NetworkTableEntry tx = table.getEntry("tx"); 
    NetworkTableEntry ty = table.getEntry("ty"); 
    NetworkTableEntry ta = table.getEntry("ta"); 
    NetworkTableEntry tv = table.getEntry("tv"); 
      
    //read values periodically 
    double x = tx.getDouble(0.0); 
    double y = ty.getDouble(0.0); 
    double area = ta.getDouble(0.0); 
    double v = tv.getDouble(0.0); 
         
     
    public VisionSubsystem() { 
         
	
	}
	
	@Override
	public void periodic(){
	
		//post to smart dashboard periodically 
    	/*tab.add("LimelightV", v); 
    	tab.add("LimelightX", x); 
    	tab.add("LimelightY", y); 
        tab.add("LimelightArea", area);*/

         x = tx.getDouble(0.0); 
         y = ty.getDouble(0.0); 
         area = ta.getDouble(0.0); 
         v = tv.getDouble(0.0); 

		double targetOffsetAngle_Vertical = ty.getDouble(0.0);

		// how many degrees back is your limelight rotated from perfectly vertical?
		double limelightMountAngleDegrees = 15.0;
	
		// distance from the center of the Limelight lens to the floor
		double limelightLensHeightInches = 30.0;
	
		// distance from the target to the floor
		double goalHeightInches = 105.6;
	
		double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
		double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
	
		//calculate distance
		double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)/Math.tan(angleToGoalRadians);
        
         
	} 
	
}

