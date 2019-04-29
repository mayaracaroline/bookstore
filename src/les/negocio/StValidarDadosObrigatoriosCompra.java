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
    if(pedido.getFrete().equals(0.0) || pedido.getFrete().equals(0)) {
      mensagem = "� necess�rio selecionar um frete";
    }
    if(pedido.getItens().size() <= 0) {
      mensagem = "N�o h� itens no carrinho selecione e tente novamente";
    }
    Cartao cartao1 = (Cartao) pedido.getPagamento().get(0).getFormaDePagamento();
    Cartao cartao2 = (Cartao) pedido.getPagamento().get(1).getFormaDePagamento();
    
    if(cartao1.getId().equals(BigInteger.ZERO) || cartao2.getId().equals(BigInteger.ZERO)) {
      mensagem = "Selecione um cart�o para pagamento";
    }
    
    return mensagem;
  }

}
