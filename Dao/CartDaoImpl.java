package com.FoodPlaza.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FoodPlaza.Pojo.Cart;
import com.FoodPlaza.Utility.DBUtility;

public class CartDaoImpl implements CartDao
{
	String str;
	   PreparedStatement stmt;
	   ResultSet rs;
	   int row=0;
	   static Connection con=DBUtility.connection();
	   
	@Override
	public boolean addToCart(Cart c)
	{
		str="insert into Cart(FoodId,Quantity,Email)"+"values"+"(?,?,?)";
		try 
		{
		
			stmt=con.prepareStatement(str);
			stmt.setInt(1,c.getFoodId());
			stmt.setInt(2,c.getQuantity());
			stmt.setString(3,c.getEmail());
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
	public List<Cart> ShowCart(String Email)
	{
		str="select c.CartId,c.FoodId,f.FoodName,f.Price,c.Quantity From  Cart as c inner join Food as f on f.FoodId=c.FoodId and c.Email=?";
		List<Cart> cli=new ArrayList<Cart>();
		try {
			stmt=con.prepareStatement(str);
			stmt.setString(1,Email);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				Cart c=new Cart(rs.getInt("FoodId"),rs.getInt("Quantity"),rs.getString("FoodName"),rs.getDouble("Price"));
			    c.setCartId(rs.getInt("CartId"));
			    cli.add(c);
			}
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return cli;
	}

	@Override
	public boolean deleteCartById(int CartId)
	{
		str="delete From Cart where CartId=?";
		try {
			stmt=con.prepareStatement(str);
			stmt.setInt(1,CartId);
			row=stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(row>0)
		{
		return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public boolean ClearCart(String Email)
	{
		str="delete From Cart where Email=?";
		try {
			stmt=con.prepareStatement(str);
			stmt.setString(1,Email);
			row=stmt.executeUpdate();
		}
		catch(Exception e)
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
