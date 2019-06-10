package viewhelper;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;

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
    Double valor = Formatter.formatDouble(request.getParameter("valor")); 
      
    TipoCupom tipo = null != request.getParameter("tipo-cupom") 
        && !"".equals(request.getParameter("tipo-cupom")) ? 
            TipoCupom.valueOf(request.getParameter("tipo-cupom")) : 
              TipoCupom.valueOf("SELECIONE");
            
    if (tipo.equals(TipoCupom.PROMOCIONAL)) {
      dataDeValidade = dataDeEmissao.plusMonths(6);
    } else if (tipo.equals(TipoCupom.TROCA)) {
      dataDeValidade = dataDeEmissao.plusMonths(12);
    }
    
    int idCliente = Formatter.format(request.getParameter("idCliente"));
    
    Integer idCupom = -1;
    
    cupom.setTipo(tipo);
    cupom.setIdCliente(idCliente);
    cupom.setId(idCupom);
    cupom.setDataDeValidade(dataDeValidade);
    cupom.setValor(valor);
    
    return cupom;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    try {
      
      response.sendRedirect("cadastrarCupom.jsp");
      
    } catch (Exception e) {
      // TODO: handle exception
    }
    
  }

}
