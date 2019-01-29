package Controller.GestioneCartadiCredito;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manager.CartaDiCreditoDAO;
import Model.CartaDiCreditoBean;

/**
 * Servlet implementation class CartaDiCredito
 */
@WebServlet("/CartaDiCredito.html")
public class CartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartaDiCredito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CartaDiCreditoDAO cartDAO = new CartaDiCreditoDAO();
		CartaDiCreditoBean carta = new CartaDiCreditoBean();
		carta.setIdUtente("porcodio");
		carta.setIntestatario("mariamaddalena");
		carta.setNumeroCarta(1);
		Date data = new Date(2019-01-11);
		carta.setDataScadenza(data);
		carta.setCvv("123");
		try {
			cartDAO.doSave(carta);
			System.out.println(cartDAO.doRetriveByKey(123450123));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
