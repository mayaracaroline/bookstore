package viewhelper;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Livro;
import dominio.Produto;
import util.Numero;
import util.Resultado;

public class VHBloqueio implements IViewHelper {
  private Carrinho carrinho;
  
  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    if (null == carrinho ) {
      
      Carrinho carrinho = new Carrinho();
      ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<>();
      carrinho.setItensCarrinho(itensCarrinho);
      carrinho.setStatus(true);
      this.carrinho = carrinho;
    }
    
    if (request.getAttribute("carrinho") != null) {
      System.out.println("getAttribute(carrinho)");
      carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
    } else {
      request.getSession().setAttribute("carrinho", carrinho);
    }
    
    Bloqueio bloqueio = new Bloqueio();
    LocalDateTime horarioBloqueio = LocalDateTime.now();
    
    VHCadastrarProduto vh = new VHCadastrarProduto();
    
    Livro  produto = (Livro) vh.getEntidade(request);
    int quantidade = Numero.format(request.getParameter("quantidade"));
    
    BigInteger idProduto = produto.getId();
    boolean contemProduto = false;
    for ( int i = 0; i < this.carrinho.getItensCarrinho().size(); i++) {
      Produto prod = this.carrinho.getItensCarrinho().get(i).getProduto();
      if(prod.getId().equals(idProduto)) {
        int quantidadeProdutosCarrinho = this.carrinho.getItensCarrinho().get(i).getQuantidade();
//        
//        if(quantidadeProdutosCarrinho >= quantidade) {
//          int quantidadeAserExcluida = quantidadeProdutosCarrinho - quantidade;
//          carrinho.getItensCarrinho().get(i)
//          .setQuantidade(quantidadeProdutosCarrinho 
//              - quantidadeAserExcluida);
//        } else {
//          int quantidadeAserAdicionada = quantidade - quantidadeProdutosCarrinho;
//          carrinho.getItensCarrinho().get(i)
//          .setQuantidade(quantidadeProdutosCarrinho 
//              + quantidadeAserAdicionada);
//        }
        this.carrinho.getItensCarrinho().get(i).setQuantidade(quantidadeProdutosCarrinho + 1);
        ItemCarrinho item = this.carrinho.getItensCarrinho().get(i);
        this.carrinho.getItensCarrinho().add(item);
        this.carrinho.getItensCarrinho().remove(i);
        contemProduto = true;
        break;
      }    
    }
    
    if (!contemProduto) {
      ItemCarrinho item = new ItemCarrinho();
      
      item.setProduto(produto);
      item.setQuantidade(quantidade);
          
      this.carrinho.addItem(item);     
    }
    
    HttpSession sessaoUsuario = request.getSession();
   
    bloqueio.setCarrinho(this.carrinho);
    bloqueio.setHorarioBloqueio(horarioBloqueio);
    bloqueio.setSessao(sessaoUsuario);    
    
    return bloqueio;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    String operacao = request.getParameter("operacao");
    String page = null != request.getParameter("page") ? request.getParameter("page") : "product" ;
    int codigo = Numero.format(request.getParameter("codigo"));   
    String mensagem[] = resultado.getMensagem().split("\n");
    
    if(resultado.getErro())
      request.getSession().setAttribute("errosBloqueio", mensagem);
    else
      request.getSession().setAttribute("sucessos", mensagem);
    
    if(operacao.equals("SALVAR")){
      if(resultado.getErro()){
        request.setAttribute("livro", resultado.getResultado());
      }
    } else if(operacao.equals("EXCLUIR")){
      if(resultado.getErro()){
        request.setAttribute("resultado", (Livro) resultado.getResultado());
      } 
    }
    try {
      if(operacao.equals("SALVAR")){
        request.getSession().setAttribute("sucessos", mensagem);
        request.getSession().setAttribute("livro", (Livro) resultado.getResultado());
        response.sendRedirect("/livraria/Pages/lumino/productDetails.jsp");
      }
       else if(operacao.equals("EXCLUIR")){
        request.setAttribute("livro", (Livro) resultado.getResultado());
        RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/productDetails.jsp");
        rd.forward(request, response);
      } 
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }   
}
