/**
 * @author Adalgiso Della Calce
 */
package Controller.GestionePost;

import java.io.IOException;
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
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		HttpSession session;
		String idUtente;
		int idPost;
		
		session=request.getSession();
		UtenteBean utente_session=(UtenteBean) session.getAttribute("utente");
		idUtente=(String) utente_session.getIdUtente();
		idPost=Integer.parseInt(request.getParameter("idPost"));
		if (idUtente!=null) {
			PostBean postBean= new PostBean();
			postBean.setIdPost(idPost);
			postBean.setIdUtente(idUtente);
			PostDAO postDAO= new PostDAO();
			try {
				postDAO.doDelete(postBean);
				RequestDispatcher view=request.getRequestDispatcher("HomePage");//12:29 14/02
				view.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}
