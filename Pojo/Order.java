package com.FoodPlaza.Pojo;



public class Order 
{
  private int OrderId;
  private String Email;
  private double Total_bill;
  private  String date;
  
public int getOrderId() {
	return OrderId;
}
public void setOrderId(int orderId) {
	OrderId = orderId;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public double getTotal_bill() {
	return Total_bill;
}
public void setTotal_bill(double total_bill) {
	Total_bill = total_bill;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
@Override
public String toString() {
	return "Order [OrderId=" + OrderId + ", Email=" + Email + ", Total_bill=" + Total_bill + ", date=" + date + "]";
}

  
}
