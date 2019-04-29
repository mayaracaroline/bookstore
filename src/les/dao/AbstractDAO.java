package les.dao;

import java.sql.Connection;

import util.Conexao;

public class AbstractDAO {
	
	Connection conexao  = new Conexao().getConnection();
	
//	public Connection getConnection () {
//	  
//	  if(conexao == null) {
//	    conexao = new Conexao().getConnection();
//	   }
//	  
//	  return conexao;
//	}

}


