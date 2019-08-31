package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_prod=request.getParameter("id");
		String quant=request.getParameter("quant");
		String nota=request.getParameter("nota");
		
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		ProdottoDAO prod=new ProdottoDAO();
		Prodotto p=prod.doRetrieveById(Integer.parseInt(id_prod));
		
		DettaglioOrdine d=new DettaglioOrdine();
		d.setNota(nota);
		d.setQuantita(Integer.parseInt(quant));
		d.setProdotto_ordinato(false);
		d.setPrezzo_acquisto(p.getPrezzo());
		d.setCliente(u);
		d.setProdotto(p);
		
		//se l'utente e' loggato
		if(u!=null) {
			
			DettaglioOrdineDAO dett=new DettaglioOrdineDAO();
			PrintWriter out=response.getWriter();
			if(dett.doSaveOrUpdateCart(d))
				out.println("Prodotto aggiunto al carrello");
			else
				out.println("Nessun bar ha questa quantita'");

			
		}else { //utente non loggato,gestire il carrello con la sessione
			
			ArrayList<DettaglioOrdine> dettagli=null;
			
			HttpSession session = request.getSession(true);
			
			if(session.getAttribute("dettaglio") == null)
				dettagli = new  ArrayList<DettaglioOrdine>();
			else 
				dettagli = (ArrayList<DettaglioOrdine>) session.getAttribute("dettaglio");
			
			
			PrintWriter out=response.getWriter();
			
			//se e' disponibile in qualche bar,allora lo aggiunto
			if(new DisponibilitaDAO().doCheckByQuantityAndProduct(d.getProdotto(), d.getQuantita())) {
				out.println("Prodotto aggiunto al carrello.");
				dettagli.add(d);
			}
			else
				out.println("Nessun bar ha questa quantita'");
			
			
			session.setAttribute("dettaglio", dettagli);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
