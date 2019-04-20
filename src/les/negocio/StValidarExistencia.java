package les.negocio;

import dominio.EntidadeDominio;
import les.dao.DAOLivro;
import util.Resultado;

public class StValidarExistencia implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		String mensagem = "";
		DAOLivro daoLivro = new DAOLivro();
		
		Resultado resultado = daoLivro.consultar(entidade);
			
			if (resultado.getContagem() == 0) {
				mensagem = "";
			} else {
				mensagem = "Ja existe um livro com o código informado";
			}
								
		return mensagem;
	}
	

}
