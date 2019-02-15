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

@WebServlet("/GetLike")

public class GetLike extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, idUtentePost;
		int idPost;
		UtenteBean utenteBean;
		LikeBean likeBean=null;
		
		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		System.out.println(utenteBean);
		idPost=Integer.parseInt(request.getParameter("idPostLike"));
		idUtentePost=request.getParameter("idUtentePostLike");
		if (utenteBean!=null) {
			idUtente=utenteBean.getIdUtente();
			LikeDAO likeDAO= new LikeDAO();
			try {
				likeBean=likeDAO.doRetrieveByKey(idUtente, idPost, idUtentePost);
				RequestDispatcher dispatcher=request.getRequestDispatcher("");
				request.setAttribute("likeBean", likeBean);
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}
