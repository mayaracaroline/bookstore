package les.command;

import dominio.EntidadeDominio;
import util.Resultado;

public class CommandExcluir extends AbstractCommand implements ICommand {

	@Override
	public Resultado executar(EntidadeDominio entidade) {

		return fachada.excluir(entidade);
	}
	

}
