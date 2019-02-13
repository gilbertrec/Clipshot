/*
 * 
 @author Adalgiso Della Calce*/

package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Model.SeguiBean;
import Model.UtenteBean;

public class SeguiDAO {
	
	public SeguiDAO() {
	}
	
	public boolean doSave(SeguiBean seguiBean) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) con.prepareStatement("insert into segui values(?, ?);");
			ps.setString(1, seguiBean.getIdFollower());
			ps.setString(2, seguiBean.getIdFollowing());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
		finally {
			try {
				ps.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean doDelete(SeguiBean seguiBean) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			ps = (PreparedStatement) con.prepareStatement("delete from segui where idFollower=? and idFollowing=?;");
			ps.setString(1, seguiBean.getIdFollower());
			ps.setString(2, seguiBean.getIdFollowing());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		finally {
			try {
				ps.close();
				DriverManagerConnectionPool.releaseConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public SeguiBean doRetrieveByKey(String idFollower, String idFollowing) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from segui where idFollower=? and idFollowing=?;");
		ps.setString(1, idFollower);
		ps.setString(2, idFollowing);
		ResultSet resultSet=ps.executeQuery();
		SeguiBean seguiBean=null;
		if (resultSet.next()) {
			seguiBean= new SeguiBean();
			seguiBean.setIdFollower(resultSet.getString("idFollower"));
			seguiBean.setIdFollowing(resultSet.getString("idFollowing"));
			ps.close();
			DriverManagerConnectionPool.releaseConnection(con);
			return seguiBean;
		}
		else {
			return null;
		}
	}
	
	public ArrayList<SeguiBean> doRetrieveAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<SeguiBean> listaSegui=new ArrayList<SeguiBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select * from segui;");
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
	
}
