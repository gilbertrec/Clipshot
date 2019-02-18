/*
 * 
 @author Adalgiso Della Calce*/
package Controller.GestioneInterazioni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import Manager.FotoDAO;
import Manager.PostDAO;
import Model.FotoBean;
import Model.PostBean;
import Model.UtenteBean;
@WebServlet("/GetFoto")
public class GetFoto extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html");
		UtenteBean utenteBean;
		HttpSession session;
		String idUtente;
		int idFoto;
		PostBean Bean;

		session=request.getSession();
		utenteBean=(UtenteBean) session.getAttribute("utente");
		idFoto=Integer.parseInt(request.getParameter("idFoto"));
		if (utenteBean!=null) {
			idUtente=utenteBean.getIdUtente();
			FotoDAO fotoDAO= new FotoDAO();
			try {
				FotoBean fotoBean=fotoDAO.doRetrieveByKey(idFoto);

				request.setAttribute("fotoBean", fotoBean);
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				System.out.println("prezzo:"+fotoBean.getPrezzo());
				out.write(String.valueOf(fotoBean.getPrezzo()));
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("SQLException");
				e.printStackTrace();
			}

		}
		else {
			System.out.println("Sessione null");
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}
}
