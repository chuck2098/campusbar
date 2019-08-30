package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;


/**
 * Servlet implementation class CategorieServlet
 */
@WebServlet("/Categorie")
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Categoria> categorie=(ArrayList<Categoria>) new CategoriaDAO().doRetrieveAll();
		request.setAttribute("categorie", categorie);
		
		RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/categoria.jsp");
		req.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
