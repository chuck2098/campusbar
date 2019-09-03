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
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class InsertCategoriaAdmin
 */
@WebServlet("/InsertCategoriaAdmin")
public class InsertCategoriaAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		
		
		Categoria cat=new Categoria();
		cat.setNomeCategoria(nome);
		
		
		PrintWriter out=response.getWriter();
		
		if(new CategoriaDAO().doSave(cat)) {
			out.println("Categoria aggiunta!");
		}else
			out.println("Impossibile aggiungere la categoria!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
