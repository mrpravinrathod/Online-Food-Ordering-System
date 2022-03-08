package com.FoodPlaza.Utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
	public static  Connection connection()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodPlaza_07676","root","root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con; 
		
	}

}
