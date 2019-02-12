/**
4 * @author Carmine Cristian Cruoglio
 */
package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import Model.OperatoreBean;

public class OperatoreDAO {
	
	public synchronized boolean doSave(OperatoreBean o) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = (PreparedStatement) con.prepareStatement(
					"insert into clipshot.operatore (username, password, nome, cognome, email, tipo) values (?, ?, ?, ?, ?, ?)");
			ps.setString(1, o.getUsername());
			ps.setString(2, o.getPassword());
			ps.setString(3, o.getNome());
			ps.setString(4, o.getCognome());
			ps.setString(5, o.getEmail());
			ps.setString(6, o.getTipo());
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
	public synchronized OperatoreBean doSaveOrUpdate(OperatoreBean o) throws SQLException{
		OperatoreBean temp = doRetrieveByKey(o.getUsername());
		if(temp==null) {
			doSave(o);
			return o;
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
			return o;
		}
	}
	public synchronized OperatoreBean doRetrieveByKey(String username) throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		OperatoreBean o = new OperatoreBean();
		o.setUsername(username);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.operatore where username=?");
		query.setString(1, o.getUsername());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new SQLException();
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
	public synchronized ArrayList<OperatoreBean> doRetrieveAll() throws SQLException{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<OperatoreBean> operatori = new ArrayList<OperatoreBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.operatore");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			OperatoreBean o = new OperatoreBean();
			o.setUsername(result.getString("username"));
			o.setPassword(result.getString("password"));
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
	
	public boolean doDelete(String username) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.operatore WHERE username=?");
			ps.setString(1, username);
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
