package les.command;

import dominio.EntidadeDominio;
import util.Resultado;

public class CommandCalcularFrete extends AbstractCommand implements ICommand {

  @Override
  public Resultado executar(EntidadeDominio entidade) {

    return fachada.chamarServico(entidade, this.getClass().getSimpleName());
    
  }

}
