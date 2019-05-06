package viewhelper;

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
import util.Formatter;
import util.Resultado;

public class VHBloqueio implements IViewHelper {
  
  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    
    Bloqueio bloqueio = new Bloqueio();
    LocalDateTime horarioBloqueio = LocalDateTime.now();
    
    VHCadastrarProduto vh = new VHCadastrarProduto();
    
    Livro  produto = (Livro) vh.getEntidade(request);
    int quantidade = Formatter.format(request.getParameter("quantidade"));
    double preco = Formatter.formatDouble(request.getParameter("preco"));
    produto.setPreco(preco);    

    Carrinho carrinho = new Carrinho();
    ItemCarrinho item = new ItemCarrinho();
    ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<>();
    
    item.setProduto(produto);
    item.setQuantidade(quantidade);
    carrinho.setItensCarrinho(itensCarrinho);
    carrinho.addItem(item);
    
    HttpSession sessaoUsuario = request.getSession();
    bloqueio.setCarrinho(carrinho);
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
        response.sendRedirect("productDetails.jsp");
      }
       else if(operacao.equals("EXCLUIR")){
         request.getSession().setAttribute("sucessos", mensagem);
         request.getSession().setAttribute("livro", (Livro) resultado.getResultado());
         response.sendRedirect("carrinho.jsp");
      } else if(operacao.equals("CARRINHOALTERAR")){
        request.getSession().setAttribute("sucessos", mensagem);
        request.getSession().setAttribute("livro", (Livro) resultado.getResultado());
        response.sendRedirect("carrinho.jsp");
     } 
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }   
}
