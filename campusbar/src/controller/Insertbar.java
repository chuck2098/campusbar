package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
/**
 * Servlet implementation class Insertbar
 */
@WebServlet("/InsertBar")
public class Insertbar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome=request.getParameter("nome");
		int orario=Integer.parseInt(request.getParameter("orario"));
		
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		String matr=request.getParameter("matr");
				
		Edificio ed=new Edificio();
		ed.setNome(nome);
		ed.setOrario_chiusura(orario);
		
		Utente u=new Utente();
		u.setEmail(email);
		u.setPassword(pass);
		u.setMatricola(matr);
		u.setNome(matr);
		u.setCognome(matr);
		
		Ruolo bar=new Ruolo();
		bar.setId_ruolo(2);
		u.setRuolo(bar);
		
		
		PrintWriter out=response.getWriter();
		int last_id_bar=new EdificioDAO().doSave(ed);
		
		if(last_id_bar!=0) {
			
			ed.setId_edificio(last_id_bar);
			u.setEdificio(ed);
			new UtenteDAO().doSave(u);
			
			//aggiorno la disponibilita per ogni prodotto dato che ho appena inserito il bar,non c'e' disponibilita
			ArrayList<Prodotto> prod=(ArrayList<Prodotto>) new ProdottoDAO().doRetrieveAll();
			for(Prodotto p:prod)
				new DisponibilitaDAO().doUploadAvailabilityGiveProduct(ed, p);
			
			out.println("Nuovo edificio aggiunto!(Accedere con matricola e password)");
		}else
			out.println("Impossibile aggiungere edificio!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
