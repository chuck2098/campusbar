package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineDAO;

/**
 * Servlet implementation class DeliveryOrder
 */
@WebServlet("/DeliveryOrder")
public class DeliveryOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineDAO ord=new OrdineDAO();
		boolean res=ord.doDeliveryOrderById(Integer.parseInt(request.getParameter("id")));
		if(res)
			response.getWriter().println("Ordine consegnato");
		else
			response.getWriter().println("Errore consegna ordine");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
