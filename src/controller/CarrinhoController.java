package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Carrinho;
import dominio.ItemCarrinho;
import dominio.Livro;
import util.Numero;
import viewhelper.VHCadastrarProduto;

/**
 * Servlet implementation class CarrinhoController
 */
@WebServlet("/Pages/lumino/carrinhoBloqueio")
public class CarrinhoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Carrinho carrinho;
	
	Carrinho getCarrinho() {
	  
	  if (null == carrinho ) {
	    
	    Carrinho carrinho = new Carrinho();
	    ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<>();
	    carrinho.setItensCarrinho(itensCarrinho);
	    this.carrinho = carrinho;
	  }
	  
	  return carrinho;
	}
	
//	private void addItem(ItemCarrinho item) {
//    this.getCarrinho().getItensCarrinho().add(item);	  
//	}
//	
//	private void removeItem(ItemCarrinho item) {
//	  ArrayList<ItemCarrinho> itens = getCarrinho().getItensCarrinho();
//	  int idProduto = item.getItem().getId().intValue();
//	  
//	  for (int i = 0; i < itens.size(); i++) {
//      
//	    if (itens.get(i).getItem().getId().intValue() ==  idProduto) {
//	      itens.remove(i);
//	    }
//    }
//	  
//	}
	
  @SuppressWarnings("unlikely-arg-type")
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    
    if(getServletContext().getAttribute("bloqueio") == null) {
      HashMap<String, Carrinho> mapProdutosBloqueados = new HashMap<>();     
      getServletContext().setAttribute("bloqueio", mapProdutosBloqueados);
    }
    
    @SuppressWarnings("unchecked")
    HashMap<String, Carrinho> produtosBloqueados = (HashMap<String, Carrinho>) getServletContext().getAttribute("bloqueio");    
    System.out.println(produtosBloqueados);
       
    if (req.getSession().getAttribute("carrinho") != null) {
      System.out.println("getAttribute(carrinho)");
      this.carrinho = (Carrinho) req.getSession().getAttribute("carrinho");
    }
    
    VHCadastrarProduto vh = new VHCadastrarProduto();
    
    Livro  produto = (Livro) vh.getEntidade(req);
    int quantidade = Numero.format(req.getParameter("quantidade"));
        
    ItemCarrinho item = new ItemCarrinho();
    
    item.setProduto(produto);
    item.setQuantidade(quantidade);
        
    getCarrinho().addItem(item);
    
    req.getSession().setAttribute("carrinho", getCarrinho());
    this.carrinho = (Carrinho) req.getSession().getAttribute("carrinho");  
    if(produtosBloqueados.containsValue(req.getSession().getId())) {
      produtosBloqueados.get(req.getSession().getId()).addItem(item);
    } else {
      produtosBloqueados.put(req.getSession().getId(), getCarrinho());
    }
    

    
    System.out.println(produtosBloqueados.get(req.getSession().getId()).getItensCarrinho().size());
    RequestDispatcher rd = req.getRequestDispatcher("/Pages/lumino/productDetails.jsp");
    rd.forward(req, resp);

  }

}
