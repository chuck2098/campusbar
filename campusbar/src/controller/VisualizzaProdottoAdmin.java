package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class VisualizzaProdottoAdmin
 */
@WebServlet("/VisualizzaProdotto")
public class VisualizzaProdottoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		
		Prodotto p;
		p=new ProdottoDAO().doRetrieveById(id);
		
		JSONObject obj=new JSONObject();
		ArrayList<Prodotto> eds=new ArrayList<>();
		eds.add(p);
		obj.put("prodotto",eds);
		
		response.setContentType("application/json");
		response.getWriter().println(obj.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}