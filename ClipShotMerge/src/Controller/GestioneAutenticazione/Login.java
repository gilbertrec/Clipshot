/**
 * @author Adalgiso Della Calce
 */
package Controller.GestioneAutenticazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.UtenteDAO;
import Model.UtenteBean;

@WebServlet("/Login")
public class Login extends HttpServlet{ 
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		String idUtente=null, password=null;
		UtenteDAO utenteDAO;
		ArrayList<UtenteBean> listaUtenti;
		UtenteBean utenteBean;
		HttpSession session;
		Cookie cookie[];
		int i;
		
		
		//leggo le credenziali dai cookie
		cookie=request.getCookies();
		if (cookie!=null) {
			for (i=0; i<cookie.length; i++) {
				if (cookie[i].getName().equals("idUtente"))
					idUtente=cookie[i].getValue();
				else {
					if (cookie[i].getName().equals("password"))
						password=cookie[i].getValue();
				}
			}
			if ((idUtente!=null)&&(password!=null)) {
				 session= request.getSession();
				 session.setMaxInactiveInterval(60*60);
				 
				 
				 utenteDAO= new UtenteDAO();
				 try {
					utenteBean=utenteDAO.doRetrieveByKey(idUtente);
					UtenteBean u_session=new UtenteBean();
					u_session.setIdUtente(utenteBean.getIdUtente());
					u_session.setTipo(utenteBean.getTipo());
					u_session.setNome(utenteBean.getNome());
					u_session.setCognome(utenteBean.getCognome());
					session.setAttribute("utente", u_session);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				RequestDispatcher view=request.getRequestDispatcher("HomePage");
				view.forward(request, response);
				System.out.println("login attraverso i cookie");
			}
		}
		
		
		
		//non sono presenti all'interno dei cookie prova dalla request
		if ((idUtente==null)&&(password==null)) {
			//modficare con username
			idUtente=request.getParameter("idUtente");
			password=request.getParameter("passwordUtente");
			if ((idUtente!=null)&&(password!=null)) {
				System.out.println("request");
				utenteDAO= new UtenteDAO();
				utenteBean= new UtenteBean();
				utenteBean.setIdUtente(idUtente);
				utenteBean.setPassword(password);
				try {
					listaUtenti=utenteDAO.doRetrieveByCond(utenteBean);
					//login effettuato
					if (listaUtenti.size()==1) {
						utenteBean=listaUtenti.get(0);
						session=request.getSession();//creo la sessione
						UtenteBean u_session=new UtenteBean();
						u_session.setIdUtente(utenteBean.getIdUtente());
						u_session.setTipo(utenteBean.getTipo());
						u_session.setNome(utenteBean.getNome());
						u_session.setCognome(utenteBean.getCognome());
						System.out.println("Login:"+utenteBean.getIdUtente());
						session.setAttribute("utente", u_session);
						//creo i cookie e li inserisco all'interno della response
						Cookie c= new Cookie("idUtente", idUtente);
						c.setMaxAge(2000);
						Cookie c2= new Cookie("password", password);
						c2.setMaxAge(2000);
						response.addCookie(c);
						response.addCookie(c2);
						System.out.println("login effettuato");
						RequestDispatcher view=request.getRequestDispatcher("HomePage");
						view.forward(request, response);
					}
					else {
						RequestDispatcher view=request.getRequestDispatcher("login.jsp");
						view.forward(request, response);
						System.out.println("errore login");
					}
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//effettuare il dispatcher alla pagina di login
			else {
				System.out.println("dispatcher");
				RequestDispatcher view=request.getRequestDispatcher("login.jsp");
				view.forward(request, response);
			}
			
		}
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}
