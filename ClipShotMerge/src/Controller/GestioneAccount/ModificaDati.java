/**
 * @author Carmine Cristian Cruoglio
 */
package Controller.GestioneAccount;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Manager.UtenteDAO;
import Model.UtenteBean;

@WebServlet("/ModificaDati")
@MultipartConfig
public class ModificaDati extends HttpServlet{
	private static final long serialVersionUID = 5497876428877631316L;
	private static final String SAVE_DIR = "C:\\Users\\Prova\\Documents\\GitHub\\clipshot\\ClipShotMerge\\WebContent\\profile_photo\\";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			UtenteBean u_session =(UtenteBean) ssn.getAttribute("utente");
			String idUtente=u_session.getIdUtente();
			String pathFoto;
			if(idUtente != null) {
				UtenteBean utenteBean = new UtenteBean();
				UtenteDAO utenteDAO = new UtenteDAO();
				try {
					utenteBean = utenteDAO.doRetrieveByKey(idUtente);	
				} catch (SQLException e) { //utente non trovato
					e.printStackTrace();
					System.out.println("utente dove sei?");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				catch(Exception e){
					System.out.println("utente dove sei?Exception");
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
				String data = request.getParameter("dataNascitaModifica");
				System.out.println("Data:"+data);
				if(data==null) {
					System.out.println("data is null");
				}
				if(data.equals("")) {
					System.out.println("data is empty");
				}
				if(data != null &&(!data.equals(""))) {
					String arrayDataNascita[] = data.split("-");
					String annoString = arrayDataNascita[0];
					String meseString = arrayDataNascita[1];
					String giornoString = arrayDataNascita[2];
					int anno = Integer.parseInt(annoString);
					int mese = Integer.parseInt(meseString)-1;
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
				
				Part filePart = request.getPart("fotoProfiloModifica");
				if(filePart!=null) {
					if(!filePart.equals("")) {
						
						
				        String fileName = filePart.getSubmittedFileName(); 
				        InputStream is = filePart.getInputStream();
				        pathFoto=fileName;
				        Files.copy(is, Paths.get(SAVE_DIR + fileName),
				                StandardCopyOption.REPLACE_EXISTING);//carica l'immagine
						String fotoProfilo = request.getParameter("fotoProfiloModifica");
						temp.setFotoProfilo(pathFoto);
					} else {
					
					temp.setFotoProfilo(utenteBean.getFotoProfilo());
					}
				}
				else {
					temp.setFotoProfilo(utenteBean.getFotoProfilo());
				}
				
				temp.setStato(utenteBean.getStato());
				temp.setTipo(utenteBean.getTipo());
				try {//dispatch at user area
					utenteDAO.doSaveOrUpdate(temp);
					System.out.println("dati modificati");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("VisualizzaDati");
					requestDispatcher.forward(request, response);	
				} catch (SQLException e) {//errore nell'update
					e.printStackTrace();
					System.out.println("errore update");
					request.setAttribute("errore_update","true");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("VisualizzaDati");
					requestDispatcher.forward(request, response);	
				}
			} else {//idUtente == null
				request.setAttribute("errore_sessione","true");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("VisualizzaDati"); 
				requestDispatcher.forward(request, response);	
			} 
		} else {// ssn == null
			request.setAttribute("errore_sessione","true");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("VisualizzaDati"); 
			requestDispatcher.forward(request, response);	
		} 
	}
}
