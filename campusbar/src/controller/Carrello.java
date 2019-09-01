package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DettaglioOrdine;
import model.DettaglioOrdineDAO;
import model.DisponibilitaDAO;
import model.EdificioDAO;
import model.Utente;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u = (Utente)request.getSession().getAttribute("logUtente");
		HttpSession session = request.getSession(true);

		
		//se e' loggato
		if(u != null) {
			ArrayList<DettaglioOrdine> cart = new DettaglioOrdineDAO().doRetrieveNotConfirmedByUser(u);

			if(cart.size()>0) {
				request.getSession().setAttribute("cart", cart);
				//se e' disponibile nell'edificio di default
				if(new DisponibilitaDAO().doCheckAvailabilityByEdifcioAndCart(u.getEdificio(),cart))
					request.setAttribute("edificioDefault",u.getEdificio());
			}
			else
				request.getSession().removeAttribute("cart");
			
		}else {
			@SuppressWarnings("unchecked")
			ArrayList<DettaglioOrdine> cart = (ArrayList<DettaglioOrdine>) session.getAttribute("dettaglio");
			request.getSession().setAttribute("cart", cart);
			
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
