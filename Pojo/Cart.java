package com.FoodPlaza.Pojo;

public class Cart 
{
    private int CartId,FoodId,Quantity;
    private String FoodName,Email;
    double Price;
     
    public Cart(int fid, int quantity, String email)
    {
    	FoodId=fid;
    	Quantity=quantity;
    	Email=email;
    	
    }

	public Cart(int foodId, int quantity, String foodName, double price) {
		super();
		FoodId = foodId;
		Quantity = quantity;
		FoodName = foodName;
		
		Price = price;
	}

	public int getCartId() {
		return CartId;
	}

	public void setCartId(int cartId) {
		CartId = cartId;
	}

	public int getFoodId() {
		return FoodId;
	}

	public void setFoodId(int foodId) {
		FoodId = foodId;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public String getFoodName() {
		return FoodName;
	}

	public void setFoodName(String foodName) {
		FoodName = foodName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	@Override
	public String toString() {
		return "Cart [CartId=" + CartId + ", FoodId=" + FoodId + ", Quantity=" + Quantity + ", FoodName=" + FoodName
				+ ", Email=" + Email + ", Price=" + Price + "]";
	}
	
    
    
}
