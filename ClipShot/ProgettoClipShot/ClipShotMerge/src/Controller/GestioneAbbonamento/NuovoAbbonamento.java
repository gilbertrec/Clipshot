package Controller.GestioneAbbonamento;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class NuovoAbbonamento
 * questa servlet preleva i dati informativi relativi alla carta di credito 
 * e all'utente per mostrarli all'utente prima di sottoscriversi all'abbonamento
 * @author Gilbert Recupito
 */
@WebServlet("/NuovoAbbonamento")
public class NuovoAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovoAbbonamento() {
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
		HttpSession ssn = request.getSession();
		if(ssn != null) {		
			UtenteBean utenteSession=(UtenteBean)ssn.getAttribute("utente");
			UtenteDAO ud=new UtenteDAO();
			UtenteBean utenteBean;
			CartaDiCreditoDAO cd=new CartaDiCreditoDAO();
			CartaDiCreditoBean cartaBean;
			if(utenteSession != null) {
				try {
					utenteBean=ud.doRetrieveByKey(utenteSession.getIdUtente());
					utenteBean.setPassword("");
					cartaBean=cd.doRetrieveByCond(utenteBean.getIdUtente());
					request.setAttribute("utente",utenteBean);
					request.setAttribute("numeroCarta",cartaBean.getNumeroCarta());
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("sottoscriviAbbonamento.jsp"); 
					requestDispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		}

	}
}
