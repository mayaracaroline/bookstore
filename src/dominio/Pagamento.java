package dominio;

public class Pagamento extends EntidadeDominio {

  FormaDePagamento formaDePagamento;
  Double valor;
  Integer quantidadeParcelas;
  
  public FormaDePagamento getFormaDePagamento() {
    return formaDePagamento;
  }
  
  public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
    this.formaDePagamento = formaDePagamento;
  }
  public Double getValor() {
    return valor;
  }
  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Integer getQuantidadeParcelas() {
    return quantidadeParcelas;
  }

  public void setQuantidadeParcelas(Integer quantidadeParcelas) {
    this.quantidadeParcelas = quantidadeParcelas;
  }
 
}
