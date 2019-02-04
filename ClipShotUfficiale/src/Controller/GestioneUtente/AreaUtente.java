package Controller.GestioneUtente;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.UtenteDAO;
import Model.UtenteBean;

public class AreaUtente extends HttpServlet{
	private static final long serialVersionUID = 8772561898734173066L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			UtenteBean utente = new UtenteBean();
			UtenteDAO utenteDAO = new UtenteDAO();
			String idUtente = (String) ssn.getAttribute("idUtente");
			if(idUtente != null) {
				try {
					utente = utenteDAO.doRetrieveByKey(idUtente);
					request.setAttribute("utenteBean", utente);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);	
			} //idUtente == null
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		} // ssn == null
	}
}
