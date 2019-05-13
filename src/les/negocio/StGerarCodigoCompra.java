package les.negocio;

import java.util.UUID;

import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;

public class StGerarCodigoCompra implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    PedidoDeCompra pedido =(PedidoDeCompra) entidade;    
    
    String mensagem = "";
    
    String codigoIdentificador = UUID.randomUUID().toString().substring(1,10);
    pedido.setCodigoIdentificador(codigoIdentificador);
  
    
    return mensagem;
  }
  

}
