package les.negocio;

import java.math.BigInteger;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.ItemCarrinho;
import dominio.Produto;
import les.dao.DAOEstoque;
import util.Resultado;

public class StConsultarQuantidadeEstoque implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    Bloqueio produtoBloqueado = (Bloqueio) entidade;
    Carrinho carrinho = produtoBloqueado.getCarrinho();
    // Index do último produto adicionado
    Integer indexProdutoAdicionado = carrinho.getItensCarrinho().size() - 1;
    ItemCarrinho itemCarrinho = carrinho.getItensCarrinho().get(indexProdutoAdicionado);

    
    Produto produto = itemCarrinho.getProduto();
    
    int quantidadeProdutoCarrinho = itemCarrinho.getQuantidade();
    
    DAOEstoque daoEstoque = new DAOEstoque();
    Resultado resultado = daoEstoque.consultar(produto);
    
    Estoque estoque = (Estoque) resultado.getResultado();
    Integer quantidadeEmEstoque = estoque.getQuantidade(); 
    
    if(quantidadeProdutoCarrinho > quantidadeEmEstoque) {
      BigInteger idProduto = itemCarrinho.getProduto().getId();
      mensagem = "Não há itens suficiente em estoque."
          + "Você solicitou " +  quantidadeProdutoCarrinho +
          ", mas nós só temos " + estoque.getQuantidade() + " :(";
      carrinho.removeItem(idProduto);        
    }
    resultado.erro(mensagem);   
    return mensagem;
  }

}
