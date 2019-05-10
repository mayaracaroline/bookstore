package les.negocio;

import dominio.Endereco;
import dominio.EntidadeDominio;

public class StValidarCepInformado implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    Endereco endereco = (Endereco) entidade;
    String cep = endereco.getCep();
    
    if (cep.trim().replace("-", "").length() != 8) {
      mensagem = "Digite um CEP válido";
    }
    System.out.println("Strategy" + mensagem);
    return mensagem;
  }

}
