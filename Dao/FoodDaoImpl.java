package com.FoodPlaza.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.FoodPlaza.Pojo.Food;
import com.FoodPlaza.Utility.DBUtility;

//Food Dao Implementation
public class FoodDaoImpl implements FoodDao 
{
	Food f;
	String str;
   PreparedStatement stmt;
   ResultSet rs;
   int row=0;
   static Connection con=DBUtility.connection();
   
	@Override
	public boolean addFood(Food f) 
	{
	str="insert into Food(FoodName,FoodType,FoodCategory,Image,FoodDescription,price)"+"values"+"(?,?,?,?,?,?)";
	try 
	{
		stmt=con.prepareStatement(str);
		stmt.setString(1,f.getFoodName());
		stmt.setString(2,f.getFoodType());
		stmt.setString(3,f.getFoodCategory());
		stmt.setBlob(4,f.getImage());
		stmt.setString(5,f.getDesc());
		stmt.setDouble(6,f.getPrice());
		row=stmt.executeUpdate();
			
	} 
	catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(row>0) return true;
	else return false;
		
	}

	@Override
	public boolean updateFood(Food f) 
	{
		str="Update Food set FoodName=?,FoodType=?,FoodCategory=?,Image=?,FoodDescription=?,Price=?"+"where FoodId=?";
		try 
		{
			stmt=con.prepareStatement(str);
			stmt.setString(1,f.getFoodName());
			stmt.setString(2,f.getFoodType());
			stmt.setString(3,f.getFoodCategory());
			stmt.setBlob(4,f.getImage());
			stmt.setString(5,f.getDesc());
			stmt.setDouble(6,f.getPrice());
			stmt.setInt(7,f.getFoodId());
			row=stmt.executeUpdate();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(row>0) return true;
		else
		return false;
	}

	@Override
	public boolean deleteFoodById(int FoodId)
	{
		str="delete from Food where FoodId=?";
		try
		{
		stmt=con.prepareStatement(str);
		stmt.setInt(1,FoodId);
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
// Get Food BY Id
	@Override
	public Food getFoodByID(int FoodId) 
	{
		
		str="select * From Food where FoodId=?";
		try 
		{
			stmt=con.prepareStatement(str);
			stmt.setInt(1,FoodId);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				f=new Food();
				f.setFoodId(rs.getInt("FoodId"));
				f.setFoodName(rs.getString("FoodName"));
				f.setFoodType(rs.getString("FoodType"));
				f.setFoodCategory(rs.getString("FoodCategory"));
				f.setImage(rs.getBinaryStream("Image"));
				f.setDesc(rs.getString("FoodDescription"));
				f.setPrice(rs.getDouble("Price"));
			}
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<Food> getAllFood() 
	{ 
		str="select * from Food";
		List<Food> li =new ArrayList<Food>();
		try
		{
			stmt=con.prepareStatement(str);
			rs=stmt.executeQuery();
			
			while(rs.next())
			{
				f=new Food (rs.getString("FoodName"),rs.getString("FoodType"),rs.getString("FoodCategory"),rs.getBinaryStream("Image"),rs.getString("FoodDescription"),rs.getDouble("Price"));
                f.setFoodId(rs.getInt("FoodId"));
                li.add(f);
			}
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
				return li;
	}

	@Override
	public List<Food> getFoodByCategory(String FoodCategory) 
	{
		str="select * from Food where FoodCategory=?";
		List<Food> li1=new ArrayList<Food>();
		try 
		{
			stmt=con.prepareStatement(str);
			stmt.setString(1,FoodCategory);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				f=new Food (rs.getString("FoodName"),rs.getString("FoodType"),rs.getString("FoodCategory"),rs.getBinaryStream("Image"),rs.getString("FoodDescription"),rs.getDouble("Price"));
                f.setFoodId(rs.getInt("FoodId"));
                li1.add(f);
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li1;
	}
  
}
