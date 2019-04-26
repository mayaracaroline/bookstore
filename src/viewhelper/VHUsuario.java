package viewhelper;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cartao;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Usuario;
import util.Formatter;
import util.Resultado;

public class VHUsuario implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    
    Usuario usuario = new Usuario();
    String password = Formatter.formatString(request.getParameter("senha"));
    String username = Formatter.formatString(request.getParameter("email")); 
    
    usuario.setUsername(username);
    usuario.setPassword(password);
    
    return usuario;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    String formName = Formatter.formatString(request.getParameter("formName"));
    String[] mensagem = resultado.getMensagem().split("\n");
    String operacao = Formatter.formatString(request.getParameter("operacao"));
    HashMap<String, ArrayList<EntidadeDominio>> mapUsuario;
    mapUsuario = resultado.getMapResultado();
    ArrayList<EntidadeDominio> resultados =  mapUsuario.get("ENDERECO");
    ArrayList<Endereco> enderecos = new ArrayList<>();
    ArrayList<Cartao> cartoes = new ArrayList<>();
    
    for(int i = 0; i < resultados.size(); i++) {
      enderecos.add((Endereco)resultados.get(i));    
    }
    
    resultados =  mapUsuario.get("CARTAO");

    for(int i = 0; i < resultados.size(); i++) {
      cartoes.add((Cartao)resultados.get(i));    
    }
    
    if(resultado.getErro())
      request.setAttribute("erro", mensagem);
    else
      request.setAttribute("sucesso", mensagem);
    
    if(operacao.equals("CONSULTAR")){
        if(!resultado.getErro()){
          request.getSession().setAttribute("enderecos", enderecos);
          request.getSession().setAttribute("cartoes", cartoes);
        } 
    }
    try {
      if(operacao.equals("CONSULTAR")){
        if(formName.equals("loginCliente")){ // rever                 
           response.sendRedirect("checkout.jsp");
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
