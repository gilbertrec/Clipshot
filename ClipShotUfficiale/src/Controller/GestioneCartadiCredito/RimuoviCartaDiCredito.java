package Controller.GestioneCartadiCredito;

import java.io.IOException;
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

@WebServlet("/RimuoviCartaDiCredito")
public class RimuoviCartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RimuoviCartaDiCredito() {
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
				CartaDiCreditoDAO cartaDAO = new CartaDiCreditoDAO();
				CartaDiCreditoBean cartaBean = new CartaDiCreditoBean();
				try {
					cartaBean = cartaDAO.doRetrieveByCond(idUtente);
				} catch (Exception e) { //nessuna carta
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
					try {
						cartaDAO.doDelete(cartaBean.getNumeroCarta());
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					} catch (Exception e1) { //problemi di eliminazione
						e1.printStackTrace();
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
				}
		        if(abbonamentoBean != null) { //se l'abbonamento esiste..
		        	if(abbonamentoBean.getStato().equals("SOSPESO")) {
		        		try { //elimino prima la carta
							cartaDAO.doDelete(cartaBean.getNumeroCarta());
						} catch (Exception e) { //problemi di eliminazione carta
							e.printStackTrace();
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						}
		        		try {//poi l'abbonamento
							abbonamentoDAO.doDelete(idUtente);
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						} catch (Exception e) { //problemi di eliminazione abbonamento
							e.printStackTrace();
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						}
			        } else { //ATTIVO - dispatch a sospendi abbonamento prima di eseguire questa operazione
			        	RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
			        }
		        } else { //sei un utente base
		        	try {
						cartaDAO.doDelete(cartaBean.getNumeroCarta());
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					} catch (Exception e) { //problemi di eliminazione
						e.printStackTrace();
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
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
