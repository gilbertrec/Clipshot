package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Model.PostBean;
import Model.UtenteBean;

public class PostBeanDao {
	
	public PostBeanDao() {
	}
	
	public void doSave(PostBean postBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into post values(?, ?, ?, ?, ?, ?, ?);");
		ps.setString(1, postBean.getIdPost());
		ps.setString(2, postBean.getIdUtente());
		ps.setString(3, postBean.getIdFoto());
		ps.setString(4, postBean.getDescrizione());
		ps.setDate(5, postBean.getData());
		ps.setTime(6, postBean.getOra());
		ps.setString(7, postBean.getStato());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public void doDelete (PostBean postBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from post where idPost=? and idUtente=?;");
		ps.setString(1, postBean.getIdPost());
		ps.setString(2, postBean.getIdUtente());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public PostBean doRetrieveByKey(String idPost, String idUtente) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from segui where idPost=? and idUtente=?;");
		ps.setString(1, idPost);
		ps.setString(2, idUtente);
		ResultSet resultSet=ps.executeQuery();
		PostBean postBean= new PostBean();
		if (resultSet.next()) {
			postBean.setIdPost(resultSet.getString("idPost"));
			postBean.setIdUtente(resultSet.getString("idUtente"));
			postBean.setIdFoto(resultSet.getString("idFoto"));
			postBean.setDescrizione(resultSet.getString("descrizione"));
			postBean.setData(resultSet.getDate("data"));
			postBean.setOra(resultSet.getTime("ora"));
			postBean.setStato(resultSet.getString("stato"));
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return postBean;
	}
	
	public void doSaveOrUpdate(PostBean postBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from segui where idPost=? and idUtente=?;");
		ps.setString(1, postBean.getIdPost());
		ps.setString(2, postBean.getIdUtente());
		ResultSet resultSet=ps.executeQuery();
		if (resultSet.next()) {
			ps=(PreparedStatement) con.prepareStatement("update segui set idFoto=?, descrizione=?, data=?, ora=?, stato=? where idPost=? and idUtente=?;");
			ps.setString(1, postBean.getIdFoto());
			ps.setString(2, postBean.getDescrizione());
			ps.setDate(3, postBean.getData());
			ps.setTime(4, postBean.getOra());
			ps.setString(5, postBean.getStato());
			ps.setString(6, postBean.getIdPost());
			ps.setString(7, postBean.getIdUtente());
			ps.executeUpdate();
		}
		else {
			ps=(PreparedStatement) con.prepareStatement("insert into post values(?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, postBean.getIdPost());
			ps.setString(2, postBean.getIdUtente());
			ps.setString(3, postBean.getIdFoto());
			ps.setString(4, postBean.getDescrizione());
			ps.setDate(5, postBean.getData());
			ps.setTime(6, postBean.getOra());
			ps.setString(7, postBean.getStato());
			ps.executeUpdate();
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public ArrayList<PostBean> doRetrieveAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<PostBean> listaPost= new ArrayList<PostBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select *from segui");
		while (resultSet.next()) {
			PostBean postBean= new PostBean();
			postBean.setIdPost(resultSet.getString("idPost"));
			postBean.setIdUtente(resultSet.getString("idUtente"));
			postBean.setIdFoto(resultSet.getString("idFoto"));
			postBean.setDescrizione(resultSet.getString("descrizione"));
			postBean.setData(resultSet.getDate("data"));
			postBean.setOra(resultSet.getTime("ora"));
			postBean.setStato(resultSet.getString("stato"));
			listaPost.add(postBean);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return listaPost;
	}
}
