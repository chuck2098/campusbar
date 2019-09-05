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
import model.Utente;

/**
 * Servlet implementation class VisualizzaUpdatePassword
 */
@WebServlet("/VisualizzaUpdatePassword")
public class VisualizzaUpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u=(Utente)request.getSession().getAttribute("logUtente");

		//se l'utente e' loggato e ha fatto l accesso come utente
		if(u!=null && u.getRuolo().getId_ruolo()==3) {
			RequestDispatcher req= request.getRequestDispatcher("WEB-INF/jsp/updatePassword.jsp");
			req.forward(request, response);
		}else
			response.sendRedirect("login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}