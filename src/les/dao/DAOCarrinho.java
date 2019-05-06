package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
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

public class DAOCarrinho extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
//    Bloqueio produtoBloqueado = (Bloqueio) entidade;
//    Carrinho carrinho = produtoBloqueado.getCarrinho();
//    // Index do último produto adicionado
//    Integer indexProdutoAdicionado = carrinho.getItensCarrinho().size() - 1;
//    ItemCarrinho itemCarrinho = carrinho.getItensCarrinho().get(indexProdutoAdicionado);
//    Produto produto = itemCarrinho.getProduto();
//    Integer quantidadeReservados = 0;
//    HttpSession sessaoUsuario = produtoBloqueado.getSessao();
//    Carrinho carrinhoSessao = (Carrinho) sessaoUsuario.getAttribute("carrinho");
//    ArrayList<ItemCarrinho> itensCarrinho = carrinhoSessao.getItensCarrinho();
//  
//    Resultado resultado = new Resultado();
//
//    for (int i= 0; i < carrinhoSessao.getItensCarrinho().size();i++) {
//      Produto produtoSessao = carrinhoSessao
//          .getItensCarrinho()
//          .get(i).getProduto();
//      // Armazenar a quantidade atual de um determinado produto
//      if(produtoSessao.getId().equals(produto.getId())) {
//        quantidadeReservados = carrinhoSessao
//            .getItensCarrinho()
//            .get(i).getQuantidade();
//      }      
//    }
//    Integer totalDeProdutosAInserir = quantidadeReservados + itemCarrinho.getQuantidade();
//    
//    Carrinho novoCarrinho = new Carrinho();
//    ItemCarrinho novoItem = new ItemCarrinho();
//    
//    if (itensCarrinho.size() > indexProdutoAdicionado ) {
//      itensCarrinho.get(indexProdutoAdicionado).setQuantidade(totalDeProdutosAInserir);
//    } else {
//      novoItem.setProduto(produto);
//      novoItem.setQuantidade(totalDeProdutosAInserir);
//      itensCarrinho.add(novoItem);     
//    }
//
//    novoCarrinho.setItensCarrinho(itensCarrinho);
//    novoCarrinho.setStatus(true);
//    
//    sessaoUsuario.setAttribute("carrinho", novoCarrinho);
//
//    HashMap<String, Bloqueio> mapProdutosBloqueados;
//    mapProdutosBloqueados = (HashMap<String, Bloqueio>) sessaoUsuario.getServletContext()
//        .getAttribute("bloqueio");
//    
//    Integer quantidadeDeItensBloqueados = 0;
//    
//    for(Map.Entry<String, Bloqueio> entry : mapProdutosBloqueados.entrySet()) {
//      
//      Bloqueio bloqueioCarrinho = (Bloqueio) entry.getValue();
//       
//      for(int i = 0; i < bloqueioCarrinho.getCarrinho().getItensCarrinho().size(); i++) {
//         ItemCarrinho itemBloqueado = bloqueioCarrinho.getCarrinho().getItensCarrinho().get(i);
//         if(itemBloqueado.getProduto().getId().equals(produto.getId())) {
//           quantidadeDeItensBloqueados += itemBloqueado.getQuantidade();
//         }  
//       }
//    }
//    
//    produtoBloqueado.setCarrinho(novoCarrinho);
//    mapProdutosBloqueados.put(sessaoUsuario.getId(), produtoBloqueado);
//
//    
//    resultado.setResultado(produto);
//    resultado.sucesso("Produto inserido com sucesso");
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado alterar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Bloqueio produtosBloqueados = (Bloqueio) entidade;
    Carrinho carrinho = produtosBloqueados.getCarrinho();
    ArrayList<ItemCarrinho> itensCarrinho = carrinho.getItensCarrinho();
    Integer indexUltimoProdutoAdicionado = itensCarrinho.size() - 1;
    Produto produtoASerInserido = itensCarrinho
        .get(indexUltimoProdutoAdicionado).getProduto();
    // Armazena a quantidade a ser removida ou inserida
    Integer quantidade = itensCarrinho
        .get(indexUltimoProdutoAdicionado).getQuantidade();
    HttpSession sessaoUsuario = produtosBloqueados.getSessao();
    Carrinho carrinhoSessao = (Carrinho) sessaoUsuario.getAttribute("carrinho");
    ArrayList<ItemCarrinho> itensCarrinhoSessao = carrinhoSessao.getItensCarrinho();
    
    Integer quantidadeDeItensBloqueados = 0;
    HashMap<String, Bloqueio> mapProdutosBloqueados;
    mapProdutosBloqueados = (HashMap<String, Bloqueio>) sessaoUsuario.getServletContext()
        .getAttribute("bloqueio");
    
    for(Map.Entry<String, Bloqueio> entry : mapProdutosBloqueados.entrySet()) {
      
      Bloqueio bloqueioCarrinho = (Bloqueio) entry.getValue();
       
      for(int i = 0; i < bloqueioCarrinho.getCarrinho().getItensCarrinho().size(); i++) {
         ItemCarrinho itemBloqueado = bloqueioCarrinho.getCarrinho().getItensCarrinho().get(i);
         if(itemBloqueado.getProduto().getId().equals(produtoASerInserido.getId())) {
           quantidadeDeItensBloqueados += itemBloqueado.getQuantidade();
         }  
       }
    }
    
    Carrinho novoCarrinho = new Carrinho();
    ItemCarrinho novoItem = new ItemCarrinho();
    Integer quantidadeAlterada = 0;
    
    if (quantidade > quantidadeDeItensBloqueados ) {
      int valorASerAcrescentado = quantidade - quantidadeDeItensBloqueados;
      quantidadeAlterada = quantidadeDeItensBloqueados + valorASerAcrescentado;   
    } else {      
      quantidadeAlterada = quantidadeDeItensBloqueados - quantidade;      
    }
    
    if (itensCarrinhoSessao.size() > indexUltimoProdutoAdicionado ) {
      itensCarrinhoSessao.get(indexUltimoProdutoAdicionado).setQuantidade(quantidadeAlterada);
    } else {
      novoItem.setProduto(produtoASerInserido);
      novoItem.setQuantidade(quantidadeAlterada);
      itensCarrinhoSessao.add(novoItem);     
    }

    novoCarrinho.setItensCarrinho(itensCarrinhoSessao);
    
    sessaoUsuario.setAttribute("carrinho", novoCarrinho);
    produtosBloqueados.setHorarioBloqueio(LocalDateTime.now());    
    produtosBloqueados.setCarrinho(novoCarrinho);
    mapProdutosBloqueados.put(sessaoUsuario.getId(), produtosBloqueados);

    
    resultado.setResultado(produtoASerInserido);
    resultado.sucesso("Produto inserido com sucesso");
        
    return resultado;
  }

  @Override
  public Resultado excluir(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Bloqueio produtosBloqueados = (Bloqueio) entidade;
    
    ArrayList<ItemCarrinho> itensCarrinho = produtosBloqueados.getCarrinho().getItensCarrinho();
    // Para guardar quantidade de itens a serem devolvidos para o estoque
    Integer quantidadeDevolucao = 0;
    // Index do último produto adicionado
    Integer indexProdutoAdicionado = itensCarrinho.size() - 1;
    HttpSession sessaoUsuario = produtosBloqueados.getSessao();
    Carrinho carrinhoSessao = (Carrinho) sessaoUsuario.getAttribute("carrinho");
    ArrayList<ItemCarrinho> itensCarrinhoSessao = carrinhoSessao.getItensCarrinho();
    BigInteger idProduto = itensCarrinho.get(indexProdutoAdicionado).getProduto().getId();
    Produto produto = itensCarrinho.get(indexProdutoAdicionado).getProduto();

    
    for (int i = 0; i< itensCarrinhoSessao.size(); i++) {
       ItemCarrinho item = itensCarrinho.get(i);
      
       if(item.getProduto().getId().equals(idProduto)) {
        itensCarrinho.remove(i);
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
    
    resultado.setResultado(produto);
    resultado.sucesso("Excluído com sucesso");
    return resultado;
  }

  @Override
  public Resultado inativar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

}
