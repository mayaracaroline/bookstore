package les.negocio;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.PedidoDeCompra;
import les.dao.DAOPedidoDeCompra;
import util.Resultado;

public class StComplementarPedidoDeCompra implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    DAOPedidoDeCompra daoPedido = new DAOPedidoDeCompra();
    ArrayList<ItemCarrinho> itens = new ArrayList<>();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    itens = pedido.getCarrinho().getItensCarrinho();
    Resultado resultado = daoPedido.consultarPorId(pedido);
    pedido = (PedidoDeCompra) resultado.getResultado();
    pedido.getCarrinho().setItensCarrinho(itens);

    return "";
  }

}
