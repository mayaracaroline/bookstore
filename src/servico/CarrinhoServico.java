package servico;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
    // Apenas um item é adicionado por vez (index sempre será 0)
    ItemCarrinho itemBloqueado = produtoBloqueado.getCarrinho().getItensCarrinho().get(0);
    Produto produto = itemBloqueado.getProduto();
    BigInteger idProduto = produto.getId();    
    HttpSession sessaoUsuario = produtoBloqueado.getSessao();
    Carrinho carrinhoSessao = (Carrinho) sessaoUsuario.getAttribute("carrinho");
    ArrayList<ItemCarrinho> itensCarrinhoSessao = carrinhoSessao.getItensCarrinho();
    Integer quantidadeReservados = 0;

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
    Integer totalDeProdutosAInserir = quantidadeReservados + itemBloqueado.getQuantidade();
    
  boolean contemProduto = false; 
  
    for ( int i = 0; i < itensCarrinhoSessao.size(); i++) {
      Produto prod = itensCarrinhoSessao.get(i).getProduto();
      if(prod.getId().equals(idProduto)) {
        
        itensCarrinhoSessao.get(i).setQuantidade(totalDeProdutosAInserir);
    
        contemProduto = true;
        break;
      }    
    }
  
    if (!contemProduto) {
      ItemCarrinho item = new ItemCarrinho();
      
      item.setProduto(produto);
      item.setQuantidade(totalDeProdutosAInserir);
          
      carrinhoSessao.addItem(item);     
    }

    
    Integer quantidadeDeItensBloqueados = 0;
    
    // Conta total de produtos no carrinho
    for(int i = 0; i < itensCarrinhoSessao.size(); i++) {
       ItemCarrinho item = itensCarrinhoSessao.get(i);
       quantidadeDeItensBloqueados += item.getQuantidade();  
    }
    
    carrinhoSessao.setQuantidadeProdutos(quantidadeDeItensBloqueados);
    
    sessaoUsuario.setAttribute("carrinho", carrinhoSessao);
    
    HashMap<String, Bloqueio> mapProdutosBloqueados;
    mapProdutosBloqueados = (HashMap<String, Bloqueio>) sessaoUsuario.getServletContext()
        .getAttribute("bloqueio");
    
    produtoBloqueado.setCarrinho(carrinhoSessao);
    
    // Atualiza de lista de produtos bloqueados ( todos os usuários )
    mapProdutosBloqueados.put(sessaoUsuario.getId(), produtoBloqueado);

    
    resultado.setResultado(produto);
    resultado.sucesso("Produto inserido com sucesso");
    
    return resultado;
  }
  
  public Resultado alterarQuantidadeItens(EntidadeDominio entidade) {
    Resultado resultado = new Resultado(); 
    Bloqueio produtoBloqueado = (Bloqueio) entidade;  
    // Apenas um item é adicionado por vez (index sempre será 0)
    ItemCarrinho itemBloqueado = produtoBloqueado.getCarrinho().getItensCarrinho().get(0);
    Produto produto = itemBloqueado.getProduto();
    BigInteger idProduto = produto.getId();    
    HttpSession sessaoUsuario = produtoBloqueado.getSessao();
    Carrinho carrinhoSessao = (Carrinho) sessaoUsuario.getAttribute("carrinho");
    ArrayList<ItemCarrinho> itensCarrinhoSessao = carrinhoSessao.getItensCarrinho();
    Integer quantidadeDeItensAlterar = itemBloqueado.getQuantidade();
    
    Integer quantidadeDeItensSessao = 0;
    HashMap<String, Bloqueio> mapProdutosBloqueados;
    mapProdutosBloqueados = (HashMap<String, Bloqueio>) sessaoUsuario.getServletContext()
        .getAttribute("bloqueio");
    
       
    for(int i = 0; i < itensCarrinhoSessao.size(); i++) {
       ItemCarrinho item = itensCarrinhoSessao.get(i);
       if(item.getProduto().getId().equals(produto.getId())) {
         quantidadeDeItensSessao = item.getQuantidade();
       }  
     }
    
    Integer quantidadeAlterada = 0;
    
    if (quantidadeDeItensAlterar > quantidadeDeItensSessao ) {
      int valorASerAcrescentado = quantidadeDeItensAlterar - quantidadeDeItensSessao;
      quantidadeAlterada = quantidadeDeItensSessao + valorASerAcrescentado;   
    } else {      
      quantidadeAlterada = quantidadeDeItensAlterar;      
    }
    
    for ( int i = 0; i < itensCarrinhoSessao.size(); i++) {
      Produto prod = itensCarrinhoSessao.get(i).getProduto();
      if(prod.getId().equals(idProduto)) {
        
        itensCarrinhoSessao.get(i).setQuantidade(quantidadeAlterada);
    
        break;
      }    
    }
    
    sessaoUsuario.setAttribute("carrinho", carrinhoSessao);
    produtoBloqueado.setHorarioBloqueio(LocalDateTime.now());    
    produtoBloqueado.setCarrinho(carrinhoSessao);
    
    // Atualiza de lista de produtos bloqueados ( todos os usuários )
    mapProdutosBloqueados.put(sessaoUsuario.getId(), produtoBloqueado);

    
    resultado.setResultado(produto);
    resultado.sucesso("Produto inserido com sucesso");
        
    return resultado;
  }
  
  @SuppressWarnings("unchecked")
  public Resultado excluirItens(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Bloqueio produtosBloqueados = (Bloqueio) entidade;
    
    ArrayList<ItemCarrinho> itensCarrinho = produtosBloqueados.getCarrinho().getItensCarrinho();
    // Para guardar quantidade de itens a serem devolvidos para o estoque
    HttpSession sessaoUsuario = produtosBloqueados.getSessao();
    Carrinho carrinhoSessao = (Carrinho) sessaoUsuario.getAttribute("carrinho");
    ArrayList<ItemCarrinho> itensCarrinhoSessao = carrinhoSessao.getItensCarrinho();
    BigInteger idProduto = itensCarrinho.get(0).getProduto().getId();
    Produto produto = itensCarrinho.get(0).getProduto();

    
    for (int i = 0; i< itensCarrinhoSessao.size(); i++) {
       ItemCarrinho item = itensCarrinho.get(i);
      
       if(item.getProduto().getId().equals(idProduto)) {
        itensCarrinhoSessao.remove(i);        
      }      
    }
    
    HashMap<String, Bloqueio> mapProdutosBloqueados;
    mapProdutosBloqueados = (HashMap<String, Bloqueio>) sessaoUsuario.getServletContext()
        .getAttribute("bloqueio");
    
    sessaoUsuario.setAttribute("carrinho", carrinhoSessao);
    produtosBloqueados.setHorarioBloqueio(LocalDateTime.now());    
    produtosBloqueados.setCarrinho(carrinhoSessao);
    mapProdutosBloqueados.put(sessaoUsuario.getId(), produtosBloqueados);
    System.out.println("Carrinho excluir");
    resultado.setResultado(produto);
    resultado.sucesso("Excluído com sucesso");
    return resultado;
  }
  
}
