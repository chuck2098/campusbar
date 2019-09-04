package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class EliminaAccount
 */
@WebServlet("/EliminaAccount")
public class EliminaAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u=(Utente) request.getSession().getAttribute("logUtente");
		PrintWriter out = response.getWriter();
		
		
		if(u!=null && u.getRuolo().getId_ruolo() == 3 ) {
			if(new UtenteDAO().doDeleteUser(u)) {
				request.getSession().invalidate();
				response.sendRedirect(".");
				out.println("account eliminato");
			}else
				out.println("account non eliminato");
				
		}else
			response.sendRedirect("login.html");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
