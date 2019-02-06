package Controller.GestioneInterazioni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.CommentoBeanDao;
import Model.CommentoBean;

@WebServlet("/RimuoviCommento")
public class RimuoviCommento extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, idUtentePost, dataCommento, oraCommento;
		int idPost;
		java.sql.Date dataCommentoDate=null;
		java.sql.Time oraCommentoTime= null;
		java.util.Date fromDate;
		GregorianCalendar data= new GregorianCalendar();
		GregorianCalendar ora= new GregorianCalendar();
		
		session=request.getSession();
		idUtente=(String) session.getAttribute("idUtente");
		idPost=Integer.parseInt(request.getParameter("idPostCommento"));
		idUtentePost=request.getParameter("idUtentePostCommento");
		oraCommento=request.getParameter("oraCommento");
		dataCommento=request.getParameter("dataCommento");
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		try {
			fromDate=dateFormat.parse(dataCommento);
			dataCommentoDate=new java.sql.Date(fromDate.getTime());
			data.setTime(dataCommentoDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateFormat= new SimpleDateFormat("HH:mm:ss");
		try {
			fromDate=dateFormat.parse(oraCommento);
			oraCommentoTime=new java.sql.Time(fromDate.getTime());
			ora.setTime(oraCommentoTime);
			System.out.println(ora.get(GregorianCalendar.HOUR_OF_DAY)+" "+ora.get(GregorianCalendar.MINUTE)+" "+ora.get(GregorianCalendar.SECOND));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (idUtente!=null) {
			CommentoBean commentoBean= new CommentoBean();
			commentoBean.setIdUtente(idUtente);
			commentoBean.setIdPost(idPost);
			commentoBean.setIdUtentePost(idUtentePost);
			commentoBean.setData(data);
			commentoBean.setOra(ora);
			CommentoBeanDao commentoBeanDao= new CommentoBeanDao();
			try {
				commentoBeanDao.doDelete(commentoBean);
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
