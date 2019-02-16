/**
 * @author Adalgiso Della Calce
   @author Gilbert Recupito
 */
package Controller.GestionePost;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
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
	private static final String SAVE_DIR = "C:\\Users\\Prova\\Documents\\GitHub\\clipshot\\ClipShotMerge\\WebContent\\photopost\\";
	private static final String WM_DIR = "C:\\Users\\Prova\\Documents\\GitHub\\clipshot\\ClipShotMerge\\WebContent\\png\\";
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		HttpSession session;
		String idUtente, descrizione, tipo, pathFoto="";
		int idPost, idFoto;
		Double prezzo=0.0;
		GregorianCalendar data, ora;
		
		session=request.getSession();
		UtenteBean u_session=(UtenteBean) session.getAttribute("utente");
		tipo=u_session.getTipo();
		String s_prezzo=request.getParameter("prezzoFoto");
		
				File sourceImageFile;
				File destImageFile;
				String fileName="";
				//String text="\\u00a9 ClipShot";
				File watermarkImageFile =new File(WM_DIR+"filigrana.png");
				Part filePart = request.getPart("fileFoto");
				if(filePart!=null) {
					if(!filePart.equals("")) {
				
						fileName = filePart.getSubmittedFileName(); 
						InputStream is = filePart.getInputStream();
						pathFoto=fileName;
		        
						Files.copy(is, Paths.get(SAVE_DIR + fileName),
								StandardCopyOption.REPLACE_EXISTING);//carica l'immagine
						String fotoProfilo = request.getParameter("fileFoto");
				
				if(s_prezzo!=null) {
					if(Double.parseDouble(s_prezzo)>0){
				System.out.println(SAVE_DIR+"  \n"+"file:"+fileName);
				sourceImageFile = new File(SAVE_DIR+fileName);
				destImageFile = new File(SAVE_DIR+"watermarked_"+fileName);
				BufferedImage sourceImage = ImageIO.read(sourceImageFile);
				Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
				BufferedImage overlay = resize(ImageIO.read(watermarkImageFile), 50, 50);
				// determine image type and handle correct transparency
				int imageType = "png".equalsIgnoreCase("png") ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
				BufferedImage watermarked = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(),imageType);
				// initializes necessary graphic properties
				// initializes necessary graphic properties
				Graphics2D w = (Graphics2D) watermarked.getGraphics();
				w.drawImage(sourceImage, 0, 0, null);
				AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
				w.setComposite(alphaChannel);
		
		 
				// calculates the coordinate where the String is painted
				int centerX = sourceImage.getWidth() / 2;
				int centerY = sourceImage.getHeight() / 2;
				// paints the textual watermark
		 
				w.drawImage(overlay, centerX, centerY, null);
				ImageIO.write(watermarked, "png", destImageFile);
				w.dispose();
					}
					prezzo=Double.parseDouble(request.getParameter("prezzoFoto"));
				}
				else {
					prezzo=0.0;
				}
			}
			System.out.println("The text watermark is added to the image.");
		 
			FotoBean fotoBean= new FotoBean();
			fotoBean.setPath(pathFoto);
			if (tipo.equalsIgnoreCase("ARTISTA")) {
				System.out.println("prezzo:"+request.getParameter("prezzoFoto"));
				
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
		fotoDAO.doSave(fotoBean);
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
				RequestDispatcher view=request.getRequestDispatcher("HomePage");//12:29 14/02
				view.forward(request, response);
			} 
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        int imageType = "png".equalsIgnoreCase("png") ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage resized = new BufferedImage(width, height, /* BufferedImage.TYPE_INT_ARGB*/imageType);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
}
