package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.GeneroLiterario;
import dominio.Livro;
import util.Resultado;

public class DAOLivro extends AbstractDAO implements IDAO {

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		Livro livro = (Livro) entidade;
		String sql = "INSERT INTO LIVROS (liv_id," + 
				"liv_cod_barras," + 
				"liv_ativo," + 
				"liv_categoria_ativacao_id," + 
				"liv_justificativa_ativacao," + 
				"liv_autor," + 
				"liv_titulo," + 
				"liv_ano_publicacao," + 
				"liv_edicao," + 
				"liv_isbn," + 
				"liv_sinopse," + 
				"liv_editora," + 
				"liv_quantidade_paginas," + 
				"liv_altura," + 
				"liv_largura," + 
				"liv_peso," + 
				"liv_profundidade)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1,livro.getId().intValue());
			statement.setString(2,livro.getCodigoBarras());
			statement.setBoolean(3, livro.isAtivo());
			statement.setInt(4,livro.getCategoriaAtivacao());
			statement.setString(5, livro.getJustificativaAtivacao());
			statement.setString(6, livro.getAutor());
			statement.setString(7, livro.getTitulo());
			statement.setInt(8, livro.getAno());
			statement.setString(9, livro.getEdicao());
			statement.setString(10, livro.getIsbn());
			statement.setString(11, livro.getSinopse());
			statement.setString(12, livro.getEditora());
			statement.setInt(13, livro.getQuantidadePaginas());
			statement.setDouble(14,livro.getAltura());
			statement.setDouble(15,livro.getLargura());
			statement.setDouble(16,livro.getPeso());
			statement.setDouble(17,livro.getProfundidade());
			
      DAOProduto daoProduto = new DAOProduto();
      daoProduto.salvar(livro);
      
			statement.execute();
			
			DAOGeneroLivro daoGenero = new DAOGeneroLivro();	
			resultado = daoGenero.salvar(livro);	
			
			livro = (Livro) resultado.getResultado();
			
			statement.close();
			
			resultado.setResultado(livro);
			resultado.sucesso("Salvo com sucesso!");
			
			return resultado;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro salvar, por favor, refaça a operação.");
			return resultado;
		}	
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {

		Resultado resultado = new Resultado();
		Livro livro = (Livro) entidade;
		List<EntidadeDominio> listLivro = new ArrayList<EntidadeDominio>();
		String sql = null;
		PreparedStatement statement;
		
		
		try {
		  
      if(livro.getId().equals(BigInteger.ZERO)) {
        sql = "SELECT * FROM LIVROS ;";
        statement = conexao.prepareStatement(sql);
      } else {
        sql =  "SELECT * FROM LIVROS WHERE liv_id = ?";
        statement = conexao.prepareStatement(sql);
        statement.setInt(1, livro.getId().intValue());
      }
			
			ResultSet resultadoConsulta = statement.executeQuery();
			int contagem = 0;
			
			while(resultadoConsulta.next()) {
				
				Livro livroEncontrado = new Livro();
				livroEncontrado.setId(resultadoConsulta.getInt("liv_id"));
				livroEncontrado.setCodigoBarras(resultadoConsulta.getString("liv_cod_barras"));
				livroEncontrado.setAtivo(resultadoConsulta.getBoolean("liv_ativo"));

				
				GeneroLiterario genero = new GeneroLiterario();
				genero.setId(resultadoConsulta.getInt("liv_categoria_ativacao_id"));
				DAOGenerosLivro daoGenero = new DAOGenerosLivro();
				Resultado generosEncontrados = daoGenero.consultar(livro);
				
							
				Livro novoLivro = (Livro) generosEncontrados.getResultado();
				livroEncontrado.setCategorias((ArrayList<GeneroLiterario>) novoLivro.getCategorias());
				livroEncontrado.setCategoriaInativacao(resultadoConsulta.getInt("liv_categoria_inativacao_id"));
				livroEncontrado.setJustificativaAtivacao(resultadoConsulta.getString("liv_justificativa_ativacao"));
				livroEncontrado.setAutor(resultadoConsulta.getString("liv_autor"));
				livroEncontrado.setTitulo(resultadoConsulta.getString("liv_titulo"));
				livroEncontrado.setAno(resultadoConsulta.getInt("liv_ano_publicacao"));
				livroEncontrado.setEdicao(resultadoConsulta.getString("liv_edicao"));
				livroEncontrado.setIsbn(resultadoConsulta.getString("liv_isbn"));
				livroEncontrado.setSinopse(resultadoConsulta.getString("liv_sinopse"));
				livroEncontrado.setEditora(resultadoConsulta.getString("liv_editora"));
				livroEncontrado.setQuantidadePaginas(resultadoConsulta.getInt("liv_quantidade_paginas"));
				livroEncontrado.setCategoriaAtivacao(resultadoConsulta.getInt("liv_categoria_ativacao_id"));
				livroEncontrado.setAltura(resultadoConsulta.getDouble("liv_altura"));
				livroEncontrado.setPeso(resultadoConsulta.getDouble("liv_peso"));
				livroEncontrado.setProfundidade(resultadoConsulta.getDouble("liv_profundidade"));
				
				contagem++;
				
				listLivro.add(livroEncontrado);
								
			}
								
			statement.close();
			resultado.setContagem(contagem);
			
			if( contagem == 1) {
        resultado.setResultado(listLivro.get(0)); 		  
			} else {
        resultado.setListaResultado(listLivro); 		  
			}

			resultado.sucesso("Sucesso");
			return resultado;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro ao consultar, por favor, refaça a operação.");
			return resultado;
		}
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		Resultado resultado = new Resultado();
		
		String sql = "UPDATE livros SET liv_cod_barras = ?, "
				+ "liv_ativo = ?,  "
				+ "liv_categoria_ativacao_id = ?,  "
				+ "liv_justificativa_ativacao = ?,  "
				+ "liv_autor = ?,  "
				+ "liv_titulo = ?,  "
				+ "liv_ano_publicacao = ?,  "
				+ "liv_edicao = ?,  "
				+ "liv_isbn = ?,  "
				+ "liv_sinopse = ?,  "
				+ "liv_editora = ?,  "
				+ "liv_quantidade_paginas = ?,  "
				+ "liv_altura = ?,  "
				+ "liv_largura = ?,  "
				+ "liv_peso = ?,  "
				+ "liv_profundidade = ?  "
				+ "WHERE liv_id = ?";
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, livro.getCodigoBarras());
			statement.setBoolean(2, livro.isAtivo());
			statement.setInt(3, livro.getCategoriaAtivacao());
			statement.setString(4, livro.getJustificativaAtivacao());
			statement.setString(5, livro.getAutor());
			statement.setString(6, livro.getTitulo() );
			statement.setInt(7, livro.getAno());
			statement.setString(8, livro.getEdicao());
			statement.setString(9, livro.getIsbn());
			statement.setString(10, livro.getSinopse());
			statement.setString(11, livro.getEditora());
			statement.setInt(12, livro.getQuantidadePaginas());
			statement.setDouble(13, livro.getAltura());			
			statement.setDouble(14, livro.getLargura());
			statement.setDouble(15, livro.getPeso());
			statement.setDouble(16, livro.getProfundidade());
			statement.setInt(17, livro.getId().intValue());
			
			statement.executeUpdate();
			
			DAOGenerosLivro daoGenero = new DAOGenerosLivro();
			daoGenero.excluir(livro);			
			resultado = daoGenero.salvar(livro);			
			
			livro = (Livro) resultado.getResultado();
			livro.setCategorias((ArrayList<GeneroLiterario>) livro.getCategorias());
			
			statement.close();
			
			resultado.sucesso("Registro atualizado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro ao alterar registro");
		}		
		
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		Resultado resultado = new Resultado();
		
		String sql = "DELETE FROM livros WHERE liv_id = ?";
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, livro.getId().intValue());
			statement.execute();
			statement.close();
			
			resultado.setResultado(livro);
			resultado.sucesso("Registro excluido com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro ao excluir o registro");
		}		
		
		return resultado;
	}
	
	public Resultado inativar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();		
		Livro livro = (Livro) entidade;
		
		String sql = "UPDATE livros SET liv_ativo = false, liv_categoria_inativacao_id = ?, liv_justificativa_inativacao = ? WHERE liv_id = ? ";
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, livro.getCategoriaInativacao());
			statement.setString(2, livro.getJustificativaInativacao());
			statement.setInt(3, livro.getId().intValue());
			statement.executeUpdate();
			statement.close();
			
			resultado.setResultado(livro);
			resultado.sucesso("Livro inativado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro ao inativar o registro");
		} 		
		
		return resultado;
	}
}
