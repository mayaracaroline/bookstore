package viewhelper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
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
import dominio.Livro;
import dominio.Pagamento;
import dominio.PedidoDeCompra;
import dominio.Produto;
import dominio.TipoCupom;
import util.Formatter;
import util.Resultado;

public class VHPedidoDeCompra implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    
    PedidoDeCompra pedido = new PedidoDeCompra();
 
    // Cartão
    Cartao cartao1 = new Cartao();
    Cartao cartao2 = new Cartao();
    int idCartao1 = Formatter.format(request.getParameter("numero-cartao1"));
    int idCartao2 = Formatter.format(request.getParameter("numero-cartao2"));
    double valorCartao1 = Formatter.formatDouble(request.getParameter("valor1"));
    double valorCartao2 = Formatter.formatDouble(request.getParameter("valor2"));
    int parcelaCartao1 = Formatter.format(request.getParameter("parcela-cartao1"));
    int parcelaCartao2 = Formatter.format(request.getParameter("parcela-cartao2"));
    cartao1.setId(idCartao1);
    cartao2.setId(idCartao2);  
     
    
    Double frete = 0.0;
    frete = Formatter.formatDouble(request.getParameter("frete")); 
    
    Endereco endereco = new Endereco();
    int idEnderecoEntrega = Formatter.format(request.getParameter("endereco-entrega"));
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
      mapCarrinhosExpirados = (HashMap<String, Carrinho>) request.getServletContext().getAttribute("desbloqueio");
      carrinho = mapCarrinhosExpirados.get(request.getSession().getId());
      if( carrinho != null) {
        for( int i = 0; i< carrinho.getItensCarrinho().size(); i++) {       
          ItemCarrinho item = new ItemCarrinho();
          
          
          item = (ItemCarrinho) carrinho.getItensCarrinho().get(i);
          Produto produto = new Produto();
          produto = item.getProduto();
          item.setProduto(produto);
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
    
    BigInteger id = Formatter.formatBigInteger(idCliente);
    
    String[] strIdCuponsTroca = request.getParameterValues("cupom-troca");
    
    ArrayList<Cupom> cuponsSelecionados = new ArrayList<>();
    
    if( null != strIdCuponsTroca) {
      for (int i = 0; i < strIdCuponsTroca.length; i++) {
        
        if(strIdCuponsTroca[i] != null) {
          Cupom cup = new Cupom();
          cup.setId(Formatter.format(strIdCuponsTroca[i]));
          cup.setTipo(TipoCupom.TROCA);
          cup.setIdCliente(id.intValue());
          cup.setValor(0.0);
          cuponsSelecionados.add(cup);        
        }    
      }    
    }

    
    Cupom cupomPromocional = new Cupom();
    Integer idCupomPromocional = Formatter.format(request.getParameter("cupom-promocional"));
    cupomPromocional.setId(idCupomPromocional);
    cupomPromocional.setTipo(TipoCupom.PROMOCIONAL);
    cupomPromocional.setIdCliente(id.intValue());
    if(cupomPromocional.getId().equals(BigInteger.ZERO)) {
      cupomPromocional.setValor(0.0);
    }
    
    
    double subtotal = 0;
    
    for (int i = 0; i < itens.size(); i++) {
      double preco = 0;
      int quantidade = 0;
      
      if(itens.get(i).getProduto().getPreco() != null ) {
        preco = itens.get(i).getProduto().getPreco();
        quantidade = itens.get(i).getQuantidade();
      } 
      
      
      subtotal += (preco * quantidade);
    }
    
    Pagamento pagamentoCartao1 = new Pagamento();
    Pagamento pagamentoCartao2 = new Pagamento();
    
    pagamentoCartao1.setFormaDePagamento(cartao1);
    pagamentoCartao1.setQuantidadeParcelas(parcelaCartao1);
    pagamentoCartao1.setValor(valorCartao1);
    
    pagamentoCartao2.setFormaDePagamento(cartao2); 
    pagamentoCartao2.setQuantidadeParcelas(parcelaCartao2);
    pagamentoCartao2.setValor(valorCartao2);
   
    ArrayList<Pagamento> pagamentos = new ArrayList<>();
    pagamentos.add(pagamentoCartao1);
    pagamentos.add(pagamentoCartao2);
    
    Integer pedidoId = Formatter.format(request.getParameter("codigoPedido"));
    
    pedido.setId(pedidoId);
    pedido.setPagamento(pagamentos);
    pedido.setCarrinho(carrinho);
    pedido.setCupomPromocional(cupomPromocional);
    pedido.setCuponsTroca(cuponsSelecionados);
    pedido.setEnderecoDeEntrega(endereco);
    pedido.setFrete(frete);
    pedido.setItens(itens);
    pedido.setValorTotal(subtotal);
    pedido.setIdCliente(id);

    return pedido;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
     String[] mensagem = resultado.getMensagem().split(";");
     String operacao = request.getParameter("operacao");
     String formName = Formatter.formatString(request.getParameter("formName")) ;
     ArrayList<PedidoDeCompra> pedidos = new ArrayList<>();
     ArrayList<Livro> livros = new ArrayList<>();
     System.out.println(operacao);
     if ( null != resultado.getListaResultado()) {
       for (EntidadeDominio pedido : resultado.getListaResultado() ) {
         pedidos.add((PedidoDeCompra) pedido);
       }
       
//       int index = 0;
//       for (PedidoDeCompra pedido : pedidos) {
//         Livro livro = (Livro) pedido.getCarrinho().getItensCarrinho()
//             .get(index).getProduto();
//         index++;
//         livros.add(livro);       
//       }
     }
     
     for (int i = 0; i < mensagem.length; i++) {
       System.out.println(mensagem[i]);
     }
     
     
     if(resultado.getErro()) {
       request.getSession().setAttribute("erro", mensagem);
     }
     try {
       
       if (operacao.equals("SALVAR")) {
         if(resultado.getErro()) {
           response.sendRedirect("finalizarCompra.jsp");
         } else {
           response.sendRedirect("areaCliente.jsp");
         }
       } else if (operacao.equals("CONSULTAR")) {
         if(formName.equals("gerenciarPedidos")) {
           request.setAttribute("pedidos", pedidos);
           RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/gerenciarPedidos.jsp");
           rd.forward(request, response);
         }  else {
           request.setAttribute("pedidos", pedidos);
           RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/meusPedidos.jsp");
           rd.forward(request, response);
         } 

//         request.setAttribute("livros", livros);
       } else if (operacao.equals("COLOCAREMTRANSPORTE") || operacao.equals("CONFIRMARENTREGA")) {
         request.setAttribute("pedidos", pedidos);
         RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/meusPedidos.jsp");
         rd.forward(request, response);
       }
    } catch (Exception e) {
      e.printStackTrace();
    }
 }
}



