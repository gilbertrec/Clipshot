package Controller.GestioneFotoAcquistate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.AcquistiBeanDao;
import Model.AcquistiBean;

@WebServlet("/AcquistaFoto")

public class AcquistaFoto extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String idUtente, dataAcquisto;
		int idFoto;
		GregorianCalendar data;
		AcquistiBean acquistiBean;
		AcquistiBeanDao acquistiBeanDao= new AcquistiBeanDao();
		idUtente=(String) session.getAttribute("idUtente");
		idFoto=Integer.parseInt(request.getParameter("idFotoAcquisto"));
		data= new GregorianCalendar();
		if (idUtente!=null) {
			acquistiBean=new AcquistiBean(idUtente, idFoto, data);
			try {
				acquistiBeanDao.doSave(acquistiBean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}
}
