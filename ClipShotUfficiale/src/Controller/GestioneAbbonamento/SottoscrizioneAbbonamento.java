package Controller.GestioneAbbonamento;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.AbbonamentoDAO;
import Model.AbbonamentoBean;

@WebServlet("/SottoscrizioneAbbonamento")
public class SottoscrizioneAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SottoscrizioneAbbonamento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			AbbonamentoBean abbonamento = new AbbonamentoBean();
			AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
			
			String idUtente = (String) ssn.getAttribute("idUtente");
			if(idUtente != null) {
				abbonamento.setIdUtente(idUtente);
				
				//l'abbonamento dura un mese
				GregorianCalendar dataScadenza = new GregorianCalendar();
				dataScadenza.add(Calendar.MONTH, 1);
				abbonamento.setDataScadenza(dataScadenza);
				
				try {
					if(abbonamentoDAO.doRetrieveByCond(idUtente).getNumeroCarta() != null) {
						abbonamento.setNumeroCarta(abbonamentoDAO.doRetrieveByCond(idUtente).getNumeroCarta());
					}
					else {//carta not exist
						//implements with dispacher at addCarta
						request.setAttribute("from", "from");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
						
						String numeroCarta = request.getParameter("numeroCartaAbbonamento");
						if(numeroCarta.length() == 16) {
							abbonamento.setNumeroCarta(numeroCarta);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				abbonamento.setStato("1"); //attivo
				
				try {
					abbonamentoDAO.doSave(abbonamento);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//idUtente==null
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
		}//ssn==null
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}

}









