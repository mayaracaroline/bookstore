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
import dominio.PedidoDeCompra;
import dominio.TipoEndereco;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;
import util.Formatter;
import util.Resultado;

public class VHPedidoDeCompra implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    
    PedidoDeCompra pedido = new PedidoDeCompra();
    Cartao cartao1 = new Cartao();
    Cartao cartao2 = new Cartao();
    List<Cupom> cupons = new ArrayList<>();
    List<ItemCarrinho> itens = new ArrayList<>();
    Carrinho carrinho = new Carrinho();
    Double frete = 0.0;
    Endereco endereco = new Endereco();
    TipoLogradouro tipoLogradouro;
    int idTipoLogradouro;

    
    carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
    itens = (ArrayList<ItemCarrinho>) carrinho.getItensCarrinho();
    frete = Formatter.formatDouble(request.getParameter("frete"));
    idTipoLogradouro = Formatter.format(request.getParameter("idTipoLogradouro"));
    String logradouro = Formatter.formatString(request.getParameter("logradouro"));
    int numero = Formatter.format("numero");
    String bairro = Formatter.formatString("bairro");
    String cep = Formatter.formatString("cep");
    String cidade = Formatter.formatString("cidade");
    String estado = Formatter.formatString("estado");
    String descricao = Formatter.formatString("descricao");
    String observacao = Formatter.formatString("observacao");
    TipoResidencia tipoResidencia = TipoResidencia.valueOf(Formatter.formatString("tipoResidencia"));
    String pais = Formatter.formatString("pais");
    String tipoEndereco = Formatter.formatString("tipoEndereco");
    endereco.setBairro(bairro);
    endereco.setCep(cep);
    endereco.setCidade(cidade);
    
    
    
    return pedido;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    
  }

}
