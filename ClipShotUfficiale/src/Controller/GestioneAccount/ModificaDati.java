/**
 * @author Carmine Cristian Cruoglio
 */
package Controller.GestioneAccount;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import Manager.UtenteDAO;
import Model.UtenteBean;

@WebServlet("/ModificaDati")
public class ModificaDati extends HttpServlet{
	private static final long serialVersionUID = 5497876428877631316L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private static final String SAVE_DIR="C:\\Users\\stefano\\Documents\\ProgettoTSW\\Project\\WebContent\\img\\Prodotti";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			String idUtente = (String) ssn.getAttribute("idUtente");
			if(idUtente != null) {
				UtenteBean utenteBean = new UtenteBean();
				UtenteDAO utenteDAO = new UtenteDAO();
				try {
					utenteBean = utenteDAO.doRetrieveByKey(idUtente);	
				} catch (SQLException e) { //utente non trovato
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				UtenteBean temp = new UtenteBean();
				temp.setIdUtente(utenteBean.getIdUtente());
				String password = request.getParameter("passwordModifica");
				if(!password.equals("")) {
					temp.setPassword(password);
				} else {
					temp.setPassword(utenteBean.getPassword());
				}
				String email = request.getParameter("emailModifica");
				if(!email.equals("")) {
					temp.setEmail(email);
				} else {
					temp.setEmail(utenteBean.getEmail());
				}
				String nome = request.getParameter("nomeModifica");
				if(!nome.equals("")) {
					temp.setNome(nome);
				} else {
					temp.setNome(utenteBean.getNome());
				}
				String cognome = request.getParameter("cognomeModifica");
				if(!cognome.equals("")) {
					temp.setCognome(cognome);
				} else {
					temp.setCognome(utenteBean.getCognome());
				}
				String data = request.getParameter("dataScadenzaCarta");
				if(data != null) {
					String arrayDataNascita[] = data.split("-");
					String annoString = arrayDataNascita[0];
					String meseString = arrayDataNascita[1];
					String giornoString = arrayDataNascita[2];
					int anno = Integer.parseInt(annoString);
					int mese = Integer.parseInt(meseString);
					int giorno = Integer.parseInt(giornoString);
					GregorianCalendar dataNascita = new GregorianCalendar(anno, mese, giorno);
					temp.setDataNascita(dataNascita);
				} else { //data == null
					temp.setDataNascita(utenteBean.getDataNascita());
				}				
				String sesso = request.getParameter("sessoModifica");
				if(!sesso.equals("")) {
					temp.setSesso(sesso);
				} else {
					temp.setSesso(utenteBean.getSesso());
				}
				String indirizzo = request.getParameter("indirizzoModifica");
				if(!indirizzo.equals("")) {
					temp.setIndirizzo(indirizzo);
				} else {
					temp.setIndirizzo(utenteBean.getIndirizzo());
				}
				
				File uploads = new File(SAVE_DIR);
				
				String fotoProfilo = request.getParameter("fotoProfiloModifica");
				Part filePart = (Part) request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
				if(filePart != null) {
					
					String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					InputStream fileContent = filePart.getInputStream();
					
					File file = new File(uploads, fileName);
					Files.copy(fileContent, file.toPath());
					
					temp.setFotoProfilo(fileName);
				} else {
					temp.setFotoProfilo(utenteBean.getFotoProfilo());
				}
				
				temp.setStato(utenteBean.getStato());
				temp.setTipo(utenteBean.getTipo());
				try {//dispatch at user area
					utenteDAO.doSaveOrUpdate(temp);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
					requestDispatcher.forward(request, response);	
				} catch (SQLException e) {//errore nell'update
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
					requestDispatcher.forward(request, response);	
				}
			} else {//idUtente == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);	
			} 
		} else {// ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		} 
	}
}
