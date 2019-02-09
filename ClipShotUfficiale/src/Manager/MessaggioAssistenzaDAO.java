package Manager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import Model.MessaggioAssistenzaBean;

public class MessaggioAssistenzaDAO {
	
	public synchronized void doSave(MessaggioAssistenzaBean ma) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into clipshot.messaggioAssistenza (idUtente, idOperatore, dataMessaggio, ora, oggetto, corpo, tipo) values (?, ?, ?, ?, ?, ?, ?)");
		query.setString(1, ma.getIdUtente());
		query.setString(2, ma.getIdOperatore());
		query.setDate(3, ma.getDataMessaggio());
		query.setTime(4, ma.getOra());
		query.setString(5, ma.getOggetto());
		query.setString(6, ma.getCorpo());
		query.setString(7, ma.getTipo());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(MessaggioAssistenzaBean ma) throws Exception{
		MessaggioAssistenzaBean temp = doRetrieveByKey(ma.getIdUtente(), ma.getIdOperatore(), ma.getDataMessaggio(), ma.getOra());
		if(temp==null) {
			doSave(ma);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.messaggioAssistenza set oggetto=?, corpo=?, tipo=? WHERE idUtente=?, idOperatore=?, dataMessaggio=?, ora=?");	
			query.setString(1, ma.getOggetto());
			query.setString(2, ma.getCorpo());
			query.setString(3, ma.getCorpo());
			query.setString(4, ma.getIdUtente());
			query.setString(5, ma.getIdOperatore());
			query.setDate(6, ma.getDataMessaggio());
			query.setTime(7, ma.getOra());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized MessaggioAssistenzaBean doRetrieveByKey(String idUtente, String idOperatore, Date dataMessaggio, Time ora) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		MessaggioAssistenzaBean ma = new MessaggioAssistenzaBean();
		ma.setIdUtente(idUtente);
		ma.setIdOperatore(idOperatore);
		ma.setDataMessaggio(dataMessaggio);
		ma.setOra(ora);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.messaggioAssistenza WHERE idUtente=?, idOperatore=?, dataMessaggio =?, ora=?");
		query.setString(1, ma.getIdUtente());
		query.setString(2, ma.getIdOperatore());
		query.setDate(3, ma.getDataMessaggio());
		query.setTime(4, ma.getOra());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		ma.setOggetto(result.getString("oggetto"));
		ma.setCorpo(result.getString("corpo"));
		ma.setTipo(result.getString("tipo"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return ma;	
	}
	public synchronized ArrayList<MessaggioAssistenzaBean> doRetrieveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<MessaggioAssistenzaBean> messaggiAssistenza = new ArrayList<MessaggioAssistenzaBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.messaggioAssistenza");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			MessaggioAssistenzaBean ma = new MessaggioAssistenzaBean();
			ma.setIdUtente(result.getString("idUtente"));
			ma.setIdOperatore(result.getString("idOperatore"));
			ma.setDataMessaggio(result.getDate("dataMessaggio"));
			ma.setOra(result.getTime("ora"));
			ma.setOggetto(result.getString("oggetto"));
			ma.setCorpo(result.getString("corpo"));
			ma.setTipo(result.getString("tipo"));
			messaggiAssistenza.add(ma);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return messaggiAssistenza;
	}
	
	public void doDelete(String idMittente, String idDestinatario, Date dataMessaggio, Time ora) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.messaggioAssistenza WHERE idMittente=?, idDestinatario=?, dataMessaggio =?, ora=?");
		query.setString(1, idMittente);
		query.setString(2, idDestinatario);
		query.setDate(3, (Date) dataMessaggio);
		query.setTime(4, ora);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
