package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineDAO;
import model.Utente;

/**
 * Servlet implementation class DeliveryOrder
 */
@WebServlet("/DeliveryOrder")
public class DeliveryOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato oppure non e' un bar
		if(!(u!=null && u.getRuolo().getId_ruolo()==2)) {
			response.sendRedirect("login.html");
			return;
		}
		
		OrdineDAO ord=new OrdineDAO();
		boolean res=ord.doDeliveryOrderById(Integer.parseInt(request.getParameter("id")));
		if(res)
			response.getWriter().println("Ordine consegnato");
		else
			response.getWriter().println("Errore consegna ordine");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
