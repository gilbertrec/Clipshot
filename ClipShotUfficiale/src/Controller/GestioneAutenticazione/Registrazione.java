package Controller.GestioneAutenticazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manager.UtenteBeanDao;
import Model.UtenteBean;

@WebServlet("/Registrazione")

public class Registrazione extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String idUtente, email, password, nome, cognome, data, sesso, tipo,indirizzo, fotoProfilo, stato;
		GregorianCalendar dataNascita;
		
		idUtente=request.getParameter("idUtenteUtente");
		email=request.getParameter("emailUtente");
		password=request.getParameter("passwordUtente");
		nome=request.getParameter("nomeUtente");
		cognome=request.getParameter("cognomeUtente");
		sesso=request.getParameter("sessoUtente");
		tipo=request.getParameter("tipoUtente");
		dataNascita= new GregorianCalendar();
			
		UtenteBean utenteBean=new UtenteBean(idUtente, email, password, nome, cognome, dataNascita, sesso, "FREE", tipo);
		if(!request.getParameter("indirizzoUtente").equals("")) {
			indirizzo=request.getParameter("indirizzoUtente");
			utenteBean.setIndirizzo(indirizzo);
		}
		if (!request.getParameter("fotoProfiloutente").equals("")) {
			fotoProfilo=request.getParameter("fotoProfiloutente");
			utenteBean.setFotoProfilo(fotoProfilo);
		}
		UtenteBeanDao utenteBeanDao= new UtenteBeanDao();
		try {
			utenteBeanDao.doSave(utenteBean);
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
