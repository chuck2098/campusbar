package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String matr=request.getParameter("matricola");
		String pass=request.getParameter("password");
		
		Utente c=new UtenteDAO().doRetrieveByMatricolaPassword(matr, pass);
		if(c==null)
			response.sendRedirect("errati.html");
		else if(c.getRuolo()==3) {
			request.getRequestDispatcher("").forward(request,response);
			HttpSession sess=request.getSession();
			sess.setAttribute("logUtente", "ok");
		}
			
		else if(c.getRuolo()==2)
			request.getRequestDispatcher("WEB-INF/jsp/indexbar.jsp").forward(request,response);
		else
			request.getRequestDispatcher("WEB-INF/jsp/indexadmin.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
