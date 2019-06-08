package viewhelper;

import java.math.BigInteger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dominio.DadosEntrega;
import dominio.Endereco;
import dominio.EntidadeDominio;
import util.Formatter;
import util.Resultado;

public class VHDadosEntrega implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    
    DadosEntrega dadosEntrega = new DadosEntrega();
    Endereco endereco = new Endereco();
    VHEnderecoEntrega vhEndereco = new VHEnderecoEntrega();
    
    endereco = (Endereco) vhEndereco.getEntidade(request);
    
    String idCliente = "";
    if(request.getCookies() !=null){
      for(Cookie cookie : request.getCookies()){
        if(cookie.getName().equals("clienteLogado")) {
          idCliente = cookie.getValue();
          break;
        }
      }
    }
    
    int id = Formatter.format(idCliente);

    dadosEntrega.setId(id);
    dadosEntrega.setEnderecoEntrega(endereco);
    
    return dadosEntrega;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
      String operacao = request.getParameter("operacao");
      
      DadosEntrega dadosEntrega = new DadosEntrega();
      dadosEntrega = (DadosEntrega) resultado.getResultado();
      Gson gson = new Gson();
      
      String JSONDadosEntrega = gson.toJson(dadosEntrega);   
  
      try {
        
        if(operacao.equals(null)) {
          request.getSession().removeAttribute("frete");
          response.sendRedirect("carrinho.jsp");
        }
        if(operacao.equals("CALCULARFRETE")) {
          
          request.getSession().removeAttribute("frete");
          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          response.getWriter().write(JSONDadosEntrega);       
        }
      
      } catch(Exception e) {
      
      } 
  }
}
