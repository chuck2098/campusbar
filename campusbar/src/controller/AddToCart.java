package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		String quant=request.getParameter("quant");
		String nota=request.getParameter("nota");
		
		
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato
		if(u!=null) {
			
			ProdottoDAO prod=new ProdottoDAO();
			Prodotto p=prod.doRetrieveById(Integer.parseInt(id_prod));
			
			DettaglioOrdine d=new DettaglioOrdine();
			d.setNota(nota);
			d.setQuantita(Integer.parseInt(quant));
			d.setProdotto_ordinato(false);
			d.setPrezzo_acquisto(p.getPrezzo());
			d.setCliente(u);
			d.setProdotto(p);
			
			DettaglioOrdineDAO dett=new DettaglioOrdineDAO();
			if(dett.doSaveCart(d)) {
				PrintWriter out=response.getWriter();
				out.println("Prodotto aggiunto al carrello");
			}

			
		}else { //utente non loggato,gestire il carrello con i cookie
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
