package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.GeneroLiterario;
import dominio.Livro;
import util.Resultado;

public class DAOGenerosLivro extends AbstractDAO implements IDAO{

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		String sql = "INSERT into GENEROS_LIVRO (glv_livro_id, glv_genero_id, glv_genero_descricao) values (? , ?, (SELECT gen_descricao FROM generos WHERE gen_id = ? ))";
		Livro livro = (Livro) entidade;
		Resultado resultado = new Resultado();
		
		try {
			PreparedStatement statement = conexao.prepareStatement(sql);

			for (int i = 0; i < livro.getCategorias().size(); i++) {
								

				statement.setInt(1, livro.getId().intValue());
				statement.setInt(2, livro.getCategorias().get(i).getId().intValue());
				statement.setInt(3, livro.getCategorias().get(i).getId().intValue());
				statement.execute();
			}
			
			statement.close();
			
			resultado.setResultado(livro);
			resultado.sucesso("Sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro ao salvar na tabela generos_livro");
		} finally {      
      try {
        conexao.close();
      } catch (SQLException e) {
        // LOGGING
        e.printStackTrace();
      }
    }
		
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		Resultado resultado = new Resultado();
		ArrayList<GeneroLiterario> generos = new ArrayList<>();
		ResultSet queryResult;
		
		String sql = "SELECT * FROM GENEROS_LIVRO WHERE 1=1";
		
		if (null != livro.getId()) {
		  sql += " AND glv_livro_id = ?";
		}
			
		try {
			
			PreparedStatement preparedStatment = conexao.prepareStatement(sql);	
			preparedStatment.setInt(1,livro.getId().intValue());
			for (int i = 0; i < livro.getCategorias().size(); i++) {						
				queryResult = preparedStatment.executeQuery();
				
				while(queryResult.next()) {
					GeneroLiterario g = new GeneroLiterario();
					
					g.setId(queryResult.getInt("glv_genero_id"));
					g.setDescricao(queryResult.getString("glv_genero_descricao"));
					
					generos.add(g);
				}
					
				queryResult.close();
			}
			
			preparedStatment.close();
			livro.setCategorias(generos);
			resultado.setResultado(livro);
			resultado.sucesso("");
						
			return resultado;
		
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro de consulta.");
			
			return resultado;
		} finally {      
      try {
        conexao.close();
      } catch (SQLException e) {
        // LOGGING
        e.printStackTrace();
      }
    }
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		String sql = "DELETE FROM generos_livro WHERE glv_livro_id = ?";
		Livro livro = (Livro) entidade;
		Resultado resultado = new Resultado();
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, livro.getId().intValue());
			statement.execute();
			statement.close();
			
			resultado.setResultado(livro);
			resultado.sucesso("Registro excluido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro ao excluir registro na tabela generos_livro");
		} finally {      
      try {
        conexao.close();
      } catch (SQLException e) {
        // LOGGING
        e.printStackTrace();
      }
    }
		
		return resultado;
	}

  @Override
  public Resultado inativar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

}
