package fachada;

import dominio.EntidadeDominio;
import les.command.ICommand;
import util.Resultado;

public interface IFachada {
	
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado inativar(EntidadeDominio entidade);
	public Resultado consultarPorId(EntidadeDominio entidade);
  public Resultado chamarServico(EntidadeDominio entidade, String command);
  
}
