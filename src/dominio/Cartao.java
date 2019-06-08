package dominio;

import java.io.Serializable;
import java.time.LocalDate;

public class Cartao extends FormaDePagamento implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Bandeira bandeira;
  private int codSeguranca;
  private String nomeTitular;
  private String numero;
  private boolean preferencial;
  LocalDate dataDeValidade;
  
  public Bandeira getBandeira() {
    return bandeira;
  }
  public void setBandeira(Bandeira bandeira) {
    this.bandeira = bandeira;
  }
  public String getNumero() {
    return numero;
  }
  public void setNumero(String numero) {
    this.numero = numero;
  }
  public int getCodSeguranca() {
    return codSeguranca;
  }
  public void setCodSeguranca(int codSeguranca) {
    this.codSeguranca = codSeguranca;
  }
  public boolean isPreferencial() {
    return preferencial;
  }
  public void setPreferencial(boolean preferencial) {
    this.preferencial = preferencial;
  }
  public String getNomeTitular() {
    return nomeTitular;
  }
  public void setNomeTitular(String nomeTitular) {
    this.nomeTitular = nomeTitular;
  }
    

}
