package servico;

import dominio.EntidadeDominio;
import util.Resultado;

public interface IServico {
  
  public Resultado executarServico(EntidadeDominio entidade);

}
