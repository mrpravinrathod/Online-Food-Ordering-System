package com.FoodPlaza.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.FoodPlaza.Pojo.Order;
import com.FoodPlaza.Utility.DBUtility;

public class OrderDaoImpl implements OrderDao
{
  
	String str;
	PreparedStatement stmt;
	ResultSet rs;
	
	int row=0;
	static Connection con=DBUtility.connection();
	
	
	@Override
	public int placeOrder(Order o) 
	{
		/*
		 * double bill; LocalDate ld=LocalDate.now(); String date=ld.toString(); try {
		 * str=" select  sum(f.Price*c.Quantity) as Totalbill From Food as f inner join Cart as c where f.FoodId=c.FoodId and Email=?"
		 * ; stmt=con.prepareStatement(str); stmt.setString(1,Email);
		 * rs=stmt.executeQuery(); while(rs.next()) { bill=rs.getDouble("Totalbill");
		 * String str1;
		 * str1="insert into FoodOrder(Email,Total_bill,Date) values(?,?,?)";
		 * stmt=con.prepareStatement(str1); stmt.setString(1,Email);
		 * stmt.setDouble(2,bill); stmt.setString(3,date); row=stmt.executeUpdate(); } }
		 * catch (SQLException e) {
		 * 
		 * e.printStackTrace(); } if (row>0) return true; else return false;
		 */
		int orderId=0;
		str="insert into Order(Email,Total_bill,Date) values(?,?,?)";
		try
		{
			stmt=con.prepareStatement(str,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,o.getEmail());
			stmt.setDouble(2,o.getTotal_bill());
			stmt.setString(3,o.getDate());
			row=stmt.executeUpdate();
			rs=stmt.getGeneratedKeys();
			if(rs.next())
			{
				orderId=rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderId;
	}


	@Override
	public List<Order> showOrder(String Email) 
	{
	  Order od=null;
	  List<Order> oli=new ArrayList<Order>();
	
	  try {
		  String  str1="select * From FoodOrder where Email=?";
		stmt=con.prepareStatement(str1);
		stmt.setString(1,Email);
		rs=stmt.executeQuery();
		while(rs.next())
		{
			od= new Order();
			od.setOrderId(rs.getInt("OrderId"));
			od.setEmail(rs.getString("Email"));
			od.setDate(rs.getString("Date"));
			od.setTotal_bill(rs.getInt("Total_bill"));
			oli.add(od);
		}
	} 
	  catch (SQLException e) 
	  {
		
		e.printStackTrace();
	}
		return oli;
	}

}
