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

public class FotoBeanDao {
	
	public FotoBeanDao() {
		
	}
	
	public void doSave (FotoBean fotoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps;
		if (fotoBean.getPrezzo()==null) {
			ps=(PreparedStatement) con.prepareStatement("insert into foto (idFoto, path) values(?, ?);");
			ps.setInt(1, fotoBean.getIdFoto());
			ps.setString(2, fotoBean.getPath());
		}
		else {
		ps=(PreparedStatement) con.prepareStatement("insert into foto values(?, ?, ?);");
		ps.setInt(1, fotoBean.getIdFoto());
		ps.setString(2, fotoBean.getPath());
		ps.setDouble(3,fotoBean.getPrezzo());
		}
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public void doDelete (FotoBean fotoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from foto where idFoto=?;");
		ps.setInt(1, fotoBean.getIdFoto());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public FotoBean doRetrieveByKey (int idFoto) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from foto where idFoto=?;");
		ps.setInt(1, idFoto);
		ResultSet resultSet=ps.executeQuery();
		FotoBean fotoBean= new FotoBean();
		if (resultSet.next()) {
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
				preparedStatement=(PreparedStatement) con.prepareStatement("insert into foto values(?, ?, ?);");
				preparedStatement.setInt(1, fotoBean.getIdFoto());
				preparedStatement.setString(2, fotoBean.getPath());
				preparedStatement.setDouble(3,fotoBean.getPrezzo());
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
	
	
}
