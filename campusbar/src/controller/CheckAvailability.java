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
import model.Edificio;
import model.EdificioDAO;

/**
 * Servlet implementation class CheckAvailability
 */
@WebServlet("/CheckAvailability")
public class CheckAvailability extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EdificioDAO ed=new EdificioDAO();
		ArrayList<Edificio> bars=ed.doRetrieveAll();
		
		@SuppressWarnings("unchecked")
		ArrayList<DettaglioOrdine> dett=(ArrayList<DettaglioOrdine>) request.getSession().getAttribute("cart");
		
		Iterator<Edificio> iter = bars.iterator();
		while(iter.hasNext()) {
			
			//se non c'e' disponibilita' di tutti i prodotti del carrello per questo edifcio allora lo rimuovo
			if(!ed.doCheckAvailabilityByEdifcioAndCart(iter.next(),dett)) {
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
