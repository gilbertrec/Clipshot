/*
 * 
 @author Adalgiso Della Calce*/
package Controller.GestioneInterazioni;

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

import Manager.SeguiDAO;
import Model.SeguiBean;
import Model.UtenteBean;

@WebServlet("/GetSegui")

public class GetSegui extends HttpServlet{
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		UtenteBean utenteBean;
		String idFollower, idFollowing;
		SeguiBean seguiBean;
		
		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		System.out.println(utenteBean);
		idFollowing=request.getParameter("idFollowingSegui");
		if (utenteBean!=null) {
			idFollower=utenteBean.getIdUtente();
			SeguiDAO seguiDAO= new SeguiDAO();
			try {
				seguiBean=seguiDAO.doRetrieveByKey(idFollower, idFollowing);
				RequestDispatcher dispatcher=request.getRequestDispatcher("");
				request.setAttribute("seguiBean", seguiBean);
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Login");
			dispatcher.forward(request, response);
		}
	}
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}
