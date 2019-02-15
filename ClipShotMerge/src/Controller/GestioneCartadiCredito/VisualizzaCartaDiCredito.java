package Controller.GestioneCartadiCredito;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.CartaDiCreditoDAO;
import Manager.UtenteDAO;
import Model.CartaDiCreditoBean;
import Model.UtenteBean;

/**
 * Servlet implementation class VisualizzaCartaDiCredito
 */
@WebServlet("/VisualizzaCartaDiCredito")
public class VisualizzaCartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaCartaDiCredito() {
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
			System.out.println("utente:"+utenteSession.toString());
			String idUtente = utenteSession.getIdUtente();
			if(idUtente != null) {
				
				CartaDiCreditoBean cartaDiCreditoBean =new CartaDiCreditoBean();
				CartaDiCreditoDAO 	cartaDiCreditoDAO =new CartaDiCreditoDAO();
				try {
					cartaDiCreditoBean =cartaDiCreditoDAO.doRetrieveByCond(idUtente);
					System.out.println("ServletVisualizza:carta:"+cartaDiCreditoBean.getNumeroCarta());
					cartaDiCreditoBean.setNumeroCarta(cartaDiCreditoBean.getNumeroCarta().substring(11,15));
					request.setAttribute("cartaDiCreditoBean", cartaDiCreditoBean);
					RequestDispatcher requestDispatcher ;
					requestDispatcher = request.getRequestDispatcher("modificaCarta.jsp"); 
					
					requestDispatcher.forward(request, response);	
				} catch (Exception e) {
					System.out.println("Exception");
					cartaDiCreditoBean.setNumeroCarta(null);
					request.setAttribute("cartaDiCreditoBean", cartaDiCreditoBean);
					RequestDispatcher requestDispatcher ;
					requestDispatcher = request.getRequestDispatcher("account.jsp"); 
					requestDispatcher.forward(request, response);
				}
			} else {//idUtente == null
				System.out.println("L'utente è null");
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


