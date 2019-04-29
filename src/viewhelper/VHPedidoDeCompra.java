package viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Carrinho;
import dominio.Cartao;
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
 
    // Cartão
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
    Carrinho carrinho = new Carrinho();   
    carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
    List<ItemCarrinho> itens = new ArrayList<>();
    
    itens.addAll(carrinho.getItensCarrinho());
    
    String[] strIdCuponsTroca = request.getParameterValues("cupom-troca");
    List<Cupom> cuponsTroca = new ArrayList<>();
  
    for (int i = 0; i < strIdCuponsTroca.length; i++) {
      Cupom cup = new Cupom();
      cup.setId(Formatter.format(strIdCuponsTroca[i]));
      cup.setTipo(TipoCupom.TROCA);
      cuponsTroca.add(cup);
    }
    
    Cupom cupomPromocional = new Cupom();
    Integer idCupomPromocional = Formatter.format(request.getParameter("cupom-promocional"));
    cupomPromocional.setId(idCupomPromocional);
    cupomPromocional.setTipo(TipoCupom.PROMOCIONAL); 
    
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
    pedido.setCupomPromocional(cupomPromocional);
    pedido.setCuponsTroca(cuponsTroca);
    pedido.setEnderecoDeEntrega(endereco);
    pedido.setFrete(frete);
    pedido.setItens(itens);

    return pedido;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    
  }

}



