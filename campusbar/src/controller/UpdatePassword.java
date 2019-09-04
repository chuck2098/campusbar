package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class UpdatePassword
 */
@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato oppure non e' un cliente
		if(!(u!=null && u.getRuolo().getId_ruolo()==3)) {
			response.sendRedirect("login.html");
			return;
		}
		
		
		String pass1= request.getParameter("pass1");
		String pass2= request.getParameter("pass2");
		String nuova= request.getParameter("pass3");

		PrintWriter out=response.getWriter();
		System.out.println(nuova);
		
		if(pass2.equals(nuova)) {
			out.println("la nuova password deve essere diversa dalla precedente");
			
		}else 
			//se la nuova password rispetta il formato,allora la confronto per vedere se e' uguale a quella reinserita 
			if((nuova != null && nuova.trim().length() >= 6 && nuova.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9_]{6,25})$"))) {
			
			if(pass1.equals(pass2)) {
				if(new UtenteDAO().doUpdatePassword(nuova,u.getMatricola())) {
					out.println("Password cambiata con successo");
				}else {
					out.println("errore nel cambio password");
				}
			}else { 
				out.println("le due password non sono uguali");
			}

		}else {
			out.println("formato non corretto");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
