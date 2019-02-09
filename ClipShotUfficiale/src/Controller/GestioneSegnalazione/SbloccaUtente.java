package Controller.GestioneSegnalazione;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.SegnalazioneDAO;
import Manager.UtenteDAO;
import Model.SegnalazioneBean;
import Model.UtenteBean;

@WebServlet("/SbloccaUtente")
public class SbloccaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SbloccaUtente() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			String username = (String) ssn.getAttribute("username");
			if(username != null) {
				SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
				SegnalazioneBean segnalazioneBean = new SegnalazioneBean();
				int idSegnalazione = Integer.parseInt(request.getParameter("segnalazioneInfo"));
				String idUtente = (String) request.getParameter("idUtenteInfo");
				try {
					segnalazioneBean = segnalazioneDAO.doRetrieveByKey(idSegnalazione, idUtente);
				} catch (Exception e) { // segnalazione not exist
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				UtenteDAO utenteDAO = new UtenteDAO();
				UtenteBean utenteBean = new UtenteBean();
				try {
					utenteBean = utenteDAO.doRetrieveByKey(segnalazioneBean.getIdUtentePost());
				} catch (SQLException e) { //utente proprietario del post segnalato - not exist
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				utenteBean.setStato("FREE");
				try {
					utenteDAO.doSaveOrUpdate(utenteBean);
				} catch (SQLException e) { // update utente fallito
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}//non serve settare nessuno stato a segnalazione perchè risultava già completata prima di arrivare a questo punto
			} else { // username == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}		
		} else { // ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
