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
import Model.UtenteBean;
/**
 * Servlet implementation class VisualizzaAbbonamento
 */
@WebServlet("/VisualizzaAbbonamento")
public class VisualizzaAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaAbbonamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			UtenteBean utenteBean = (UtenteBean) ssn.getAttribute("utente");
			String idUtente =utenteBean.getIdUtente();
			if(idUtente != null) {
				AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
				AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
				try {
					abbonamentoBean = abbonamentoDAO.doRetrieveByKey(idUtente);
					request.setAttribute("abbonamento",abbonamentoBean);
					System.out.println("AbbonamentoYes");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("datiAbbonamento.jsp"); 
					requestDispatcher.forward(request, response);
					
				} catch (Exception e1) { //nessun abbonamento
					System.out.println("NoAbbonamento");
					abbonamentoBean=null;
					request.setAttribute("abbonamento",abbonamentoBean);
					e1.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("datiAbbonamento.jsp"); 
					requestDispatcher.forward(request, response);
				}
			} else { //idUtente == null
				System.out.println("AbbonamentoNoUtente");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp"); 
				requestDispatcher.forward(request, response);
			}
		} else { //ssn == null
			System.out.println("AbbonamentoNoSessione");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp"); 
			requestDispatcher.forward(request, response);
		}
	}
}


