package Manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import Model.AbbonamentoBean;

public class AbbonamentoDAO {
	public synchronized void doSave(AbbonamentoBean a) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into clipshot.abbonamento (idUtente, dataScadenza, numeroCarta, stato) values (?, ?, ?, ?)");
		query.setString(1, a.getIdUtente());
		query.setDate(2, a.getDataScadenza());
		query.setString(3, a.getNumeroCarta());
		query.setString(4, a.getStato());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(AbbonamentoBean a) throws Exception{
		AbbonamentoBean temp = doRetrieveByKey(a.getIdUtente());
		if(temp==null) {
			doSave(a);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.abbonamento set dataScadenza=? , numeroCarta=? , stato=? where idUtente =?");	
			query.setDate(1, a.getDataScadenza());
			query.setString(2, a.getNumeroCarta());
			query.setString(3, a.getStato());
			query.setString(4, a.getIdUtente());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized AbbonamentoBean doRetrieveByKey(String idUtente) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		AbbonamentoBean a = new AbbonamentoBean();
		a.setIdUtente(idUtente);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.abbonamento where idUtente=?");
		query.setString(1, a.getIdUtente());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		a.setDataScadenza(result.getDate("dataScadenza"));
		a.setNumeroCarta(result.getString("numeroCarta"));
		a.setStato(result.getString("stato"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return a;	
	}
	public synchronized ArrayList<AbbonamentoBean> doRetrieveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<AbbonamentoBean> abbonamenti = new ArrayList<AbbonamentoBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.abbonamento");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			AbbonamentoBean a = new AbbonamentoBean();
			a.setIdUtente(result.getString("idUtente"));
			a.setDataScadenza(result.getDate("dataScadenza"));
			a.setNumeroCarta(result.getString("numeroCarta"));
			a.setStato(result.getString("stato"));
			abbonamenti.add(a);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return abbonamenti;
	}
	
	public void doDelete(String idUtente) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.abbonamento WHERE idUtente=?");
		query.setString(1, idUtente);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
