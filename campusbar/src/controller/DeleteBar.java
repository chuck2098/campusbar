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
import model.UtenteDAO;

/**
 * Servlet implementation class DeleteBar
 */
@WebServlet("/DeleteBar")
public class DeleteBar extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		PrintWriter out=response.getWriter();
		
		Edificio ed=new Edificio();
		ed.setId_edificio(id);
		
		//per una question di foreign key,devo eliminare prima l utente
		if(new UtenteDAO().doDeleteBar(ed) && new EdificioDAO().doDeletebyId(id)) {
			out.println("Bar eliminato!");
		}else
			out.println("Bar non eliminato");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
