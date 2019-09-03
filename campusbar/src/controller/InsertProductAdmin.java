package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaDAO;
import model.DisponibilitaDAO;
import model.Edificio;
import model.EdificioDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class InsertProductAdmin
 */
@WebServlet("/InsertProduct")
public class InsertProductAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome=request.getParameter("nome");
		String descr=request.getParameter("descr");
		float prezz=Float.parseFloat(request.getParameter("prezzo"));
		int cat=Integer.parseInt(request.getParameter("cate"));
		
		Prodotto p=new Prodotto();
		p.setNome(nome);
		p.setDescrizione(descr);
		p.setPrezzo(prezz);
		p.setCategoria(new CategoriaDAO().doRetrieveById(cat));
		
		PrintWriter out=response.getWriter();
		int id_prod=new ProdottoDAO().doSave(p);
		
		if(id_prod!=0) {
			//setto al prodotto l'id appena inserito
			p.setId(id_prod);
			//bisogna inserire la disponibilita impostata a 0 per ogni edificio.
			ArrayList<Edificio> edifici=new EdificioDAO().doRetrieveAll();
			for(Edificio ed:edifici) {
				new DisponibilitaDAO().doUploadAvailabilityGiveProduct(ed,p);
			}
			
			out.println("Prodotto aggiunto!");
		}else
			out.println("Impossibile aggiungere prodotto!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
