package les.negocio;

import java.math.BigInteger;

import dominio.Endereco;
import dominio.EntidadeDominio;

public class StValidarEnderecoSelecionado implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    Endereco enderecoEntrega = (Endereco) entidade;
    
    if(enderecoEntrega.getId().equals(BigInteger.valueOf(-1))) {
      mensagem = "Selecione um endereco de entrega";
    }
    
    return mensagem;
  }

}
