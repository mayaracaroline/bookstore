package les.negocio;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.GeneroLiterario;
import dominio.Livro;
import les.dao.DAOGeneroLivro;
import util.Resultado;

public class StComplementarGeneroLiterario implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Livro livro = (Livro) entidade;
		DAOGeneroLivro daoGenero = new DAOGeneroLivro();
		Resultado resultado = new Resultado();
		String mensagem = "";
			
		try {	
			
			resultado = daoGenero.consultar(livro);				
			
			resultado.setResultado(livro);
			resultado.sucesso("SUCESSO");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resultado.erro("Erro ao gravar genero");
			mensagem = "Erro ao gravar genero";
		}
			
				
		return mensagem;
	}

}
