package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.ItemCarrinho;
import dominio.Produto;
import util.ConnectionFactory;
import util.Resultado;

public class DAOEstoque extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Estoque estoque = (Estoque) entidade;
    
  String sql = "INSERT INTO estoque (est_pro_id, est_pro_cod_barras, est_quantidade) VALUES(?,?,?) ";
  
  conexao = ConnectionFactory.getConnection();

  PreparedStatement pst = null;

  try {
    pst = conexao.prepareStatement(sql);
    pst.setInt(1, estoque.getProduto().getId().intValue());
    pst.setString(2, estoque.getProduto().getCodigoBarras());
    pst.setInt(3, estoque.getQuantidade());


    pst.execute();
    
    resultado.sucesso("Salvo com sucesso");
    resultado.setResultado(estoque);
 
            
    } catch(Exception e) {
      
      resultado.erro("Falha ao inserir produto no estoque");
      e.printStackTrace();
    }
    
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Produto produto = (Produto) entidade;
    Estoque estoque = new Estoque();
    String sql = "SELECT * FROM estoque WHERE est_pro_cod_barras = ? ;";
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
    Resultado resultado = new Resultado();
    Estoque estoque = (Estoque) entidade;
    String sql = "UPDATE estoque SET est_quantidade = est_quantidade + ? WHERE est_pro_cod_barras = ? ";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;

    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, estoque.getQuantidade());
      pst.setString(2, estoque.getProduto().getCodigoBarras());

      pst.executeUpdate();
      resultado.sucesso("Salvo com sucesso");
      resultado.setResultado(estoque);
            
    } catch(Exception e) {
      
      resultado.erro("Falha ao inserir produto no estoque");
      e.printStackTrace();
    }
    
    return resultado;
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
      pst.setString(2, item.getProduto().getCodigoBarras());
      
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

  @Override
  public Resultado consultarPorId(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }
  
  
//  String sql = "INSERT INTO estoque (est_pro_id, est_pro_cod_barras, est_quantidade) VALUES(?,?,?) ";
//  
//  conexao = ConnectionFactory.getConnection();
//
//  PreparedStatement pst = null;
//
//  try {
//    pst = conexao.prepareStatement(sql);
//    pst.setInt(1, estoque.getProduto().getId().intValue());
//    pst.setString(2, estoque.getProduto().getCodigoBarras());
//    pst.setInt(3, estoque.getQuantidade());
//
//
//    pst.execute();
//    
//    resultado.sucesso("Salvo com sucesso");
//    resultado.setResultado(estoque);

}
