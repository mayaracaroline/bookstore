package dominio;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

public class Bloqueio extends EntidadeDominio {
  Carrinho carrinho;
  HttpSession sessaoUsuario; 
  LocalDateTime horarioBloqueio;
  
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
  public LocalDateTime getHorarioBloqueio() {
    return horarioBloqueio;
  }
  public void setHorarioBloqueio(LocalDateTime horarioBloqueio) {
    this.horarioBloqueio = horarioBloqueio;
  }

}
