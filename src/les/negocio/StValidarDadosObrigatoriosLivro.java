package les.negocio;

import java.math.BigInteger;

import dominio.EntidadeDominio;
import dominio.Livro;

public class StValidarDadosObrigatoriosLivro implements IStrategy {
	@Override
	public String processar(EntidadeDominio entidade) {
		
		Livro livro = (Livro) entidade;
		
		StringBuilder mensagem = new StringBuilder();
		
		if(livro.getId().intValue() == -1){
			mensagem.append("Digite um código válido\n");
		}
		
		if(livro.getTitulo().equals(".")){
			mensagem.append("Título é obrigatório\n");
		}
		
		if(livro.getAltura() == 0.0){
			mensagem.append("Altura é obrigatória\n");
		}

		if(livro.getAno() == 0){
			mensagem.append("Ano de publicação é um campo obrigatório\n");
		}
		
		if(livro.getAutor().equals(".")){
			mensagem.append("Insira o nome do autor\n");
		}
		
		if(livro.getCategorias().get(0).getId().equals(BigInteger.ZERO)){			
			mensagem.append("Selecione no mínimo um gênero \n");
		}
		
		if(livro.getEdicao().equals(".")){
			mensagem.append("Insira o ano de edição\n");
		}
		
		if(livro.getEditora().equals(".")){
			mensagem.append("Numero é obrigatório\n");
		}
		
		if(livro.getIsbn().equals(".")){
			mensagem.append("Código ISBN é obrigatório\n");
		}
		
		if(livro.getLargura() == 0.0){
			mensagem.append("Insira a largura do livro\n");
		}
		
		if(livro.getPeso() == 0.0){
			mensagem.append("Insira o peso\n");
		}

		if(livro.getProfundidade() == 0.0){
			mensagem.append("Profundidade é obrigatória\n");
		}

		if(livro.getQuantidadePaginas() == 0){
			mensagem.append("Insira a quantidade de páginas\n");
		}

		if(livro.getSinopse().equals(".")){
			mensagem.append("A sinopse é obrigatória\n");
		}

		if(livro.getCodigoBarras().equals(".")){
			mensagem.append("Insira o código de barras\n");
		}
		
		if(mensagem.length() == 0){
			return null;
		}
		return mensagem.toString();
	}

}
