package Controller.GestioneAbbonamento;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.AbbonamentoDAO;
import Model.AbbonamentoBean;

@WebServlet("/DisdettaAbbonamento")
public class DisdettaAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisdettaAbbonamento() {
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
				AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
				AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
				try {
					abbonamentoBean = abbonamentoDAO.doRetrieveByKey(idUtente);
				} catch (Exception e) { //nessun abbonamento
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				try {
					abbonamentoDAO.doDelete(abbonamentoBean.getIdUtente());
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (Exception e) { //problemi di eliminazione
					e.printStackTrace();
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
