/*
 * 
 @author Adalgiso Della Calce*/

package Controller.GestioneAutenticazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.UtenteDAO;
import Model.UtenteBean;

public class GetUtente extends HttpServlet{
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		UtenteBean utenteBean;
		String idUtente;
		HttpSession session;
		
		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		if (utenteBean!=null) {
			idUtente=utenteBean.getIdUtente();
			utenteBean= new UtenteBean();
			UtenteDAO utenteDAO= new UtenteDAO();
			try {
				utenteBean=utenteDAO.doRetrieveByKey(idUtente);
				//dispatcher
				RequestDispatcher dispatcher=request.getRequestDispatcher("");
				request.setAttribute("utenteBean", utenteBean);
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}
