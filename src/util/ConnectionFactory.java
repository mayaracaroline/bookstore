package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {
  
	public static Connection getConnection(){
		
		String url = "jdbc:mysql://localhost:3306/livraria";
		
		try {
		  
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, "root", "");
			
		} catch(Exception e){
		  
			throw new RuntimeException(e);
		}
	
	}
	
	public static void closeConnection(PreparedStatement stmt, Connection conn) {
	  if(stmt != null) {
	    
	    try {
	      
	      stmt.close();
	      
	    } catch (SQLException e ) {
	      e.printStackTrace();
	    }
	    
	  }
	  
	  if(conn != null) {
	   
	    try {
	      
        conn.close();
        
      } catch (SQLException e) {
        
        e.printStackTrace();
      }
	     
	  }
	}
}
