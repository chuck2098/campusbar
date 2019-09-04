package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DettaglioOrdineDAO;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

/**
 * Servlet implementation class IndexAdminServlet
 */
@WebServlet("/IndexAdmin")
public class IndexAdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		if(u!=null && u.getRuolo().getId_ruolo()==1) {
			
			ArrayList<Prodotto> prod=(ArrayList<Prodotto>) new DettaglioOrdineDAO().doRetrieveProductMostSold();
			request.setAttribute("prodotti", prod);
			RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/indexadmin.jsp");
			req.forward(request, response); 
			
		}else {
			response.sendRedirect("login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
