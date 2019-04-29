package dominio;

import java.time.LocalDate;

public class Cupom extends EntidadeDominio implements IFormaDePagamento {
  
  Double valor;
  String codigo;
  TipoCupom tipo;
  LocalDate dataDeValidade; 
  boolean status;

  @Override
  public void processarPagamento() {
    // TODO Auto-generated method stub
    
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public TipoCupom getTipo() {
    return tipo;
  }

  public void setTipo(TipoCupom tipo) {
    this.tipo = tipo;
  }

  public LocalDate getDataDeValidade() {
    return dataDeValidade;
  }

  public void setDataDeValidade(LocalDate dataDeValidade) {
    this.dataDeValidade = dataDeValidade;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }  

}
