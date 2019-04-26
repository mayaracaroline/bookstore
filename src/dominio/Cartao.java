package dominio;

import java.time.LocalDate;

public class Cartao extends EntidadeDominio implements IFormaDePagamento {
  
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
    
  @Override
  public void processarPagamento() {
    // TODO Auto-generated method stub
    
  }

}
