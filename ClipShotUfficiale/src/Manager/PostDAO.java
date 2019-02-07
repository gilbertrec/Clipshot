package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import Model.PostBean;

public class PostDAO {
	
	public PostDAO() {
	}
	
	public void doSave(PostBean postBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into post values(?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(1, postBean.getIdPost());
		ps.setString(2, postBean.getIdUtente());
		ps.setInt(3, postBean.getIdFoto());
		ps.setString(4, postBean.getDescrizione());
		ps.setString(5, postBean.getStringData());
		ps.setString(6, postBean.getStringOra());
		ps.setString(7, postBean.getStato());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public void doDelete (PostBean postBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from post where idPost=? and idUtente=?;");
		ps.setInt(1, postBean.getIdPost());
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
			postBean.setIdPost(resultSet.getInt("idPost"));
			postBean.setIdUtente(resultSet.getString("idUtente"));
			postBean.setIdFoto(resultSet.getInt("idFoto"));
			postBean.setDescrizione(resultSet.getString("descrizione"));
			Date dataFrom=resultSet.getDate("data");
			GregorianCalendar data= new GregorianCalendar();
			data.setTime(dataFrom);
			Time oraFrom=resultSet.getTime("ora");
			GregorianCalendar ora= new GregorianCalendar();
			ora.setTime(oraFrom);
			postBean.setData(data);
			postBean.setOra(ora);
			postBean.setStato(resultSet.getString("stato"));
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return postBean;
	}
	
	public void doSaveOrUpdate(PostBean postBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from segui where idPost=? and idUtente=?;");
		ps.setInt(1, postBean.getIdPost());
		ps.setString(2, postBean.getIdUtente());
		ResultSet resultSet=ps.executeQuery();
		if (resultSet.next()) {
			ps=(PreparedStatement) con.prepareStatement("update segui set idFoto=?, descrizione=?, data=?, ora=?, stato=? where idPost=? and idUtente=?;");
			ps.setInt(1, postBean.getIdFoto());
			ps.setString(2, postBean.getDescrizione());
			ps.setString(3, postBean.getStringData());
			ps.setString(4, postBean.getStringOra());
			ps.setString(5, postBean.getStato());
			ps.setInt(6, postBean.getIdPost());
			ps.setString(7, postBean.getIdUtente());
			ps.executeUpdate();
		}
		else {
			doSave(postBean);
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
			postBean.setIdPost(resultSet.getInt("idPost"));
			postBean.setIdUtente(resultSet.getString("idUtente"));
			postBean.setIdFoto(resultSet.getInt("idFoto"));
			postBean.setDescrizione(resultSet.getString("descrizione"));
			Date dataFrom=resultSet.getDate("data");
			GregorianCalendar data= new GregorianCalendar();
			data.setTime(dataFrom);
			Time oraFrom=resultSet.getTime("ora");
			GregorianCalendar ora=new GregorianCalendar();
			ora.setTime(oraFrom);
			postBean.setData(data);
			postBean.setOra(ora);
			postBean.setStato(resultSet.getString("stato"));
			listaPost.add(postBean);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return listaPost;
	}
}
