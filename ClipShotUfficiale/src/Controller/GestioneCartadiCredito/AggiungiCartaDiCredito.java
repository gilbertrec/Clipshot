package Controller.GestioneCartadiCredito;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		CartaDiCreditoBean carta = new CartaDiCreditoBean();
		String numeroCarta = request.getParameter("numeroCarta");
		if(Integer.parseInt(numeroCarta) > 15) {
			carta.setNumeroCarta(Integer.parseInt(numeroCarta));
		}
		String intestatario = request.getParameter("intestatario");
		if(intestatario.matches("^[0-9A-Za-z\\.-]+$")) {
			carta.setIntestatario(intestatario);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dataScadenza = request.getParameter("dataScadenza");
        try {
			Date parsed = (Date) format.parse(dataScadenza);
			carta.setDataScadenza(parsed);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
