package com.FoodPlaza.Dao;

import java.util.List;

import com.FoodPlaza.Pojo.Cart;

public interface CartDao 
{
 boolean addToCart(Cart c);
List<Cart> ShowCart(String Email);
boolean deleteCartById(int CartId);
boolean ClearCart(String Email);
}
