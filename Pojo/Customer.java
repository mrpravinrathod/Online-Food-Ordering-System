package com.FoodPlaza.Pojo;

public class Customer 
{
  private int CustId;
  private String CustName;
  private String CustEmail;
  private String CustAddress;
  private String CustPassword;
  private String Contact_No;
  
  public Customer()
  {
	  
  }
  
public Customer( String custName, String custEmail, String custAddress, String custPassword,
		String contact_No) {
	super();

	CustName = custName;
	CustEmail = custEmail;
	CustAddress = custAddress;
	CustPassword = custPassword;
	Contact_No = contact_No;
}

public int getCustId() {
	return CustId;
}

public void setCustId(int custId) {
	CustId = custId;
}

public String getCustName() {
	return CustName;
}

public void setCustName(String custName) {
	CustName = custName;
}

public String getCustEmail() {
	return CustEmail;
}

public void setCustEmail(String custEmail) {
	CustEmail = custEmail;
}

public String getCustAddress() {
	return CustAddress;
}

public void setCustAddress(String custAddress) {
	CustAddress = custAddress;
}

public String getCustPassword() {
	return CustPassword;
}

public void setCustPassword(String custPassword) {
	CustPassword = custPassword;
}

public String getContact_No() {
	return Contact_No;
}

public void setContact_No(String contact_No) {
	Contact_No = contact_No;
}

@Override
public String toString() {
	return "Customer [CustId=" + CustId + ", CustName=" + CustName + ", CustEmail=" + CustEmail + ", CustAddress="
			+ CustAddress + ", CustPassword=" + CustPassword + ", Contact_No=" + Contact_No + "]";
}
	
  
}
