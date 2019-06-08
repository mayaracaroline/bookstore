package viewhelper;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.PedidoDeCompra;
import dominio.Produto;
import util.Formatter;
import util.Resultado;

public class VHItemPedido implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    ItemCarrinho item = new ItemCarrinho();
    PedidoDeCompra pedido = new PedidoDeCompra();
    Produto produto = new Produto();
    ArrayList<ItemCarrinho> itens = new ArrayList<ItemCarrinho>();
    int idPedido = Formatter.format(request.getParameter("idPedido"));
    int idItem = Formatter.format(request.getParameter("idItemPedido"));
    int qtdProduto = Formatter.format(request.getParameter("qtdProduto"));
    String codBarras = Formatter.formatString(request.getParameter("codBarras"));
    Carrinho carrinho = new Carrinho();
    
    produto.setCodigoBarras(codBarras);
    item.setQuantidade(qtdProduto);
    item.setId(idItem);
    item.setProduto(produto);
    itens.add(item);
    carrinho.setItensCarrinho(itens);
    
    pedido.setId(idPedido);
    pedido.setCarrinho(carrinho);
    
    return pedido;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    int idPedido = Formatter.format(request.getParameter("idPedido"));
    String operacao = Formatter.formatString(request.getParameter("operacao"));
    String formName = Formatter.formatString(request.getParameter("formName")) ;
    try {
      if(operacao.equals("CONSULTAR")) {
        if(formName.equals("gerenciarPedidos")) {
          RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/pedido?operacao=CONSULTAR&formName=gerenciarPedido");
          rd.forward(request, response);
        } else if (formName.equals("meusPedidos")) {
          RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/pedido?operacao=CONSULTAR&formName=meusPedidos&codigoPedido="+idPedido);  
          rd.forward(request, response); 
        }
       
      } else if (operacao.equals("APROVARTROCA")) {
        RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/pedido?operacao=CONSULTAR&formName=gerenciarItemPedido&codigoPedido="+idPedido);  
        rd.forward(request, response);       
      } else if (operacao.equals("COLOCARITEMEMTROCA")) {
        RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/pedido?operacao=CONSULTAR&formName=meusPedidos&codigoPedido="+idPedido);  
        rd.forward(request, response);       
      } 

    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  

}
