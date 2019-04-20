package dominio;

import java.io.Serializable;
import java.math.BigInteger;

public class GeneroLiterario extends EntidadeDominio implements Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String descricao;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
