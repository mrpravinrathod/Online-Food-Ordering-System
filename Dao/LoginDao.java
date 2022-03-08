package com.FoodPlaza.Dao;

public interface LoginDao 
{
  boolean userLogin(String email,String pass);
  boolean changePass(String email,String pass);
  boolean adminLogin(String email,String password);
  boolean adminChangePass(String email,String password);
}
