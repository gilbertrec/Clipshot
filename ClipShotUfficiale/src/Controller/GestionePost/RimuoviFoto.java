package Controller.GestionePost;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manager.FotoBeanDao;
import Model.FotoBean;

@WebServlet("/RimuoviFoto")

public class RimuoviFoto extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		int idFoto;
		
		idFoto=Integer.parseInt(request.getParameter("idFotoFoto"));
		FotoBean fotoBean= new FotoBean(idFoto);
		FotoBeanDao fotoBeanDao= new FotoBeanDao();
		try {
			fotoBeanDao.doDelete(fotoBean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}

}
