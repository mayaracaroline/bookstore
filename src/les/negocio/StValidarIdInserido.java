package les.negocio;

import java.math.BigInteger;

import dominio.EntidadeDominio;
import dominio.Livro;

public class StValidarIdInserido implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		String mensagem = "";
		Livro livro = (Livro) entidade;
		
		if(livro.getId().intValue() == -1) {
			mensagem = "Insira o código para consulta\n";			
		}		
		return mensagem;
	}

}
