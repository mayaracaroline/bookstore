package les.negocio;

import dominio.EntidadeDominio;
import dominio.Livro;

public class StValidarMotivoAtivacao implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		StringBuilder mensagem = new StringBuilder();
		
		if (livro.isAtivo()) {
			if(livro.getJustificativaAtivacao().equals("") ||
				livro.getCategoriaAtivacao() == 0) {				
				mensagem.append("Por favor informe motivo e categoria de ativação\n");
			}			
			
		}
		return mensagem.toString();
	}
	
	

}
