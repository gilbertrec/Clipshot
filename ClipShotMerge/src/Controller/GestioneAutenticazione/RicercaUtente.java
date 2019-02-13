package Controller.GestioneAutenticazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manager.UtenteDAO;
import Model.UtenteBean;

@WebServlet ("/RicercaUtente")

public class RicercaUtente extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String campoRicerca;
		ArrayList<UtenteBean> lista;
		campoRicerca=request.getParameter("campoRicerca");
		UtenteDAO utenteDAO= new UtenteDAO();
		try {
			lista=utenteDAO.doRetrieveByKeyOrNomeOrCognome(campoRicerca);
			System.out.println(lista.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}

}
