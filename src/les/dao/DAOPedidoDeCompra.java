package les.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import util.Resultado;

public class DAOPedidoDeCompra extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    PedidoDeCompra pedido  = (PedidoDeCompra) entidade;
    String sql = "INSERT INTO pedidos (ped_cli_id, ped_frete, ped_valor_total, ped_status, ped_data_solicitacao, ped_data_conclusao) values (? , ? , ? , ?, ?, ?)";
    PreparedStatement pst;
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, pedido.getIdCliente().intValue());
      pst.setDouble(2, pedido.getFrete());
      pst.setDouble(3, pedido.getValorTotal());
      pst.setInt(4, 1);
      pst.setDate(5, Date.valueOf(LocalDate.now()));
      pst.setDate(6, null);
      
      pst.execute();
      
      ResultSet rs = pst.getGeneratedKeys();
      Integer idPedido = 0;
      
      while(rs.next()) {
        idPedido = rs.getInt(1);
      }
      
      pedido.setId(idPedido);
      
      DAOItensPedido daoItens = new DAOItensPedido();
      
      daoItens.salvar(pedido);
      
      pst.close();
      
      resultado.setResultado(pedido);
      resultado.sucesso("Pedido salvo com sucesso");
      
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
