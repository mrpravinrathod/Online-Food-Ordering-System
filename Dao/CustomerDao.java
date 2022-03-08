package com.FoodPlaza.Dao;

import java.util.List;

import com.FoodPlaza.Pojo.Customer;

public interface CustomerDao 
{
boolean addCustomer(Customer c);
boolean updateCustomer(Customer c);
boolean deleteCustId(int CustId);
Customer getCustById(int CustId);
List<Customer> getAllCustomer();
}
