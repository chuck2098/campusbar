package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DettaglioOrdine;
import model.DettaglioOrdineDAO;
import model.Utente;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u = (Utente)request.getSession().getAttribute("logUtente");
		
		//se e' loggato
		if(u != null) {
			ArrayList<DettaglioOrdine> cart = new DettaglioOrdineDAO().doRetrieveNotConfirmedByUser(u);
			System.out.println("--->"+cart.size());
			
			request.getSession().setAttribute("cart", cart);
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
