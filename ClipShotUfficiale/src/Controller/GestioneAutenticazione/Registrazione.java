/**
 * @author Adalgiso Della Calce
 */
package Controller.GestioneAutenticazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Manager.UtenteDAO;
import Model.UtenteBean;

@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		String idUtente, email, password, nome, cognome, sesso, tipo,indirizzo, fotoProfilo, dataNascitaString, annoData, meseData, giornoData;
		String arrayDataString[];
		int anno, mese, giorno;
		GregorianCalendar dataNascita;
		
		idUtente=request.getParameter("idUtenteUtente");
		email=request.getParameter("emailUtente");
		password=request.getParameter("passwordUtente");
		nome=request.getParameter("nomeUtente");
		cognome=request.getParameter("cognomeUtente");
		sesso=request.getParameter("sessoUtente");
		tipo=request.getParameter("tipoUtente");
		//prendo la data dalla request
		dataNascitaString=request.getParameter("dataNascitaUtente");
		//attraverso la funzione split mi prendo l'anno, il mese e il giorno
		arrayDataString=dataNascitaString.split("-");
		annoData=arrayDataString[0];
		meseData=arrayDataString[1];
		giornoData=arrayDataString[2];
		//converto anno, mese e giorno in interi
		anno=Integer.parseInt(annoData);
		mese=Integer.parseInt(meseData);
		mese=mese-1;
		giorno=Integer.parseInt(giornoData);
		//creo l'oggetto gregorian calendar
		dataNascita= new GregorianCalendar(anno, mese, giorno);
		UtenteBean utenteBean= new UtenteBean();
		utenteBean.setIdUtente(idUtente);
		utenteBean.setEmail(email);
		utenteBean.setPassword(password);
		utenteBean.setNome(nome);
		utenteBean.setCognome(cognome);
		utenteBean.setDataNascita(dataNascita);
		utenteBean.setSesso(sesso);
		utenteBean.setStato("FREE");
		utenteBean.setTipo(tipo);
		if(!request.getParameter("indirizzoUtente").equals("")) {
			indirizzo=request.getParameter("indirizzoUtente");
			utenteBean.setIndirizzo(indirizzo);
		}
		if (!request.getParameter("fotoProfiloutente").equals("")) {
			fotoProfilo=request.getParameter("fotoProfiloutente");
			utenteBean.setFotoProfilo(fotoProfilo);
		}
		UtenteDAO utenteDAO= new UtenteDAO();
		try {
			utenteDAO.doSave(utenteBean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//errore inserimento
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}

}
