/**
 * @author Adalgiso Della Calce
 */
package Controller.GestioneInterazioni;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.CommentoDAO;
import Model.CommentoBean;

@WebServlet("/AggiungiCommento")
public class AggiungiCommento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, idUtentePost, descrizione;
		int idPost;
		
		session=request.getSession();
		idUtente=(String) session.getAttribute("idUtente");
		idPost=Integer.parseInt(request.getParameter("idPostCommento"));
		idUtentePost=request.getParameter("idUtentePostCommento");
		descrizione=request.getParameter("descrizioneCommento");
		GregorianCalendar data= new GregorianCalendar();
		GregorianCalendar ora= new GregorianCalendar();
		if (idUtente!=null) {
			CommentoBean commentoBean= new CommentoBean();
			commentoBean.setIdUtente(idUtente);
			commentoBean.setIdPost(idPost);
			commentoBean.setIdUtentePost(idUtentePost);
			commentoBean.setData(data);
			commentoBean.setOra(ora);
			commentoBean.setDescrizione(descrizione);
			CommentoDAO commentoDAO= new CommentoDAO();
			try {
				commentoDAO.doSave(commentoBean);
				//effettuare il dispatcher alla jsp inserendo nella request idUtente, descrizione, data ed ora
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
