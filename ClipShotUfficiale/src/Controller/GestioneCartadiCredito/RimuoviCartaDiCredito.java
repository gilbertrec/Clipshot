package Controller.GestioneCartadiCredito;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manager.CartaDiCreditoDAO;

@WebServlet("/RimuoviCartaDiCredito")
public class RimuoviCartaDiCredito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RimuoviCartaDiCredito() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CartaDiCreditoDAO cartaDAO = new CartaDiCreditoDAO();
		String numeroCarta = request.getParameter("numeroCartaCarta");
		try {
			cartaDAO.doDelete(numeroCarta);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
