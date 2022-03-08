package com.FoodPlaza.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FoodPlaza.Dao.LoginDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		session.invalidate();
		request.setAttribute("status","Successfully logged Out...!");
		RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		LoginDaoImpl dao=new LoginDaoImpl();
		PrintWriter out=response.getWriter();
		
		String op=request.getParameter("operation");
		String type=request.getParameter("type");
		String email=request.getParameter("uname");
		String pass=request.getParameter("pass");
		boolean flag;
		
		if(op!=null && op.equals("login"))
		{
			if(type.equals("admin"))
			{
			flag=dao.adminLogin(email,pass);
			
				if(flag)
				{
				session.setAttribute("admin",email);
				request.setAttribute("status","<b style= color:red;>You have Logged In Successfully....</b>");
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.forward(request,response);
				}
				else
				{
					out.println("<b style=color:red;>You have Entered Wrong Data</b>");
					RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
					rd.include(request,response);
				}
			
			}
			else
			{
				
				flag=dao.userLogin(email,pass);
				if(flag==true)
				{
					session.setAttribute("username",email);
					request.setAttribute("status","<b style= color:red;>You have Logged In Successfully....</b>");
					RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
					rd.forward(request,response);
				}
				else
				{
					out.println("<b style=color:red;>You have Entered Wrong Data</b>");
					RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
					rd.include(request,response);
				}
				
			}
				
		
	
	}
	

	else if(op!=null && op.equals("changepass"))
	{
		String newpass=request.getParameter("newpass");
		flag=dao.adminLogin(email,pass);
		if(type.equals("admin"))
		{
		 flag=dao.adminChangePass(email,pass);
		 if(flag)
		 {
			 session.invalidate();
			 request.setAttribute("status","Sucessfully changed "+"password.Please Login again");
			 RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
			 rd.forward(request,response);
		 }
		 else
		 {
			 request.setAttribute("status","Falied to set"+"password.....try again");
			 RequestDispatcher rd=request.getRequestDispatcher("ChangePassword.jsp");
			 rd.forward(request,response);
		 }
		}
		else
		{
			flag=dao.changePass(email,pass);
			if(flag)
			{
				session.invalidate();
				 request.setAttribute("status","Sucessfully changed "+"password.Please Login again");
				 RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
				 rd.forward(request,response);
			}
			else
			{
			request.setAttribute("status","you entered wrong password");
			RequestDispatcher rd=request.getRequestDispatcher("ChangePassword.jsp");
			rd.forward(request,response);
		
			}
			}
	}
		
}

}
