package servico.pedido;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import les.dao.DAOPedidoDeCompra;
import servico.IServico;
import util.Resultado;

public class PedidoConfirmarEntrega implements IServico {

  @Override
  public Resultado executarServico(EntidadeDominio entidade) {
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
