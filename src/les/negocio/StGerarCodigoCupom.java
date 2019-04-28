package les.negocio;

import java.util.UUID;

import dominio.Cupom;
import dominio.EntidadeDominio;

public class StGerarCodigoCupom implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    Cupom cupom = (Cupom) entidade;
    UUID uuid = UUID.randomUUID();
    String randomCode = uuid.toString();
    String codigoCupom = randomCode.substring(0,20);
    
    cupom.setCodigo(codigoCupom);
    
    
    return "";
  }

}
