package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Edificio;
import model.EdificioDAO;
import model.Utente;

/**
 * Servlet implementation class SearchAdmin
 */
@WebServlet("/SearchAdminBar")
public class SearchAdminBar extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String patt = request.getParameter("ricerca");
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");

		if(u!=null && u.getRuolo().getId_ruolo()==1) {

			ArrayList<Edificio> ed=new EdificioDAO().doRetrieveByName(patt);
			request.setAttribute("edifici",ed);
			
			request.setAttribute("pattern", patt);
			RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/gestione_bars.jsp");
			req.forward(request, response); 

		}else {
			response.sendRedirect("login.html");
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
