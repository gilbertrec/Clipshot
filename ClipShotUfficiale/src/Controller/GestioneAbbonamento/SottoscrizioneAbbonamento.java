package Controller.GestioneAbbonamento;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
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
				
				//l'abbonamento quanto dura?
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, 0);
				cal.add(Calendar.MONTH, 1);
				cal.add(Calendar.DAY_OF_MONTH, 0);
				java.sql.Date dataScadenza = (Date) cal.getTime();
				abbonamento.setDataScadenza(dataScadenza);
				
				try {
					if(abbonamentoDAO.doRetrieveByCond(idUtente).getNumeroCarta() != null) {
						abbonamento.setNumeroCarta(abbonamentoDAO.doRetrieveByCond(idUtente).getNumeroCarta());
					}
					else {//carta not exist
						//implements with dispacher
						String numeroCarta = request.getParameter("numeroCartaAbbonamento");
						if(numeroCarta.length() == 16) {
							abbonamento.setNumeroCarta(numeroCarta);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String stato = request.getParameter("statoAbbonamento");
				if(stato.equals("attivo")) {
					abbonamento.setStato("1");
				}
				else {//sospeso
					abbonamento.setStato("2");
				}
			}//idUtente==null
			else {}
		}//ssn==null
		else {}
	}

}









