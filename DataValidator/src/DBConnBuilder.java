import java.sql.*;
public class DBConnBuilder {

	private Connection conn;
	
	public Connection getDB2LUWConn(String user, String pwd, String host, String port, String dbname) {
		
		try {
			String url="jdbc:db2://"+host+":"+port+"/"+dbname;
			
			// Load the driver
		      Class.forName("com.ibm.db2.jcc.DB2Driver");       
		      conn = DriverManager.getConnection (url, user, pwd); 
		      
		     // Commit changes automatically
		      conn.setAutoCommit(true);
		      
		    
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getOracleConn(String user, String pwd, String host, String port, String dbname) {
		
		try {
			String url="jdbc:oracle:thin:@"+host+":"+port+":"+dbname;
			
			// Load the driver
		      Class.forName("oracle.jdbc.driver.OracleDriver");       
		      conn = DriverManager.getConnection (url, user, pwd); 
		      
		     // Commit changes automatically
		      conn.setAutoCommit(true);
		      
		    
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getPgConn(String user, String pwd, String host, String port, String dbname) {
		try {
			String url="jdbc:postgresql://"+host+":"+port+"/"+dbname;
			
			// Load the driver
		      Class.forName("org.postgresql.Driver");       
		      conn = DriverManager.getConnection (url, user, pwd); 
		      
		     // Commit changes automatically
		      conn.setAutoCommit(true);
		      
		    
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public Connection getMSSConn(String user, String pwd, String host, String port, String dbname) {
		try {
			String url="jdbc:sqlserver://"+host+":"+port+";databaseName="+dbname;
			
			// Load the driver
		      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");       
		      conn = DriverManager.getConnection (url, user, pwd); 
		      
		     // Commit changes automatically
		      conn.setAutoCommit(true);
		      
		    
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
		
	public Connection getMYSConn(String user, String pwd, String host, String port, String dbname) {
		return conn;
	}
}
