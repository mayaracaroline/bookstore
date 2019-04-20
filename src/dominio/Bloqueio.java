package dominio;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

public class Bloqueio extends EntidadeDominio {
  Carrinho carrinho;
  HttpSession sessaoUsuario; 
  LocalDate horarioBloqueio;
  
  public Carrinho getCarrinho() {
    return carrinho;
  }
  public void setCarrinho(Carrinho carrinho) {
    this.carrinho = carrinho;
  }
  public HttpSession getSessao() {
    return sessaoUsuario;
  }
  public void setSessao(HttpSession sessao) {
    this.sessaoUsuario = sessao;
  }
  public LocalDate getHorarioBloqueio() {
    return horarioBloqueio;
  }
  public void setHorarioBloqueio(LocalDate horarioBloqueio) {
    this.horarioBloqueio = horarioBloqueio;
  }

}
