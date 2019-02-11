/**
 * @author Carmine Cristian Cruoglio
 */
package Controller.GestioneOperatore;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.OperatoreDAO;
import Model.OperatoreBean;

@WebServlet("/infoOperatore")
public class InfoOperatore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InfoOperatore() {
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
				OperatoreBean operatoreBean = new OperatoreBean();
				String user = (String) request.getAttribute("usernameInfo");
				try {
					operatoreBean = operatoreDAO.doRetrieveByKey(user);
					request.setAttribute("operatore", operatoreBean);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (Exception e) { //operatore not exist
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
