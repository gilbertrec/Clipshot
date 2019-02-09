package Controller.GestioneAccount;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.AcquistiDAO;
import Manager.UtenteDAO;
import Model.AcquistiBean;
import Model.UtenteBean;

@WebServlet("/VisualizzaAcquisti")
public class VisualizzaAcquisti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VisualizzaAcquisti() {
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
				AcquistiDAO acquistiDAO = new AcquistiDAO();
				ArrayList<AcquistiBean> acquistiUtente = new ArrayList<>();
				try {
					acquistiUtente = acquistiDAO.doRetrieveByCond(utenteBean.getIdUtente());
				} catch (SQLException e) { //nessun acquisto 
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);	
				}
				request.setAttribute("acquisti", acquistiUtente);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);	
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
