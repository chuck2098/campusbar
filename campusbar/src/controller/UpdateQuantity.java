package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Disponibilita;
import model.DisponibilitaDAO;
import model.Prodotto;
import model.Utente;

/**
 * Servlet implementation class UpdateQuantity
 */
@WebServlet("/UpdateQuantity")
public class UpdateQuantity extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_prod=Integer.parseInt(request.getParameter("id"));
		int quant=Integer.parseInt(request.getParameter("q"));
		Utente u=(Utente) request.getSession().getAttribute("logUtente");
		
		if(u!=null && u.getRuolo().getId_ruolo()==2) {
			DisponibilitaDAO d=new DisponibilitaDAO();
			Disponibilita disp=new Disponibilita();
			Prodotto p=new Prodotto();
			p.setId(id_prod);
			disp.setProdotto(p);
			disp.setEdificio(u.getEdificio());
			disp.setQuantita(quant);
			
			PrintWriter out=response.getWriter();
			if(d.doUpdateByEdificio(disp))
				out.println("Quantità aggiornata correttamente");
			else
				out.println("Impossibile aggiornare quantità");
				
		}else {
				response.sendRedirect("login.html");
			}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
