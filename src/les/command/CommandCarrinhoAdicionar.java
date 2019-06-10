package les.command;

import dominio.EntidadeDominio;
import util.Resultado;

public class CommandCarrinhoAdicionar extends AbstractCommand {

  @Override
  public Resultado executar(EntidadeDominio entidade) {
    
    return fachada.chamarServico(entidade,this.getClass().getSimpleName());
  }

}
