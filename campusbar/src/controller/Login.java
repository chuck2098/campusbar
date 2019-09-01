package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import model.DettaglioOrdine;
import model.DettaglioOrdineDAO;
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
		HttpSession sess=request.getSession();
		
		Utente c=new UtenteDAO().doRetrieveByMatricolaPassword(matr, pass);
		if(c==null) 
			response.sendRedirect("errati.html");
		
			
		else if(c.getRuolo().getId_ruolo()==3) {
			
			sess.setMaxInactiveInterval(1800); //dopo 30 min scade la sessione
			sess.setAttribute("logUtente",c);
			
			if(sess.getAttribute("dettaglio") == null) {// condizione per l'utente che si � loggato senza passare per il carrello
				response.sendRedirect(".");    
			}else {// condizione per l'utente che ha prima caricato il carrello, poi in fase di conferma si � loggato
				
				ArrayList<DettaglioOrdine> dettagli=null;
				DettaglioOrdineDAO det=new DettaglioOrdineDAO();
				
				dettagli = (ArrayList<DettaglioOrdine>) sess.getAttribute("dettaglio");
				for(DettaglioOrdine dettOrd: dettagli) {
					dettOrd.setCliente(c);
					det.doSaveOrUpdateCart(dettOrd);
				}
				response.sendRedirect("Carrello");  
			}
		}
		else if(c.getRuolo().getId_ruolo()==2) {
			
			sess.setMaxInactiveInterval(1800); //dopo 30 min scade la sessione
			sess.setAttribute("logUtente",c);
			
			response.sendRedirect("IndexBar");
		}
		else {
			sess.setMaxInactiveInterval(1800); //dopo 30 min scade la sessione
			sess.setAttribute("logUtente",c);
			
			response.sendRedirect("IndexAdmin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
