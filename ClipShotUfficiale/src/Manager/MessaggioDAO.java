package Manager;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import com.mysql.jdbc.PreparedStatement;
import Model.MessaggioBean;

public class MessaggioDAO {
	
	public synchronized void doSave(MessaggioBean m) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into clipshot.messaggio (idMittente, idDestinatario, dataMessaggio, ora, corpo) values (?, ?, ?, ?, ?)");
		query.setString(1, m.getIdMittente());
		query.setString(2, m.getIdDestinatario());
		query.setDate(3, m.getDataMessaggio());
		query.setTime(4, m.getOra());
		query.setString(5, m.getCorpo());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(MessaggioBean m) throws Exception{
		MessaggioBean temp = doRetrieveByKey(m.getIdMittente(), m.getIdDestinatario(), m.getDataMessaggio(), m.getOra());
		if(temp==null) {
			doSave(m);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.messaggio set corpo=? WHERE idMittente=?, idDestinatario=?, dataMessaggio =?, ora=?");	
			query.setString(1, m.getCorpo());
			query.setString(2, m.getIdMittente());
			query.setString(3, m.getIdDestinatario());
			query.setDate(4, m.getDataMessaggio());
			query.setTime(5, m.getOra());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized MessaggioBean doRetrieveByKey(String idMittente, String idDestinatario, Date dataMessaggio, Time ora) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		MessaggioBean m = new MessaggioBean();
		m.setIdMittente(idMittente);
		m.setIdDestinatario(idDestinatario);
		m.setDataMessaggio(dataMessaggio);
		m.setOra(ora);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.messaggio WHERE idMittente=?, idDestinatario=?, dataMessaggio =?, ora=?");
		query.setString(1, m.getIdMittente());
		query.setString(2, m.getIdDestinatario());
		query.setDate(3, m.getDataMessaggio());
		query.setTime(4, m.getOra());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		m.setCorpo(result.getString("corpo"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return m;	
	}
	public synchronized ArrayList<MessaggioBean> doRetrieveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<MessaggioBean> messaggi = new ArrayList<MessaggioBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.messaggio");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			MessaggioBean m = new MessaggioBean();
			m.setIdMittente(result.getString("idMittente"));
			m.setIdDestinatario(result.getString("idDestinatario"));
			m.setDataMessaggio(result.getDate("dataMessaggio"));
			m.setOra(result.getTime("ora"));
			m.setCorpo(result.getString("corpo"));
			messaggi.add(m);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return messaggi;
	}
	
	public void doDelete(String idMittente, String idDestinatario, Date dataMessaggio, Time ora) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.messaggio WHERE idMittente=?, idDestinatario=?, dataMessaggio =?, ora=?");
		query.setString(1, idMittente);
		query.setString(2, idDestinatario);
		query.setDate(3, (Date) dataMessaggio);
		query.setTime(4, ora);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
