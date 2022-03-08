package com.FoodPlaza.Pojo;

import java.io.InputStream;

public class Food 
{
  private int FoodId;
 private String FoodName,FoodType,FoodCategory,Desc;
private InputStream image;
  double Price;
  
  //NON -parameterized Constructor
  public Food()
  {
	  
  }
  //Parameterized Constructor
public Food(String foodName, String foodType, String foodCategory, InputStream image, String desc, double price) 
   {
	super();
	FoodName = foodName;
	FoodType = foodType;
	FoodCategory = foodCategory;
	this.image = image;
	Desc = desc;
	Price = price; 
    }
 
// Getter-Setter Method
    public int getFoodId() 
    {
	return FoodId;
      }
    
public void setFoodId(int foodId) 
{
	FoodId = foodId;
}
public String getFoodName() 
{
	return FoodName;
}
public void setFoodName(String foodName) 
{
	FoodName = foodName;
}
public String getFoodType()
{
	return FoodType;
}
public void setFoodType(String foodType) 
{
	FoodType = foodType;
}
public String getFoodCategory() 
{
	return FoodCategory;
}
public void setFoodCategory(String foodCategory)
{
	FoodCategory = foodCategory;
}
public InputStream getImage() 
{
	return image;
}
public void setImage(InputStream image) 
{
	this.image = image;
}
public String getDesc() 
{
	return Desc;
}
public void setDesc(String desc) 
{
	Desc = desc;
}
public double getPrice()
{
	return Price;
}
public void setPrice(double price)
{
	Price = price;
}

 // TO-STRING METHOD

@Override
public String toString() {
	return "Food [FoodId=" + FoodId + ", FoodName=" + FoodName + ", FoodType=" + FoodType + ", FoodCategory="
			+ FoodCategory + ", Image=" + image + ", Desc=" + Desc + ", Price=" + Price + "]";
}


   
}
