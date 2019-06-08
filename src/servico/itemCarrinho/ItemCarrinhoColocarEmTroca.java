package servico.itemCarrinho;

import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.PedidoDeCompra;
import les.dao.DAOItensPedido;
import les.dao.DAOPedidoDeCompra;
import servico.IServico;
import util.Resultado;

public class ItemCarrinhoColocarEmTroca implements IServico {

  @Override
  public Resultado executarServico(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    DAOItensPedido daoItemPedido = new DAOItensPedido();
    DAOPedidoDeCompra daoPedidodeCompra = new DAOPedidoDeCompra();
    
    daoItemPedido.colocarItemEmTroca(pedido);
                                                      
    daoPedidodeCompra.colocarEmTroca(pedido);
    
    resultado.setResultado(pedido);
    resultado.sucesso("Item colocado em troca com sucesso");
    
    return resultado;
  }

}
