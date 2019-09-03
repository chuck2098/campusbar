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

/**
 * Servlet implementation class VisualizzaBar
 */
@WebServlet("/VisualizzaBar")
public class VisualizzaBarAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id=Integer.parseInt(request.getParameter("id"));

		Edificio ed;
		ed=new EdificioDAO().doRetrieveById(id);

		JSONObject obj=new JSONObject();
		ArrayList<Edificio> eds=new ArrayList<>();
		eds.add(ed);
		obj.put("edificio",eds);


		response.setContentType("application/json");
		response.getWriter().println(obj.toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
