/**
 * @author Adalgiso Della Calce
 */
package Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import Model.CommentoBean;

public class CommentoDAO {
	
	public CommentoDAO() {}
	
	public void doSave (CommentoBean commentoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into clipshot.commento values(?, ?, ?, ?, ?, ?);");
		ps.setString(1, commentoBean.getIdUtente());
		ps.setInt(2, commentoBean.getIdPost());
		ps.setString(3, commentoBean.getIdUtentePost());
		ps.setString(4, commentoBean.getStringData());
		ps.setString(5, commentoBean.getStringOra());
		ps.setString(6, commentoBean.getDescrizione());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public CommentoBean doRetrieveByKey (String idUtente, int idPost, String idUtentePost, GregorianCalendar data, GregorianCalendar ora) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from clipshot.commento where idUtente=? and idPost=? and idUtentePost=? and data=? and ora=?;");
		ps.setString(1, idUtente);
		ps.setInt(2, idPost);
		ps.setString(3, idUtentePost);
		int year, month, day, hour, minute, second;
		year=data.get(GregorianCalendar.YEAR);
		month=data.get(GregorianCalendar.MONTH)+1;
		day=data.get(GregorianCalendar.DAY_OF_MONTH);
		String dataCommentoPost=year+"-"+month+"-"+day;
		ps.setString(4, dataCommentoPost);
		hour=ora.get(GregorianCalendar.HOUR_OF_DAY);
		minute=ora.get(GregorianCalendar.MINUTE);
		second=ora.get(GregorianCalendar.SECOND);
		String oraCommentoPost=hour+":"+minute+":"+second;
		ps.setString(5, oraCommentoPost);
		ResultSet resultSet=ps.executeQuery();
		CommentoBean commentoBean= new CommentoBean();
		if (resultSet.next()) {
			commentoBean.setIdUtente(resultSet.getString("idUtente"));
			commentoBean.setIdPost(resultSet.getInt("idPost"));
			commentoBean.setIdUtentePost(resultSet.getString("idUtentePost"));
			Date dataFrom=resultSet.getDate("data");
			Time oraFrom=resultSet.getTime("ora");
			GregorianCalendar dataCommento= new GregorianCalendar();
			dataCommento.setTime(dataFrom);
			commentoBean.setData(dataCommento);
			GregorianCalendar oraCommento= new GregorianCalendar();
			oraCommento.setTime(oraFrom);
			commentoBean.setOra(oraCommento);
			commentoBean.setDescrizione(resultSet.getString("descrizione"));
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);	
		return commentoBean;
	}
	
	public void doSaveOrUpdate (CommentoBean commentoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from clipshot.commento where idUtente=? and idPost=? and idUtentePost=? and data=? and time=?;");
		ps.setString(1, commentoBean.getIdUtente());
		ps.setInt(2, commentoBean.getIdPost());
		ps.setString(3, commentoBean.getIdUtentePost());
		ps.setString(4, commentoBean.getStringData());
		ps.setString(5, commentoBean.getStringOra());
		ResultSet resultSet=ps.executeQuery();
		if (resultSet.next()) {
			ps=(PreparedStatement) con.prepareStatement("update clipshot.commento set descrizione=? where idUtente=? and idPost=? and idUtentePost=? and data=? and time=?;");
			ps.setString(1, commentoBean.getDescrizione());
			ps.setString(2, commentoBean.getIdUtente());
			ps.setInt(3, commentoBean.getIdPost());
			ps.setString(4, commentoBean.getIdUtentePost());
			ps.setString(5, commentoBean.getStringData());
			ps.setString(6, commentoBean.getStringOra());
			ps.executeUpdate();
		}
		else {
			doSave(commentoBean);
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);	
	}
	
	public ArrayList<CommentoBean> doRetrieveByAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<CommentoBean> listaCommenti=new ArrayList<CommentoBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet resultSet=query.executeQuery("select * from clipshot.commento;");
		while (resultSet.next()) {
			CommentoBean commentoBean= new CommentoBean();
			commentoBean.setIdUtente(resultSet.getString("idutente"));
			commentoBean.setIdPost(resultSet.getInt("idPost"));
			commentoBean.setIdUtentePost(resultSet.getString("idUtentePost"));
			Date dateFrom=resultSet.getDate("data");
			GregorianCalendar data= new GregorianCalendar();
			data.setTime(dateFrom);
			Time oraFrom=resultSet.getTime("ora");
			GregorianCalendar ora= new GregorianCalendar();
			ora.setTime(oraFrom);
			commentoBean.setData(data);
			commentoBean.setOra(ora);
			commentoBean.setDescrizione("descrizione");
			listaCommenti.add(commentoBean);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);	
		return listaCommenti;
	}

	public void doDelete(CommentoBean commentoBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from clipshot.commento where idUtente=? and idPost=? and idUtentePost=? and data=? and ora=?;");
		ps.setString(1, commentoBean.getIdUtente());
		ps.setInt(2, commentoBean.getIdPost());
		ps.setString(3, commentoBean.getIdUtentePost());
		ps.setString(4, commentoBean.getStringData());
		ps.setString(5, commentoBean.getStringOra());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);	
	}
}
