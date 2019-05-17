package les.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.PedidoDeCompra;
import dominio.Produto;
import util.Resultado;

public class DAOPedidoDeCompra extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    PedidoDeCompra pedido  = (PedidoDeCompra) entidade;
    String sql = "INSERT INTO pedidos (ped_cli_id, ped_frete, ped_valor_total, ped_status, ped_data_solicitacao, ped_data_conclusao, ped_codigo_identificador) values (? , ? , ? , ?, ?, ?, ?)";
    PreparedStatement pst;
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, pedido.getIdCliente().intValue());
      pst.setDouble(2, pedido.getFrete());
      pst.setDouble(3, pedido.getValorTotal());
      pst.setInt(4, 1);
      pst.setDate(5, Date.valueOf(LocalDate.now()));
      pst.setDate(6, null);
      pst.setString(7, pedido.getCodigoIdentificador().toUpperCase());
      
      pst.execute();
      
      ResultSet rs = pst.getGeneratedKeys();
      Integer idPedido = 0;
      
      while(rs.next()) {
        idPedido = rs.getInt(1);
      }
      
      pedido.setId(idPedido);
      
      DAOItensPedido daoItens = new DAOItensPedido();
      
      daoItens.salvar(pedido);
      
      for (ItemCarrinho item : pedido.getCarrinho().getItensCarrinho()) {
        DAOEstoque daoEstoque = new DAOEstoque();
        
        daoEstoque.retirarDoEstoque(item);
      }
      
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
    String sql = "SELECT * FROM pedidos WHERE ped_cli_id = ? ";
    Resultado resultado = new Resultado();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    ArrayList<EntidadeDominio> pedidos = new ArrayList<>();
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1,pedido.getIdCliente().intValue());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        PedidoDeCompra ped = new PedidoDeCompra();
        ped.setId(rs.getInt("ped_id"));
        ped.setCodigoIdentificador(rs.getString("ped_codigo_identificador"));
        ped.setStatus(rs.getInt("ped_status"));
        String dataSolicitacao = rs.getDate("ped_data_solicitacao").toString();
        ped.setValorTotal(rs.getDouble("ped_valor_total"));
//        String dataConclusao = rs.getDate("ped_data_conclusao").toString();
        ped.setDataConclusao(LocalDate.parse(dataSolicitacao) ); 
//        ped.setDataConclusao(LocalDate.parse(dataConclusao));
        DAOItensPedido daoItemPedido = new  DAOItensPedido();
        
        Resultado resultadoItemPedido = daoItemPedido.consultar(ped);
        
        ArrayList<ItemCarrinho> itens = new ArrayList<>();
        
        for (EntidadeDominio item : resultadoItemPedido.getListaResultado()) {
          itens.add((ItemCarrinho)item);
        }
        Carrinho carrinho = new Carrinho();
        carrinho.setItensCarrinho(itens);
        ped.setCarrinho(carrinho);
        pedidos.add(ped);
        
      }
      
      pst.close();
      resultado.setListaResultado(pedidos);
      resultado.sucesso("Consulta realizada com sucesso");

    } catch (Exception e) {
      resultado.erro("Erro ao consultar pedido");
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
 
  public void aprovarCompra() {
    String sql = "UPDATE pedidos set ped_status = ? WHERE ped_status = ? ";
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, 2);
      pst.setInt(2, 1);
      
      pst.executeUpdate();
      
      DAOItensPedido daoItens = new DAOItensPedido();
      Resultado resultadoItens = daoItens.consultarPedidosEmProcessamento();
      List<EntidadeDominio> itens = resultadoItens.getListaResultado();
      
      for(EntidadeDominio it : itens) {
        PedidoDeCompra pedido = new PedidoDeCompra();
        ItemCarrinho item = (ItemCarrinho) it;
        pedido.setId(item.getId().intValue());
        pedido.setStatus(2);
        
        daoItens.alterar(pedido);
        
      }
      
      pst.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void reprovarCompra() {
    String sql = "UPDATE pedidos set ped_status = ? WHERE ped_status = ? ";
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, 3);
      pst.setInt(2, 1);
      
      pst.executeUpdate();
      
      DAOItensPedido daoItens = new DAOItensPedido();
      Resultado resultadoItens = daoItens.consultarPedidosEmProcessamento();
      List<EntidadeDominio> itens = resultadoItens.getListaResultado();
      
      for(EntidadeDominio it : itens) {
        PedidoDeCompra pedido = new PedidoDeCompra();
        ItemCarrinho item = (ItemCarrinho) it;
        pedido.setId(item.getId().intValue());
        pedido.setStatus(3);
        
        daoItens.alterar(pedido);
        
      }
      
      pst.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void colocarEmtransporte(EntidadeDominio entidade) {
    String sql = "UPDATE pedidos set ped_status = ? WHERE ped_id = ? ";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, 4);
      pst.setInt(2, pedido.getId().intValue());
      
      pst.executeUpdate();
      
      DAOItensPedido daoItens = new DAOItensPedido();
      
      daoItens.colocarItensEmTransporte(pedido);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void confirmarEntrega(EntidadeDominio entidade) {
    String sql = "UPDATE pedidos set ped_status = ? WHERE ped_id = ? ";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, 5);
      pst.setInt(2, pedido.getId().intValue());
      
      
      pst.executeUpdate();
      
      DAOItensPedido daoItens = new DAOItensPedido();
      
      daoItens.confirmarEntregaItens(pedido);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
    
}
