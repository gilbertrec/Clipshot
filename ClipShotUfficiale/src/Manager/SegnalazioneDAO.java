package Manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import Model.SegnalazioneBean;

public class SegnalazioneDAO {
	public synchronized void doSave(SegnalazioneBean se) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into clipshot.segnalazione (idSegnalazione, idUtente, idPost, idUtentePost, causa, stato, data, descrizione) values (?, ?, ?, ?, ?, ?, ?, ?)");
		query.setInt(1, se.getIdSegnalazione());
		query.setString(2, se.getIdUtente());
		query.setInt(3, se.getIdPost());
		query.setString(4, se.getIdUtentePost());
		query.setString(5, se.getCausa());
		query.setString(6, se.getStato());
		query.setDate(7, se.getData());
		query.setString(8, se.getDescrizione());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(SegnalazioneBean se) throws Exception{
		SegnalazioneBean temp = doRetrieveByKey(se.getIdSegnalazione(), se.getIdUtente());
		if(temp==null) {
			doSave(se);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.segnalazione set idPost =?, idUtentePost =?, causa =?, stato =?, data =?, descrizione =? where idSegnalazione =?, idUtente =?");	
			query.setInt(1, se.getIdPost());
			query.setString(2, se.getIdUtentePost());
			query.setString(3, se.getCausa());
			query.setString(4, se.getStato());
			query.setDate(5, se.getData());
			query.setString(6, se.getDescrizione());
			query.setInt(7, se.getIdSegnalazione());
			query.setString(8, se.getIdUtente());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized SegnalazioneBean doRetrieveByKey(int idSegnalazione, String idUtente) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		SegnalazioneBean se = new SegnalazioneBean();
		se.setIdSegnalazione(idSegnalazione);
		se.setIdUtente(idUtente);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.segnalazione where idSegnalazione =?, idUtente=?");
		query.setInt(1, se.getIdSegnalazione());
		query.setString(2, se.getIdUtente());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		se.setIdPost(result.getInt("idPost"));
		se.setIdUtentePost(result.getString("idUtentePost"));
		se.setCausa(result.getString("causa"));
		se.setStato(result.getString("stato"));
		se.setData(result.getDate("data"));
		se.setDescrizione(result.getString("descrizione"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return se;	
	}
	public synchronized ArrayList<SegnalazioneBean> doRetrieveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<SegnalazioneBean> segnalazioni = new ArrayList<SegnalazioneBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.segnalazione");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			SegnalazioneBean se = new SegnalazioneBean();
			se.setIdSegnalazione(result.getInt("idSegnalazione"));
			se.setIdUtente(result.getString("idUtente"));
			se.setIdPost(result.getInt("idPost"));
			se.setIdUtentePost(result.getString("idUtentePost"));
			se.setCausa(result.getString("causa"));
			se.setStato(result.getString("stato"));
			se.setData(result.getDate("data"));
			se.setDescrizione(result.getString("descrizione"));
			segnalazioni.add(se);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return segnalazioni;
	}
	
	public void doDelete(int idSegnalazione, String idUtente) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.segnalazione WHERE idSegnalazione =?, idUtente=?");
		query.setInt(1, idSegnalazione);
		query.setString(2, idUtente);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
