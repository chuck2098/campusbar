package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class EditProduct
 */
@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String nome=request.getParameter("nome");
		String descr=request.getParameter("descr");
		float prezz=Float.parseFloat(request.getParameter("prezzo"));
		int cat=Integer.parseInt(request.getParameter("cat"));
		
		Prodotto p=new Prodotto();
		p.setId(id);
		p.setNome(nome);
		p.setDescrizione(descr);
		p.setPrezzo(prezz);
		p.setCategoria(new CategoriaDAO().doRetrieveById(cat));
		
		PrintWriter out=response.getWriter();
		
		if(new ProdottoDAO().doUpdate(p)) {
			out.println("Prodotto modificato!");
		}else
			out.println("Prodotto non modificato!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
