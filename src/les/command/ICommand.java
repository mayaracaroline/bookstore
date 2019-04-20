package les.command;

import dominio.EntidadeDominio;
import util.Resultado;

public interface ICommand {
	
	Resultado executar (EntidadeDominio entidade);

}
