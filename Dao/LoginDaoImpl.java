package com.FoodPlaza.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FoodPlaza.Utility.DBUtility;

public class LoginDaoImpl implements LoginDao 
{
    String str;
    PreparedStatement stmt;
    ResultSet rs;
    int row=0;
   
    static Connection con=DBUtility.connection();
	@Override
	public boolean userLogin(String email, String pass)
	{
	   str="select * from Customer where CustEmail=? and CustPassword=?";
	   try {
		stmt=con.prepareStatement(str);
		stmt.setString(1,email);
		stmt.setString(2,pass);
		rs=stmt.executeQuery();
		
		while(rs.next())
		{
			if(email.equals(rs.getString("CustEmail"))&& pass.equals(rs.getString("CustPassword")) )
			{
				return true;
			}
		}
	}
	   catch (SQLException e) 
	   
	   {
		
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public boolean changePass(String email, String pass) 
	{
	  str="update Customer set Password=? where Email=?";
	  try
	  {
		stmt=con.prepareStatement(str);
		stmt.setString(1,pass);
		stmt.setString(2,email);
		row=stmt.executeUpdate();
	} 
	  
	  catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if(row>0)
	  {return true;}
	  else
		return false;
	}

	@Override
	public boolean adminLogin(String email, String password) 
	{
	str="select * from Admin where Email=? and Password=?";
	try {
		stmt=con.prepareStatement(str);
		stmt.setString(1,email);
		stmt.setString(2,password);
		rs=stmt.executeQuery();
		while(rs.next())
		{
			if(email.equals(rs.getString("Email")) && password.equals(rs.getString("Password")))
			{
				return true;
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public boolean adminChangePass(String email, String pass) 
	{
	 str="update Admin set Password=? where Email=?";
	 try {
		stmt=con.prepareStatement(str);
		stmt.setString(1,pass);
		stmt.setString(2,email);
		row=stmt.executeUpdate();
	} 
	 catch (SQLException e)
	 {
		
		e.printStackTrace();
	}
	 if(row>0)
	 {
		 return true;
	 }
	 else
		return false;
	}

}
