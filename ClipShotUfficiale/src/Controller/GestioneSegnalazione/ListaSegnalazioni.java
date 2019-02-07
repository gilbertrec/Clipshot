package Controller.GestioneSegnalazione;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.SegnalazioneDAO;
import Model.SegnalazioneBean;

@WebServlet("/ListaSegnalazioni")
public class ListaSegnalazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListaSegnalazioni() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			String username = (String) ssn.getAttribute("username");
			if(username != null) {
				SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
				ArrayList<SegnalazioneBean> segnalazioniBean = new ArrayList<>();
				try {
					segnalazioniBean = segnalazioneDAO.doRetrieveByCond("in_attesa");
					request.setAttribute("segnalazioni", segnalazioniBean);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (Exception e) { //nessuna segnalazione
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} else { // username == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}		
		} else { // ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
