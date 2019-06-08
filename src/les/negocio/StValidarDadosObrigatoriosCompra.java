package les.negocio;

import java.math.BigInteger;

import dominio.Cartao;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;

public class StValidarDadosObrigatoriosCompra implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    StringBuilder mensagem = new StringBuilder();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    if(pedido.getCarrinho() != null) {
      
      if(pedido.getEnderecoDeEntrega().getId().equals(BigInteger.ZERO)) {
        mensagem.append("� necess�rio informar um endere�o para entrega\n");      
      }
      
      if(pedido.getFrete().equals(0.0)) {
        mensagem.append("� necess�rio selecionar um frete\n");
      }
      if(pedido.getCarrinho().getItensCarrinho().size() <= 0) {
        mensagem.append("N�o h� itens no carrinho selecione e tente novamente\n");
      }
      
    }

    return mensagem.toString();
  }

}
