package Controller.GestioneAutenticazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.PostDAO;
import Model.PostBean;

@WebServlet("/HomePage")
public class HomePage extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session = null;
		String idUtente=null;
		PostDAO postDAO;
		ArrayList<PostBean> listaPost;
		ArrayList<PostBean> listaPost2;
		
		
		session=request.getSession();
		idUtente=(String) session.getAttribute("idUtente");
		if (idUtente!=null) {
			postDAO= new PostDAO();
			try {
				listaPost=postDAO.doRetrievePostOfFollowing(idUtente);
				listaPost2=postDAO.doRetrievePostByIdUtente(idUtente);
				int i, y, flag=0;
				PostBean postBean2, postBean;
				for (i=0; i<listaPost2.size(); i++) {
					postBean2=listaPost2.get(i);
					flag=0;
					for (y=0; y<listaPost.size()&&flag==0; y++) {
						postBean=listaPost.get(y);
						if (postBean2.getData().after(postBean.getData())) {
							flag=1;
							listaPost.add(y, postBean2);
						}
					}
					if (flag==0)
						listaPost.add(y, postBean2);
				}
				//dispatcher jsp inserendo listPost nella request
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
