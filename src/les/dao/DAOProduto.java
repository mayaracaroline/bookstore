package les.dao;

import java.sql.PreparedStatement;

import dominio.EntidadeDominio;
import dominio.Produto;
import util.ConnectionFactory;
import util.Resultado;

public class DAOProduto extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Produto produto = (Produto) entidade;
    String sql = "INSERT INTO produtos(pro_cod_barras, pro_preco) VALUES (?,?)";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    try {
      
      pst = conexao.prepareStatement(sql);
      pst.setString(1, produto.getCodigoBarras());
      pst.setDouble(2, produto.getPreco());
      System.out.println(produto.getCodigoBarras());
      pst.execute();     

      resultado.setResultado(produto);
      resultado.sucesso("Produto cadastrado com sucesso");
      
    } catch (Exception e) {
      resultado.erro("Erro ao consultar produto");
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
        
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Produto produto = (Produto) entidade;
    String sql = "SELECT * FROM produtos WHERE pro_cod_barras = ?";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    try {
      
      pst = conexao.prepareStatement(sql);
      pst.setString(1, produto.getCodigoBarras());
      
      pst.executeQuery();   
      pst.close();
      
      resultado.setResultado(produto);
      resultado.sucesso("Consulta realizada com sucesso");
      
    } catch (Exception e) {
      resultado.erro("Erro ao consultar produto");
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
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

}
