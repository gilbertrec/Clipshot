package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Model.CommentoBean;

public class CommentoBeanDao {
	
	public CommentoBeanDao() {
		
	}
	
	public void doSave (CommentoBean commentoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into commento values(?, ?, ?, ?, ?;");
		ps.setString(1, commentoBean.getIdUtente());
		ps.setString(2, commentoBean.getIdPost());
		ps.setDate(3, commentoBean.getData());
		ps.setTime(4, commentoBean.getOra());
		ps.setString(5, commentoBean.getDescrizione());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public void doDelete(CommentoBean commentoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from commento where idUtente=? and idPost=?;");
		ps.setString(1, commentoBean.getIdUtente());
		ps.setString(2, commentoBean.getIdPost());
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);	
	}
	
	public CommentoBean doRetrieveByKey (String idUtente, String idPost) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from commento where idUtente=? and idPost=?;");
		ps.setString(1, idUtente);
		ps.setString(2, idPost);
		ResultSet resultSet=ps.executeQuery();
		CommentoBean commentoBean= new CommentoBean();
		if (resultSet.next()) {
			commentoBean.setIdUtente(resultSet.getString("idUtente"));
			commentoBean.setIdPost(resultSet.getString("idPost"));
			commentoBean.setData(resultSet.getDate("data"));
			commentoBean.setOra(resultSet.getTime("ora"));
			commentoBean.setDescrizione(resultSet.getString("descrizione"));
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);	
		return commentoBean;
	}
	
	public void doSaveOrUpdate (CommentoBean commentoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from commento where idUtente=? and idPost=?;");
		ps.setString(1, commentoBean.getIdUtente());
		ps.setString(2, commentoBean.getIdPost());
		ResultSet resultSet=ps.executeQuery();
		if (resultSet.next()) {
			ps=(PreparedStatement) con.prepareStatement("update commento set data=?, ora=?, descrizione=? where idUtente=? and idPost=?;");
			ps.setDate(1, commentoBean.getData());
			ps.setTime(2, commentoBean.getOra());
			ps.setString(3, commentoBean.getDescrizione());
			ps.setString(4, commentoBean.getIdUtente());
			ps.setString(5, commentoBean.getIdPost());
			ps.executeUpdate();
		}
		else {
			ps=(PreparedStatement) con.prepareStatement("insert into commento values(?, ?, ?, ?, ?;");
			ps.setString(1, commentoBean.getIdUtente());
			ps.setString(2, commentoBean.getIdPost());
			ps.setDate(3, commentoBean.getData());
			ps.setTime(4, commentoBean.getOra());
			ps.setString(5, commentoBean.getDescrizione());
			ps.executeUpdate();
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);	
	}
	
	public ArrayList<CommentoBean> doRetrieveByAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<CommentoBean> listaCommenti=new ArrayList<CommentoBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select * from commento;");
		while (resultSet.next()) {
			CommentoBean commentoBean= new CommentoBean();
			commentoBean.setIdUtente(resultSet.getString("idutente"));
			commentoBean.setIdPost(resultSet.getString("idPost"));
			commentoBean.setData(resultSet.getDate("data"));
			commentoBean.setOra(resultSet.getTime("ora"));
			commentoBean.setDescrizione("descrizione");
			listaCommenti.add(commentoBean);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);	
		return listaCommenti;
	}

}
