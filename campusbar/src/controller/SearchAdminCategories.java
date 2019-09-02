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
import model.Edificio;
import model.EdificioDAO;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

/**
 * Servlet implementation class SearchAdminCategories
 */
@WebServlet("/SearchAdminCategories")
public class SearchAdminCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String patt = request.getParameter("ricerca");

		Utente u=(Utente)request.getSession().getAttribute("logUtente");

		if(u!=null && u.getRuolo().getId_ruolo()==1) {

			ArrayList<Categoria> cat=new CategoriaDAO().doRetrieveByName(patt);
			request.setAttribute("categorie",cat);
			
			request.setAttribute("pattern", patt);
			RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/gestione_categorie.jsp");
			req.forward(request, response); 

		}else {
			response.sendRedirect("login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
