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

/**
 * Servlet implementation class IndexServlet
 */

//non specificando il nome della webservlet,fa si che venga eseguita per prima
@WebServlet("")
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Prodotto> prod=(ArrayList<Prodotto>) new DettaglioOrdineDAO().doRetrieveProductMostSold();
		request.setAttribute("prodotti", prod);
		RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		req.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
