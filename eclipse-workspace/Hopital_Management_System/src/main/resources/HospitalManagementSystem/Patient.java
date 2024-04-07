package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient 
{
	private Connection connection;
	private Scanner scanner;
	
	public Patient(Connection connection, Scanner scanner)
	{
		
		this.connection = connection;
		this.scanner = scanner;
	}
	
	public void addPatient() {
		System.out.println("Enter Patient Name:");
		String name= scanner.next();
		System.out.println("Enter Patient Age:");
		int age=scanner.nextInt();
		System.out.println("Enter Patient Gender:");
		String gender= scanner.next();
		
		try 
		{
		String query="INSERT INTO PATIENTS(name,age,gender)Values(?,?,?)";
		PreparedStatement ps= connection.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt( 2, age);
		ps.setString( 3, query);
		
		int affectedRows =ps.executeUpdate();
		if(affectedRows>0)
		{
			System.out.println("Patient Added Succesfully");
		}
		else
		{
			System.out.println("Failed to add Patient");
		}
		
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}
	public void viewPatients()
	{
		String query ="select *from patients";
		
		try
		{
			PreparedStatement ps= connection.prepareStatement(query);
			ResultSet resultset= ps.executeQuery();
			System.out.println("Patients:");
			System.out.println("+------------+--------------------+-----------+------------+");
			System.out.println("| Patient Id |Name                 |Age        | Gender     |");
			System.out.println("+------------+--------------------+-----------+------------+");
			while(resultset.next())
			{
				int id= resultset.getInt("id");
				String name=resultset.getString( "name");
				int age=resultset.getInt("age" );
				System.out.printf("|%-12s|%-20s|%-10s|%-12s|\n , id , name , gender");
				System.out.println("+------------+--------------------+-----------+------------+");
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public boolean getPatientById(int id)
	{
		String query ="select *from patients Where id=?";
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
