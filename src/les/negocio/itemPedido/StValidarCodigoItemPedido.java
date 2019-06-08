package les.negocio.itemPedido;

import java.math.BigInteger;

import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.PedidoDeCompra;
import les.negocio.IStrategy;

public class StValidarCodigoItemPedido implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    ItemCarrinho item = pedido.getCarrinho().getItensCarrinho().get(0);
    String mensagem = "";
    if(item.getId().equals(BigInteger.ZERO)) {
      mensagem = "Erro ao consultar id pedido";
    }
    return mensagem;
  }

}
