package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineDAO;
import model.Utente;

/**
 * Servlet implementation class DeleteOrder
 */
@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_order=request.getParameter("id");
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se e' loggato ed e' un bar
		if(u!=null && (u.getRuolo().getId_ruolo()==2 || u.getRuolo().getId_ruolo()==3)) {
			OrdineDAO ord=new OrdineDAO();
			PrintWriter out=response.getWriter();
			if(ord.doDeleteById(Integer.parseInt(id_order)))
				out.println("Ordine Eliminato!");
			else
				out.println("Impossibile eliminare l'ordine");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
