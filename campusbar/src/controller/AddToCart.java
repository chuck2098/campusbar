package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_prod=request.getParameter("id");
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		//se l'utente e' loggato
		if(u!=null) {
			
			Prodotto p=new Prodotto();
			p.setId(Integer.parseInt(id_prod));
			
			DettaglioOrdine d=new DettaglioOrdine();
			d.setCliente(u);
			d.setProdotto(p);
			
			DettaglioOrdineDAO dett=new DettaglioOrdineDAO();
			dett.doSaveCart(d);
			
		}else { //utente non loggato,gestire il carrello con i cookie
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
