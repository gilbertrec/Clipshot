/**
 * @author Carmine Cristian Cruoglio
 */
package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import com.mysql.jdbc.PreparedStatement;
import Model.AbbonamentoBean;

public class AbbonamentoDAO {
	public synchronized boolean doSave(AbbonamentoBean a) throws SQLException{
		Connection con = null;
		PreparedStatement query = null;
		try {
			con = DriverManagerConnectionPool.getConnection();
			query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"insert into clipshot.abbonamento (idUtente, dataScadenza, numeroCarta, stato) values (?, ?, ?, ?)");
			query.setString(1, a.getIdUtente());
			query.setString(2, a.getStringDataScadenza());
			query.setString(3, a.getNumeroCarta()); 
			query.setString(4, a.getStato());
			query.executeUpdate();
			return true;
		} catch(SQLException e) {
			return false;
		} finally {
			try {
				query.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized AbbonamentoBean doSaveOrUpdate(AbbonamentoBean a) throws SQLException{
		AbbonamentoBean temp = doRetrieveByKey(a.getIdUtente());
		if(temp==null) {
			doSave(a);
			return a;
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.abbonamento set dataScadenza=? , numeroCarta=? , stato=? where idUtente = ?");	
			query.setString(1, a.getStringDataScadenza());
			query.setString(2, a.getNumeroCarta());
			query.setString(3, a.getStato());
			query.setString(4, a.getIdUtente());
			query.executeUpdate();
			query.close();
			DriverManagerConnectionPool.releaseConnection(con);
			return a;
		}
	}
	public synchronized AbbonamentoBean doRetrieveByKey(String idUtente) throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		AbbonamentoBean a = new AbbonamentoBean();
		a.setIdUtente(idUtente);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.abbonamento where idUtente=?");
		query.setString(1, a.getIdUtente());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new SQLException();
		}else {
		java.sql.Date data = result.getDate("dataScadenza");
		GregorianCalendar dataScadenza = new GregorianCalendar();
		dataScadenza.setTime(data);
		a.setDataScadenza(dataScadenza);
		a.setNumeroCarta(result.getString("numeroCarta"));
		a.setStato(result.getString("stato"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		}
		return a;	
	}
	public synchronized ArrayList<AbbonamentoBean> doRetrieveAll() throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<AbbonamentoBean> abbonamenti = new ArrayList<AbbonamentoBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.abbonamento");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			AbbonamentoBean a = new AbbonamentoBean();
			a.setIdUtente(result.getString("idUtente"));
			java.sql.Date data = result.getDate("dataScadenza");
			GregorianCalendar dataScadenza = new GregorianCalendar();
			dataScadenza.setTime(data);
			a.setDataScadenza(dataScadenza);
			a.setNumeroCarta(result.getString("numeroCarta"));
			a.setStato(result.getString("stato"));
			abbonamenti.add(a);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return abbonamenti;
	}
	public synchronized AbbonamentoBean doRetrieveByCond(String idUtente) throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		AbbonamentoBean a = new AbbonamentoBean();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT c.numeroCarta FROM clipshot.utente u JOIN clipshot.cartadicredito c WHERE u.idUtente = c.idUtente AND u.idUtente = ?");
		query.setString(1, idUtente);
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new SQLException();
		}
		a.setNumeroCarta(result.getString("c.numeroCarta"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return a;
	}
	public boolean doDelete(String idUtente) {
		Connection con = null;
		PreparedStatement query = null;
		try {
			con = DriverManagerConnectionPool.getConnection();
			query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.abbonamento WHERE idUtente=?");
			query.setString(1, idUtente);
			query.executeUpdate();
			return true;
		} catch(SQLException e) {
			return false;
		} finally {
			try {
				query.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
