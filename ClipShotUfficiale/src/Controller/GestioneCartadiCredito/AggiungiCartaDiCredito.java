package Controller.GestioneCartadiCredito;

import java.io.IOException;
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
			
			String idUtente = (String) ssn.getAttribute("idUtente");
			if(idUtente != null) {
				carta.setIdUtente(idUtente);
				
				String numeroCarta = request.getParameter("numeroCartaCarta");
				if(numeroCarta.length() == 16) {
					carta.setNumeroCarta(numeroCarta);
				} 
				else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}//numeroCarta == null
				
				String intestatario = request.getParameter("intestatarioCarta");
				if(intestatario.matches("^[0-9A-Za-z\\.-]+$")) {
					carta.setIntestatario(intestatario);
				}
				else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}// intestatario == null
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String temp =  request.getParameter("dataScadenzaCarta");
				try {
					java.util.Date fromDate = format.parse(temp);
					java.sql.Date dataScadenza = new java.sql.Date(fromDate.getTime()); 
					carta.setDataScadenza(dataScadenza);
				} catch (ParseException e1) { //dataScadenza == null
					e1.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} 
				
		        String cvv = request.getParameter("cvvCarta");
		        if(cvv.length() == 3) {
		        	carta.setCvv(cvv);
		        } else { // cvv == null
		        	RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
		        } //cvv == null
		        
		        CartaDiCreditoDAO cartaDAO = new CartaDiCreditoDAO();
		        try {
					cartaDAO.doSave(carta);
					
					String from = request.getParameter("from"); //<input type="hidden" value="from"> proveniente da addCarta di sottoscrizione abbonamento
					if(from != null) { //riporta al form di sottoscrizione abbonamento
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
					else { // visualizza dati carta					
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);	
					}
										
				} catch (Exception e) {
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			}	
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);	
			} // idUtente == null
		}		
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		} // ssn == null
	}
}

