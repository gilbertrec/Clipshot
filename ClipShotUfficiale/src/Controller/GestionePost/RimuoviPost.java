package Controller.GestionePost;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.PostDAO;
import Model.PostBean;

@WebServlet("/RimuoviPost")
public class RimuoviPost extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idUtente;
		int idPost;
		
		session=request.getSession();
		idUtente=(String) session.getAttribute("idUtente");
		idPost=Integer.parseInt(request.getParameter("idPostPost"));
		if (idUtente!=null) {
			PostBean postBean= new PostBean();
			postBean.setIdPost(idPost);
			postBean.setIdUtente(idUtente);
			PostDAO postDAO= new PostDAO();
			try {
				postDAO.doDelete(postBean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}

}
