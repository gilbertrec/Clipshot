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
import com.sun.javafx.font.freetype.FTFactory;

import Model.FotoBean;

public class FotoDAO {
	
	public FotoDAO() {
		
	}
	
	public boolean doSave (FotoBean fotoBean){
		Connection con=null;
		PreparedStatement ps=null;
			try {
			if (fotoBean.getPrezzo()==null) {
				con=DriverManagerConnectionPool.getConnection();
				ps=(PreparedStatement) con.prepareStatement("insert into foto (idFoto, path) values(?, ?);");
				ps.setInt(1, fotoBean.getIdFoto());
				ps.setString(2, fotoBean.getPath());
			}
			else {
			con=DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) con.prepareStatement("insert into foto values(?, ?, ?);");
			ps.setInt(1, fotoBean.getIdFoto());
			ps.setString(2, fotoBean.getPath());
			ps.setDouble(3,fotoBean.getPrezzo());
			}
			ps.executeUpdate();
			return true;
			}
		catch (SQLException e) {
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
	
	public boolean doDelete (FotoBean fotoBean) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManagerConnectionPool.getConnection();
			ps = (PreparedStatement) con.prepareStatement("delete from foto where idFoto=?;");
			ps.setInt(1, fotoBean.getIdFoto());
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
	
	public FotoBean doRetrieveByKey (int idFoto) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from foto where idFoto=?;");
		ps.setInt(1, idFoto);
		ResultSet resultSet=ps.executeQuery();
		FotoBean fotoBean=null;
		if (resultSet.next()) {
			fotoBean= new FotoBean();
			fotoBean.setIdFoto(resultSet.getInt("idFoto"));
			fotoBean.setPath(resultSet.getString("path"));
			fotoBean.setPrezzo(resultSet.getDouble("prezzo"));
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return fotoBean;
	}
	
	public void doSaveOrUpdate(FotoBean fotoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from foto where idFoto=?;");
		ps.setInt(1, fotoBean.getIdFoto());
		ResultSet resultSet=ps.executeQuery();
		if (resultSet.next()) {
			ps=(PreparedStatement) con.prepareStatement("update foto set path=?, prezzo=? where idFoto=?;");
			ps.setString(1, fotoBean.getPath());
			ps.setDouble(2, fotoBean.getPrezzo());
			ps.setInt(3, fotoBean.getIdFoto());
			ps.executeUpdate();
		}
		else {
			PreparedStatement preparedStatement;
			if (fotoBean.getPrezzo()==null) {
				preparedStatement=(PreparedStatement) con.prepareStatement("insert into foto (idFoto, path) values(?, ?);");
				preparedStatement.setInt(1, fotoBean.getIdFoto());
				preparedStatement.setString(2, fotoBean.getPath());
			}
			else {
				this.doSave(fotoBean);
			}
			ps.executeUpdate();
			ps.close();
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public ArrayList<FotoBean> doRetrieveByAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<FotoBean> listaFoto= new ArrayList<FotoBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select * from foto;");
		while (resultSet.next()) {
			FotoBean fotoBean= new FotoBean();
			fotoBean.setIdFoto(resultSet.getInt("idFoto"));
			fotoBean.setPath(resultSet.getString("path"));
			fotoBean.setPrezzo(resultSet.getDouble("prezzo"));
			listaFoto.add(fotoBean);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return listaFoto;	
	}
	
	public int doRetrieveMaxId() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<FotoBean> listaFoto = new ArrayList<FotoBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select MAX(idFoto) as maxid from clipshot.foto;");
		int id; //id da ritornare
		if(!resultSet.next()) {
			
			id= 0;
			query.close();
			DriverManagerConnectionPool.releaseConnection(con);
		}
		else {
			
			id=resultSet.getInt("maxid");
			query.close();
			DriverManagerConnectionPool.releaseConnection(con);
		}	
		return id;
	}
	
	
}
