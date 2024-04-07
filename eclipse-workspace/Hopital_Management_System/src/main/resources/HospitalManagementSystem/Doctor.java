package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctor 
{
	private Connection connection;
	
	
	public Doctor(Connection connection)
	{
		
		this.connection = connection;
		
	}
	
	
	public void viewDoctors()
	{
		String query ="select *from patients";
		
		try
		{
			PreparedStatement ps= connection.prepareStatement(query);
			ResultSet resultset= ps.executeQuery();
			System.out.println("Doctors:");
			System.out.println("+------------+--------------------+------------------+");
			System.out.println("| Doctor Id  |Name                 |Specialization   |");
			System.out.println("+------------+--------------------+------------------+");
			while(resultset.next())
			{
				int id= resultset.getInt("id");
				String name=resultset.getString( "name");
				String specialization =resultset.getNString("specialization");
				System.out.printf("|%-12s|%-20s|%-18s|\n , id , name , specialization");
				System.out.println("+------------+--------------------+------------------+");
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public boolean getDoctorById(int id)
	{
		String query ="select *from doctors Where id=?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultset= ps.executeQuery();
			if(resultset.next())
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	
		
		
	}
	
}
