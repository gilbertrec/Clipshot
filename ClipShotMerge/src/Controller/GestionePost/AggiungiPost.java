/**
 * @author Adalgiso Della Calce
   @author Gilbert Recupito
 */
package Controller.GestionePost;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Manager.FotoDAO;
import Manager.PostDAO;
import Model.FotoBean;
import Model.PostBean;
import Model.UtenteBean;

@WebServlet("/AggiungiPost")
@MultipartConfig
public class AggiungiPost extends HttpServlet {
	private static final String SAVE_DIR = "C:\\Users\\Gilbert\\eclipse-workspace\\ClipShotMerge\\WebContent\\photopost\\";
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, descrizione, tipo, pathFoto;
		int idPost, idFoto;
		Double prezzo;
		GregorianCalendar data, ora;
		
		session=request.getSession();
		UtenteBean u_session=(UtenteBean) session.getAttribute("utente");
		tipo=u_session.getTipo();
		
		//UploadingFile
		Part filePart = request.getPart("fileFoto");
		
        String fileName = filePart.getSubmittedFileName(); 
        InputStream is = filePart.getInputStream();
        pathFoto=fileName;
        Files.copy(is, Paths.get(SAVE_DIR + fileName),
                StandardCopyOption.REPLACE_EXISTING);//carica l'immagine
        
        
		//Preparazione Bean da inserire
		FotoBean fotoBean= new FotoBean();
		fotoBean.setPath(pathFoto);
		if (tipo.equalsIgnoreCase("ARTISTA")) {
			prezzo=Double.parseDouble(request.getParameter("prezzoFoto"));
			fotoBean.setPrezzo(prezzo);
		}
		else {
			fotoBean.setPrezzo(0.0);
		}
		FotoDAO fotoDAO= new FotoDAO();
		try {
			int last_id= fotoDAO.doRetrieveMaxId(); //Ricavo l'id più grande per effettuare l'autoincrement
			fotoBean.setIdFoto(last_id+1);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			fotoDAO.doSave(fotoBean);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//fine
		
		
		//campi post
		
		idUtente=u_session.getIdUtente();
		descrizione=request.getParameter("descrizionePost");
		data=new GregorianCalendar();
		ora= new GregorianCalendar();
		PostDAO postDAO=new PostDAO();
		
		if (idUtente!=null) {
			int last_id;
			PostBean postBean= new PostBean();
			try {
				last_id= postDAO.doRetrieveMaxId(idUtente);//Ricavo l'id più grande per effettuare l'autoincrement
				postBean.setIdPost(last_id+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			postBean.setIdUtente(idUtente);
			postBean.setIdFoto(fotoBean.getIdFoto());
			postBean.setDescrizione(descrizione);
			postBean.setData(data);
			postBean.setOra(ora);
			postBean.setStato("FREE");
			try {
				postDAO.doSave(postBean);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}
	
}
