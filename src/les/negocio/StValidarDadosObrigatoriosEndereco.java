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
      mensagem = "Necess�rio informar o logradouro.\n";
    }
    if(endereco.getBairro().equals("")) {
      mensagem = "Necess�rio informar o bairro.\n";
    }
    if(endereco.getCep().equals("")) {
      mensagem = "Necess�rio informar o cep.\n";
    }
    if(endereco.getCidade().equals("")) {
      mensagem = "Necess�rio informar o cidade.\n";
    }
    if(endereco.getEstado().equals("")) {
      mensagem = "Necess�rio informar o estado.\n";
    }
    if(endereco.getNumero() == 0) {
      mensagem = "Necess�rio informar o numero.\n";
    }
    if(endereco.getPais().equals("")) {
      mensagem = "Necess�rio informar o pa�s.\n";
    }
    if(endereco.getTipoLogradouro().getId().equals(BigInteger.ZERO)) {
      mensagem = "Necess�rio informar um tipo de logradouro \n";
    }
    
    return mensagem;
  }

}
