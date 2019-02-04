package Controller.GestionePost;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.FotoBeanDao;
import Manager.PostBeanDao;
import Manager.UtenteBeanDao;
import Model.FotoBean;
import Model.PostBean;
import Model.UtenteBean;

@WebServlet("/AggiungiPost")

public class AggiungiPost extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, descrizione, dataPost, oraPost, tipo, pathFoto, pathPost;
		int idPost, idFoto;
		Double prezzo;
		GregorianCalendar data, ora;
		
		session=request.getSession();
		tipo=(String) session.getAttribute("tipo");
		//campi foto;
		idFoto=Integer.parseInt(request.getParameter("idFotoFoto"));
		pathFoto=request.getParameter("pathFoto");
		FotoBean fotoBean= new FotoBean(idFoto, pathFoto);
		if (tipo.equals("ARTISTA")) {
			prezzo=Double.parseDouble(request.getParameter("prezzoFoto"));
			fotoBean.setPrezzo(prezzo);
		}
		FotoBeanDao fotoBeanDao= new FotoBeanDao();
		try {
			fotoBeanDao.doSave(fotoBean);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//fine
		
		
		//campi post
		idPost=Integer.parseInt(request.getParameter("idPostPost"));
		idUtente=(String) session.getAttribute("idUtente");
		descrizione=request.getParameter("descrizionePost");
		data=new GregorianCalendar();
		ora= new GregorianCalendar();
		if (idUtente!=null) {
			PostBean postBean= new PostBean(idPost, idUtente, idFoto, descrizione, data, ora, "FREE");
			PostBeanDao postBeanDao= new PostBeanDao();
			try {
				postBeanDao.doSave(postBean);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}
	
}
