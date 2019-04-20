package les.negocio;

import java.math.BigInteger;

import dominio.Cliente;
import dominio.EntidadeDominio;

public class StValidarIdConsultaCliente implements IStrategy {
  
  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    Cliente cliente = (Cliente) entidade;
    
    if(cliente.getId().equals(BigInteger.ZERO)) {
      mensagem = "Insira o código para consulta\n";     
    }
    
    return mensagem;
  }

}
