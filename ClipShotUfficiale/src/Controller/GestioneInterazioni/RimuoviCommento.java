package Controller.GestioneInterazioni;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.CommentoDAO;
import Model.CommentoBean;

@WebServlet("/RimuoviCommento")
public class RimuoviCommento extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, idUtentePost, dataCommento, oraCommento, annoData, meseData, giornoData, oraString, minutiString, secondiString;
		int idPost;
		String arrayDataString[];
		String arrayOraString[];
		int anno, mese, giorno, ore, minuti, secondi;
		GregorianCalendar data;
		GregorianCalendar ora;
		
		session=request.getSession();
		idUtente=(String) session.getAttribute("idUtente");
		idPost=Integer.parseInt(request.getParameter("idPostCommento"));
		idUtentePost=request.getParameter("idUtentePostCommento");
		oraCommento=request.getParameter("oraCommento");
		dataCommento=request.getParameter("dataCommento");
		arrayDataString=dataCommento.split("-");
		annoData=arrayDataString[0];
		meseData=arrayDataString[1];
		giornoData=arrayDataString[2];
		anno=Integer.parseInt(annoData);
		mese=Integer.parseInt(meseData);
		mese=mese-1;
		giorno=Integer.parseInt(giornoData);
		data= new GregorianCalendar(anno, mese, giorno);
		arrayOraString=oraCommento.split(":");
		oraString=arrayOraString[0];
		minutiString=arrayOraString[1];
		secondiString=arrayOraString[2];
		ore=Integer.parseInt(oraString);
		minuti=Integer.parseInt(minutiString);
		secondi=Integer.parseInt(secondiString);
		ora=new GregorianCalendar(anno, mese, giorno, ore, minuti, secondi);
		if (idUtente!=null) {
			CommentoBean commentoBean= new CommentoBean();
			commentoBean.setIdUtente(idUtente);
			commentoBean.setIdPost(idPost);
			commentoBean.setIdUtentePost(idUtentePost);
			commentoBean.setData(data);
			commentoBean.setOra(ora);
			System.out.println(idUtente+" "+idPost+" "+idUtentePost+" "+data+" "+ora);
			CommentoDAO commentoDAO= new CommentoDAO();
			try {
				commentoDAO.doDelete(commentoBean);
				//effettuare il dispatcher alla jsp della foto
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}

}
