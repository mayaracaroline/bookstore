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
import util.Formatter;
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
    
    if (request.getSession().getAttribute("carrinho") == null) {
      Carrinho novoCarrinho = new Carrinho();
      ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<>();
      novoCarrinho.setItensCarrinho(itensCarrinho);
      novoCarrinho.setStatus(true);      
      request.getSession().setAttribute("carrinho", novoCarrinho); //colocar o mesmo carrinho
    }
    
    Bloqueio bloqueio = new Bloqueio();
    LocalDateTime horarioBloqueio = LocalDateTime.now();
    
    VHCadastrarProduto vh = new VHCadastrarProduto();
    
    Livro  produto = (Livro) vh.getEntidade(request);
    int quantidade = Formatter.format(request.getParameter("quantidade"));
    double preco = Formatter.formatDouble(request.getParameter("preco"));
    produto.setPreco(preco);
    BigInteger idProduto = produto.getId();
    boolean contemProduto = false;
    
    for ( int i = 0; i < this.carrinho.getItensCarrinho().size(); i++) {
      Produto prod = this.carrinho.getItensCarrinho().get(i).getProduto();
      if(prod.getId().equals(idProduto)) {
        this.carrinho.getItensCarrinho().get(i).setQuantidade(quantidade);
        ItemCarrinho item = this.carrinho.getItensCarrinho().get(i);
        // Coloca o item como último da lista (para controle na Strategy e DAO)
        this.carrinho.getItensCarrinho().add(item);
        // Exclui o atual para não haver duplicidade
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
    int codigo = Formatter.format(request.getParameter("codigo"));   
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
      if(operacao.equals("CARRINHOADICIONAR")){
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
