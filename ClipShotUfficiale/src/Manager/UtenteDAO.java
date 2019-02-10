/**
 * @author Adalgiso Della Calce
 */
package Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import Model.UtenteBean;

public class UtenteDAO {
	
	public UtenteDAO() {}
	
	public void doSave(UtenteBean utente) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into clipshot.utente values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		ps.setString(1, utente.getIdUtente());
		ps.setString(2, utente.getPassword());
		ps.setString(3, utente.getEmail());
		ps.setString(4, utente.getNome());
		ps.setString(5, utente.getCognome());
		ps.setString(6, utente.getStringData());
		ps.setString(7, utente.getSesso());
		ps.setString(8, utente.getIndirizzo());
		ps.setString(9, utente.getStato());
		ps.setString(10, utente.getTipo());
		ps.setString(11, utente.getFotoProfilo());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public UtenteBean doRetrieveByKey(String key) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		UtenteBean utente= new UtenteBean();
		ResultSet result;
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from clipshot.utente where idUtente=?;");
		ps.setString(1, key);
		result=ps.executeQuery();
		if (result.next()) {
			utente.setIdUtente(result.getString("idUtente"));
			utente.setPassword(result.getString("password"));
			utente.setEmail(result.getString("email"));
			utente.setNome(result.getString("nome"));
			utente.setCognome(result.getString("cognome"));
			Date dataFrom=result.getDate("dataNascita");
			GregorianCalendar data= new GregorianCalendar();
			data.setTime(dataFrom);
			utente.setDataNascita(data);
			utente.setSesso(result.getString("sesso"));
			utente.setIndirizzo(result.getString("indirizzo"));
			utente.setStato(result.getString("stato"));
			utente.setTipo(result.getString("tipo"));
			utente.setFotoProfilo(result.getString("fotoProfilo"));
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return utente;
	}
	
	public void doSaveOrUpdate(UtenteBean utente) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from clipshot.utente where idUtente=?;");
		ps.setString(1, utente.getIdUtente());
		ResultSet resultSet=ps.executeQuery();
		if (resultSet.next()) {
			ps=(PreparedStatement) con.prepareStatement("update clipshot.utente set password=?, email=?, nome=?, cognome=?, dataNascita=?, sesso=?, indirizzo=?, stato=?, tipo=?, fotoProfilo=? where idUtente=?;");
			ps.setString(1, utente.getPassword());
			ps.setString(2, utente.getEmail());
			ps.setString(3, utente.getNome());
			ps.setString(4, utente.getCognome());
			ps.setString(5, utente.getStringData());
			ps.setString(6, utente.getSesso());
			ps.setString(7, utente.getIndirizzo());
			ps.setString(8, utente.getStato());
			ps.setString(9, utente.getTipo());
			ps.setString(10, utente.getFotoProfilo());
			ps.setString(11, utente.getIdUtente());
			ps.executeUpdate();
		}
		else {
			doSave(utente);
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public ArrayList<UtenteBean> doRetrieveByCond (UtenteBean utenteBean) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<UtenteBean> listaUtente= new ArrayList<UtenteBean>();
		String sql="select * from clipshot.utente where ";
		int flag=0;
		if (utenteBean.getIdUtente()!=null) {
			sql=sql+"idUtente='"+utenteBean.getIdUtente()+"'";
			flag=1;
		}
		if (utenteBean.getEmail()!=null) {
			if (flag==1) {
				sql=sql+" and email'"+utenteBean.getEmail()+"'";
			}
			else {
				sql=sql+"email='"+utenteBean.getEmail()+"'";
				flag=1;
			}
		}
		if (utenteBean.getPassword()!=null) {
			if (flag==1) {
				sql=sql+" and password='"+utenteBean.getPassword()+"'";
			}
			else {
				sql=sql+" password='"+utenteBean.getPassword()+"'";
				flag=1;
			}
		}
		if (utenteBean.getNome()!=null) {
			if (flag==1) {
				sql=sql+" and nome='"+utenteBean.getNome()+"'";
			}
			else {
				sql=sql+" nome='"+utenteBean.getNome()+"'";
				flag=1;
			}
		}
		if (utenteBean.getCognome()!=null) {
			if (flag==1) {
				sql=sql+" and cognome='"+utenteBean.getCognome()+"'";
			}
			else {
				sql=sql+" cognome='"+utenteBean.getCognome()+"'";
				flag=1;
			}
		}
		if (utenteBean.getDataNascita()!=null) {
			if (flag==1) {
				sql=sql+" and dataNascita='"+utenteBean.getDataNascita()+"'";
			}
			else {
				sql=sql+" dataNascita='"+utenteBean.getDataNascita()+"'";
				flag=1;
			}
		}
		if (utenteBean.getSesso()!=null) {
			if (flag==1) {
				sql=sql+" and sesso='"+utenteBean.getSesso()+"'";
			}
			else {
				sql=sql+" sesso='"+utenteBean.getSesso()+"'";
				flag=1;
			}
		}
		if (utenteBean.getIndirizzo()!=null) {
			if (flag==1) {
				sql=sql+" and indirizzo='"+utenteBean.getIndirizzo()+"'";
			}
			else {
				sql=sql+" indirizzo='"+utenteBean.getIndirizzo()+"'";
				flag=1;
			}
		}
		if (utenteBean.getFotoProfilo()!=null) {
			if (flag==1) {
				sql=sql+" and fotoProfilo='"+utenteBean.getFotoProfilo()+"'";
			}
			else {
				sql=sql+" fotoProfilo='"+utenteBean.getFotoProfilo()+"'";
				flag=1;
			}
		}
		if (utenteBean.getStato()!=null) {
			if (flag==1) {
				sql=sql+" and stato='"+utenteBean.getStato()+"'";
			}
			else {
				sql=sql+" stato='"+utenteBean.getStato()+"'";
				flag=1;
			}
		}
		if (utenteBean.getTipo()!=null) {
			if (flag==1) {
				sql=sql+" and tipo='"+utenteBean.getTipo()+"'";
			}
			else {
				sql=sql+" tipo='"+utenteBean.getTipo()+"'";
				flag=1;
			}
		}
		sql=sql+";";
		Statement query=(Statement) con.createStatement();
		ResultSet result=query.executeQuery(sql);
		while (result.next()) {
			UtenteBean utente= new UtenteBean();
			utente.setIdUtente(result.getString("idUtente"));
			utente.setPassword(result.getString("password"));
			utente.setEmail(result.getString("email"));
			utente.setNome(result.getString("nome"));
			utente.setCognome(result.getString("cognome"));
			Date dataFrom=result.getDate("dataNascita");
			GregorianCalendar data=new GregorianCalendar();
			data.setTime(dataFrom);
			utente.setDataNascita(data);
			utente.setSesso(result.getString("sesso"));
			utente.setIndirizzo(result.getString("indirizzo"));
			utente.setStato(result.getString("stato"));
			utente.setTipo(result.getString("tipo"));
			utente.setFotoProfilo(result.getString("fotoProfilo"));
			listaUtente.add(utente);
		}
		return listaUtente;
	}
	
	public ArrayList<UtenteBean> doRetrieveAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<UtenteBean> listaUtente= new ArrayList<UtenteBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet result=query.executeQuery("select * from clipshot.utente;");
		while (result.next()) {
			UtenteBean utente= new UtenteBean();
			utente.setIdUtente(result.getString("idUtente"));
			utente.setPassword(result.getString("password"));
			utente.setEmail(result.getString("email"));
			utente.setNome(result.getString("nome"));
			utente.setCognome(result.getString("cognome"));
			Date dataFrom=result.getDate("dataNascita");
			GregorianCalendar data= new GregorianCalendar();
			data.setTime(dataFrom);
			utente.setDataNascita(data);
			utente.setSesso(result.getString("sesso"));
			utente.setIndirizzo(result.getString("indirizzo"));
			utente.setStato(result.getString("stato"));
			utente.setTipo(result.getString("tipo"));
			utente.setFotoProfilo(result.getString("fotoProfilo"));
			listaUtente.add(utente);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return listaUtente;
	}
	
	public void doDelete(UtenteBean utente) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from clipshot.utente where idUtente=?;");
		ps.setString(1, utente.getIdUtente());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
