package servico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Produto;
import util.Resultado;

public class CarrinhoServico {
  
  public Resultado adicionar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado(); 
    Bloqueio produtoBloqueado = (Bloqueio) entidade;
    Carrinho carrinho = produtoBloqueado.getCarrinho();
    // Index do último produto adicionado
    Integer indexProdutoAdicionado = carrinho.getItensCarrinho().size() - 1;
    ItemCarrinho itemCarrinho = carrinho.getItensCarrinho().get(indexProdutoAdicionado);
    Produto produto = itemCarrinho.getProduto();
    Integer quantidadeReservados = 0;
    HttpSession sessaoUsuario = produtoBloqueado.getSessao();
    Carrinho carrinhoSessao = (Carrinho) sessaoUsuario.getAttribute("carrinho");
    ArrayList<ItemCarrinho> itensCarrinho = carrinhoSessao.getItensCarrinho();

    for (int i= 0; i < carrinhoSessao.getItensCarrinho().size();i++) {
      Produto produtoSessao = carrinhoSessao
          .getItensCarrinho()
          .get(i).getProduto();
      // Armazenar a quantidade atual de um determinado produto
      if(produtoSessao.getId().equals(produto.getId())) {
        quantidadeReservados = carrinhoSessao
            .getItensCarrinho()
            .get(i).getQuantidade();
      }      
    }
    Integer totalDeProdutosAInserir = quantidadeReservados + itemCarrinho.getQuantidade();
    
    Carrinho novoCarrinho = new Carrinho();
    ItemCarrinho novoItem = new ItemCarrinho();
    
    if (itensCarrinho.size() > indexProdutoAdicionado ) {
      itensCarrinho.get(indexProdutoAdicionado).setQuantidade(totalDeProdutosAInserir);
    } else {
      novoItem.setProduto(produto);
      novoItem.setQuantidade(totalDeProdutosAInserir);
      itensCarrinho.add(novoItem);     
    }

    novoCarrinho.setItensCarrinho(itensCarrinho);
    novoCarrinho.setStatus(true);
    
    sessaoUsuario.setAttribute("carrinho", novoCarrinho);

    HashMap<String, Bloqueio> mapProdutosBloqueados;
    mapProdutosBloqueados = (HashMap<String, Bloqueio>) sessaoUsuario.getServletContext()
        .getAttribute("bloqueio");
    
    Integer quantidadeDeItensBloqueados = 0;
    
    for(Map.Entry<String, Bloqueio> entry : mapProdutosBloqueados.entrySet()) {
      
      Bloqueio bloqueioCarrinho = (Bloqueio) entry.getValue();
       
      for(int i = 0; i < bloqueioCarrinho.getCarrinho().getItensCarrinho().size(); i++) {
         ItemCarrinho itemBloqueado = bloqueioCarrinho.getCarrinho().getItensCarrinho().get(i);
         if(itemBloqueado.getProduto().getId().equals(produto.getId())) {
           quantidadeDeItensBloqueados += itemBloqueado.getQuantidade();
         }  
       }
    }
    
    produtoBloqueado.setCarrinho(novoCarrinho);
    mapProdutosBloqueados.put(sessaoUsuario.getId(), produtoBloqueado);

    
    resultado.setResultado(produto);
    resultado.sucesso("Produto inserido com sucesso");
    
    return resultado;
  }
  
}
