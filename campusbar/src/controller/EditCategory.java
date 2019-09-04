package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;
import model.Edificio;
import model.EdificioDAO;
import model.Utente;
 
/**
 * Servlet implementation class EditCategory  
 */    
@WebServlet("/EditCategory")
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente e' loggato oppure non e' un admin
		if(!(u!=null && u.getRuolo().getId_ruolo()==1)) {
			response.sendRedirect("login.html");
			return;
		}
		
		int id=Integer.parseInt(request.getParameter("id"));
		String nome=request.getParameter("nome_cat");
		
		
		Categoria cat=new Categoria();
		cat.setId_categoria(id);
		cat.setNomeCategoria(nome);
		
		
		PrintWriter out=response.getWriter();
		
		if(new CategoriaDAO().doUpdate(cat)) {
			out.println("Categoria modificata!");
		}else
			out.println("Categoria non modificata!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
