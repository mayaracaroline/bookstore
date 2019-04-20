package les.negocio;

import java.math.BigInteger;

import dominio.Cliente;
import dominio.EntidadeDominio;

public class StValidarEnderecosObrigatorios implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
   String mensagem = "";
   Cliente cliente = (Cliente) entidade;
   
   if(cliente.getEnderecoEntrega().getId().equals(BigInteger.ZERO) ||
       cliente.getEnderecoResidencial().getId().equals(BigInteger.ZERO) ||
       cliente.getEnderecoCobranca().getId().equals(BigInteger.ZERO)) {
     
     mensagem = "É necessário preencher ao menos um endereço: \n Residencial, Entrega e Cobrança";
     
   }
   
    return mensagem;
  }
  
  

}
