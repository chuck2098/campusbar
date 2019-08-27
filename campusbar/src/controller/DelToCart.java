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
		int index=0;
		
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato
		if(u!=null) {
			
			DettaglioOrdineDAO d=new DettaglioOrdineDAO();
			boolean r=d.doDeleteById(Integer.parseInt(id_prod));
			PrintWriter out=response.getWriter();
			
			if(r) out.println("Prodotto eliminato dal carrello");
			else  out.println("Impossibile eliminare il prodotto dal carrello");

		}else { //utente non loggato,gestire il carrello con la sessione
			HttpSession session = request.getSession(true);
			ArrayList<DettaglioOrdine> dettagli = (ArrayList<DettaglioOrdine>) session.getAttribute("dettaglio");
			
			for(int i =0 ;i<dettagli.size(); i++) {
				if(dettagli.get(i).getId_dettaglio()==Integer.parseInt(id_prod) );
					index=i;
			}
			dettagli.remove(index);
			
			if(dettagli.size()==0)
				session.removeAttribute("dettaglio");
			else
				session.setAttribute("dettaglio", dettagli);
			
			PrintWriter out=response.getWriter();
			out.println("Prodotto rimosso dal carrello");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
