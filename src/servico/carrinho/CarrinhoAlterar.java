package servico.carrinho;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Produto;
import servico.IServico;
import util.Resultado;

public class CarrinhoAlterar implements IServico {

  @Override
  public Resultado executarServico(EntidadeDominio entidade) {
    System.out.println("CarrinhoAltera");
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

}
