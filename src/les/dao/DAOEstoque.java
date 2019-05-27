package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.ItemCarrinho;
import dominio.Produto;
import util.ConnectionFactory;
import util.Resultado;

public class DAOEstoque extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Produto produto = (Produto) entidade;
    Estoque estoque = new Estoque();
    String sql = "SELECT * FROM estoque WHERE est_pro_cod_barras = ?;";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    try {
      pst = conexao.prepareStatement(sql);

      pst.setString(1, produto.getCodigoBarras());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        Produto pro = new Produto();
        pro.setId(rs.getInt("est_pro_id"));
        estoque.setProduto(pro);
        estoque.setQuantidade(rs.getInt("est_quantidade"));
      }
      
      resultado.sucesso("Produto consultado com sucesso");
      resultado.setResultado(estoque);
      
    } catch (Exception e) {
      resultado.setResultado(estoque);
      resultado.erro("Erro ao consultar produto no estoque");
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
  
  public void adicionarAoEstoque(EntidadeDominio entidade) {
    ItemCarrinho item = (ItemCarrinho) entidade;
    String sql = "UPDATE estoque SET est_quantidade = est_quantidade + ? WHERE est_pro_cod_barras = ?";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    try {
     pst = conexao.prepareStatement(sql);
      pst.setInt(1, item.getQuantidade());
      pst.setInt(2, item.getProduto().getId().intValue());
      
      pst.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
  }
  
  public void retirarDoEstoque(EntidadeDominio entidade) {
    ItemCarrinho item = (ItemCarrinho) entidade;
    String sql = "UPDATE estoque SET est_quantidade = est_quantidade - ? WHERE est_pro_cod_barras = ?";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, item.getQuantidade());
      pst.setString(2, item.getProduto().getCodigoBarras());
      
      pst.executeUpdate();
      
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
  }

}
