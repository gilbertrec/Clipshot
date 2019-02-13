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

import Model.LikeBean;
import Model.SeguiBean;

public class LikeDAO {
	
	public LikeDAO() {
		
	}
	
	public boolean doSave(LikeBean likeBean) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) con.prepareStatement("insert into clipshot.like values (?, ?, ?);");
			ps.setString(1, likeBean.getIdUtente());
			ps.setInt(2, likeBean.getIdPost());
			ps.setString(3, likeBean.getIdUtentePost());
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
	
	public boolean doDelete (LikeBean likeBean) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) con.prepareStatement("delete from clipshot.like where idUtente=? and idPost=? and idUtentePost=?;");
			ps.setString(1, likeBean.getIdUtente());
			ps.setInt(2, likeBean.getIdPost());
			ps.setString(3, likeBean.getIdUtentePost());
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
	
	public LikeBean doRetrieveByKey (String idUtente, int idPost, String idUtentePost) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from clipshot.like where idUtente=? and idPost=? and idUtentePost=?;");
		ps.setString(1, idUtente);
		ps.setInt(2, idPost);
		ps.setString(3, idUtentePost);
		ResultSet resultSet=ps.executeQuery();
		LikeBean likeBean=null;
		if (resultSet.next()) {
			likeBean= new LikeBean();
			likeBean.setIdUtente(resultSet.getString("idUtentePost"));
			likeBean.setIdPost(resultSet.getInt("idPost"));
			likeBean.setIdUtentePost(resultSet.getString("idUtentePost"));
			ps.close();
			DriverManagerConnectionPool.releaseConnection(con);
			return likeBean;
		}
		else {
			return null;
		}
	}
	
	public ArrayList<LikeBean> doRetrieveByAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<LikeBean> listaLike= new ArrayList<LikeBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select * from clipshot.like");
		while (resultSet.next()) {
			LikeBean likeBean= new LikeBean();
			likeBean.setIdUtente(resultSet.getString("idUtente"));
			likeBean.setIdPost(resultSet.getInt("idPost"));
			likeBean.setIdUtentePost(resultSet.getString("idUtentePost"));
			listaLike.add(likeBean);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return listaLike;
	}

}
