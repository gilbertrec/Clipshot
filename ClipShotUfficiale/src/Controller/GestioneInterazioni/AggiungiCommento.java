package Controller.GestioneInterazioni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.CommentoBeanDao;
import Model.CommentoBean;

@WebServlet("/AggiungiCommento")

public class AggiungiCommento extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, idUtentePost, descrizione;
		int idPost;
		
		session=request.getSession();
		idUtente=(String) session.getAttribute("idUtente");
		idPost=Integer.parseInt(request.getParameter("idPostCommento"));
		idUtentePost=request.getParameter("idUtentePostCommento");
		descrizione=request.getParameter("descrizioneCommento");
		GregorianCalendar data= new GregorianCalendar();
		GregorianCalendar ora= new GregorianCalendar();
		if (idUtente!=null) {
			CommentoBean commentoBean= new CommentoBean(idUtente, idPost, idUtentePost, data, ora, descrizione);
			CommentoBeanDao commentoBeanDao= new CommentoBeanDao();
			try {
				commentoBeanDao.doSave(commentoBean);
				//effettuare il dispatcher alla jsp inserendo nella request idUtente, descrizione, data ed ora
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}

}
