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

import Manager.AbbonamentoDAO;
import Manager.CartaDiCreditoDAO;
import Manager.UtenteDAO;
import Model.AbbonamentoBean;
import Model.CartaDiCreditoBean;
import Model.UtenteBean;

/**
 * Servlet implementation class VisualizzaDatiCompleto
 */
@WebServlet("/VisualizzaDatiCompleto")
public class VisualizzaDatiCompleto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaDatiCompleto() {
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
			UtenteBean utenteSession =(UtenteBean) ssn.getAttribute("utente");
			System.out.println(utenteSession.toString());
			String idUtente = utenteSession.getIdUtente();
			if(idUtente != null) {
				UtenteBean utenteBean = new UtenteBean();
				UtenteDAO utenteDAO = new UtenteDAO();
				CartaDiCreditoBean cartaDiCreditoBean =new CartaDiCreditoBean();
				CartaDiCreditoDAO 	cartaDiCreditoDAO =new CartaDiCreditoDAO();
				try {
					utenteBean = utenteDAO.doRetrieveByKey(idUtente);
				}
				catch(Exception e) {
					RequestDispatcher requestDispatcher ;
					requestDispatcher = request.getRequestDispatcher("login.jsp"); 
					requestDispatcher.forward(request, response);	
				}
					
				try {
					cartaDiCreditoBean =cartaDiCreditoDAO.doRetrieveByCond(idUtente);
					
					cartaDiCreditoBean.setNumeroCarta(cartaDiCreditoBean.getNumeroCarta().substring(11,15));
					request.setAttribute("cartaDiCreditoBean", cartaDiCreditoBean);
					request.setAttribute("utenteBean", utenteBean);
					RequestDispatcher requestDispatcher ;
					requestDispatcher = request.getRequestDispatcher("account.jsp"); 
					requestDispatcher.forward(request, response);	
				} catch (Exception e) {
					request.setAttribute("utenteBean", utenteBean);
					cartaDiCreditoBean.setNumeroCarta(null);
					request.setAttribute("cartaDiCreditoBean", cartaDiCreditoBean);
					RequestDispatcher requestDispatcher ;
					requestDispatcher = request.getRequestDispatcher("account.jsp"); 
					requestDispatcher.forward(request, response);
				}
			} else {//idUtente == null
				request.setAttribute("errore", "true");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp"); 
				requestDispatcher.forward(request, response);	
			} 
		} else {// ssn == null
			request.setAttribute("errore", "true");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp"); 
			requestDispatcher.forward(request, response);	
		} 
	}
}


