package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dominio.EntidadeDominio;

public class Resultado {

	private List<EntidadeDominio> listaResultado;
	private HashMap<String, ArrayList<EntidadeDominio>> mapResultado;
	 private HashMap<Integer, Integer[]> mapResultadoInteger;
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
  public HashMap<String, ArrayList<EntidadeDominio>> getMapResultado() {
    return mapResultado;
  }
  public void setMapResultado(HashMap<String, ArrayList<EntidadeDominio>> mapResultado) {
    this.mapResultado = mapResultado;
  }
  public HashMap<Integer, Integer[]> getMapResultadoInteger() {
    return mapResultadoInteger;
  }
  public void setMapResultadoInteger(HashMap<Integer, Integer[]> mapResultadoInteger) {
    this.mapResultadoInteger = mapResultadoInteger;
  }
  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
	
}
