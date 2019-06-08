package servico.itemCarrinho;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.PedidoDeCompra;
import les.dao.DAOCupom;
import les.dao.DAOEstoque;
import les.dao.DAOItensPedido;
import les.dao.DAOPedidoDeCompra;
import servico.IServico;
import util.Resultado;

public class ItemCarrinhoAprovarTroca implements IServico {

  @Override
  public Resultado executarServico(EntidadeDominio entidade) {
    
    Resultado resultado = new Resultado();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    ItemCarrinho itemPedido = pedido.getCarrinho().getItensCarrinho().get(0);
    DAOEstoque daoEstoque = new DAOEstoque();
    DAOItensPedido daoItemPedido = new DAOItensPedido();
    DAOPedidoDeCompra daoPedidodeCompra = new DAOPedidoDeCompra();
    Cupom cupom = pedido.getCuponsTroca().get(0);
    
    if(itemPedido.getQuantidade() > 0) {
      daoEstoque.adicionarAoEstoque(itemPedido);
    }
    
    daoItemPedido.aprovarTroca(pedido);
    
    Resultado resultadoItensEmTroca = daoItemPedido.consultarItensPorStatus(6); 
    
    // Validar se existe mais de um item com status "Em troca"
    
    if(resultadoItensEmTroca.getContagem() == 0) {
      daoPedidodeCompra.concluirTroca(pedido);
    }
    
    DAOCupom daoCupom = new DAOCupom();
    
    daoCupom.salvar(cupom);
    

    resultado.setResultado(pedido);
    resultado.sucesso("Item em troca com sucesso");
    
    return resultado;
  }

}
