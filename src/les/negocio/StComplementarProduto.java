package les.negocio;

import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.Produto;
import les.dao.DAOProduto;
import util.Resultado;

public class StComplementarProduto implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    Estoque estoque = (Estoque) entidade;
    DAOProduto daoProduto = new DAOProduto();
    Produto produto = estoque.getProduto();
    Resultado resultado = daoProduto.consultar(produto);
    if(resultado.getContagem() <= 0) {
      mensagem = "Não existe produto com o codigo de barras cadastrado.";
    }
    
    return mensagem;
  }

}
