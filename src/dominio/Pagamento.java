package dominio;

public class Pagamento {

  IFormaDePagamento formaDePagamento;
  Double valor;
  public IFormaDePagamento getFormaDePagamento() {
    return formaDePagamento;
  }
  public void setFormaDePagamento(IFormaDePagamento formaDePagamento) {
    this.formaDePagamento = formaDePagamento;
  }
  public Double getValor() {
    return valor;
  }
  public void setValor(Double valor) {
    this.valor = valor;
  }
 
}
