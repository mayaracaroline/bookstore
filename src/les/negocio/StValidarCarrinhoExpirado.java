package les.negocio;

import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.Livro;
import dominio.PedidoDeCompra;

public class StValidarCarrinhoExpirado implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    StringBuilder mensagem = new StringBuilder();
    mensagem.append("");
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Carrinho carrinho = pedido.getCarrinho();
    if(null == carrinho) {
      mensagem.append("Pedido expirado por tempo excedido,");
      mensagem.append("coloque os itens no carrinho novamente.");
           
    } else if(!carrinho.isStatus()) {
      if(null !=  carrinho.getItensCarrinho()) {
        mensagem.append("Pedido expirado por tempo excedido,");
        mensagem.append("os seguintes itens foram retirados do carrinho: ");
        
        for(int i = 0; i < carrinho.getItensCarrinho().size(); i++) {
          Livro livro = (Livro) carrinho.getItensCarrinho().get(i).getProduto();
          mensagem.append("- ");
          mensagem.append(livro.getTitulo());
          mensagem.append("\n");
        }
      } else {
        mensagem.append("Pedido expirado por tempo excedido,");
        mensagem.append("coloque os itens no carrinho novamente.");
      }
    }
    
    return mensagem.toString();
  }

}
