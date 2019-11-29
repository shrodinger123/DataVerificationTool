import java.sql.*;

import org.apache.log4j.Logger;
public class DataFetcher {

	private static final Logger log = Logger.getLogger(DataFetcher.class);
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
	
	
	public boolean verifyDataMatch(
			Connection src_db_conn,
			String src_db_type,
			String src_tb_name,
			String src_order_by_cols,
			String src_cols,
			Connection tgt_db_conn,
			String tgt_db_type,
			String tgt_tb_name,
			String tgt_order_by_cols,
			String tgt_cols) {
		
		return true;
	}
	
}
