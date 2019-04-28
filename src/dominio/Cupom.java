package dominio;

import java.time.LocalDate;

public class Cupom extends EntidadeDominio implements IFormaDePagamento {
  
  Double valor;
  String codigo;
  TipoCupom tipo;
  LocalDate dataDeValidade;  

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

}
