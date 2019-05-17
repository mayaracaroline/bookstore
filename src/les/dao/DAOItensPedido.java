package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Livro;
import dominio.PedidoDeCompra;
import dominio.Produto;
import util.Resultado;

public class DAOItensPedido extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    String sql = "INSERT INTO itens_pedido (itp_ped_id, itp_pro_id, itp_pro_quantidade, itp_status, itp_preco) values (?,?,?,?,?);";
    PreparedStatement pst;
    Integer idPedido = pedido.getId().intValue();
    ArrayList<ItemCarrinho> itens = pedido.getCarrinho().getItensCarrinho();
    try {
      pst = conexao.prepareStatement(sql);
      
      for(ItemCarrinho item : itens) {
        pst.setInt(1,idPedido);
        pst.setInt(2,item.getProduto().getId().intValue());
        pst.setInt(3, item.getQuantidade());
        pst.setInt(4, 1);
        pst.setDouble(5, item.getProduto().getPreco());
        
        pst.execute();
      }
      
      pst.close();
      
      resultado.setResultado(pedido);
      resultado.sucesso("Itens salvos com sucesso");
      
    } catch (Exception e) {
      resultado.erro("Erro ao salvar pedido");
      e.printStackTrace();
    }
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    String sql = "SELECT * FROM itens_pedido WHERE itp_ped_id = ?;";
    PreparedStatement pst;
    Integer idPedido = pedido.getId().intValue();
    ArrayList<EntidadeDominio> itens = new ArrayList<>();
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, idPedido);
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        
        ItemCarrinho item = new ItemCarrinho();
        Livro livro = new Livro();
        
        livro.setId(rs.getInt("itp_pro_id"));
        
        DAOLivro daoLivro = new DAOLivro();
        
        Resultado resultadoLivro = daoLivro.consultar(livro);
        Livro livroEncontrado = (Livro) resultadoLivro.getResultado();
        
        item.setProduto(livroEncontrado);
        item.setQuantidade(rs.getInt("itp_pro_quantidade"));
        
        itens.add(item); 
        
      }
      
      pst.close();
      resultado.setListaResultado(itens);
      resultado.sucesso("Consulta realizada com sucesso");

    } catch (Exception e) {
      resultado.erro("Erro ao consultar item de pedido");
      e.printStackTrace();

    }
    
    return resultado;
  }

  @Override
  public Resultado alterar(EntidadeDominio entidade) {
    String sql = "UPDATE itens_pedido set itp_status = ?  WHERE itp_ped_id = ?";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Resultado resultado = new Resultado();
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, pedido.getStatus());
      pst.setInt(2, pedido.getId().intValue());
      
      pst.executeUpdate();
      pst.close();
      resultado.sucesso("Status atualizado com sucesso");
    } catch (Exception e) {
      resultado.erro("Erro ao atualizar status");
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
  
  public Resultado consultarPedidosEmProcessamento() {
    Resultado resultado = new Resultado();
//    String sql = "SELECT * FROM  pedidos a JOIN itens_pedido b ON a.ped_id = b.itp_ped_id WHERE ped_status = ? ";
    String sql = "SELECT * FROM  itens_pedido WHERE itp_status = ? ";
    ArrayList<EntidadeDominio> itens = new ArrayList<>();
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, 1);     
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {       
        ItemCarrinho item = new ItemCarrinho();
        Produto produto = new Produto();
        produto.setId(rs.getInt("itp_pro_id"));
        item.setId(rs.getInt("itp_ped_id"));
        item.setProduto(produto);
        item.setQuantidade(rs.getInt("itp_pro_quantidade"));
        itens.add(item);
      }
      
      pst.close();
      resultado.setListaResultado(itens);
      resultado.sucesso("Consulta realizada com sucesso");
    } catch (Exception e) {
      resultado.erro("Erro ao consultar itens em processamento");
      e.printStackTrace();
    }
    return resultado;
  }
  
  public void colocarItensEmTransporte(EntidadeDominio entidade) {
    String sql = "UPDATE itens_pedido SET itp_status = ? WHERE itp_ped_id = ?";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, 4);
      pst.setInt(2, pedido.getId().intValue());
      
      pst.executeUpdate();
      pst.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

  public void confirmarEntregaItens(EntidadeDominio entidade) {
    String sql = "UPDATE itens_pedido SET itp_status = ? WHERE itp_ped_id = ?";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, 5);
      pst.setInt(2, pedido.getId().intValue());
      
      pst.executeUpdate();
      pst.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
