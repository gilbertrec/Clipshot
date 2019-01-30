package Controller.GestioneCartadiCredito;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.CartaDiCreditoDAO;
import Model.CartaDiCreditoBean;

@WebServlet("/AggiungiCartaDiCredito")
public class AggiungiCartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AggiungiCartaDiCredito() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			CartaDiCreditoBean carta = new CartaDiCreditoBean();
			String numeroCarta = request.getParameter("numeroCartaCarta");
			if(numeroCarta.length() == 16) {
				carta.setNumeroCarta(numeroCarta);
			}
			
			if(ssn.getAttribute("idUtente") != null) {
				String idUtente = (String) ssn.getAttribute("idUtente");
				carta.setIdUtente(idUtente);
			}
			
			String intestatario = request.getParameter("intestatarioCarta");
			if(intestatario.matches("^[0-9A-Za-z\\.-]+$")) {
				carta.setIntestatario(intestatario);
			}
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dataScadenza =  request.getParameter("dataScadenzaCarta");
			try {
				java.util.Date fromDate = format.parse(dataScadenza);
				java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime()); 
				carta.setDataScadenza(sqlDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}  
			
	        String cvv = request.getParameter("cvvCarta");
	        if(cvv.length() == 3) {
	        	carta.setCvv(cvv);
	        }
	        
	        CartaDiCreditoDAO cartaDAO = new CartaDiCreditoDAO();
	        try {
				cartaDAO.doSave(carta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			
		}
	}
}

