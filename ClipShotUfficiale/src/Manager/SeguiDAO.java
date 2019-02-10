/**
 * @author Adalgiso Della Calce
 */
package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import Model.SeguiBean;

public class SeguiDAO {
	
	public SeguiDAO() {	}
	
	public void doSave(SeguiBean seguiBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert clipshot.into segui values(?, ?);");
		ps.setString(1, seguiBean.getIdFollower());
		ps.setString(2, seguiBean.getIdFollowing());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public SeguiBean doRetrieveByKey(String idFollower, String idFollowing) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from clipshot.segui where idFollower=? and idFollowing=?;");
		ps.setString(1, idFollower);
		ps.setString(2, idFollowing);
		ResultSet resultSet=ps.executeQuery();
		SeguiBean seguiBean= new SeguiBean();
		if (resultSet.next()) {
			seguiBean.setIdFollower(resultSet.getString("idFollower"));
			seguiBean.setIdFollowing(resultSet.getString("idFollowing"));
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return seguiBean;
	}
	
	public ArrayList<SeguiBean> doRetrieveAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<SeguiBean> listaSegui=new ArrayList<SeguiBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select * from clipshot.segui;");
		while (resultSet.next()) {
			SeguiBean seguiBean= new SeguiBean();
			seguiBean.setIdFollower(resultSet.getString("idFollower"));
			seguiBean.setIdFollowing(resultSet.getString("idFollowing"));
			listaSegui.add(seguiBean);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return listaSegui;
	}
	
	public void doDelete(SeguiBean seguiBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from clipshot.segui where idFollower=? and idFollowing=?;");
		ps.setString(1, seguiBean.getIdFollower());
		ps.setString(2, seguiBean.getIdFollowing());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
