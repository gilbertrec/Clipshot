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

@WebServlet("/RimuoviCommento")
public class RimuoviCommento extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, idUtentePost, dataCommento, oraCommento, annoData, meseData, giornoData, oraString, minutiString, secondiString;
		int idPost;
		UtenteBean utenteBean;
		String arrayDataString[];
		String arrayOraString[];
		int anno, mese, giorno, ore, minuti, secondi;
		GregorianCalendar data;
		GregorianCalendar ora;
		boolean result;
		
		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		idPost=Integer.parseInt(request.getParameter("idPostCommento"));
		idUtentePost=request.getParameter("idUtentePostCommento");
		oraCommento=request.getParameter("oraCommento");
		dataCommento=request.getParameter("dataCommento");
		arrayDataString=dataCommento.split("-");
		annoData=arrayDataString[0];
		meseData=arrayDataString[1];
		giornoData=arrayDataString[2];
		anno=Integer.parseInt(annoData);
		mese=Integer.parseInt(meseData);
		mese=mese-1;
		giorno=Integer.parseInt(giornoData);
		data= new GregorianCalendar(anno, mese, giorno);
		arrayOraString=oraCommento.split(":");
		oraString=arrayOraString[0];
		minutiString=arrayOraString[1];
		secondiString=arrayOraString[2];
		ore=Integer.parseInt(oraString);
		minuti=Integer.parseInt(minutiString);
		secondi=Integer.parseInt(secondiString);
		ora=new GregorianCalendar(anno, mese, giorno, ore, minuti, secondi);
		if (utenteBean!=null) {
			idUtente=utenteBean.getIdUtente();
			CommentoBean commentoBean= new CommentoBean();
			commentoBean.setIdUtente(idUtente);
			commentoBean.setIdPost(idPost);
			commentoBean.setIdUtentePost(idUtentePost);
			commentoBean.setData(data);
			commentoBean.setOra(ora);
			CommentoDAO commentoDAO= new CommentoDAO();
			result=commentoDAO.doDelete(commentoBean);
			if (result==true) {
				/*
				Ritornare alla jsp chiamante
				RequestDispatcher dispatcher=request.getRequestDispatcher("");
				dispatcher.forward(request, response);*/
			} 
			else {
				/*RequestDispatcher dispatcher=request.getRequestDispatcher("");
				request.setAttribute("errorElimCommento", true);
				dispatcher.forward(request, response);*/
			}
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Login");
			dispatcher.forward(request, response);
		}
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}
