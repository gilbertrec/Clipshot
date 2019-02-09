package Controller.GestioneInterazioni;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.SeguiDAO;
import Model.SeguiBean;

@WebServlet("/AggiungiSegui")
public class AggiungiSegui extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		HttpSession session;
		String idFollower, idFollowing;
		
		session=request.getSession();
		idFollower=(String) session.getAttribute("idUtente");
		idFollowing=request.getParameter("idFollowingSegui");
		if (idFollower!=null) {
			SeguiBean seguiBean= new SeguiBean();
			seguiBean.setIdFollower(idFollower);
			seguiBean.setIdFollowing(idFollowing);
			SeguiDAO seguiDAO= new SeguiDAO();
			try {
				seguiDAO.doSave(seguiBean);
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
