package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.DettaglioOrdine;
import model.DisponibilitaDAO;
import model.Edificio;
import model.EdificioDAO;
import model.Utente;

/**
 * Servlet implementation class CheckAvailability
 */
@WebServlet("/CheckAvailability")
public class CheckAvailability extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u=(Utente) request.getSession().getAttribute("logUtente");
		
		//se non e' loggato oppure non e' un cliente
		if(!(u!=null && u.getRuolo().getId_ruolo()==3)) {
			response.sendRedirect("login.html");
			return;
		}
		
		EdificioDAO ed=new EdificioDAO();
		DisponibilitaDAO dispDao=new DisponibilitaDAO();
		
		//prelevo tutti gli edifici
		ArrayList<Edificio> bars=ed.doRetrieveAll();
		
		//prelevo il dettaglio ordine non confermato(carrello) del'utente loggato
		@SuppressWarnings("unchecked")
		ArrayList<DettaglioOrdine> dett=(ArrayList<DettaglioOrdine>) request.getSession().getAttribute("cart");
		
		Iterator<Edificio> iter = bars.iterator();
		while(iter.hasNext()) {
			
			//se non c'e' disponibilita' di tutti i prodotti del carrello per questo edifcio allora lo rimuovo
			if(!dispDao.doCheckAvailabilityByEdifcioAndCart(iter.next(),dett)) {
				iter.remove();
			}
		}
		
		JSONObject obj=new JSONObject();
		obj.put("edifici",bars);
		
		response.setContentType("application/json");
		response.getWriter().println(obj.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
