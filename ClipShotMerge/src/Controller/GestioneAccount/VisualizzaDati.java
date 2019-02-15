/**
 * @author Carmine Cristian Cruoglio
 */
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

import Manager.PostDAO;
import Manager.UtenteDAO;
import Model.PostBean;
import Model.UtenteBean;

@WebServlet("/VisualizzaDati")
public class VisualizzaDati extends HttpServlet{
	private static final long serialVersionUID = 8772561898734173066L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			UtenteBean utenteSession =(UtenteBean) ssn.getAttribute("utente");
			String idUtente = utenteSession.getIdUtente();
			if(idUtente != null) {
				UtenteBean utenteBean = new UtenteBean();
				UtenteDAO utenteDAO = new UtenteDAO();
				try {
					utenteBean = utenteDAO.doRetrieveByKey(idUtente);
					request.setAttribute("utenteBean", utenteBean);
					RequestDispatcher requestDispatcher ;
					if(request.getParameter("mode")!=null) {
						if(request.getParameter("mode").equals("edit")) {
							requestDispatcher = request.getRequestDispatcher("modificaUtente.jsp"); 
						}
						else {
							requestDispatcher = request.getRequestDispatcher("profiloUtente.jsp"); 
						}
						
					}
					else {
						ArrayList<PostBean> listaPost;
						idUtente=utenteSession.getIdUtente();
						if (idUtente!=null) {
							PostDAO postDAO= new PostDAO();
							try {
								listaPost=postDAO.doRetrievePostByIdUtente(idUtente);
								request.setAttribute("post_list", listaPost);
							} catch (SQLException e) {
								System.out.println("eccezione");
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						requestDispatcher = request.getRequestDispatcher("profiloUtente.jsp"); 
						
					}
					requestDispatcher.forward(request, response);
				} catch (SQLException e) { //utente non trovato
					e.printStackTrace();
					request.setAttribute("errore", "true");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp"); 
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
