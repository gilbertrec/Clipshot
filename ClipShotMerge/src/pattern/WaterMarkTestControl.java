package pattern;

import java.io.File;
import java.io.IOException;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author Gilberto Recupito
 * Servlet implementation class WaterMarkTestControl
 */
@WebServlet("/WaterMarkTestControl")
@MultipartConfig
public class WaterMarkTestControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "C:\\Users\\Gilbert\\Desktop\\Watermark\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaterMarkTestControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		File sourceImageFile;
		File destImageFile;
		String fileName="";
		String pathFoto;
		String text="\\u00a9 Watermark by Gilbert Recupito";
		Part filePart = request.getPart("fotoWatermark");
		if(filePart!=null) {
			if(!filePart.equals("")) {
				
		        fileName = filePart.getSubmittedFileName(); 
		        InputStream is = filePart.getInputStream();
		        pathFoto=fileName;
		        
		        Files.copy(is, Paths.get(SAVE_DIR + fileName),
		                StandardCopyOption.REPLACE_EXISTING);//carica l'immagine
				String fotoProfilo = request.getParameter("fotoWatermark");
			}
		}
		
			System.out.println(SAVE_DIR+"  \n"+"file:"+fileName);
			sourceImageFile = new File(SAVE_DIR+fileName);
			destImageFile = new File(SAVE_DIR+"//Watermark"+"//"+"watermarked_"+fileName);
		BufferedImage sourceImage = ImageIO.read(sourceImageFile);
		Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
		 
		// initializes necessary graphic properties
		AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
		g2d.setComposite(alphaChannel);
		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font("Arial", Font.BOLD, 64));
		FontMetrics fontMetrics = g2d.getFontMetrics();
		Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);
		 
		// calculates the coordinate where the String is painted
		int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
		int centerY = sourceImage.getHeight() / 2;
		 
		// paints the textual watermark
		g2d.drawString(text, centerX, centerY);
		 
		ImageIO.write(sourceImage, "png", destImageFile);
		g2d.dispose();
		 
		System.out.println("The text watermark is added to the image.");
		 
		}




	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
