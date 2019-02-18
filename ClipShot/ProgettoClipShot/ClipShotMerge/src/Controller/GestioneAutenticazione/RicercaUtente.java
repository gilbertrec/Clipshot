package Controller.GestioneAutenticazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manager.UtenteDAO;
import Model.UtenteBean;

@WebServlet ("/RicercaUtente")

public class RicercaUtente extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String campoRicerca;
		ArrayList<UtenteBean> lista;
		campoRicerca=request.getParameter("campoRicerca");
		UtenteDAO utenteDAO= new UtenteDAO();
		try {
			lista=utenteDAO.doRetrieveByKeyOrNomeOrCognome(campoRicerca);
			for(int i=0; i<lista.size();i++) {
				lista.get(i).setDataNascita(new GregorianCalendar());
				lista.get(i).setEmail("");
				lista.get(i).setPassword("");
				lista.get(i).setIndirizzo("");
			}
			System.out.println(lista.size());
			request.setAttribute("utente_list", lista);
			RequestDispatcher view=request.getRequestDispatcher("ricercaUtente.jsp");//12:29 14/02
			view.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RequestDispatcher view=request.getRequestDispatcher("HomePage");//12:29 14/02
			view.forward(request, response);
			e.printStackTrace();
		}
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}
