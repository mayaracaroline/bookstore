package les.negocio;

import java.math.BigInteger;
import java.util.List;

import dominio.Cartao;
import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;

public class StValidarFormaDePagamento implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Cupom cupomPromocional = pedido.getCupomPromocional();
    List<Cupom> cuponsTroca = pedido.getCuponsTroca();
    boolean usouCupomPromocional = false;
    boolean usouCupomTroca = false;
    boolean usouCartao = false;
    
    for(Cupom cupom : cuponsTroca) {
      if(!cupom.getId().equals(BigInteger.ZERO)) {
        usouCupomTroca = true;
        break;
      }
    }
    
    usouCupomPromocional = !cupomPromocional.getId().equals(BigInteger.ZERO) ? true : false;
    
    Cartao cartao1 = (Cartao) pedido.getPagamento().get(0).getFormaDePagamento();
    Cartao cartao2 = (Cartao) pedido.getPagamento().get(1).getFormaDePagamento();
    
    
    usouCartao = cartao1.getId().equals(BigInteger.ZERO) 
        && cartao2.getId().equals(BigInteger.ZERO) ? false : true;
    
    if(!usouCartao && !usouCupomPromocional && !usouCupomTroca) {
      mensagem = "Selecione uma forma de pagamento";
    }
    
    return mensagem;
  }

}
