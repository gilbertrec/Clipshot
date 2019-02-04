package Controller.GestioneUtente;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.UtenteDAO;
import Model.UtenteBean;

public class ModificaUtente extends HttpServlet{
	private static final long serialVersionUID = 5497876428877631316L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			UtenteBean utente = new UtenteBean();
			UtenteDAO utenteDAO = new UtenteDAO();
			String idUtente = (String) ssn.getAttribute("idUtente");
			
			if(idUtente != null) {
				
				try {
					utente = utenteDAO.doRetrieveByKey(idUtente);	
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				UtenteBean temp = new UtenteBean();
				temp.setIdUtente(utente.getIdUtente());
				
				String password = request.getParameter("passwordModifica");
				if(!password.equals("")) {
					temp.setPassword(password);
				}
				else {
					temp.setPassword(utente.getPassword());
				}
				String email = request.getParameter("emailModifica");
				if(!email.equals("")) {
					temp.setEmail(email);
				}
				else {
					temp.setEmail(utente.getEmail());
				}
				String nome = request.getParameter("nomeModifica");
				if(!nome.equals("")) {
					temp.setNome(nome);
				}
				else {
					temp.setNome(utente.getNome());
				}
				String cognome = request.getParameter("cognomeModifica");
				if(!cognome.equals("")) {
					temp.setCognome(cognome);
				}
				else {
					temp.setCognome(utente.getCognome());
				}
				String dataNascita = request.getParameter("dataNascitaModifica");
				// scopri come si lavora 
				
				String sesso = request.getParameter("sessoModifica");
				if(!sesso.equals("")) {
					temp.setSesso(sesso);
				}
				else {
					temp.setSesso(utente.getSesso());
				}
				String indirizzo = request.getParameter("indirizzoModifica");
				if(!indirizzo.equals("")) {
					temp.setIndirizzo(indirizzo);
				}
				else {
					temp.setIndirizzo(utente.getIndirizzo());
				}
				String fotoProfilo = request.getParameter("fotoProfiloModifica");
				//codice di emilio che me lo deve dare stefano
				
				temp.setStato(utente.getStato());
				temp.setTipo(utente.getTipo());
				
				try {
					utenteDAO.doSaveOrUpdate(temp);
					//dispatch at user area
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
					requestDispatcher.forward(request, response);	
				} catch (SQLException e) {
					// error
					e.printStackTrace();
				}
				
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);	
			} //idUtente == null
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		} // ssn == null
	}
}
