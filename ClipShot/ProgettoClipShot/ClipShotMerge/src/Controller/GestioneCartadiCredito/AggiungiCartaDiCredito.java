/**
 * @author Carmine Cristian Cruoglio
 */
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
import Manager.CartaDiCreditoDAO;
import Model.CartaDiCreditoBean;
import Model.UtenteBean;

@WebServlet("/AggiungiCartaDiCredito")
public class AggiungiCartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AggiungiCartaDiCredito() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {			
			UtenteBean utente=(UtenteBean)ssn.getAttribute("utente");
			String idUtente = utente.getIdUtente();
			if(idUtente != null) {
				CartaDiCreditoDAO cartaDiCreditoDAO = new CartaDiCreditoDAO();
				try {
					cartaDiCreditoDAO.doRetrieveByCond(idUtente);
					System.out.println("il catch non lo fa vuoi vede?");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (Exception e1) {//carta dell'utente non trovata
					e1.printStackTrace();
					CartaDiCreditoBean cartaDiCreditoBean = new CartaDiCreditoBean();
					cartaDiCreditoBean.setIdUtente(idUtente);
					String numeroCarta = request.getParameter("numeroCartaCarta");
					if(numeroCarta.length() == 16) {
						cartaDiCreditoBean.setNumeroCarta(numeroCarta);
					} else {//numeroCarta != 16
						System.out.println("carta non so 16 caratteri");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
					String intestatario = request.getParameter("intestatarioCarta");
					if(!intestatario.equals("")) {
						cartaDiCreditoBean.setIntestatario(intestatario);
					} else { // intestatario == null
						System.out.println("intestatario � null");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
					String data = request.getParameter("dataScadenzaCarta");
					System.out.println("data:"+data);
					System.out.println("NumeroCarta"+numeroCarta.length());
					System.out.println("Intestario"+intestatario+"\nCVV:"+request.getParameter("cvvCarta"));
					if(data != null) {
						String arrayDataScadenza[] = data.split("-");
						String annoString = arrayDataScadenza[0];
						String meseString = arrayDataScadenza[1];
						String giornoString = "01";//arrayDataScadenza[2];
						int anno = Integer.parseInt(annoString);
						int mese = Integer.parseInt(meseString)-1;
						int giorno = Integer.parseInt(giornoString);
						GregorianCalendar dataScadenza = new GregorianCalendar(anno, mese, giorno);
						cartaDiCreditoBean.setDataScadenza(dataScadenza);
					} else { //data == null
						System.out.println("data � null");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
			        String cvv = request.getParameter("cvvCarta");
			        if(cvv.length() == 3) {
			        	cartaDiCreditoBean.setCvv(cvv);
			        } else { // cvv != 3
			        	request.setAttribute("errore_cvv", "true");
			        	System.out.println("lunghezza sbagliata");
			        	RequestDispatcher requestDispatcher = request.getRequestDispatcher("aggiungiCarta.jsp"); 
						requestDispatcher.forward(request, response);
			        } //cvv == null
			        try {
						cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
						request.setAttribute("cartaDiCreditoBean", cartaDiCreditoBean);
						String from = request.getParameter("from"); //<input type="hidden" value="from"> viene visualizzato se l'attributo from viene inviato da sottoscrizione abbonamento ad aggiungi carta 
						if(from != null) { //riporta al form di sottoscrizione abbonamento
							System.out.println("ho trovato from");
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						} else { // visualizza dati carta
							System.out.println("tutto apposto");
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("VisualizzaDatiCompleto"); 
							requestDispatcher.forward(request, response);	
						}		
					} catch (Exception e) { //errore creazione carta
						e.printStackTrace();
						System.out.println("non riesco a creare la carta :(");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
				}
			} else {// idUtente == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);	
			} 
		} else {// ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		} 
	}
}
