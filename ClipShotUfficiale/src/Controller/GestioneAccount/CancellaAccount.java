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
import Manager.UtenteDAO;
import Model.UtenteBean;

@WebServlet("/CancellaAccount")
public class CancellaAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CancellaAccount() {
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
				if(utenteDAO.doDelete(utenteBean)) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} else { //errore di cancellazione
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
