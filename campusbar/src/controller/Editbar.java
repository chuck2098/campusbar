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
		
		Edificio ed=new Edificio();
		ed.setId_edificio(id);
		ed.setNome(nome);
		ed.setOrario_chiusura(orario);
		
		PrintWriter out=response.getWriter();
		
		if(new EdificioDAO().doUpdate(ed)) {
			out.println("Edificio modificato!");
		}else
			out.println("Edificio non modificato!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
