package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
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

    Resultado resultado = new Resultado();

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

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado alterar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado excluir(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Bloqueio bloqueio = (Bloqueio) entidade;
    ArrayList<ItemCarrinho> itensCarrinho = bloqueio.getCarrinho().getItensCarrinho();
    // Para guardar quantidade de itens a serem devolvidos para o estoque
    Integer quantidadeDevolucao = 0;
    // Index do último produto adicionado
    Integer indexProdutoAdicionado = itensCarrinho.size() - 1;
    BigInteger idProduto = itensCarrinho.get(indexProdutoAdicionado).getProduto().getId();
    
    for (int i =0; i< itensCarrinho.size(); i++) {
       ItemCarrinho item = itensCarrinho.get(i);
      
       if(item.getProduto().getId().equals(idProduto)) {
        quantidadeDevolucao += item.getQuantidade();
        itensCarrinho.remove(i);        
      }      
    }
    
    String sql = "UPDATE estoque SET est_quantidade = est_quantidade + ? WHERE est_pro_id = ?";
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, quantidadeDevolucao);
      pst.setInt(2, idProduto.intValue());
      
      pst.executeUpdate();
      
      
    } catch (Exception e) {
      // TODO: handle exception
    }
    
    
    return resultado;
  }

  @Override
  public Resultado inativar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

}
