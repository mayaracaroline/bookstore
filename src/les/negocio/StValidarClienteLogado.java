package les.negocio;

import java.math.BigInteger;

import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;

public class StValidarClienteLogado implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    
    if (pedido.getIdCliente().equals(BigInteger.ZERO)) {
      
      mensagem = "Por favor, efetuar login para seguir.";
      
    }
    
    return mensagem;
  }
  
  

}
