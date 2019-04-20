package les.dao;

import java.sql.Connection;

import util.Conexao;

public class AbstractDAO {
	
	Connection conexao = new Conexao().getConnection();

}


