package com.FoodPlaza.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.FoodPlaza.Pojo.Customer;
import com.FoodPlaza.Pojo.Food;
import com.FoodPlaza.Utility.DBUtility;


public class CustomerDaoImpl implements  CustomerDao
{
   Customer c;
   String str1;
   PreparedStatement stmt;
   ResultSet rs;
   int row=0;
   static Connection con=DBUtility.connection();
	
   //Add Customer Details
   @Override
	public boolean addCustomer(Customer c)
     {
	   str1="insert into Customer(CustName,CustEmail,CustAddress,CustPassword,Contact_No)"+"values"+"(?,?,?,?,?)";
	   try
	   {
		   stmt=con.prepareStatement(str1);
		   stmt.setString(1,c.getCustName());
		   stmt.setString(2,c.getCustEmail());
		   stmt.setString(3,c.getCustAddress());
		   stmt.setString(4,c.getCustPassword());
		   stmt.setString(5,c.getContact_No());
		   row=stmt.executeUpdate();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	if(row>0)
		return true;
	else 
		return false;
	}

	@Override
	public boolean updateCustomer(Customer c) 
	{
		str1="update Customer set CustName=?,CustEmail=?,CustAddress=?,CustPassword=?,Contact_No=? where CustId=?";
		try {
			stmt=con.prepareStatement(str1);
			stmt.setString(1,c.getCustName());
			stmt.setString(2,c.getCustEmail());
			stmt.setString(3,c.getCustAddress());
			stmt.setString(4,c.getCustPassword());
			stmt.setString(5,c.getContact_No());
			stmt.setInt(6,c.getCustId());
			row=stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(row>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteCustId(int CustId)
	{
	    str1="delete From Customer where CustId=?";
	    try {
	    	stmt=con.prepareStatement(str1);
			stmt.setInt(1,CustId);
			row=stmt.executeUpdate();
		}
	    catch (SQLException e)
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(row>0)
			return true;
			else
			return false;
	}

	@Override
	public Customer getCustById(int CustId) 
	{
		str1="select * From Customer where CustId=?";
		try 
		{
			stmt=con.prepareStatement(str1);
			stmt.setInt(1,CustId);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				c=new Customer();
				c.setCustId(rs.getInt("CustId"));
				c.setCustName(rs.getString("CustName"));
				c.setCustEmail(rs.getString("CustEmail"));
				c.setCustAddress(rs.getString("FoodCategory"));
				c.setCustPassword(rs.getString("Image"));
				c.setContact_No(rs.getString("FoodDescription"));
				stmt.executeQuery();
			}
		} 
		catch(Exception e)
		{
			
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() 
	{
	 	str1="select * from Customer";
	 	List<Customer> li=new ArrayList<>();
	 	try 
	 	{
			stmt=con.prepareStatement(str1);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				c=new Customer(rs.getString("CustName"),rs.getString("CustEmail"),rs.getString("CustAddress"),rs.getString("CustPassword"),rs.getString("Contact_No"));
			}
		} 
	 	catch (SQLException e) 
	 	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}

}
