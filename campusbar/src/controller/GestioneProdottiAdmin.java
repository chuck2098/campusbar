package controller;

import java.io.IOException;
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

/**
 * Servlet implementation class GestioneProdottiAdmin
 */
@WebServlet("/GestioneProdottiAdmin")
public class GestioneProdottiAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		if(u!=null && u.getRuolo().getId_ruolo()==1) {
			
			ArrayList<Prodotto> pro=(ArrayList<Prodotto>) new ProdottoDAO().doRetrieveAll();
			request.setAttribute("prodotti",pro);
			RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/gestione_prodotti.jsp");
			req.forward(request, response); 
			
		}else {
			response.sendRedirect("login.html");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
