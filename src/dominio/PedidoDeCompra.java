package dominio;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PedidoDeCompra extends EntidadeDominio {
  
  List<Cupom> cuponsTroca;
  Cupom cupomPromocional;
  List<ItemCarrinho> itens;
  Endereco enderecoDeEntrega;
  Double frete;
  ArrayList<Pagamento> pagamento;
  Double valorTotal;
  BigInteger idCliente;
  
  public List<Cupom> getCuponsTroca() {
    return cuponsTroca;
  }
  public void setCuponsTroca(List<Cupom> cuponsTroca) {
    this.cuponsTroca = cuponsTroca;
  }
  public Cupom getCupomPromocional() {
    return cupomPromocional;
  }
  public void setCupomPromocional(Cupom cupomPromocional) {
    this.cupomPromocional = cupomPromocional;
  }
  public List<ItemCarrinho> getItens() {
    return itens;
  }
  public void setItens(List<ItemCarrinho> itens) {
    this.itens = itens;
  }
  public Endereco getEnderecoDeEntrega() {
    return enderecoDeEntrega;
  }
  public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }
  public Double getFrete() {
    return frete;
  }
  public void setFrete(Double frete) {
    this.frete = frete;
  }
  public Double getValorTotal() {
    return valorTotal;
  }
  public void setValorTotal(Double valorTotal) {
    this.valorTotal = valorTotal;
  }
  public ArrayList<Pagamento> getPagamento() {
    return pagamento;
  }
  public void setPagamento(ArrayList<Pagamento> pagamento) {
    this.pagamento = pagamento;
  }
  public BigInteger getIdCliente() {
    return idCliente;
  }
  public void setIdCliente(BigInteger idCliente) {
    this.idCliente = idCliente;
  }
   
}
