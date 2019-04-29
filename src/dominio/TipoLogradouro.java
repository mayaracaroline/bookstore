package dominio;

import java.io.Serializable;

public class TipoLogradouro extends EntidadeDominio implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String tipo;

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
 
}

