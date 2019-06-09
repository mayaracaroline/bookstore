package les.negocio;

import java.math.BigInteger;

import dominio.EntidadeDominio;
import dominio.Estoque;

public class StValidarDadosObrigatoriosEstoque implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    StringBuilder mensagem = new StringBuilder();
    Estoque estoque = (Estoque) entidade;
    
    if(estoque.getQuantidade() <= 0 ) {
      mensagem.append("A quantidade deve ser maior que zero\n");
    }
    if(estoque.getProduto().getCodigoBarras().equals("")) {
      mensagem.append("Informe um codigo de barras\n");
    }
    
    return mensagem.toString();
  }

}
