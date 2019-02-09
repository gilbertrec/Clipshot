package proxy;

/**
 * @author Gilbert Recupito

 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proxy.ProxyImage;

/**
 * Servlet implementation class ImageProxy
 * Questa servlet è l'implementazione
 * per il design patter Proxy, se la foto non viene caricata in tempo verrà
 * caricata una di default, idem se una foto non è presente.
 */
@WebServlet("/ImageProxyController")
public class ImageProxyController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static final String SAVE_DIR =  "\\WebContent\\bootstrap\\images\\";

  public ImageProxyController() {
    super();
    // TODO Auto-generated constructor stub
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    String name = request.getParameter("name");
    Path iconPath = Paths.get(realPath(request)).resolve(SAVE_DIR).resolve(name);
    File f = null;
    InputStream in = null;
    ProxyImage prox = null;

    if (name != "") {
      if (iconPath.toFile().exists()) {
        // f = iconPath.toFile();
        prox = new ProxyImage(iconPath);
      } else {
        iconPath = Paths.get(realPath(request)).resolve(SAVE_DIR).resolve("default.jpg");
        f = iconPath.toFile();
        prox = new ProxyImage(iconPath);
      }
    } else {
      iconPath = Paths.get(realPath(request)).resolve(SAVE_DIR).resolve("default.jpg");
      f = iconPath.toFile();
      prox = new ProxyImage(iconPath);
    }

    in = prox.display();
    byte[] buff = new byte[40000];
    int bytesRead = 0;
    ByteArrayOutputStream bao = new ByteArrayOutputStream();

    while ((bytesRead = in.read(buff)) != -1) {
      bao.write(buff, 0, bytesRead);
    }

    byte[] data = bao.toByteArray();

    response.setContentType(getServletContext().getMimeType(iconPath.toString()));
    response.setContentLength(data.length);
    response.getOutputStream().write(data);

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

  private String realPath(HttpServletRequest request) {
    String requestUri = request.getRequestURI();
    String realPath = request.getSession().getServletContext().getRealPath(requestUri);
    return realPath;
  }

}
