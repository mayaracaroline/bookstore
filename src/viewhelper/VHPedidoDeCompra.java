package viewhelper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Carrinho;
import dominio.Cartao;
import dominio.Cliente;
import dominio.Cupom;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Pagamento;
import dominio.PedidoDeCompra;
import dominio.TipoCupom;
import util.Formatter;
import util.Resultado;

public class VHPedidoDeCompra implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    
    PedidoDeCompra pedido = new PedidoDeCompra();
 
    // Cart�o
    Cartao cartao1 = new Cartao();
    Cartao cartao2 = new Cartao();
    int idCartao1 = Formatter.format(request.getParameter("numero-cartao1"));
    int idCartao2 = Formatter.format(request.getParameter("numero-cartao2"));
    double valorCartao1 = Formatter.formatDouble(request.getParameter("valor-cartao1"));
    double valorCartao2 = Formatter.formatDouble(request.getParameter("valor-cartao2"));
    cartao1.setId(idCartao1);
    cartao2.setId(idCartao2);  
    
    Double frete = 0.0;
    frete = Formatter.formatDouble(request.getParameter("frete")); 
    
    Endereco endereco = new Endereco();
    int idEnderecoEntrega = Formatter.format(request.getParameter("cep-entrega"));
    endereco.setId(idEnderecoEntrega);    
    
    
    // Itens de Carrinho

    ArrayList<ItemCarrinho> itens = new ArrayList<>();
    Carrinho carrinho = new Carrinho();
    if(request.getSession().getAttribute("carrinho") != null) {
      carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
      
      for( int i=0; i< carrinho.getItensCarrinho().size(); i++) {
        
          ItemCarrinho item = new ItemCarrinho();
          item = (ItemCarrinho) carrinho.getItensCarrinho().get(i);
          itens.add(item);
      }
      carrinho.setItensCarrinho(itens);
      carrinho.setStatus(true);
    } else if (request.getServletContext().getAttribute("desbloqueio") != null) {
      HashMap<String, Carrinho> mapCarrinhosExpirados = new HashMap<>();
      carrinho = mapCarrinhosExpirados.get(request.getSession().getId());
      if( carrinho != null) {
        for( int i=0; i< carrinho.getItensCarrinho().size(); i++) {       
          ItemCarrinho item = new ItemCarrinho();
          item = (ItemCarrinho) carrinho.getItensCarrinho().get(i);
          itens.add(item);
        }
        carrinho.setItensCarrinho(itens);
        carrinho.setStatus(false);  
      }
    }
    
    Cliente cliente = (Cliente) request.getSession().getAttribute("clientes");
    
    String idCliente = "";
    if(request.getCookies() !=null){
      for(Cookie cookie : request.getCookies()){
        if(cookie.getName().equals("clienteLogado")) {
          idCliente = cookie.getValue();
          break;
        }
      }
    }
    
    int id = Formatter.format(idCliente);
    
    String[] strIdCuponsTroca = request.getParameterValues("cupom-troca");
    
    ArrayList<Cupom> cuponsSelecionados = new ArrayList<>();
    List<Cupom> cuponsTroca = new ArrayList<>();
    
    if( null != strIdCuponsTroca) {
      for (int i = 0; i < strIdCuponsTroca.length; i++) {
        
        if(strIdCuponsTroca[i] != null) {
          Cupom cup = new Cupom();
          cup.setId(Formatter.format(strIdCuponsTroca[i]));
          cup.setTipo(TipoCupom.TROCA);
          cup.setIdCliente(id);
          cuponsSelecionados.add(cup);        
        }    
      }    
    }

    
    Cupom cupomPromocional = new Cupom();
    Integer idCupomPromocional = Formatter.format(request.getParameter("cupom-promocional"));
    cupomPromocional.setId(idCupomPromocional);
    cupomPromocional.setTipo(TipoCupom.PROMOCIONAL);
    cupomPromocional.setIdCliente(id);
    
    double subtotal = 0;
    
    for (int i = 0; i < itens.size(); i++) {
      double preco = itens.get(i).getProduto().getPreco();
      int quantidade = itens.get(i).getQuantidade();
      
      subtotal += (preco * quantidade);
    }
    
    Pagamento pagamentoCartao1 = new Pagamento();
    Pagamento pagamentoCartao2 = new Pagamento();
    
    pagamentoCartao1.setFormaDePagamento(cartao1);
    pagamentoCartao1.setValor(valorCartao1);
    
    pagamentoCartao2.setFormaDePagamento(cartao2);
    pagamentoCartao2.setValor(valorCartao2);
   
    ArrayList<Pagamento> pagamentos = new ArrayList<>();
    pagamentos.add(pagamentoCartao1);
    pagamentos.add(pagamentoCartao2);
    
    pedido.setPagamento(pagamentos);
    pedido.setCarrinho(carrinho);
    pedido.setCupomPromocional(cupomPromocional);
    pedido.setCuponsTroca(cuponsTroca);
    pedido.setEnderecoDeEntrega(endereco);
    pedido.setFrete(frete);
    pedido.setItens(itens);
    pedido.setValorTotal(subtotal);
    pedido.setIdCliente(BigInteger.valueOf(id));

    return pedido;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
     String mensagem = resultado.getMensagem();
     String operacao = request.getParameter("operacao");
     if(resultado.getErro()) {
       request.getSession().setAttribute("erro", mensagem);
     }
     try {
       
       if (operacao.equals("SALVAR")) {
         if(resultado.getErro()) {
           response.sendRedirect("finalizarCompra.jsp");
         } else {
           response.sendRedirect("produtos.jsp");
         }
       }      
    } catch (Exception e) {
      e.printStackTrace();
    }
 }
}



