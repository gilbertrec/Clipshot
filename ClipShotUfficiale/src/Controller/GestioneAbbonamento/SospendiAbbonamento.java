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

@WebServlet("/SospendiAbbonamento")
public class SospendiAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SospendiAbbonamento() {
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
				AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
				AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
				try {
					abbonamentoBean = abbonamentoDAO.doRetrieveByKey(idUtente);
				} catch (Exception e1) { //nessun abbonamento
					e1.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				if(abbonamentoBean.getStato().equals("ATTIVO")) {
					abbonamentoBean.setStato("SOSPESO");
					try {
						abbonamentoDAO.doSaveOrUpdate(abbonamentoBean);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					} catch (Exception e) {//salvataggio non riuscito
						e.printStackTrace();
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
				} else { //sospeso
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
