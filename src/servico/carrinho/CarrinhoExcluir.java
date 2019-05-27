package servico.carrinho;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Produto;
import servico.IServico;
import util.Resultado;

public class CarrinhoExcluir implements IServico {

  @Override
  public Resultado executarServico(EntidadeDominio entidade) {
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
    resultado.setResultado(produto);

    return resultado;
  }

}
