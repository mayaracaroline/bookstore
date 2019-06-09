package viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.Produto;
import util.Formatter;
import util.Resultado;

public class VHEstoque implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    Estoque estoque = new Estoque();
    Produto produto = new Produto();
    
    int idProduto = Formatter.stringToInt(request.getParameter("produto"));
    int quantidade = Formatter.format(request.getParameter("quantidade"));
    String codBarras = Formatter.formatString(request.getParameter("codBarras"));
    
    produto.setId(idProduto);
    produto.setCodigoBarras(codBarras);
    estoque.setProduto(produto);
    estoque.setQuantidade(quantidade);
    
    return estoque;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    String operacao = request.getParameter("operacao");
    String mensagem[] = resultado.getMensagem().split("\n");
    
    if(operacao.equals("SALVAR")) {
      
    }
    
  }

}
