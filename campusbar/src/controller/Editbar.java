package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Edificio;
import model.EdificioDAO;
import model.Ruolo;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class Editbar
 */
@WebServlet("/Editbar")
public class Editbar extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		String nome=request.getParameter("nome");
		int orario=Integer.parseInt(request.getParameter("orario"));
		
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		String matr=request.getParameter("matr");
		
		Edificio ed=new Edificio();
		ed.setId_edificio(id);
		ed.setNome(nome);
		ed.setOrario_chiusura(orario);
		
		Utente u=new Utente();
		u.setEdificio(ed);
		u.setMatricola(matr);
		u.setEmail(email);
		u.setPassword(pass);
		u.setNome(matr);
		u.setCognome(matr);
		Ruolo bar=new Ruolo();
		bar.setId_ruolo(2);
		u.setRuolo(bar);
		
		PrintWriter out=response.getWriter();
		
		if(new EdificioDAO().doUpdate(ed) && new UtenteDAO().doUpdate(u))
			out.println("Edificio modificato!");
		else
			out.println("Edificio non modificato!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
