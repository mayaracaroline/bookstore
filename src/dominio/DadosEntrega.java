package dominio;

import java.time.LocalDate;

public class DadosEntrega extends EntidadeDominio {
  
  private double frete;
  private LocalDate dataEntrega;
  private Endereco enderecoEntrega;
  
  public double getFrete() {
    return frete;
  }
  public void setFrete(double preco) {
    this.frete = preco;
  }
  public LocalDate getDataEntrega() {
    return dataEntrega;
  }
  public void setDataEntrega(LocalDate dataEntrega) {
    this.dataEntrega = dataEntrega;
  }
  
  public Endereco getEnderecoEntrega() {
    return enderecoEntrega;
  }
  public void setEnderecoEntrega(Endereco enderecoEntrega) {
    this.enderecoEntrega = enderecoEntrega;
  }
  
}
