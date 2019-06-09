package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.GeneroLiterario;
import dominio.Livro;
import util.ConnectionFactory;
import util.Resultado;

public class DAOGeneroLivro extends AbstractDAO implements IDAO {

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		Resultado resultado = new Resultado();
		ArrayList<GeneroLiterario> generos = new ArrayList<>();
		ResultSet queryResult;
    conexao = ConnectionFactory.getConnection();
    PreparedStatement preparedStatment = null;
		
//		String sql = "SELECT * FROM GENEROS_LIVRO WHERE glv_livro_id = ?";
		String sql = "SELECT * FROM GENEROS WHERE gen_id = ?";		
		try {
			
			preparedStatment = conexao.prepareStatement(sql);	
			
			for (int i = 0; i < livro.getCategorias().size(); i++) {
				preparedStatment.setInt(1,livro.getCategorias().get(i).getId().intValue());
				
				queryResult = preparedStatment.executeQuery();
				
				GeneroLiterario g = new GeneroLiterario();
				
				while(queryResult.next()) {
					g.setId(queryResult.getInt("gen_id"));
					g.setDescricao(queryResult.getString("gen_descricao"));						
				}
			
				generos.add(g);
				
				queryResult.close();
			}
			
			livro.setCategorias(generos);
			
			resultado.setResultado(livro);
			resultado.sucesso("");			
			
			return resultado;
		
			
		} catch (Exception e) {
			resultado.erro("Erro de consulta.");
			e.printStackTrace();
			return resultado;
		} finally {
      ConnectionFactory.closeConnection(preparedStatment, conexao);
    } 
	}

	public Resultado salvar(EntidadeDominio entidade) {
		String sql = "INSERT into GENEROS_LIVRO (glv_livro_id, glv_genero_id, glv_genero_descricao) values (? , ?, (SELECT gen_descricao FROM generos WHERE gen_id = ? ))";
		Livro livro = (Livro) entidade;
		Resultado resultado = new Resultado();
    conexao = ConnectionFactory.getConnection();
    PreparedStatement statement = null;
    
		try {
			statement = conexao.prepareStatement(sql);

			for (int i = 0; i < livro.getCategorias().size(); i++) {
				
				Resultado result = this.consultar(livro);
				
				livro = (Livro) result.getResultado();
				statement.setInt(1, livro.getId().intValue());
				statement.setInt(2, livro.getCategorias().get(i).getId().intValue());
				statement.setString(3, livro.getCategorias().get(i).getDescricao());
				statement.execute();
			}
			
//			for (int i = 0; i < livro.getCategorias().size(); i++) {
//								
//				statement.setInt(1, livro.getId().intValue());
//				statement.setInt(2, livro.getCategorias().get(i).getId().intValue());
//				statement.setInt(3, livro.getCategorias().get(i).getId().intValue());
//				statement.execute();
//			}
			
			resultado.setResultado(livro);
			resultado.sucesso("Sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro ao salvar na tabela generos_livro");
		}  finally {
      ConnectionFactory.closeConnection(statement, conexao);
    }
		
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

  @Override
  public Resultado inativar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultarPorId(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public List<EntidadeDominio> consultarTodos() {
    Resultado resultado = new Resultado();
    conexao = ConnectionFactory.getConnection();
    String sql =  "SELECT * FROM GENEROS;";
    PreparedStatement pst = null;
    int contagem = 0;
    List<EntidadeDominio> generos = new ArrayList<EntidadeDominio>();
    
    try {
      pst = conexao.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        GeneroLiterario genero = new GeneroLiterario();
        genero.setId(rs.getInt("gen_id"));
        genero.setDescricao(rs.getString("gen_descricao"));
        generos.add(genero);
        contagem++;
      }
      
      
    } catch (Exception e) {
      
      e.printStackTrace();
    }
    
    return generos;
  }
	

}
