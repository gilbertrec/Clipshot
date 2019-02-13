/*
 * 
 @author Adalgiso Della Calce*/
package Controller.GestionePost;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.PostDAO;
import Model.PostBean;
import Model.UtenteBean;

@WebServlet("/RimuoviPost")
public class RimuoviPost extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		UtenteBean utenteBean;
		String idUtente;
		int idPost;
		boolean result;
		
		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		idPost=Integer.parseInt(request.getParameter("idPostPost"));
		if (utenteBean!=null) {
			idUtente=utenteBean.getIdUtente();
			PostBean postBean= new PostBean();
			postBean.setIdPost(idPost);
			postBean.setIdUtente(idUtente);
			PostDAO postDAO= new PostDAO();
			result=postDAO.doDelete(postBean);
			if (result==true) {
				/*
				Ritornare alla jsp chiamante
				RequestDispatcher dispatcher=request.getRequestDispatcher("");
				dispatcher.forward(request, response);*/
			}
			else {
				/*RequestDispatcher dispatcher=request.getRequestDispatcher("");
				request.setAttribute("errorElimPost", true);
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
