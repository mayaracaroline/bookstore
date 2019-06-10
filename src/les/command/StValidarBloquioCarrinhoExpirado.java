package les.command;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import les.negocio.IStrategy;

public class StValidarBloquioCarrinhoExpirado implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    StringBuilder mensagem = new StringBuilder();
    mensagem.append("");
    Bloqueio bloqueio = (Bloqueio) entidade;
    Carrinho carrinho = bloqueio.getCarrinho();
    if(null == carrinho) {
      mensagem.append("Pedido expirado por tempo excedido, coloque os itens no carrinho novamente.\n");
           
    } 
    return mensagem.toString();
  }

}
