package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Model.AcquistiBean;

public class AcquistiBeanDao {
	
	public AcquistiBeanDao() {
		
	}
	
	public void doSave (AcquistiBean acquistiBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into acquisto(?, ?, ?);");
		ps.setString(1, acquistiBean.getIdUtente());
		ps.setString(2, acquistiBean.getIdFoto());
		ps.setDate(3, acquistiBean.getData());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public void doDelete (AcquistiBean acquistiBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from acquisto where idUtente=? and idFoto=?;");
		ps.setString(1, acquistiBean.getIdUtente());
		ps.setString(2, acquistiBean.getIdFoto());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public AcquistiBean doRetrieveByKey (String idUtente, String idFoto) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from acquisto where idUtente=? and idFoto=?;");
		ps.setString(1, idUtente);
		ps.setString(2, idFoto);
		ResultSet resultSet=ps.executeQuery();
		AcquistiBean acquistiBean= new AcquistiBean();
		if (resultSet.next()) {
			acquistiBean.setIdUtente(resultSet.getString("idUtente"));
			acquistiBean.setIdFoto(resultSet.getString("idFoto"));
			acquistiBean.setData(resultSet.getDate("data"));
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return acquistiBean;
	}
	
	public void doSaveOrUpdate (AcquistiBean acquistiBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement)con.prepareStatement("select * from acquisto where idUtente=? and idFoto=?;");
		ps.setString(1, acquistiBean.getIdUtente());
		ps.setString(2, acquistiBean.getIdFoto());
		ResultSet resultSet=ps.executeQuery();
		if (resultSet.next()) {
			ps=(PreparedStatement) con.prepareStatement("update acquisto set dataAcquisto=? where idUtente=? and idFoto=?");
			ps.setDate(1, acquistiBean.getData());
			ps.setString(2, acquistiBean.getIdUtente());
			ps.setString(3, acquistiBean.getIdFoto());
			ps.executeUpdate();
		}
		else {
			ps=(PreparedStatement) con.prepareStatement("insert into acquisto(?, ?, ?);");
			ps.setString(1, acquistiBean.getIdUtente());
			ps.setString(2, acquistiBean.getIdFoto());
			ps.setDate(3, acquistiBean.getData());
			ps.executeUpdate();
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public ArrayList<AcquistiBean> doRetrieveByAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<AcquistiBean> listaAcquisti= new ArrayList<AcquistiBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select * from acquisto");
		while (resultSet.next()) {
			AcquistiBean acquistiBean= new AcquistiBean();
			acquistiBean.setIdUtente(resultSet.getString("idUtente"));
			acquistiBean.setIdFoto(resultSet.getString("idFoto"));
			acquistiBean.setData(resultSet.getDate("data"));
			listaAcquisti.add(acquistiBean);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return listaAcquisti;
	}

}
