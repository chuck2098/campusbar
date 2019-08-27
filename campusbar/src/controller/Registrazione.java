package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Edificio;
import model.Ruolo;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//prelevo tutti i parametri dalla request
		
		Edificio edificio= new Edificio();
		Ruolo ruolo = new Ruolo();
		String matr=request.getParameter("matricola");
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		edificio.setId_edificio(Integer.parseInt(request.getParameter("bar")));
		ruolo.setId_ruolo(3);
		
		
		
		if (! (matr != null && matr.matches("^[0-9]+$"))) {
			throw new ServletException("Matricola non valida.");
		}

		if (!(nome != null && nome.trim().length() > 0 && nome.matches("^[ a-zA-Z]+$"))) {
			throw new ServletException("Nome non valido.");
		}
		
		if (!(cognome != null && cognome.trim().length() > 0 && cognome.matches("^[ a-zA-Z]+$"))) {
			throw new ServletException("Cognome non valido.");
		}

		if (!(email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$"))) {
			throw new ServletException("Email non valida.");
		}
		
		if (!(password != null && password.length() >= 6 && password.matches("[0-9]+") && password.matches("[a-z]+") && password.matches("[A-Z]+"))) {
			throw new ServletException("Password non valida.");
		}
		
		Utente u=new Utente(matr,nome,cognome,email,password,edificio,ruolo);
		
		new UtenteDAO().doSave(u);
		
		response.sendRedirect("Login?matricola="+matr+"&password="+password);
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
