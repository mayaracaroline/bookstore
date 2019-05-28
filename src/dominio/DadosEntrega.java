package dominio;

import java.time.LocalDate;

public class DadosEntrega extends EntidadeDominio {
  
  private double frete;
  private LocalDate dataEntrega;
  
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
  
}
