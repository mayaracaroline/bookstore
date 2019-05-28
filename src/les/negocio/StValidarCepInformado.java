package les.negocio;

import dominio.Endereco;
import dominio.EntidadeDominio;
import util.Formatter;

public class StValidarCepInformado implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    Endereco endereco = (Endereco) entidade;
    String cep = endereco.getCep();
    
    if (cep.trim().replace("-", "").length() != 8) {
      mensagem = "Digite um CEP válido";
    }
    if (Formatter.stringToInt(cep.trim().substring(0, 1)) < 0) {
      mensagem = "Digite um CEP válido";
    }

    return mensagem;
  }

}
