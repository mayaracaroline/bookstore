package dominio;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDeCompra extends EntidadeDominio {
  Carrinho carrinho;
  List<Cupom> cuponsTroca;
  Cupom cupomPromocional;
  Endereco enderecoDeEntrega;
  Double frete;
  ArrayList<Pagamento> pagamento;
  Double valorTotal;
  BigInteger idCliente;
  String codigoIdentificador;
  Integer status;
  LocalDate dataSolicitacao;
  LocalDate dataConclusao;
  
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
  public Carrinho getCarrinho() {
    return carrinho;
  }
  public void setCarrinho(Carrinho carrinho) {
    this.carrinho = carrinho;
  }
  public String getCodigoIdentificador() {
    return codigoIdentificador;
  }
  public void setCodigoIdentificador(String codigoIdentificador) {
    this.codigoIdentificador = codigoIdentificador;
  }
  public Integer getStatus() {
    return status;
  }
  public void setStatus(Integer status) {
    this.status = status;
  }
  public LocalDate getDataSolicitacao() {
    return dataSolicitacao;
  }
  public void setDataSolicitacao(LocalDate dataSolicitacao) {
    this.dataSolicitacao = dataSolicitacao;
  }
  public LocalDate getDataConclusao() {
    return dataConclusao;
  }
  public void setDataConclusao(LocalDate dataConclusao) {
    this.dataConclusao = dataConclusao;
  }
   
}
