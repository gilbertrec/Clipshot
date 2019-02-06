package Controller.GestioneInterazioni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.SeguiBeanDao;
import Model.SeguiBean;

@WebServlet("/AggiungiSegui")

public class AggiungiSegui extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idFollower, idFollowing;
		
		session=request.getSession();
		idFollower=(String) session.getAttribute("idUtente");
		idFollowing=request.getParameter("idFollowingSegui");
		if (idFollower!=null) {
			SeguiBean seguiBean= new SeguiBean(idFollower, idFollowing);
			SeguiBeanDao seguiBeanDao= new SeguiBeanDao();
			try {
				seguiBeanDao.doSave(seguiBean);
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