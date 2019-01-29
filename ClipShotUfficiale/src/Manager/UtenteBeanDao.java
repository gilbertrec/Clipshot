package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Model.UtenteBean;

public class UtenteBeanDao {
	
	public UtenteBeanDao() {	
	}
	
	public void doSave(UtenteBean utente) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into utente values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		ps.setString(1, utente.getIdUtente());
		ps.setString(2, utente.getPassword());
		ps.setString(3, utente.getEmail());
		ps.setString(4, utente.getNome());
		ps.setString(5, utente.getCognome());
		ps.setDate(6, utente.getDataNascita());
		ps.setString(7, utente.getSesso());
		ps.setString(8, utente.getIndirizzo());
		ps.setString(9, utente.getStato());
		ps.setString(10, utente.getTipo());
		ps.setString(11, utente.getFotoProfilo());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public void doDelete(UtenteBean utente) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from utente where idUtente=?;");
		ps.setString(1, utente.getIdUtente());
		ps.executeUpdate();
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public UtenteBean doRetrieveByKey(String key) throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		UtenteBean utente= new UtenteBean();
		ResultSet result;
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from utente where idUtente=?;");
		ps.setString(1, key);
		result=ps.executeQuery();
		if (result.next()) {
			utente.setIdUtente(result.getString("idUtente"));
			utente.setPassword(result.getString("password"));
			utente.setEmail(result.getString("email"));
			utente.setNome(result.getString("nome"));
			utente.setCognome(result.getString("cognome"));
			utente.setDataNascita(result.getDate("dataNascita"));
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
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from utente where idUtente=?;");
		ps.setString(1, utente.getIdUtente());
		ResultSet resultSet=ps.executeQuery();
		if (resultSet.next()) {
			ps=(PreparedStatement) con.prepareStatement("update utente set password=?, email=?, nome=?, cognome=?, dataNascita=?, sesso=?, indirizzo=?, stato=?, tipo=?, fotoProfilo=? where idUtente=?;");
			ps.setString(1, utente.getPassword());
			ps.setString(2, utente.getEmail());
			ps.setString(3, utente.getNome());
			ps.setString(4, utente.getCognome());
			ps.setDate(5, utente.getDataNascita());
			ps.setString(6, utente.getSesso());
			ps.setString(7, utente.getIndirizzo());
			ps.setString(8, utente.getStato());
			ps.setString(9, utente.getTipo());
			ps.setString(10, utente.getFotoProfilo());
			ps.setString(11, utente.getIdUtente());
			ps.executeUpdate();
		}
		else {
			ps=(PreparedStatement) con.prepareStatement("insert into utente values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, utente.getIdUtente());
			ps.setString(2, utente.getPassword());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getNome());
			ps.setString(5, utente.getCognome());
			ps.setDate(6, utente.getDataNascita());
			ps.setString(7, utente.getSesso());
			ps.setString(8, utente.getIndirizzo());
			ps.setString(9, utente.getStato());
			ps.setString(10, utente.getTipo());
			ps.setString(11, utente.getFotoProfilo());
			ps.executeUpdate();
		}
		ps.close();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public ArrayList<UtenteBean> doRetrieveAll() throws SQLException {
		Connection con=DriverManagerConnectionPool.getConnection();
		ArrayList<UtenteBean> listaUtente= new ArrayList<UtenteBean>();
		Statement query=(Statement) con.createStatement();
		ResultSet result=query.executeQuery("select * from utente;");
		while (result.next()) {
			UtenteBean utente= new UtenteBean();
			utente.setIdUtente(result.getString("idUtente"));
			utente.setPassword(result.getString("password"));
			utente.setEmail(result.getString("email"));
			utente.setNome(result.getString("nome"));
			utente.setCognome(result.getString("cognome"));
			utente.setDataNascita(result.getDate("dataNascita"));
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
	
	
	
}
