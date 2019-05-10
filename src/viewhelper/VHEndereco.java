package viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Endereco;
import dominio.EntidadeDominio;
import util.Formatter;
import util.Resultado;

public class VHEndereco implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    Endereco endereco = new Endereco();
    
    String cep = Formatter.formatString(request.getParameter("cep").trim().replace("-", ""));
    
    endereco.setCep(cep);
    
    return endereco;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {

    String frete = resultado.getMensagem();
    String operacao = request.getParameter("operacao");
    String formName = request.getParameter("formName");
    try {
      request.getSession().removeAttribute("frete");
      if(operacao.equals(null)) {
        request.getSession().removeAttribute("frete");
        response.sendRedirect("carrinho.jsp");
      }
      if(operacao.equals("CALCULARFRETE")) {
        request.getSession().setAttribute("frete", frete);
        if(formName.equals("checkout")) {
          response.sendRedirect("checkout.jsp");
        } else {
          response.sendRedirect("carrinho.jsp");
        }             
      }

    } catch (Exception e) {
      // TODO: handle exception
    }
    
  }

}
