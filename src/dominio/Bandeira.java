package dominio;

import java.io.Serializable;

public class Bandeira extends EntidadeDominio implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  String nome;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
  
}
