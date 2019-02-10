/**
 * @author Carmine Cristian Cruoglio
 */
package Controller.GestioneAccount;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.AcquistiDAO;
import Manager.FotoDAO;
import Manager.UtenteDAO;
import Model.AcquistiBean;
import Model.FotoBean;
import Model.UtenteBean;

@WebServlet("/DownloadFotoAcquistate")
public class DownloadFotoAcquistate extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public DownloadFotoAcquistate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			String idUtente = (String) ssn.getAttribute("idUtente");
			if(idUtente != null) {
				UtenteBean utenteBean = new UtenteBean();
				UtenteDAO utenteDAO = new UtenteDAO();
				try {
					utenteBean = utenteDAO.doRetrieveByKey(idUtente);
				} catch (SQLException e) { //utente non trovato
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);	
				}
				AcquistiDAO acquistiDAO = new AcquistiDAO();
				AcquistiBean acquistiBean = new AcquistiBean();
				int idFoto = Integer.parseInt(request.getParameter("idFotoVisualizza"));
				try {
					acquistiBean = acquistiDAO.doRetrieveByKey(utenteBean.getIdUtente(), idFoto);
				} catch (SQLException e) { //acquisto non trovato
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				FotoBean fotoBean = new FotoBean();
				FotoDAO fotoDAO = new FotoDAO();
				try {
					fotoBean = fotoDAO.doRetrieveByCond(acquistiBean.getIdUtente(), acquistiBean.getIdFoto());
					request.setAttribute("pathFoto", fotoBean.getPath());
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (SQLException e) { //nessun path 
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} else {//idUtente == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);	
			} 
		} else {// ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		} 
	}
}
