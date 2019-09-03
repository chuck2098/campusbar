package controller;

import java.io.IOException;
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

/**
 * Servlet implementation class VisualizzaCategorieAdmin
 */
@WebServlet("/VisualizzaCategorieAdmin")
public class VisualizzaCategorieAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));

		Categoria cat;
		cat=new CategoriaDAO().doRetrieveById(id);   

		JSONObject obj=new JSONObject();
		ArrayList<Categoria> cats=new ArrayList<>();
		cats.add(cat);
		obj.put("categoria",cats);

		System.out.println(obj.toString());
		response.setContentType("application/json");
		response.getWriter().println(obj.toString());

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
