/**
 * @author Adalgiso Della Calce
 */
package Controller.GestioneFotoAcquistate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.AcquistiDAO;
import Model.AcquistiBean;

@WebServlet("/AcquistaFoto")
public class AcquistaFoto extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String idUtente;
		int idFoto;
		GregorianCalendar data;
		AcquistiBean acquistiBean;
		AcquistiDAO acquistiDAO= new AcquistiDAO();
		idUtente=(String) request.getParameter("idUtente");
		idFoto=Integer.parseInt(request.getParameter("idFoto"));
		System.out.println("Acquisto:"+idFoto+"utente:"+idUtente);
		data= new GregorianCalendar();
		if (idUtente!=null) {
			acquistiBean= new AcquistiBean();
			acquistiBean.setIdUtente(idUtente);
			acquistiBean.setIdFoto(idFoto);
			acquistiBean.setData(data);
			acquistiDAO.doSave(acquistiBean);
			
		}	
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}
}
