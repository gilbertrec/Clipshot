package Controller.GestioneOperatore;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.OperatoreDAO;
import Model.OperatoreBean;

@WebServlet("/ListaOperatori")
public class ListaOperatori extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListaOperatori() {
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
				OperatoreDAO operatoreDAO = new OperatoreDAO();
				ArrayList<OperatoreBean> operatoriBean = new ArrayList<>();
				try {
					operatoriBean = operatoreDAO.doRetrieveAll();
					request.setAttribute("operatori", operatoriBean);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (Exception e) { // nessun operatore presente
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} else { //username == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
		} else { //ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
