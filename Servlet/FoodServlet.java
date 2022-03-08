package com.FoodPlaza.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.FoodPlaza.Dao.FoodDaoImpl;
import com.FoodPlaza.Pojo.Food;

/**
 * Servlet implementation class FoodServlet
 */
@WebServlet("/FoodServlet")
@MultipartConfig(maxFileSize=16777215)
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		FoodDaoImpl fdao=new FoodDaoImpl();
		HttpSession session=request.getSession();
		
		String operation=request.getParameter("action");
		
		
		if(operation!=null && operation.equals("edit"))
		{
			
			int id=Integer.parseInt(request.getParameter("id"));
			Food f=fdao.getFoodByID(id);
			session .setAttribute("foodObj",f);
			RequestDispatcher rd=request.getRequestDispatcher("UpdateFood.jsp");
			rd.forward(request,response);
			
			
		}
		else if(operation!=null && operation.equals("delete"))
		{
		 int id=Integer.parseInt(request.getParameter("id"));
		 boolean flag=fdao.deleteFoodById(id);
		 if(flag)
		 {
			 RequestDispatcher rd=request.getRequestDispatcher("FoodList.jsp");
				rd.include(request,response);
		 }
		 else
		 {
			 RequestDispatcher rd=request.getRequestDispatcher("AddCustomer.jsp");
				rd.include(request,response);
		 }
		 
		}
		else
		{
		
		List<Food> flist=fdao.getAllFood();
		session.setAttribute("foodList",flist);
		RequestDispatcher rd=request.getRequestDispatcher("FoodList.jsp");
		rd.forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		//doGet(request, response);
		FoodDaoImpl fdao=new FoodDaoImpl();
		boolean flag;
		String operation=request.getParameter("operation");
		
		if(operation!=null && operation.equals("AddFood"))
		{
		String fname=request.getParameter("fname");
		String ftype=request.getParameter("ftype");
		String fcat=request.getParameter("fcat");
		Part getPart =request.getPart("image");
		InputStream image=getPart.getInputStream();
		String desc=request.getParameter("desc");
		Double price=Double.parseDouble(request.getParameter("price"));
		Food f=new Food(fname,ftype,fcat,image,desc,price);
		flag=fdao.addFood(f);
		if(flag)
		{
		response.sendRedirect("Successful.html");	
		}
		else
		{
			response.sendRedirect("Fail.html");
		}
		}
		else if(operation!=null && operation.equals("UpdateFood"))	
		{
			int id=Integer.parseInt(request.getParameter("fid"));
			String fname=request.getParameter("fname");
			String ftype=request.getParameter("ftype");
			String fcat=request.getParameter("fcat");
			Part getPart =request.getPart("image");
			InputStream image=getPart.getInputStream();
			String desc=request.getParameter("desc");
			Double price=Double.parseDouble(request.getParameter("price"));
			Food f=new Food(fname,ftype,fcat,image,desc,price);
			f.setFoodId(id);
			flag=fdao.updateFood(f);
			if(flag)
			{
			response.sendRedirect("Successful.html");	
			}
			else
			{
				response.sendRedirect("Fail.html");
			}
		}
	}

}
