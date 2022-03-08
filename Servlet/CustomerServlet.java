package com.FoodPlaza.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FoodPlaza.Dao.CustomerDaoImpl;
import com.FoodPlaza.Pojo.Customer;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//doGet(request, response);
		CustomerDaoImpl cdao=new CustomerDaoImpl();
		boolean flag;
		String operation=request.getParameter("operation");
		
		
		if(operation!=null && operation.equals("addcustomer") )
		{
			String custname=request.getParameter("custname");
			String address=request.getParameter("address");
			String cno=request.getParameter("cno");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			Customer c=new Customer(custname,email,address,password,cno);
			flag=cdao.addCustomer(c);
			if(flag)
			{
			response.sendRedirect("Successful.html");	
			}
			else
			{
				response.sendRedirect("Fail.html");
			}
		}
			else if(operation!=null && operation.equals("updatecustomer"))
			{
				int id=Integer.parseInt(request.getParameter("custid"));
				String custname=request.getParameter("custname");
				String address=request.getParameter("address");
				String cno=request.getParameter("cno");
				String email=request.getParameter("email");
				String password=request.getParameter("password");
				
				Customer c=new Customer(custname,email,address,password,cno);
				c.setCustId(id);
				flag=cdao.updateCustomer(c);
				
				if(flag)
				{
					request.setAttribute("status","<b style=color:red;>Registration Has been Successfully done.</b>");
					 RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
					 rd.forward(request,response);
				}
				else
				{
					request.setAttribute("status","<b style=color:red;>Registration Has been Successfully done.</b>");
					 RequestDispatcher rd=request.getRequestDispatcher("Updateprofile.jsp");
					 rd.forward(request,response);
				}
			}
		}
	}


