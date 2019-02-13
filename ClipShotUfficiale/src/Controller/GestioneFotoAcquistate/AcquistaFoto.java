/*
 * 
 @author Adalgiso Della Calce*/

package Controller.GestioneFotoAcquistate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.AcquistiDAO;
import Model.AcquistiBean;
import Model.UtenteBean;

@WebServlet("/AcquistaFoto")

public class AcquistaFoto extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		UtenteBean utenteBean;
		String idUtente, dataAcquisto, jspChiamante;
		int idFoto;
		GregorianCalendar data;
		AcquistiBean acquistiBean;
		AcquistiDAO acquistiDAO= new AcquistiDAO();
		Boolean result;
		
		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		System.out.println("utente: " +utenteBean);
		idFoto=Integer.parseInt(request.getParameter("idFotoAcquisto"));
		jspChiamante=request.getParameter("jspChiamante");
		System.out.println(jspChiamante);
		data= new GregorianCalendar();
		if (utenteBean!=null) {
			idUtente=utenteBean.getIdUtente();
			acquistiBean= new AcquistiBean();
			acquistiBean.setIdUtente(idUtente);
			acquistiBean.setIdFoto(idFoto);
			acquistiBean.setData(data);
			result=acquistiDAO.doSave(acquistiBean);
			if(result==false) {
				//errore acquisto
				System.out.println("errore acquisto");
				//effettuare il dispatcher
				request.setAttribute("erroreAcquisto", true);
			}
			else {
				System.out.println("acquisto effettuato");
				RequestDispatcher view=request.getRequestDispatcher(jspChiamante);
				request.setAttribute("acquistoBean", acquistiBean);
				view.forward(request, response);
			}
		}	
		else {
			System.out.println("dispatcher login");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Login");
			dispatcher.forward(request, response);
		}
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
}
