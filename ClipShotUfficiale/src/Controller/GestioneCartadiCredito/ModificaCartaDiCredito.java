package Controller.GestioneCartadiCredito;

import java.io.IOException;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.AbbonamentoDAO;
import Manager.CartaDiCreditoDAO;
import Model.AbbonamentoBean;
import Model.CartaDiCreditoBean;

@WebServlet("/ModificaCartaDiCredito")
public class ModificaCartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificaCartaDiCredito() {
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
				CartaDiCreditoBean cartaDiCreditoBean = new CartaDiCreditoBean();
				CartaDiCreditoDAO cartaDiCreditoDAO = new CartaDiCreditoDAO();
				cartaDiCreditoBean.setIdUtente(idUtente);
				String numeroCarta = request.getParameter("numeroCartaCarta");
				if(numeroCarta.length() == 16) {
					cartaDiCreditoBean.setNumeroCarta(numeroCarta);
				} else { // numeroCarta.length() != 16
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				String intestatario = request.getParameter("intestatarioCarta");
				if(!intestatario.equals("")) {
					cartaDiCreditoBean.setIntestatario(intestatario);
				} else { //intestatario == ""
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				String data = request.getParameter("dataScadenzaCarta");
				if(data != null) {
					String arrayDataScadenza[] = data.split("-");
					String annoString = arrayDataScadenza[0];
					String meseString = arrayDataScadenza[1];
					String giornoString = arrayDataScadenza[2];
					int anno = Integer.parseInt(annoString);
					int mese = Integer.parseInt(meseString);
					int giorno = Integer.parseInt(giornoString);
					GregorianCalendar dataScadenza = new GregorianCalendar(anno, mese, giorno);
					cartaDiCreditoBean.setDataScadenza(dataScadenza);
				} else { //data == null
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				String cvv = request.getParameter("cvvCarta");
		        if(cvv.length() == 3) {
		        	cartaDiCreditoBean.setCvv(cvv);
		        } else { //cvv != 3
		        	RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
		        }
		        try {
					cartaDiCreditoDAO.doSaveOrUpdate(cartaDiCreditoBean);
				} catch (Exception e) { //update fallito
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
		        AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
		        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
		        try {
					abbonamentoBean = abbonamentoDAO.doRetrieveByKey(idUtente);
				} catch (Exception e) { //dispatch per l'utente base, nessun abbonamento
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
		        if(abbonamentoBean != null) { //se l'abbonamento esiste..
		        	abbonamentoBean.setNumeroCarta(cartaDiCreditoBean.getNumeroCarta());
		        	try {
						abbonamentoDAO.doSaveOrUpdate(abbonamentoBean);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					} catch (Exception e) {//update fallito
						e.printStackTrace();
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
		        }
			} else { // idUtente == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
		} else { // ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		}
	}
}
