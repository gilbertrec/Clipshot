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

/**
 * Servlet implementation class AggiungiCartaDiCredito
 */
@WebServlet("/AggiungiCartaDiCredito")
public class AggiungiCartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiCartaDiCredito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ssn = request.getSession();
		synchronized (ssn)
		{
			CartaDiCreditoBean carta = new CartaDiCreditoBean();
			String numeroCarta = request.getParameter("numeroCarta");
			if(numeroCarta.length() == 16) {
				carta.setNumeroCarta(numeroCarta);
			}
			
			String idUtente = (String) ssn.getAttribute("idUtente");
			carta.setIdUtente(idUtente);
			
			String intestatario = request.getParameter("intestatario");
			if(intestatario.matches("^[0-9A-Za-z\\.-]+$")) {
				carta.setIntestatario(intestatario);
			}
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dataScadenza =  request.getParameter("dataScadenza");
			
			try {
				java.util.Date fromDate = format.parse(dataScadenza);
				java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime()); 
				carta.setDataScadenza(sqlDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			
	        String cvv = request.getParameter("cvv");
	        if(cvv.length() == 3) {
	        	carta.setCvv(cvv);
	        }
	        
	        CartaDiCreditoDAO cartaDAO = new CartaDiCreditoDAO();
	        try {
				cartaDAO.doSave(carta);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

