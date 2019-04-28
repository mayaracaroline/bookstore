package viewhelper;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.TipoCupom;
import util.Formatter;
import util.Resultado;

public class VHCupom implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    Cupom cupom = new Cupom();
    LocalDate dataDeEmissao = LocalDate.now();  
    LocalDate dataDeValidade = dataDeEmissao.plusMonths(6);
    TipoCupom tipo = null != request.getParameter("tipo-cupom") 
        && !"".equals(request.getParameter("tipo-cupom")) ? 
            TipoCupom.valueOf(request.getParameter("tipo-cupom")) : 
              TipoCupom.valueOf("SELECIONE");
            
    if (tipo.equals(TipoCupom.PROMOCIONAL)) {
      dataDeValidade = dataDeEmissao.plusMonths(6);
    } else if (tipo.equals(TipoCupom.TROCA)) {
      dataDeValidade = dataDeEmissao.plusMonths(12);
    }
    
    String codigoCupom = Formatter.formatString(request.getParameter("codigo-cupom"));
    Double valor = Formatter.formatDouble(request.getParameter("valor-cupom"));
    // Pegar o id de dos os cupons de troca selecionados
    request.getParameterValues("cupom-troca");
    
    cupom.setCodigo(codigoCupom);
    cupom.setDataDeValidade(dataDeValidade);
    cupom.setTipo(tipo);
    cupom.setValor(valor);
    
    
    
    //Retornar uma lista, pois mais de um cupom pode ser selecionado pelo usuário.
    
    return cupom;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    
  }

}
