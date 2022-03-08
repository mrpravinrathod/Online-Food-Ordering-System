package com.FoodPlaza.Dao;

import java.util.List;

import com.FoodPlaza.Pojo.Order;

public interface OrderDao 
{
 int placeOrder(Order o);
 List<Order> showOrder(String Email);
}
