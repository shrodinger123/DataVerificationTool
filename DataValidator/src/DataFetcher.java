import java.sql.*;
public class DataFetcher {

	public int verifyRowCount(Connection dbConn, String tbName, String rowCount ) {
		int count = -1;

		// Create the Statement
	     Statement stmt;
		try {
			stmt = dbConn.createStatement();
			String row_count_stmt = "Select count(*) as \"rowCount\" from "+tbName;
		      ResultSet rs = stmt.executeQuery(row_count_stmt); 
		      rs.next();
		      count =rs.getInt("rowCount");
		      rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return count;
	}
}
