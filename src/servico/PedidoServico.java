package servico;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import les.dao.DAOPedidoDeCompra;
import util.Resultado;

public class PedidoServico {
  
  public Resultado colocarEmTransporte(EntidadeDominio entidade) {
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Resultado resultado = new Resultado();
    
    DAOPedidoDeCompra daoPedido = new DAOPedidoDeCompra();
    System.out.println(pedido.getId().intValue());
    daoPedido.colocarEmtransporte(pedido);
    
    resultado.setResultado(pedido);
    List<EntidadeDominio> pedidos = new ArrayList<>();
    pedidos.add(pedido);
    resultado.setListaResultado(pedidos);
    resultado.sucesso("Pedido colocado em transporte");
    
    return resultado;
  }
  
  public Resultado confirmarEntrega (EntidadeDominio entidade) {
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Resultado resultado = new Resultado();
    
    DAOPedidoDeCompra daoPedido = new DAOPedidoDeCompra();
    daoPedido.confirmarEntrega(pedido);
    
    resultado.setResultado(pedido);
    List<EntidadeDominio> pedidos = new ArrayList<>();
    pedidos.add(pedido);
    resultado.setListaResultado(pedidos);
    resultado.sucesso("Pedido entregue");
    
    return resultado;
  }

}
