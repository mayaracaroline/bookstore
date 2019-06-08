package les.negocio;

import java.math.BigInteger;

import dominio.Cartao;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;

public class StValidarValorMinimoParaPagamentoComCartao implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Double valorTotalCupons = 0.0;
   
     for(int i = 0; i< pedido.getCuponsTroca().size() ; i++ ) {
       valorTotalCupons += pedido.getCuponsTroca().get(i).getValor();
     }
     
     valorTotalCupons += pedido.getCupomPromocional().getValor();
     
     double valorCartao1 = pedido.getPagamento().get(0).getValor();
     double valorCartao2 = pedido.getPagamento().get(1).getValor();
  
     Cartao cartao1 = (Cartao) pedido.getPagamento().get(0).getFormaDePagamento();
     Cartao cartao2 = (Cartao) pedido.getPagamento().get(1).getFormaDePagamento();
     
     double totalAPagar = pedido.getValorTotal() + pedido.getFrete();
  
     double totalComDesconto = totalAPagar - valorTotalCupons;
  
     
     if(totalComDesconto < 10 ) {
       if (valorCartao1 > 0 && valorCartao2 > 0) {
         mensagem = "Apenas um cartão é aceito para essa operação\n";
       }  
     } else {
       if(cartao2.getId().equals(BigInteger.ZERO) && valorCartao1 < 10) {
         mensagem = "O valor minímo para pagamento em cartão é R$10.00\n";
       } else if (cartao1.getId().equals(BigInteger.ZERO) && valorCartao2 < 10) {
         mensagem = "O valor minímo para pagamento em cartão é R$10.00\n";
       }
     }
       
    return mensagem;
  }

}
