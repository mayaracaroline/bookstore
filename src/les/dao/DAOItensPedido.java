package les.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.PedidoDeCompra;
import util.Resultado;

public class DAOItensPedido extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    String sql = "INSERT INTO itens_pedido (itp_ped_id, itp_pro_id, itp_pro_quantidade, itp_status) values (?,?,?,?);";
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
    // TODO Auto-generated method stub
    return null;
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
