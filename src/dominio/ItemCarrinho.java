package dominio;

public class ItemCarrinho {
  
  private Produto produto;
  private int quantidade;
  

  public int getQuantidade() {
    return quantidade;
  }
  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }
  public Produto getProduto() {
    return produto;
  }
  public void setProduto(Produto produto) {
    this.produto = produto;
  }
  
  public void removeProduto() {
    if(this.quantidade > 0 ) {
      this.quantidade -= 1;
    }   
  }
  
}
