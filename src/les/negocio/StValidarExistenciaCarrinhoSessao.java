package les.negocio;

import java.util.ArrayList;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;

public class StValidarExistenciaCarrinhoSessao implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    
    Bloqueio bloqueio = (Bloqueio) entidade;
    
    if (bloqueio.getSessao().getAttribute("carrinho") == null) {
      Carrinho novoCarrinho = new Carrinho();
      ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<>();
      novoCarrinho.setItensCarrinho(itensCarrinho);
      novoCarrinho.setStatus(true);      
      bloqueio.getSessao().setAttribute("carrinho", novoCarrinho); 
    }
    
    return "";
  }

}
