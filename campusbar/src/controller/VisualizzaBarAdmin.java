package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Categoria;
import model.CategoriaDAO;
import model.Edificio;
import model.EdificioDAO;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class VisualizzaBar
 */
@WebServlet("/VisualizzaBar")
public class VisualizzaBarAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato oppure non e' un admin
		if(!(u!=null && u.getRuolo().getId_ruolo()==1)) {
			response.sendRedirect("login.html");
			return;
		}
		
		int id=Integer.parseInt(request.getParameter("id"));

		Edificio ed;
		ed=new EdificioDAO().doRetrieveById(id);

		JSONObject obj=new JSONObject();
		ArrayList<Edificio> eds=new ArrayList<>();
		ArrayList<Utente> utente=new ArrayList<>();
		
		eds.add(ed);
		utente.add(new UtenteDAO().doRetrieveUserBarByEdificio(ed));
		obj.put("edificio",eds);
		obj.put("utenteEdificio",utente);

		response.setContentType("application/json");
		response.getWriter().println(obj.toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
