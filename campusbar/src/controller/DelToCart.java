package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DettaglioOrdine;
import model.DettaglioOrdineDAO;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

/**
 * Servlet implementation class DelToCart
 */
@WebServlet("/DelToCart")
public class DelToCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id_prod=request.getParameter("id");
		
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato
		if(u!=null) {
			
			DettaglioOrdineDAO d=new DettaglioOrdineDAO();
			boolean r=d.doDeleteById(Integer.parseInt(id_prod));
			PrintWriter out=response.getWriter();
			
			if(r) out.println("Prodotto eliminato dal carrello");
			else  out.println("Impossibile eliminare il prodotto dal carrello");

		}else { //utente non loggato,gestire il carrello con i cookie
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
