package Controller.GestioneCartadiCredito;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.CartaDiCreditoDAO;
import Model.CartaDiCreditoBean;

@WebServlet("/ModificaCartaDiCredito")
public class ModificaCartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificaCartaDiCredito() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			CartaDiCreditoBean carta = new CartaDiCreditoBean();
			CartaDiCreditoDAO cartaDAO = new CartaDiCreditoDAO();
			
			String numeroCarta = request.getParameter("numeroCartaCarta");
			if(numeroCarta.length() == 16) {
				carta.setNumeroCarta(numeroCarta);
			}
			
			String idUtente = (String) ssn.getAttribute("idUtente");
			carta.setIdUtente(idUtente);
			
			String intestatario = request.getParameter("intestatarioCarta");
			if(intestatario.matches("^[0-9A-Za-z\\.-]+$")) {
				carta.setIntestatario(intestatario);
			}
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String dataScadenza = request.getParameter("dataScadenzaCarta");
	        try {
				Date parsed = (Date) format.parse(dataScadenza);
				carta.setDataScadenza(parsed);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        
	        String cvv = request.getParameter("cvvCarta");
	        if(cvv.length() == 3) {
	        	carta.setCvv(cvv);
	        }
	        
	        try {
				cartaDAO.doSaveOrUpdate(carta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		}
	}

}
