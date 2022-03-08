package com.FoodPlaza.Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FoodPlaza.Dao.CartDaoImpl;
import com.FoodPlaza.Dao.OrderDaoImpl;
import com.FoodPlaza.Pojo.Cart;
import com.FoodPlaza.Pojo.Order;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		CartDaoImpl cdao=new CartDaoImpl();
		String operation=request.getParameter("operation");
		String username=(String)session.getAttribute("username");
		boolean flag;
		if(operation!=null && operation.equals("addToCart"))
		{
			int fid=Integer.parseInt(request.getParameter("id"));
			Cart C=new Cart(fid,1,username);
			flag=cdao.addToCart(C);
			if(flag)
			{
				request.setAttribute("Status","Food Addded in Cart");
				RequestDispatcher rd=request.getRequestDispatcher("FoodList.jsp");
				rd.forward(request,response);
				
				
			}
			else
			{
				request.setAttribute("status","Food Not Added in Cart!!");
				RequestDispatcher rd=request.getRequestDispatcher("FoodList.jsp");
				rd.forward(request,response);
			}
			
			
		}
		else if(operation!=null && operation.equals("delete"))
		{
			int cid=Integer.parseInt(request.getParameter("CartId"));
	
			flag=cdao.deleteCartById(cid);
			
			if(flag)
			{
				request.setAttribute("status","Cart deleted Sucessfully");
				List<Cart> clist=cdao.ShowCart(username);
				session.setAttribute("clist",clist);
				RequestDispatcher rd=request.getRequestDispatcher("CartList.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("status","Cart Not deleted Successfully");
				List<Cart> clist=cdao.ShowCart(username);
				session.setAttribute("clist",clist);
				RequestDispatcher rd=request.getRequestDispatcher("CartList.jsp");
				rd.forward(request,response);
			}
		}
		else
		{
			
			List<Cart> clist=cdao.ShowCart(username);
			
			session.setAttribute("clist",clist);
			RequestDispatcher rd=request.getRequestDispatcher("CartList.jsp");
			rd.forward(request,response);
		}
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String price[]=request.getParameterValues("price");
		String quantity[]=request.getParameterValues("quantity");
		HttpSession session =request.getSession();
		OrderDaoImpl odao=new OrderDaoImpl();
		CartDaoImpl cdao=new CartDaoImpl();
		LocalDate dt=LocalDate.now();
		String date=dt.toString();
		String userEmail=(String)session.getAttribute("userId");
		double total=0;
		for(int i=0;i<price.length;i++)
		{
			total=total+Double.parseDouble(price[i])*Integer.parseInt(quantity[i]);
		}
		Order o=new Order();
		o.setEmail(userEmail);
		o.setTotal_bill(total);
		o.setDate(date);
		int orderId=odao.placeOrder(o);
		if(orderId>0)
		{
			cdao.ClearCart(userEmail);
			request.setAttribute("status","<p style=\"color:red; text-align:center;\"><b>"+"Your Order is Placed."+"with Order ID:"+orderId+"Total Bill"+total);
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request,response);
		}
	}

}
