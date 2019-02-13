/*
 * 
 @author Adalgiso Della Calce*/
package Controller.GestioneInterazioni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.PostDAO;
import Model.PostBean;
import Model.UtenteBean;

public class GetFoto extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		UtenteBean utenteBean;
		HttpSession session;
		String idUtente;
		int idPost;
		PostBean postBean;
		
		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		idPost=Integer.parseInt(request.getParameter("idPost"));
		if (utenteBean!=null) {
			idUtente=utenteBean.getIdUtente();
			PostDAO postDAO= new PostDAO();
			try {
				postBean=postDAO.doRetrieveByKey(idPost, idUtente);
				RequestDispatcher dispatcher=request.getRequestDispatcher("");
				request.setAttribute("postBean", postBean);
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
