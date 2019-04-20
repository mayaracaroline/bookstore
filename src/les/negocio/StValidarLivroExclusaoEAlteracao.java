package les.negocio;

import dominio.EntidadeDominio;
import les.dao.DAOLivro;
import util.Resultado;

public class StValidarLivroExclusaoEAlteracao implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		String mensagem = "";
		DAOLivro daoLivro = new DAOLivro();
		
		Resultado resultado = daoLivro.consultar(entidade);
		
		if(resultado.getContagem() == 0) {			
			mensagem = "Não existe livro cadastrado com este código\n";			
		}
		
		return mensagem;
	}

}
