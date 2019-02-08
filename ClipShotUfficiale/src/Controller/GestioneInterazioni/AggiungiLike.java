package Controller.GestioneInterazioni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.LikeDAO;
import Model.LikeBean;

@WebServlet("/AggiungiLike")

public class AggiungiLike extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, idUtentePost;
		int idPost;
		
		session=request.getSession();
		idUtente=(String) session.getAttribute("idUtente");
		idPost=Integer.parseInt(request.getParameter("idPostLike"));
		idUtentePost=request.getParameter("idUtentePostLike");
		if (idUtente!=null) {
			LikeBean likeBean= new LikeBean();
			likeBean.setIdUtente(idUtente);
			likeBean.setIdPost(idPost);
			likeBean.setIdUtentePost(idUtentePost);
			LikeDAO likeDAO= new LikeDAO();
			try {
				likeDAO.doSave(likeBean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}

}
