package Manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import Model.OperatoreBean;

public class OperatoreDAO {
	public synchronized void doSave(OperatoreBean o) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into clipshot.operatore (username, password, nome, cognome, email, tipo) values (?, ?, ?, ?, ?, ?)");
		query.setString(1, o.getUsername());
		query.setString(2, o.getPassword());
		query.setString(3, o.getNome());
		query.setString(4, o.getCognome());
		query.setString(5, o.getEmail());
		query.setString(6, o.getTipo());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(OperatoreBean o) throws Exception{
		OperatoreBean temp = doRetrieveByKey(o.getUsername());
		if(temp==null) {
			doSave(o);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.operatore set password =?, nome =?, cognome =?, email =?, tipo =? where username =?");	
			query.setString(1, o.getPassword());
			query.setString(2, o.getNome());
			query.setString(3, o.getCognome());
			query.setString(4, o.getEmail());
			query.setString(5, o.getTipo());
			query.setString(6, o.getUsername());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized OperatoreBean doRetrieveByKey(String username) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		OperatoreBean o = new OperatoreBean();
		o.setUsername(username);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.operatore where username=?");
		query.setString(1, o.getUsername());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		o.setPassword(result.getString("password"));
		o.setNome(result.getString("nome"));
		o.setCognome(result.getString("cognome"));
		o.setEmail(result.getString("email"));
		o.setTipo(result.getString("tipo"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return o;	
	}
	public synchronized ArrayList<OperatoreBean> doRetrieveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<OperatoreBean> operatori = new ArrayList<OperatoreBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.operatore");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			OperatoreBean o = new OperatoreBean();
			o.setUsername(result.getString("username"));
			o.setPassword(result.getString("passowrd"));
			o.setNome(result.getString("nome"));
			o.setCognome(result.getString("cognome"));
			o.setEmail(result.getString("email"));
			o.setTipo(result.getString("tipo"));
			operatori.add(o);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return operatori;
	}
	
	public void doDelete(String username) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.operatore WHERE username=?");
		query.setString(1, username);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
