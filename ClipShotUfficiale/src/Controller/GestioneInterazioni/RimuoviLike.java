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

import Manager.LikeDAO;
import Model.LikeBean;
import Model.UtenteBean;

@WebServlet("/RimuoviLike")

public class RimuoviLike extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String idUtente, idUtentePost, jspChiamante;
		int idPost;
		boolean result;
		
		
		idUtente=(String) request.getAttribute("idUtente");
		idPost=(int) request.getAttribute("idPost");
		idUtentePost=(String) request.getAttribute("idUtentePost");
		LikeBean likeBean= new LikeBean();
		jspChiamante=(String) request.getAttribute("jspChiamante");
		likeBean.setIdUtente(idUtente);
		likeBean.setIdPost(idPost);
		likeBean.setIdUtentePost(idUtentePost);
		LikeDAO likeDAO= new LikeDAO();
		result=likeDAO.doDelete(likeBean);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}

}
