package les.negocio;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
    HttpSession sessaoUsuario = produtoBloqueado.getSessao();
    
    Produto produto = itemCarrinho.getProduto();
    
    Integer quantidadeAInserir = itemCarrinho.getQuantidade();      
    
    DAOEstoque daoEstoque = new DAOEstoque();
    Resultado resultado = daoEstoque.consultar(produto);
    
    Integer quantidadeDeItensBloqueados = 0;
    HashMap<String, Bloqueio> mapProdutosBloqueados;
    mapProdutosBloqueados = (HashMap<String, Bloqueio>) sessaoUsuario.getServletContext()
        .getAttribute("bloqueio");
    
    for(Map.Entry<String, Bloqueio> entry : mapProdutosBloqueados.entrySet()) {
      
      Bloqueio bloqueioCarrinho = (Bloqueio) entry.getValue();
       
      for(int i = 0; i < bloqueioCarrinho.getCarrinho().getItensCarrinho().size(); i++) {
         ItemCarrinho itemBloqueado = bloqueioCarrinho.getCarrinho().getItensCarrinho().get(i);
         if(itemBloqueado.getProduto().getId().equals(produto.getId())) {
           quantidadeDeItensBloqueados += itemBloqueado.getQuantidade();
         }  
       }
    }
 
    Estoque estoque = (Estoque) resultado.getResultado();
    Integer quantidadeEmEstoque = estoque.getQuantidade(); 
    Integer quantidadeDisponivel = quantidadeEmEstoque - quantidadeDeItensBloqueados ;
   
    if(quantidadeAInserir > quantidadeDisponivel) {
      BigInteger idProduto = itemCarrinho.getProduto().getId();
      mensagem = "Não há itens suficiente em estoque."
          + "Você solicitou " +  quantidadeAInserir +
          ", mas nós só temos " + quantidadeDisponivel + " :(";
      carrinho.removeItem(idProduto);        
    }
    
    resultado.erro(mensagem);   
    return mensagem;
  }

}
