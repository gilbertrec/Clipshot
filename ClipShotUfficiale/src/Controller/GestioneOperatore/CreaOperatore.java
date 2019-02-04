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
			OperatoreBean operatoreBean = new OperatoreBean();
			OperatoreDAO operatoreDAO = new OperatoreDAO();
			
			String username = (String) request.getAttribute("usernameOperatore");
			try {
				OperatoreBean temp = operatoreDAO.doRetrieveByKey(username);
				if(!username.equals(temp.getUsername())) {
					operatoreBean.setUsername(username);
					ssn.setAttribute("username", username);
				} else { //username operatore già esistente
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} catch (Exception e) { //la doRetrieve non ha ritornato nessun operatore corrispondente a quella username
				e.printStackTrace();
				operatoreBean.setUsername(username);
				ssn.setAttribute("username", username);
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
			String tipo = (String) request.getAttribute("tipoOperatore");
			if(tipo.equals("AMMINISTRATORE")) {
				operatoreBean.setTipo("1");
			} else {// OPERATORE
				operatoreBean.setTipo("2");
			}
			
			try {
				operatoreDAO.doSave(operatoreBean);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			} catch (Exception e) { //problemi di creazione
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
			
		} else { // ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
