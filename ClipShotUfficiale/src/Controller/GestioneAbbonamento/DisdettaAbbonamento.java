/**
 * @author Carmine Cristian Cruoglio
 */
package Controller.GestioneAbbonamento;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.AbbonamentoDAO;
import Manager.StatisticheDAO;
import Manager.UtenteDAO;
import Model.AbbonamentoBean;
import Model.StatisticheBean;
import Model.UtenteBean;

@WebServlet("/DisdettaAbbonamento")
public class DisdettaAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisdettaAbbonamento() {
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
				AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
				AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
				try {
					abbonamentoBean = abbonamentoDAO.doRetrieveByKey(idUtente);
				} catch (Exception e) { //nessun abbonamento
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				try {
					abbonamentoDAO.doDelete(abbonamentoBean.getIdUtente());
				} catch (Exception e) { //problemi di eliminazione
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				//elimino le statistiche
				StatisticheBean statisticheBean = new StatisticheBean();
				StatisticheDAO statisticheDAO = new StatisticheDAO();
				try {
					statisticheDAO.doDelete(statisticheBean);
				} catch (Exception e) { //errore cancellazione statistiche
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				UtenteBean utenteBean = new UtenteBean();
				UtenteDAO utenteDAO = new UtenteDAO();
				try {
					utenteBean = utenteDAO.doRetrieveByKey(idUtente);
				} catch (SQLException e) { //utente non trovato
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				utenteBean.setStato("BASE");
				try { 
					utenteDAO.doSaveOrUpdate(utenteBean); //anche utente aggiornato, abbonamento cancellato con successo
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (SQLException e) { //utente non salvato
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} else { //idUtente == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
		} else { //ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
