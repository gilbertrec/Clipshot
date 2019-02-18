/**
 * @author Carmine Cristian Cruoglio
 */
package Controller.GestioneStatistiche;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.AbbonamentoDAO;
import Manager.FotoDAO;
import Manager.StatisticheDAO;
import Model.AbbonamentoBean;
import Model.FotoBean;
import Model.StatisticheBean;

@WebServlet("/StatisticheVisualizzazioni")
public class StatisticheVisualizzazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public StatisticheVisualizzazioni() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
		AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
		AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
		//prendo dalla request l'id del profilo visitato
		String idUtenteVisitato = (String) request.getAttribute("idUtenteVisitato");
		if(idUtenteVisitato != null) {
			try {//controllo se è pro
				abbonamentoBean = abbonamentoDAO.doRetrieveByKey(idUtenteVisitato);
			} catch (Exception e1) { //nessun abbonamento
				e1.printStackTrace();
				request.setAttribute("idUtenteVisitato", null); //così non entro più qui dentro perchè l'utente visitato non è un PRO
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
			StatisticheBean statisticheBean = new StatisticheBean();
			StatisticheDAO statisticheDAO = new StatisticheDAO();
			try {
				ArrayList<StatisticheBean> bean = statisticheDAO.doRetrieveByCond(abbonamentoBean.getIdUtente());
			} catch (Exception e) { //nessuna statistica
				
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
			statisticheBean.setNumeroVisualizzazioni(statisticheBean.getNumeroVisualizzazioni() + 1);
			try {
				statisticheDAO.doSaveOrUpdate(statisticheBean);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
		}
		
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
				} catch (Exception e1) { //abbonamento non presente
					e1.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				if(abbonamentoBean.getStato().equals("ATTIVO") && abbonamentoBean.isScaduto(abbonamentoBean.getDataScadenza())) {
					StatisticheDAO statisticheDAO = new StatisticheDAO();
					StatisticheBean statisticheBean = new StatisticheBean();
					ArrayList<StatisticheBean> statistiche_list=new ArrayList<StatisticheBean>();
					try {
						statistiche_list = statisticheDAO.doRetrieveByCond(idUtente);
						request.setAttribute("StatisticheVisualizzazioni", statistiche_list);	
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("statistiche.jsp"); 
						requestDispatcher.forward(request, response);
					} catch (Exception e) { //nessuna statistica
						e.printStackTrace();
						request.setAttribute("nostatistiche",true);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("statistiche.jsp"); 
						requestDispatcher.forward(request, response);
					}
					ArrayList<FotoBean> listaFotoBean = new ArrayList<>();
					FotoDAO fotoDAO = new FotoDAO();
					try {
						listaFotoBean = fotoDAO.doRetrieveByCondFoto(idUtente);
						request.setAttribute("listaFoto", listaFotoBean);
					} catch (Exception e) { //nessuna foto
						e.printStackTrace();
						request.setAttribute("listaFoto", null);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}//tutto va a buon fine disp
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} else { // abbonamento SOSPESO opp. SCADUTO dispatch ad AttivaAbbonamento
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
