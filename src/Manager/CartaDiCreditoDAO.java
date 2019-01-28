package Manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import Model.CartaDiCreditoBean;

public class CartaDiCreditoDAO {
	public synchronized void doSave(CartaDiCreditoBean c) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into clipshot.cartadiCredito (numeroCarta, idUtente, intestatario, dataScadenza, cvv) values (?, ?, ?, ?, ?)");
		query.setInt(1, c.getNumeroCarta());
		query.setString(2, c.getIdUtente());
		query.setString(3, c.getIntestatario());
		query.setString(4, c.getDataScadenza());
		query.setString(5, c.getCvv());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(CartaDiCreditoBean c) throws Exception{
		CartaDiCreditoBean temp = doRetriveByKey(c.getNumeroCarta());
		if(temp==null) {
			doSave(c);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.cartadiCredito set idUtente=? , intestatario=? , dataScadenza=? , cvv=? where numeroCarta =?");	
			query.setString(1, c.getIdUtente());
			query.setString(2, c.getIntestatario());
			query.setString(3, c.getDataScadenza());
			query.setString(4, c.getCvv());
			query.setInt(5, c.getNumeroCarta());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized CartaDiCreditoBean doRetriveByKey(int numeroCarta) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		CartaDiCreditoBean c = new CartaDiCreditoBean();
		c.setNumeroCarta(numeroCarta);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.cartadiCredito where numeroCarta=?");
		query.setInt(1, c.getNumeroCarta());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		c.setIdUtente(result.getString("idUtente"));
		c.setIntestatario(result.getString("intestatario"));
		c.setDataScadenza(result.getString("dataScadenza"));
		c.setCvv(result.getString("cvv"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return c;	
	}
	public synchronized ArrayList<CartaDiCreditoBean> doRetriveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<CartaDiCreditoBean> carte = new ArrayList<CartaDiCreditoBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.cartadiCredito");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			CartaDiCreditoBean c = new CartaDiCreditoBean();
			c.setNumeroCarta(result.getInt("numeroCarta"));
			c.setIdUtente(result.getString("idUtente"));
			c.setIntestatario(result.getString("intestatario"));
			c.setDataScadenza(result.getString("dataScadenza"));
			c.setCvv(result.getString("cvv"));
			carte.add(c);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return carte;
	}
	
	public void doDelete(int numeroCarta) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.cartadiCredito WHERE numeroCarta=?");
		query.setInt(1, numeroCarta);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}	
}
