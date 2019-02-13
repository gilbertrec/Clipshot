package Manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.mysql.jdbc.PreparedStatement;
import Model.StatisticheBean;

public class StatisticheDAO {
	public synchronized void doSave(StatisticheBean s) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into clipshot.statistiche (idUtente, dataInizio, dataFine, numeroVisualizzazioni) values (?, ?, ?, ?)");
		query.setString(1, s.getIdUtente());
		query.setString(2, s.getStringDataInizio());
		GregorianCalendar dataFine = s.getDataFine();
		dataFine.add(Calendar.DAY_OF_MONTH, 7); //7 giorni dopo
		s.setDataFine(dataFine);
		query.setString(3, s.getStringDataFine());
		query.setInt(4, s.getNumeroVisualizzazioni());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(StatisticheBean s) throws Exception {
		GregorianCalendar c_start=new GregorianCalendar();
		GregorianCalendar c_end=new GregorianCalendar();
		c_end.add(GregorianCalendar.DAY_OF_MONTH,7 );
		StatisticheBean temp = doRetrieveByKey(s.getIdUtente());
		if(temp==null) {
			doSave(s);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update clipshot.statistiche set numeroVisualizzazioni=? where idUtente =?, dataInizio = ?, dataFine = ?");	
			query.setString(2, s.getIdUtente());
			//gilberto ha detto che non fa niente se la funzione è fatta male
			GregorianCalendar today=new GregorianCalendar();
			
			if(new GregorianCalendar().before(s.getDataFine())) { //se la settimana di valutazione statistiche non è ancora finita
				query.setInt(1, s.getNumeroVisualizzazioni());
				query.setString(3, s.getStringDataInizio());
				query.setString(4, s.getStringDataFine());
			} else { 
				query.setInt(1, 1); //nuova settimana, nuovo conteggio partenda da 1 perchè viene chiamato la prima volta
				s.setDataInizio(s.getDataFine()); //inizia quando finisce la settimana precedente
				query.setString(3, s.getStringDataInizio());
				GregorianCalendar dataFine = s.getDataFine();
				dataFine.add(Calendar.DAY_OF_MONTH, 7); //7 giorni dopo
				s.setDataFine(dataFine);
				query.setString(4, s.getStringDataFine());
				
				
			}
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized StatisticheBean doRetrieveByKey(String idUtente) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		StatisticheBean s = new StatisticheBean();
		s.setIdUtente(idUtente);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.statistiche where idUtente=?, dataInizio = ?, dataFine = ?");
		query.setString(1, s.getIdUtente());
		query.setString(2, s.getStringDataInizio());
		query.setString(3, s.getStringDataFine());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		s.setIdUtente(result.getString("idUtente"));
		Date dataI = result.getDate("dataInizio");
		GregorianCalendar dataInizio = new GregorianCalendar();
		dataInizio.setTime(dataI);
		s.setDataInizio(dataInizio);
		Date dataF = result.getDate("dataFine");
		GregorianCalendar dataFine = new GregorianCalendar();
		dataFine.setTime(dataF);
		s.setDataInizio(dataFine);
		s.setNumeroVisualizzazioni(result.getInt("numeroVisualizzazioni"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return s;	
	}
	public synchronized ArrayList<StatisticheBean> doRetrieveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<StatisticheBean> statistiche = new ArrayList<StatisticheBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM clipshot.statistiche");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			StatisticheBean s = new StatisticheBean();
			s.setIdUtente(result.getString("idUtente"));
			Date dataI = result.getDate("dataInizio");
			GregorianCalendar dataInizio = new GregorianCalendar();
			dataInizio.setTime(dataI);
			s.setDataInizio(dataInizio);
			Date dataF = result.getDate("dataFine");
			GregorianCalendar dataFine = new GregorianCalendar();
			dataFine.setTime(dataF);
			s.setDataInizio(dataFine);
			s.setNumeroVisualizzazioni(result.getInt("numeroVisualizzazioni"));
			statistiche.add(s);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return statistiche;
	}
	public synchronized String doRetrieveByCondNLike(String idUtentePost, int idFoto) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		String numeroLike = null;
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT COUNT(l.idUtente) AS numeroLike FROM clipshot.post p JOIN clipshot.like l ON (l.idUtentePost = p.idUtente AND l.idPost = p.idPost) JOIN clipshot.foto f ON (f.idFoto = p.idFoto) WHERE l.idUtentePost = ? AND f.idFoto = ?");
		query.setString(1, idUtentePost);
		query.setInt(2, idFoto);
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		numeroLike = result.getString("numeroLike");
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return numeroLike;	
	}
	public synchronized String doRetrieveByCondNAcquisti(int idFoto) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		String numeroAcquisti = null;
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT COUNT(a.idFoto) AS numeroAcquisti FROM clipshot.acquisto a WHERE a.idFoto = ?");
		query.setInt(1, idFoto);
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		numeroAcquisti = result.getString("numeroAcquisti");
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return numeroAcquisti;	
	}
	public void doDelete(StatisticheBean s) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM clipshot.statistiche WHERE idUtente = ?, dataInizio = ?, dataFine = ?");
		query.setString(1, s.getIdUtente());
		query.setString(2, s.getStringDataInizio());
		query.setString(3, s.getStringDataFine());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
