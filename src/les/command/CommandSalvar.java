package les.command;

import dominio.EntidadeDominio;
import fachada.Fachada;
import util.Resultado;

public class CommandSalvar extends AbstractCommand {
		
	public Resultado executar(EntidadeDominio entidade) {
		
		return fachada.salvar(entidade);
		
	}

}
