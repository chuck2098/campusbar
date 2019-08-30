package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ordine;
import model.OrdineDAO;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

/**
 * Servlet implementation class IndexBarServlet
 */
@WebServlet("/IndexBar")
public class IndexBarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		if(u!=null && u.getRuolo().getId_ruolo()==2) {
			//prelevo tutti gli ordini dell'utente loggato che non sono stati consegnati
			ArrayList<Ordine> ordini=(ArrayList<Ordine>) new OrdineDAO().doRetrieveOrderNotDeliveredByUser(u);
			request.setAttribute("ordini", ordini);
			RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/indexbar.jsp");
			req.forward(request, response); 
			
		}else {
			response.sendRedirect("login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
