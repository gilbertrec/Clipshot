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
import Model.CartaDiCreditoBean;

public class CartaDiCreditoDAO {
	public synchronized boolean doSave(CartaDiCreditoBean c){
		Connection con = null;
		PreparedStatement query = null;
		try {
			con = DriverManagerConnectionPool.getConnection();
			query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
				"insert into clipshot.cartadiCredito (numeroCarta, idUtente, intestatario, dataScadenza, cvv) values (?, ?, ?, ?, ?)");
			query.setString(1, c.getNumeroCarta());
			query.setString(2, c.getIdUtente());
			query.setString(3, c.getIntestatario());
			query.setString(4, c.getStringDataScadenza());
			query.setString(5, c.getCvv());
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
	public synchronized CartaDiCreditoBean doSaveOrUpdate(CartaDiCreditoBean c) throws SQLException{
		CartaDiCreditoBean temp = doRetrieveByKey(c.getNumeroCarta());
		if(temp==null) {
			doSave(c);
			return c;
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.cartadiCredito set idUtente=? , intestatario=? , dataScadenza=? , cvv=? where numeroCarta = ?");	
			query.setString(1, c.getIdUtente());
			query.setString(2, c.getIntestatario());
			query.setString(3, c.getStringDataScadenza());
			query.setString(4, c.getCvv());
			query.setString(5, c.getNumeroCarta());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
			return c;
		}
	}
	public synchronized CartaDiCreditoBean doRetrieveByKey(String numeroCarta) throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		CartaDiCreditoBean c = new CartaDiCreditoBean();
		c.setNumeroCarta(numeroCarta);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.cartadiCredito where numeroCarta=?");
		query.setString(1, c.getNumeroCarta());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new SQLException();
		}
		java.sql.Date data = result.getDate("dataScadenza");
		GregorianCalendar dataScadenza = new GregorianCalendar();
		dataScadenza.setTime(data);
		c.setIdUtente(result.getString("idUtente"));
		c.setIntestatario(result.getString("intestatario"));
		c.setDataScadenza(dataScadenza);
		c.setCvv(result.getString("cvv"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return c;	
	}
	public synchronized ArrayList<CartaDiCreditoBean> doRetrieveAll() throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<CartaDiCreditoBean> carte = new ArrayList<CartaDiCreditoBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.cartadiCredito");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			java.sql.Date data = result.getDate("dataScadenza");
			GregorianCalendar dataScadenza = new GregorianCalendar();
			dataScadenza.setTime(data);
			CartaDiCreditoBean c = new CartaDiCreditoBean();
			c.setNumeroCarta(result.getString("numeroCarta"));
			c.setIdUtente(result.getString("idUtente"));
			c.setIntestatario(result.getString("intestatario"));
			c.setDataScadenza(dataScadenza);
			c.setCvv(result.getString("cvv"));
			carte.add(c);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return carte;
	}
	public synchronized CartaDiCreditoBean doRetrieveByCond(String idUtente) throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		CartaDiCreditoBean c = new CartaDiCreditoBean();
		c.setIdUtente(idUtente);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT c.numeroCarta, c.idUtente, c.intestatario, c.dataScadenza, c.cvv FROM clipshot.utente u JOIN clipshot.cartadicredito c WHERE u.idUtente = c.idUtente AND c.idUtente = ?");
		query.setString(1, c.getIdUtente());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new SQLException();
		}
		java.sql.Date data = result.getDate("dataScadenza");
		GregorianCalendar dataScadenza = new GregorianCalendar();
		dataScadenza.setTime(data);
		c.setNumeroCarta(result.getString("numeroCarta"));
		c.setIdUtente(result.getString("idUtente"));
		c.setIntestatario(result.getString("intestatario"));
		c.setDataScadenza(dataScadenza);
		c.setCvv(result.getString("cvv"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return c;	
	}
	public boolean doDelete(String numeroCarta){
		Connection con = null;
		PreparedStatement query = null;
		try {
			con = DriverManagerConnectionPool.getConnection();
			query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.cartadiCredito WHERE numeroCarta=?");
			query.setString(1, numeroCarta);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
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
