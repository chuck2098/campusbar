package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Categoria;
import model.CategoriaDAO;
import model.DisponibilitaDAO;
import model.ProdottoDAO;
import model.Utente;

/**
 * Servlet implementation class GestioneProdotti
 */
@WebServlet("/GestioneQuantita")
public class GestioneQuantitaBar extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u=(Utente) request.getSession().getAttribute("logUtente");
		
			if(u!=null && u.getRuolo().getId_ruolo()==2) {
				String id_cat=request.getParameter("id");
				//se passo l'id della categoria allora restituisco la disponibilita dei prodotti di quella categoria
				if(id_cat!=null) {
					Categoria c=new Categoria();
					c.setId_categoria(Integer.parseInt(id_cat));
					
					JSONObject obj=new JSONObject();
					obj.put("disponibilita",new DisponibilitaDAO().doRetrieveByEdificioAndCategoria(u.getEdificio(),c));
					response.setContentType("application/json");
					response.getWriter().println(obj.toString());
				}
				else { //ritorno le categorie
					request.setAttribute("categorie",new CategoriaDAO().doRetrieveAll());
					request.getRequestDispatcher("WEB-INF/jsp/gestione_quantita.jsp").forward(request, response);
				}
			}else
				response.sendRedirect("login.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
