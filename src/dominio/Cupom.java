package dominio;

import java.time.LocalDate;

public class Cupom extends EntidadeDominio implements IFormaDePagamento {
  
  Double valor;
  String codigo;
  String tipo;
  LocalDate dataDeValidade;  

  @Override
  public void processarPagamento() {
    // TODO Auto-generated method stub
    
  }  

}
