package Controller.GestioneOperatore;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.OperatoreDAO;
import Model.OperatoreBean;

@WebServlet("/CreaOperatore")
public class CreaOperatore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreaOperatore() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			String username = (String) ssn.getAttribute("username");
			if(username != null) {
				OperatoreBean operatoreBean = new OperatoreBean();
				OperatoreBean temp = new OperatoreBean();
				OperatoreDAO operatoreDAO = new OperatoreDAO();
				
				String user = (String) request.getAttribute("usernameOperatore");
				try {
					temp = operatoreDAO.doRetrieveByKey(user);
					if(!username.equals(temp.getUsername())) {
						operatoreBean.setUsername(user);
					} else { //username operatore già esistente
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
				} catch (Exception e) { //nessun operatore corrispondente a quella username
					e.printStackTrace();
					operatoreBean.setUsername(user);
					request.setAttribute("user", operatoreBean.getUsername());
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); // si continua con la compilazione del form 
					requestDispatcher.forward(request, response);
				}
				String password = (String) request.getAttribute("passwordOperatore");
				if(password.length() > 7) {
					operatoreBean.setPassword(password);
				} else { // error password
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				String nome = (String) request.getAttribute("nomeOperatore");
				if(!nome.equals("")) {
					operatoreBean.setNome(nome);
				} else { //error name
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				String cognome = (String) request.getAttribute("cognomeOperatore");
				if(!cognome.equals("")) {
					operatoreBean.setCognome(cognome);
				} else { //error cognome
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				String email = (String) request.getAttribute("emailOperatore");
				if(email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
					operatoreBean.setEmail(email);
				} else { //error email
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				operatoreBean.setTipo("2"); // OPERATORE 
				try {
					operatoreDAO.doSave(operatoreBean);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (Exception e) { //problemi di creazione
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} else { // username == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}		
		} else { // ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
