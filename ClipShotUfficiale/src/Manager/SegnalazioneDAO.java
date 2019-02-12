/**
 * @author Carmine Cristian Cruoglio
 */
package Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import com.mysql.jdbc.PreparedStatement;
import Model.SegnalazioneBean;

public class SegnalazioneDAO {
	public synchronized boolean doSave(SegnalazioneBean se) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = (PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"insert into clipshot.segnalazione (idSegnalazione, idUtente, idPost, idUtentePost, causa, stato, data, descrizione) values (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, se.getIdSegnalazione());
			ps.setString(2, se.getIdUtente());
			ps.setInt(3, se.getIdPost());
			ps.setString(4, se.getIdUtentePost());
			ps.setString(5, se.getCausa());
			ps.setString(6, se.getStato());
			ps.setString(7, se.getStringData());
			ps.setString(8, se.getDescrizione());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				ps.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	public synchronized SegnalazioneBean doSaveOrUpdate(SegnalazioneBean se) throws SQLException{
		SegnalazioneBean temp = doRetrieveByKey(se.getIdSegnalazione(), se.getIdUtente());
		if(temp==null) {
			doSave(se);
			return se;
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.segnalazione set idPost =?, idUtentePost =?, causa =?, stato =?, data =?, descrizione =? where idSegnalazione =? AND idUtente =?");	
			query.setInt(1, se.getIdPost());
			query.setString(2, se.getIdUtentePost());
			query.setString(3, se.getCausa());
			query.setString(4, se.getStato());
			query.setString(5, se.getStringData());
			query.setString(6, se.getDescrizione());
			query.setInt(7, se.getIdSegnalazione());
			query.setString(8, se.getIdUtente());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
			return se;
		}
	}
	public synchronized SegnalazioneBean doRetrieveByKey(int idSegnalazione, String idUtente) throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		SegnalazioneBean se = new SegnalazioneBean();
		se.setIdSegnalazione(idSegnalazione);
		se.setIdUtente(idUtente);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.segnalazione where idSegnalazione =? AND idUtente=?");
		query.setInt(1, se.getIdSegnalazione());
		query.setString(2, se.getIdUtente());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new SQLException();
		}
		se.setIdPost(result.getInt("idPost"));
		se.setIdUtentePost(result.getString("idUtentePost"));
		se.setCausa(result.getString("causa"));
		se.setStato(result.getString("stato"));
		Date dateFrom = result.getDate("data");
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(dateFrom);
		se.setData(data);
		se.setDescrizione(result.getString("descrizione"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return se;	
	}
	public synchronized ArrayList<SegnalazioneBean> doRetrieveAll() throws SQLException{
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
			Date dateFrom = result.getDate("data");
			GregorianCalendar data = new GregorianCalendar();
			data.setTime(dateFrom);
			se.setData(data);
			se.setDescrizione(result.getString("descrizione"));
			segnalazioni.add(se);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return segnalazioni;
	}
	public synchronized ArrayList<SegnalazioneBean> doRetrieveByCond(String stato) throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<SegnalazioneBean> segnalazioni = new ArrayList<SegnalazioneBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.segnalazione WHERE stato = ?");
		query.setString(1, stato);
		ResultSet result = query.executeQuery();
		while(result.next()) {
			SegnalazioneBean se = new SegnalazioneBean();
			se.setIdSegnalazione(result.getInt("idSegnalazione"));
			se.setIdUtente(result.getString("idUtente"));
			se.setIdPost(result.getInt("idPost"));
			se.setIdUtentePost(result.getString("idUtentePost"));
			se.setCausa(result.getString("causa"));
			se.setStato(result.getString("stato"));
			Date dateFrom = result.getDate("data");
			GregorianCalendar data = new GregorianCalendar();
			data.setTime(dateFrom);
			se.setData(data);
			se.setDescrizione(result.getString("descrizione"));
			segnalazioni.add(se);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return segnalazioni;		
	}
	
	public boolean doDelete(int idSegnalazione, String idUtente) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.segnalazione WHERE idSegnalazione =? AND idUtente=?");
			ps.setInt(1, idSegnalazione);
			ps.setString(2, idUtente);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				ps.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}
