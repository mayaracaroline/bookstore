package les.command;

import dominio.EntidadeDominio;
import util.Resultado;

public class CommandConsultar extends AbstractCommand implements ICommand {

	@Override
	public Resultado executar(EntidadeDominio entidade) {

		return fachada.consultar(entidade);
	}

}
