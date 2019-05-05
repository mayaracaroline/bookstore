package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
  private Connection conn;
  
	public Connection getConnection(){
		
		String url = "jdbc:mysql://localhost:3306/livraria";
		
		if (null == conn) {
  		try{
  		  
  			Class.forName("com.mysql.jdbc.Driver");
  			conn = DriverManager.getConnection(url, "root", "");
  			
  		}catch(Exception e){
  			System.out.println("Conexão não abriu");
  			throw new RuntimeException(e);
  		}
		}
  	return conn;
	}
}
