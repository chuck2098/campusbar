package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class ConfirmCart
 */
@WebServlet("/ConfirmCart")
public class ConfirmCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nota_ordine=request.getParameter("not");
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato
		if(u!=null) {
			Ordine ord=new Ordine();
			OrdineDAO o=new OrdineDAO();
			DettaglioOrdineDAO det=new DettaglioOrdineDAO();
			 
			ord.setNota_ordine(nota_ordine);
			ord.setData_ordine(new GregorianCalendar());
			ord.setConsegnato(false);
			
			//edificio da modificar dopo lo prendiamo da parametro
			ord.setId_edificio(u.getEdificio());
			
			//mi restituisce il carrello dell'utente loggato
			ord.setDettaglio(det.doRetrieveNotConfirmedByUser(u));
			
			o.doSaveByUser(ord,u);
			
			PrintWriter out=response.getWriter();
			out.println("Ordine confermato!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

