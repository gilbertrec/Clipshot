package Manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import Model.StatisticheBean;

public class StatisticheDAO {
	public synchronized void doSave(StatisticheBean s) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into clipshot.statistiche (idUtente, numeroVisualizzazioni) values (?, ?)");
		query.setString(1, s.getIdUtente());
		query.setInt(2, s.getNumeroVisualizzazioni());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(StatisticheBean s) throws Exception{
		StatisticheBean temp = doRetrieveByKey(s.getIdUtente());
		if(temp==null) {
			doSave(s);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.statistiche set numeroVisualizzazioni=? where idUtente =?");	
			query.setInt(1, s.getNumeroVisualizzazioni());
			query.setString(2, s.getIdUtente());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized StatisticheBean doRetrieveByKey(String idUtente) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		StatisticheBean s = new StatisticheBean();
		s.setIdUtente(idUtente);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.statistiche where idUtente=?");
		query.setString(1, s.getIdUtente());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		s.setNumeroVisualizzazioni(result.getInt("numeroVisualizzazioni"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return s;	
	}
	public synchronized ArrayList<StatisticheBean> doRetrieveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<StatisticheBean> statistiche = new ArrayList<StatisticheBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.statistiche");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			StatisticheBean s = new StatisticheBean();
			s.setIdUtente(result.getString("idUtente"));
			s.setNumeroVisualizzazioni(result.getInt("numeroVisualizzazioni"));
			statistiche.add(s);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return statistiche;
	}
	
	public void doDelete(String idUtente) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.statistiche WHERE idUtente=?");
		query.setString(1, idUtente);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
