package controller;

/*import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
//import helper.MailUtility;

@WebServlet("/MailServlet")
public class MailServlet extends javax.servlet.http.HttpServlet
    implements javax.servlet.Servlet
{
  
	private static final long serialVersionUID = 1L;

public MailServlet(){ super(); }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    String dest = request.getParameter("mittente");
    String testo = request.getParameter("contenuto");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try
    {
      MailUtility.sendMail(dest, testo);
      out.println("Invio messaggio OK!");
    }
    catch (MessagingException exc)
    {
      out.println("Invio non riuscito!");
      log("MessagingException: " + dest);
      log(exc.toString());
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    this.doPost(request, response);
  }
}*/