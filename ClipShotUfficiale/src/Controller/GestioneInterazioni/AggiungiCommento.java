/*
 * 
 @author Adalgiso Della Calce*/

package Controller.GestioneInterazioni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.CommentoDAO;
import Model.CommentoBean;
import Model.UtenteBean;

@WebServlet("/AggiungiCommento")

public class AggiungiCommento extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, idUtentePost, descrizione;
		UtenteBean utenteBean=null;
		int idPost;
		boolean result;
		
		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		idPost=Integer.parseInt(request.getParameter("idPostCommento"));
		idUtentePost=request.getParameter("idUtentePostCommento");
		descrizione=request.getParameter("descrizioneCommento");
		GregorianCalendar data= new GregorianCalendar();
		GregorianCalendar ora= new GregorianCalendar();
		if (utenteBean!=null) {
			idUtente=utenteBean.getIdUtente();
			CommentoBean commentoBean= new CommentoBean();
			commentoBean.setIdUtente(idUtente);
			commentoBean.setIdPost(idPost);
			commentoBean.setIdUtentePost(idUtentePost);
			commentoBean.setData(data);
			commentoBean.setOra(ora);
			commentoBean.setDescrizione(descrizione);
			CommentoDAO commentoDAO= new CommentoDAO();
			result=commentoDAO.doSave(commentoBean);
			//effettuare il dispatcher alla jsp inserendo nella request idUtente, descrizione, data ed ora
			if (result==false) {
				System.out.println("errore inserimento commento");
				/*RequestDispatcher dispatcher=request.getRequestDispatcher("");
				request.setAttribute("errore", true);
				dispatcher.forward(request, response);*/
			}
			else {
				System.out.println("commento inserito");
				request.setAttribute("commentoBean", commentoBean);
				//effettuare il dispatche
			}
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Login");
			dispatcher.forward(request, response);
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}

}
