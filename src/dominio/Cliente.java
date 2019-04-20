package dominio;

import java.time.LocalDate;

public class Cliente extends PessoaFisica {
  
  private Telefone telefone;
  private Endereco enderecoResidencial;
  private Endereco enderecoEntrega;
  private Endereco enderecoCobranca;
  private Usuario usuario; 
  private Cartao cartao;
  private LocalDate dataCadastro;
  
  public Telefone getTelefone() {
    return telefone;
  }
  public void setTelefone(Telefone telefone) {
    this.telefone = telefone;
  }
  public Endereco getEnderecoResidencial() {
    return enderecoResidencial;
  }
  public void setEnderecoResidencial(Endereco enderecoResidencial) {
    this.enderecoResidencial = enderecoResidencial;
  }
  public Endereco getEnderecoEntrega() {
    return enderecoEntrega;
  }
  public void setEnderecoEntrega(Endereco enderecoEntrega) {
    this.enderecoEntrega = enderecoEntrega;
  }
  public Endereco getEnderecoCobranca() {
    return enderecoCobranca;
  }
  public void setEnderecoCobranca(Endereco enderecoCobranca) {
    this.enderecoCobranca = enderecoCobranca;
  }
  public Usuario getUsuario() {
    return usuario;
  }
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  public Cartao getCartao() {
    return cartao;
  }
  public void setCartao(Cartao cartao) {
    this.cartao = cartao;
  }
  public LocalDate getDataCadastro() {
    return dataCadastro;
  }
  public void setDataCadastro(LocalDate dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

}
