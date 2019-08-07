package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ciao a tutti e benvenuti!!!");
		System.out.println("ciao a tutti e benvenuti!!!");
		System.out.println("ciao a tutti e benvenuti!!!");
		System.out.println("ciao a tutti e benvenuti!!!");
		System.out.println("ciao a tutti e benvenuti!!!");
		System.out.println("ciao a tutti e benvenuti!!!");
		System.out.println("ciao a tutti e benvenuti!!!");
		System.out.println("ciao sono vincenzo e soni gay");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
