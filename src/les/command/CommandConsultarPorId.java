package les.command;

import dominio.EntidadeDominio;
import util.Resultado;

public class CommandConsultarPorId extends AbstractCommand implements ICommand {

  @Override
  public Resultado executar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return fachada.consultarPorId(entidade);
  }

}
