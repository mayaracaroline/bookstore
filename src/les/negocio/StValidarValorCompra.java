package les.negocio;

import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;

public class StValidarValorCompra implements IStrategy {

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
    
    double valorTotalPagamento = valorTotalCupons + valorCartao1 +valorCartao2;
    double valorTotalCompra = pedido.getValorTotal() + pedido.getFrete();
    
    if(valorTotalCompra > valorTotalPagamento) {
      mensagem = "O valor informado para pagamento é inferior ao total do pedido\n";
    }

    return mensagem;
  }

}
