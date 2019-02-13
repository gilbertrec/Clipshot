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

@WebServlet("/AggiungiSegui")

public class AggiungiSegui extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String idFollower, idFollowing, jspChiamante;
		
		idFollower=(String) request.getAttribute("idFollower");
		idFollowing=(String) request.getAttribute("idFollowing");
		SeguiBean seguiBean= new SeguiBean();
		seguiBean.setIdFollower(idFollower);
		seguiBean.setIdFollowing(idFollowing);
		SeguiDAO seguiDAO= new SeguiDAO();
		seguiDAO.doSave(seguiBean);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}

}
