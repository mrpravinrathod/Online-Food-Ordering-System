package com.FoodPlaza.Pojo;

public class Admin 
{
  private  int adminid;
  private String email;
  private String password;
public int getAdminid() {
	return adminid;
}
public void setAdminid(int adminid) {
	this.adminid = adminid;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Admin [adminid=" + adminid + ", email=" + email + ", password=" + password + "]";
}
  
  
}
