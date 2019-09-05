package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.DettaglioOrdineDAO;
import model.Edificio;
import model.Utente;

/**
 * Servlet implementation class VisualizzaTotaliBar
 */
@WebServlet("/VisualizzaTotaliBar")
public class VisualizzaTotaliBar extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u=(Utente)request.getSession().getAttribute("logUtente");
		
		//se l'utente non e'loggato oppure non e' un bar
		if(!(u!=null && u.getRuolo().getId_ruolo()==2)) {
			response.sendRedirect("login.html");
			return;
		}
		String data_in=request.getParameter("dateStart");
		String data_end=request.getParameter("dateEnd");
		
		if(data_in==null || data_end==null) {
			request.setAttribute("totali",null);
			request.getRequestDispatcher("WEB-INF/jsp/totalibar.jsp").forward(request, response);
		}else {
			Edificio ed=u.getEdificio();
			JSONObject obj=new JSONObject();
			obj.put("totali",new DettaglioOrdineDAO().doRetrieveAmountByEdificioAndByDateStartDateEnd(ed,data_in,data_end));
			response.setContentType("application/json");
			response.getWriter().println(obj.toString());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
