package dominio;

import java.math.BigInteger;
import java.util.ArrayList;

public class Carrinho {
  
  private ArrayList<ItemCarrinho> itensCarrinho;

  public ArrayList<ItemCarrinho> getItensCarrinho() {
    return itensCarrinho;
  }

  public void setItensCarrinho(ArrayList<ItemCarrinho> itensCarrinho) {
    this.itensCarrinho = itensCarrinho;
  }
  
  public void addItem(ItemCarrinho item) {
    this.getItensCarrinho().add(item);    
  }
  
  public void removeItem(BigInteger id) {
    ArrayList<ItemCarrinho> itens = this.getItensCarrinho();
    
    for (int i = 0; i < itens.size(); i++) {
      
      if (itens.get(i).getProduto().getId() ==  id) {
        itens.remove(i);
      }
    }
    
  }

}
