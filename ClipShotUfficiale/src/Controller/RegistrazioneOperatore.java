package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manager.OperatoreDAO;
import Model.OperatoreBean;

/**
 * Servlet implementation class RegistrazioneOperatore
 */
@WebServlet(name ="RegistrazioneOperatore", urlPatterns = {"/operatore/RegistrazioneOperatore"})
public class RegistrazioneOperatore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	OperatoreDAO model=null;
    public RegistrazioneOperatore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		model = new OperatoreDAO();
		OperatoreBean o=new OperatoreBean();
		String tipo= ((String) request.getParameter("tipo"));
		if(tipo.equalsIgnoreCase("Amministratore")) {
			o.setTipo("Amministratore");
		}else if(tipo.equalsIgnoreCase("Operatore")) {
			o.setTipo("Operatore");
		}
		
		o.setNome(request.getParameter("nome"));
		o.setCognome(request.getParameter("cognome"));
		o.setPassword(request.getParameter("password"));
		o.setUsername(request.getParameter("username"));
		o.setMail(request.getParameter("email"));
		try {
			model.doSave(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
