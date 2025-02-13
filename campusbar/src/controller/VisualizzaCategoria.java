package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class VisualizzaCategoria
 */
@WebServlet("/VisualizzaCategoria")
public class VisualizzaCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		CategoriaDAO cat = new CategoriaDAO();
		
		ArrayList<Prodotto> prod=(ArrayList<Prodotto>) new ProdottoDAO().doRetrieveByIdCategoria(Integer.parseInt(id));
		request.setAttribute("prodotti", prod);
		request.setAttribute("categoria", cat.doRetrieveById(Integer.parseInt(id)));
		RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		req.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
