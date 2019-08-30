package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/Login")
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
		
			
		else if(c.getRuolo().getId_ruolo()==3) {
			HttpSession sess=request.getSession();
			sess.setMaxInactiveInterval(1800); //dopo 30 min scade la sessione
			sess.setAttribute("logUtente",c);
			
			if(sess.getAttribute("dettaglio") == null) {// condizione per l'utente che si è loggato senza passare per il carrello
				response.sendRedirect(".");  
			}else {// condizione per l'utente che ha prima caricato il carrello, poi in fase di conferma si è loggato
				
			}
		}
		else if(c.getRuolo().getId_ruolo()==2)
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
