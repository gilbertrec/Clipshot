/**
 * @author Adalgiso Della Calce
 */
package Controller.GestionePost;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.FotoDAO;
import Manager.PostDAO;
import Model.FotoBean;
import Model.PostBean;

@WebServlet("/AggiungiPost")

public class AggiungiPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, descrizione, tipo, pathFoto;
		int idPost, idFoto;
		Double prezzo;
		GregorianCalendar data, ora;
		
		session=request.getSession();
		tipo=(String) session.getAttribute("tipo");
		//campi foto;
		idFoto=Integer.parseInt(request.getParameter("idFotoFoto"));
		pathFoto=request.getParameter("pathFoto");
		FotoBean fotoBean= new FotoBean();
		fotoBean.setIdFoto(idFoto);
		fotoBean.setPath(pathFoto);
		if (tipo.equals("ARTISTA")) {
			prezzo=Double.parseDouble(request.getParameter("prezzoFoto"));
			fotoBean.setPrezzo(prezzo);
		}
		FotoDAO fotoDAO= new FotoDAO();
		try {
			fotoDAO.doSave(fotoBean);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//fine
		
		
		//campi post
		idPost=Integer.parseInt(request.getParameter("idPostPost"));
		idUtente=(String) session.getAttribute("idUtente");
		descrizione=request.getParameter("descrizionePost");
		data=new GregorianCalendar();
		ora= new GregorianCalendar();
		if (idUtente!=null) {
			PostBean postBean= new PostBean();
			postBean.setIdPost(idPost);
			postBean.setIdUtente(idUtente);
			postBean.setIdFoto(idFoto);
			postBean.setDescrizione(descrizione);
			postBean.setData(data);
			postBean.setOra(ora);
			postBean.setStato("FREE");
			PostDAO postDAO= new PostDAO();
			try {
				postDAO.doSave(postBean);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}
	
}
