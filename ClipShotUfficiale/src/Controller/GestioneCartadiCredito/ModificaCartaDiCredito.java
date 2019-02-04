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
			
			String idUtente = (String) ssn.getAttribute("idUtente");
			if(idUtente != null) {
				carta.setIdUtente(idUtente);
				
				String numeroCarta = request.getParameter("numeroCartaCarta");
				if(numeroCarta.length() == 16) {
					carta.setNumeroCarta(numeroCarta);
				} else { // numeroCarta == null
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				
				String intestatario = request.getParameter("intestatarioCarta");
				if(intestatario.matches("^[0-9A-Za-z\\.-]+$")) {
					carta.setIntestatario(intestatario);
				} else { //intestatario == null
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				
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
		        } else { //cvv == null
		        	RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
		        }
		        
		        try {
					cartaDAO.doSaveOrUpdate(carta);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { // idUtente == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
		} else { // ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);	
		}
	}

}
