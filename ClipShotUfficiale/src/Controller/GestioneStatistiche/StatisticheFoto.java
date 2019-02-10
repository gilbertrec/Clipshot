/**
 * @author Carmine Cristian Cruoglio
 */
package Controller.GestioneStatistiche;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.AbbonamentoDAO;
import Manager.FotoDAO;
import Manager.PostDAO;
import Manager.StatisticheDAO;
import Model.AbbonamentoBean;
import Model.FotoBean;
import Model.PostBean;

@WebServlet("/StatisticheFoto")
public class StatisticheFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StatisticheFoto() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			String idUtente = (String) ssn.getAttribute("idUtente");
			if(idUtente != null) {
				AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
				AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
				try {
					abbonamentoBean = abbonamentoDAO.doRetrieveByKey(idUtente);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(abbonamentoBean.getStato().equals("ATTIVO") && abbonamentoBean.isScaduto(abbonamentoBean.getDataScadenza())) {
					StatisticheDAO statisticheDAO = new StatisticheDAO();
					String idFotoString = request.getParameter("idFoto");
					if(idFotoString != null) {
						int idFoto = Integer.parseInt(idFotoString);
						FotoBean fotoBean = new FotoBean();
						FotoDAO fotoDAO = new FotoDAO();
						try {
							fotoBean = fotoDAO.doRetrieveByKey(idFoto);
							request.setAttribute("fotoBean", fotoBean);
						} catch (SQLException e1) { //nessuna foto
							e1.printStackTrace();
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						}
						PostBean postBean = new PostBean();
						PostDAO postDAO = new PostDAO();
						try {
							postBean = postDAO.doRetrieveByCond(fotoBean.getIdFoto(), abbonamentoBean.getIdUtente());
							request.setAttribute("descrizionePost", postBean.getDescrizione());
						} catch (Exception e1) { //nesssun post
							e1.printStackTrace();
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						}
						try {
							String numeroLike = statisticheDAO.doRetrieveByCondNLike(abbonamentoBean.getIdUtente(), idFoto);
							request.setAttribute("numeroLike", numeroLike);
						} catch (Exception e) { //nessuna foto
							e.printStackTrace();
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						}
						try {
							String numeroAcquisti = statisticheDAO.doRetrieveByCondNAcquisti(idFoto);
							request.setAttribute("numeroAcquisti", numeroAcquisti);
						} catch (Exception e) { //nessuna foto
							e.printStackTrace();
							RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
							requestDispatcher.forward(request, response);
						}//tutto va a buon fine
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					} else { //nessuna foto
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
						requestDispatcher.forward(request, response);
					}
				} else { // abbonamento SOSPESO opp. SCADUTO
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} else { //idUtente == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}
		} else { //ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
