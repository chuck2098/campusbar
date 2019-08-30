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
		String id_edificio=request.getParameter("ed");
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato   
		if(u!=null) {
			Ordine ord=new Ordine();
			OrdineDAO o=new OrdineDAO();
			DettaglioOrdineDAO det=new DettaglioOrdineDAO();
			 
			ord.setNota_ordine(nota_ordine);
			ord.setData_ordine(new GregorianCalendar());
			ord.setConsegnato(false);
			
			Edificio e=new Edificio();
			e.setId_edificio(Integer.parseInt(id_edificio));
			ord.setEdificio(e);
			
			//mi restituisce il carrello dell'utente loggato
			ord.setDettaglio(det.doRetrieveNotConfirmedByUser(u));
			
			o.doSaveByUser(ord,u);
			
			PrintWriter out=response.getWriter();
			out.println("Ordine confermato!!");
			request.getSession().removeAttribute("cart");
		}else {// l'utente non � loggato
			PrintWriter out=response.getWriter();
			out.println("notLogged");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

