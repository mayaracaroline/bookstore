package dominio;

import java.time.LocalDate;
import java.util.List;

public class Relatorio extends EntidadeDominio {
  
  private LocalDate dataInicio;
  private LocalDate dataFim;
  List<EntidadeDominio> variavel1;
  List<EntidadeDominio> variavel2;

  
  public LocalDate getDataInicio() {
    return dataInicio;
  }
  public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
  }
  public LocalDate getDataFim() {
    return dataFim;
  }
  public void setDataFim(LocalDate dataFim) {
    this.dataFim = dataFim;
  }
  public List<EntidadeDominio> getVariavel1() {
    return variavel1;
  }
  public void setVariavel1(List<EntidadeDominio> variavel1) {
    this.variavel1 = variavel1;
  }
  public List<EntidadeDominio> getVariavel2() {
    return variavel2;
  }
  public void setVariavel2(List<EntidadeDominio> variavel2) {
    this.variavel2 = variavel2;
  }
  
  
}
