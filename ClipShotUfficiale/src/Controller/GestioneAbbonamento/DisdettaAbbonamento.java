package Controller.GestioneAbbonamento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisdettaAbbonamento")
public class DisdettaAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisdettaAbbonamento() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//java.sql.Date today = (Date) cal.getTime();
	}

}
