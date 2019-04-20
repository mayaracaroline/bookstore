package dominio;

public class Telefone {
  
  private TipoTelefone tipoTelefone;
  private String ddd;
  private String numero;
  
  public TipoTelefone getTipo() {
    return tipoTelefone;
  }
  public void setTipo(TipoTelefone tipo) {
    this.tipoTelefone = tipo;
  }
  public String getDdd() {
    return ddd;
  }
  public void setDdd(String ddd) {
    this.ddd = ddd;
  }
  public String getNumero() {
    return numero;
  }
  public void setNumero(String numero) {
    this.numero = numero;
  }
  
}
