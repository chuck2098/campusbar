package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.ProdottoDAO;

/**
 * Servlet implementation class DeleteProdotto
 */
@WebServlet("/DeleteProdotto")
public class DeleteProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		PrintWriter out=response.getWriter();
		
		if(new ProdottoDAO().doDeletebyId(id)) {
			out.println("Prodotto eliminato!");
		}else
			out.println("Prodotto non eliminato");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}