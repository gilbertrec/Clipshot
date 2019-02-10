/**
 * @author Carmine Cristian Cruoglio
 */
package Controller.GestioneOperatore;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.OperatoreDAO;
import Model.OperatoreBean;

@WebServlet("/RimuoviOperatore")
public class RimuoviOperatore extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RimuoviOperatore() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			String username = (String) ssn.getAttribute("username");
			if(username != null) { //username di chi opera per eliminare l'altro operatore
				OperatoreDAO operatoreDAO = new OperatoreDAO();
				OperatoreBean operatoreBean = new OperatoreBean();
				try {
					operatoreBean = operatoreDAO.doRetrieveByKey(username);
				} catch (Exception e1) { //non esiste nessun operatore con questa username
					e1.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				if(operatoreBean.getTipo().equals("AMMINISTRATORE")) {
					String deleteUsername = (String) request.getAttribute("usernameRimuovi");
					OperatoreBean temp = new OperatoreBean();
					try {
						temp = operatoreDAO.doRetrieveByKey(deleteUsername);
					} catch (Exception e1) { //non esiste nessun operatore
						e1.printStackTrace();
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
					if(!temp.getTipo().equals("AMMINISTRATORE")) {
						try {
							operatoreDAO.doDelete(deleteUsername);
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						} catch (Exception e) { //delete non andata a buon fine
							e.printStackTrace();
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						}
					} else { //gli admin non possono essere eliminati
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
				} else { // è un operatore e non può fare questa operazione
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} else { //username == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
		} else { //ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
