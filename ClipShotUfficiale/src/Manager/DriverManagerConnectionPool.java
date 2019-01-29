package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverManagerConnectionPool {
	private static ArrayList<Connection> listaConnessione;
	static {
		//istanzio la listaConnesione
		listaConnessione= new ArrayList<Connection>();
		//carico il jar
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection createDBConnection () throws SQLException {
		String url = "jdbc:mysql://localhost:3306/clipshot?autoReconect=true&useSSL=false"; //connessione attraverso jdbc, 3306: numero di porte
		String user = "root"; 
		String pwd= "root";
		Connection con=DriverManager.getConnection(url, user, pwd);
		con.setAutoCommit(true);
		return con;
	}
	
	public static synchronized Connection getConnection () throws SQLException {
		Connection con;
		if (listaConnessione.isEmpty())
			con=createDBConnection();
		else {
			con=listaConnessione.get(0);
			listaConnessione.remove(0);
			try {
				if (con.isClosed())
					con=getConnection();
			}
			catch (SQLException e){
				con.close();
				con=getConnection();
			}
		}
		return con;
	}
	
	public static synchronized void releaseConnection(Connection con) {
		if (con!=null)
			listaConnessione.add(con);
	}
}
