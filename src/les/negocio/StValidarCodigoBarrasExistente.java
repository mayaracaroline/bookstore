package les.negocio;

import dominio.EntidadeDominio;
import dominio.Estoque;

public class StValidarCodigoBarrasExistente implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    Estoque estoque = (Estoque) entidade;
    
    return "";
  }

}
