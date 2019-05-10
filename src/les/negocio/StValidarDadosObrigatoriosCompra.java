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
      mensagem = "É necessário informar um endereço para entrega";      
    }
    
    if(pedido.getFrete().equals(0.0)) {
      mensagem = "É necessário selecionar um frete";
    }
    if(pedido.getItens().size() <= 0) {
      mensagem = "Não há itens no carrinho selecione e tente novamente";
    }

    return mensagem;
  }

}
