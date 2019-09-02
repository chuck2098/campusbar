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
 * Servlet implementation class Insertbar
 */
@WebServlet("/Insertbar")
public class Insertbar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome=request.getParameter("nome");
		int orario=Integer.parseInt(request.getParameter("orario"));
		
		Edificio ed=new Edificio();
		ed.setNome(nome);
		ed.setOrario_chiusura(orario);
		
		PrintWriter out=response.getWriter();
		
		if(new EdificioDAO().doSave(ed)) {
			out.println("Nuovo edificio aggiunto!");
		}else
			out.println("Impossibile aggiungere edificio!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
