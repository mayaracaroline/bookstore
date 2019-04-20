package les.negocio;

import dominio.EntidadeDominio;
import dominio.Livro;

public class StValidarMotivoCategoriaInativacao implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		String mensagem = "";
		
		if (!livro.isAtivo()) {
			if(livro.getJustificativaInativacao().equals("") ||
				livro.getCategoriaInativacao() == 0) {
				
				mensagem = "Por favor informe motivo e categoria de inativação\n";
			}			
			
		}
		return mensagem;
	}
	
	

}
