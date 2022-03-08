package com.FoodPlaza.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FoodPlaza.Dao.FoodDaoImpl;
import com.FoodPlaza.Pojo.Food;

/**
 * Servlet implementation class DispImgServlet
 */
@WebServlet("/DispImgServlet")
public class DispImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		  FoodDaoImpl fdao=new FoodDaoImpl();
		  int id=Integer.parseInt(request.getParameter("fid"));
		  Food f=fdao.getFoodByID(id);
		  InputStream is=f.getImage();
		  response.setContentType("*/image");
		  OutputStream os=response.getOutputStream();
		  int img=is.read();
		  while(img!=-1)
		  {
			  os.write(img);
			  img=is.read();
			  
		  }
		  os.flush();
		  os.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
