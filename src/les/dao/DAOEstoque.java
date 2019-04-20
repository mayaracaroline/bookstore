package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.Produto;
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
    List<EntidadeDominio> produtos = new ArrayList<>();
    String sql = "SELECT * FROM estoque WHERE est_pro_id = ?;";
   
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, produto.getId().intValue());
      
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

}
