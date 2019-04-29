package dominio;

import java.io.Serializable;

public class Endereco extends EntidadeDominio implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private TipoLogradouro tipoLogradouro;
  private String logradouro;
  private int numero;
  private String bairro;
  private String cep;
  private String cidade;
  private String estado;
  private String descricao;
  private String observacao;  
  private TipoResidencia tipoResidencia;
  private String pais;
  private String tipoEndereco;
  
  public TipoLogradouro getTipoLogradouro() {
    return tipoLogradouro;
  }
  public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
    this.tipoLogradouro = tipoLogradouro;
  }
  public String getLogradouro() {
    return logradouro;
  }
  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }
  public int getNumero() {
    return numero;
  }
  public void setNumero(int numero) {
    this.numero = numero;
  }
  public String getBairro() {
    return bairro;
  }
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }
  public String getCep() {
    return cep;
  }
  public void setCep(String cep) {
    this.cep = cep;
  }
  public String getCidade() {
    return cidade;
  }
  public void setCidade(String cidade) {
    this.cidade = cidade;
  }
  public String getEstado() {
    return estado;
  }
  public void setEstado(String estado) {
    this.estado = estado;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public String getObservacao() {
    return observacao;
  }
  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }
  public TipoResidencia getTipoResidencia() {
    return tipoResidencia;
  }
  public void setTipoResidencia(TipoResidencia tipoResidencia) {
    this.tipoResidencia = tipoResidencia;
  }
  public String getPais() {
    return pais;
  }
  public void setPais(String pais) {
    this.pais = pais;
  }
  public String getTipoEndereco() {
    return tipoEndereco;
  }
  public void setTipoEndereco(String tipoEndereco) {
    this.tipoEndereco = tipoEndereco;
  }

}
