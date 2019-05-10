package les.negocio;

import java.math.BigInteger;

import dominio.Cartao;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;

public class StValidarDadosObrigatoriosCompra implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    
    if(pedido.getEnderecoDeEntrega().getId().equals(BigInteger.ZERO)) {
      mensagem = "� necess�rio informar um endere�o para entrega";      
    }
    
    if(pedido.getFrete().equals(0.0)) {
      mensagem = "� necess�rio selecionar um frete";
    }
    if(pedido.getItens().size() <= 0) {
      mensagem = "N�o h� itens no carrinho selecione e tente novamente";
    }

    return mensagem;
  }

}
