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
			String tgt_cols) 
	{
		Boolean result = true;
		Statement stmt;
		try {
			/* Initialize ResultSet for source table data */
			stmt = src_db_conn.createStatement();
			String src_select_stmt = "Select "+src_cols+" from "+src_tb_name + " order by "+src_order_by_cols;
			log.info(src_select_stmt);
		    ResultSet src_rs = stmt.executeQuery(src_select_stmt); 
		    ResultSetMetaData src_rsmd = src_rs.getMetaData();
		    int src_col_count = src_rsmd.getColumnCount();
		    
		    /* Initialize ResultSet for target table data */
			stmt = tgt_db_conn.createStatement();
			String tgt_select_stmt = "Select "+tgt_cols+" from "+tgt_tb_name + " order by "+tgt_order_by_cols;
		    ResultSet tgt_rs = stmt.executeQuery(tgt_select_stmt); 
		    ResultSetMetaData tgt_rsmd = tgt_rs.getMetaData();
		    int tgt_col_count = tgt_rsmd.getColumnCount();
		    
		    /* Exit if source and targt table column count not matching */
		    if(src_col_count!=tgt_col_count) {
		    	log.error("Fail: source and target table column count not matching");
		    	log.error("aborting...");
		    	src_rs.close();
		    	tgt_rs.close();
		    	src_db_conn.close();
		    	tgt_db_conn.close();
		    	System.exit(1);
		    }
		    

		    
		    while(src_rs.next()&&tgt_rs.next()) {
			    /* Store source and target row in a string */
			    StringBuilder src_row =new StringBuilder("\"");
			    StringBuilder tgt_row =new StringBuilder("\"");
		    	for (int i=1;i<=src_col_count;i++) {
		    		src_row=src_row.append(src_rs.getString(i)+",");
		    		tgt_row=tgt_row.append(tgt_rs.getString(i)+",");
		    	}
		    	
		    	/* Remove comma at the end and replace with double quote */
		    	src_row.setCharAt(src_row.length()-1,'\"');
		    	tgt_row.setCharAt(tgt_row.length()-1, '\"');
		    	
		    	log.info(" src row: "+src_row);
		    	log.info(" tgt row: "+tgt_row);
		    	
		    	if(src_row.toString().equals(tgt_row.toString())){
		    	log.info("Success: rows match!" );
		    	result=result&&true;
		    	}
		    	else {
		    		result=result&&false;
		    		log.error("Fail: rows don't match!" );
		    	}
		    	
		    }
		    
		    /* Close ResultSets */
		    src_rs.close();
		    tgt_rs.close();
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return result;
	}
	
	
}
