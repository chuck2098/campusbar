package imageUploader;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class FileUploader
 */
@WebServlet("/Upload")
public class ImageUploadHandler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int LIMIT_SIZE=400000;
	private static final String PATH= "/home/vincenzo/git/campusbar/campusbar/WebContent/images";
	
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) 
						  throws ServletException, IOException {
		
		String ajaxUpdateResult = "";
		String filename="";
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			for (FileItem item : items) {
				
				if (item.isFormField()) {
					//leggo il nome del file dal campo nascosto del form
					filename=item.getString();
				} else {
					File fi=new File(PATH+ File.separator + filename);
					
					if(item.getSize()>LIMIT_SIZE) {
						ajaxUpdateResult="Immagine troppo grande.(Max"+LIMIT_SIZE/1000+"Kb)";
						break;
					}
					if(fi.exists())
						fi.delete();
					
					item.write(new File(PATH + File.separator + filename));

					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					if(new File(PATH + File.separator + filename).exists())
						ajaxUpdateResult += "Immagine caricata!";
					else
						ajaxUpdateResult += "Percorso immagine non trovato!";
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Parsing file upload failed.", e);
		} catch (Exception e) {
			ajaxUpdateResult += "Errore caricamento immagine";
			e.printStackTrace();
		}

		response.getWriter().print(ajaxUpdateResult);
	}

}