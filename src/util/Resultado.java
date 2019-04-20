package util;

import java.util.List;

import dominio.EntidadeDominio;
import dominio.GeneroLiterario;

public class Resultado {

	private List<EntidadeDominio> listaResultado;
	private EntidadeDominio resultado;
	private boolean erro;
	private String mensagem;
	private int contagem = 0;
	
	
	public int getContagem(){
		return contagem;
	}
	public void setContagem(int contagem) {
		this.contagem = contagem;
	}

	public void erro( String mensagem ) {
		erro = true;
		this.mensagem = mensagem;
	}
	
	public void sucesso( String mensagem ) {
		erro = false;
		this.mensagem = mensagem;
	}
	
	public String getMensagem(){
		return mensagem;
	}
	public boolean getErro(){
		return erro;
	}
	
	public List<EntidadeDominio> getListaResultado() {
		return listaResultado;
	}
	public void setListaResultado(List<EntidadeDominio> listaResultado) {
		this.listaResultado = listaResultado;
	}
	public EntidadeDominio getResultado() {
		return resultado;
	}
	public void setResultado(EntidadeDominio resultado) {
		this.resultado = resultado;
	}
	
}
