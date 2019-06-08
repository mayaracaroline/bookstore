package les.negocio;

import java.math.BigInteger;

import dominio.DadosEntrega;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.TipoLogradouro;

public class StValidarDadosObrigatoriosEndereco implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    DadosEntrega dadosEntrega = (DadosEntrega) entidade;
    Endereco endereco = dadosEntrega.getEnderecoEntrega();
    
    if(endereco.getLogradouro().equals("")) {
      mensagem = "Necessário informar o logradouro.\n";
    }
    if(endereco.getBairro().equals("")) {
      mensagem = "Necessário informar o bairro.\n";
    }
    if(endereco.getCep().equals("")) {
      mensagem = "Necessário informar o cep.\n";
    }
    if(endereco.getCidade().equals("")) {
      mensagem = "Necessário informar o cidade.\n";
    }
    if(endereco.getEstado().equals("")) {
      mensagem = "Necessário informar o estado.\n";
    }
    if(endereco.getNumero() == 0) {
      mensagem = "Necessário informar o numero.\n";
    }
    if(endereco.getPais().equals("")) {
      mensagem = "Necessário informar o país.\n";
    }
    if(endereco.getTipoLogradouro().getId().equals(BigInteger.ZERO)) {
      mensagem = "Necessário informar um tipo de logradouro \n";
    }
    
    return mensagem;
  }

}
