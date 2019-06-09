package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominio.EntidadeDominio;
import dominio.Estoque;
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
      
      pst.execute();     
      
      ResultSet rs =  pst.getGeneratedKeys();
      int idProduto = 0;
      
      while(rs.next()) {
        idProduto = rs.getInt(1);
      }
      DAOEstoque daoEstoque = new DAOEstoque();
      Estoque estoque = new Estoque();
      produto.setId(idProduto);
      estoque.setQuantidade(0);
      estoque.setProduto(produto);
      
      daoEstoque.salvar(estoque);
      
      resultado.setResultado(produto);
      resultado.sucesso("Produto cadastrado com sucesso");
      
    } catch (Exception e) {
      resultado.erro("Erro ao salvar produto");
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
      System.out.println("Cod barras " + produto.getCodigoBarras());
      pst = conexao.prepareStatement(sql);
      pst.setString(1, produto.getCodigoBarras());
      
      ResultSet rs = pst.executeQuery(); 
      
      while(rs.next()) {
        produto.setCodigoBarras(rs.getString("pro_cod_barras"));
        produto.setId(rs.getInt("pro_id"));
        produto.setPreco(rs.getDouble("pro_preco"));
        produto.setAtivo(rs.getBoolean("pro_status"));
        produto.setImagePath(rs.getString("pro_imagem_path"));       
      }
      
      
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
