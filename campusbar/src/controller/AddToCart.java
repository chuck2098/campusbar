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
			if(dett.doSaveOrUpdateCart(d)) {
				PrintWriter out=response.getWriter();
				out.println("Prodotto aggiunto al carrello");
			}

			
		}else { //utente non loggato,gestire il carrello con i cookie
			
			
			ArrayList<DettaglioOrdine> dettagli=null;
			
			HttpSession session = request.getSession(true);
			
			if(session.getAttribute("dettaglio") == null) {
				dettagli = new  ArrayList<DettaglioOrdine>();
				dettagli.add(d);
			}else {
				dettagli = (ArrayList<DettaglioOrdine>) session.getAttribute("dettaglio");
				dettagli.add(d);
			}
			
			
			session.setAttribute("dettaglio", dettagli);
			PrintWriter out=response.getWriter();
			out.println("Prodotto aggiunto al carrello");
				
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
