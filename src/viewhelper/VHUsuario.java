package viewhelper;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dominio.Cartao;
import dominio.Cliente;
import dominio.Cupom;
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
    int id = Formatter.stringToInt(request.getParameter("id")); 
    
    usuario.setId(id);
    usuario.setUsername(username);
    usuario.setPassword(password);
    
    return usuario;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    String formName = Formatter.formatString(request.getParameter("formName"));
    String[] mensagem = resultado.getMensagem().split("\n");
    String operacao = Formatter.formatString(request.getParameter("operacao"));
    HashMap<String, ArrayList<EntidadeDominio>> mapDadosUsuario;
    mapDadosUsuario = resultado.getMapResultado();
    ArrayList<EntidadeDominio> resultados =  mapDadosUsuario.get("ENDERECO");
    ArrayList<Endereco> enderecos = new ArrayList<>();
    ArrayList<Cartao> cartoes = new ArrayList<>();
    ArrayList<Cupom> cuponsPromocionais = new ArrayList<>();
    ArrayList<Cupom> cuponsTroca = new ArrayList<>();
    
    Gson gson = new Gson();
    
    String JSONDadosUsuario = gson.toJson(mapDadosUsuario);
    System.out.println(JSONDadosUsuario);
    
    for(int i = 0; i < resultados.size(); i++) {
      enderecos.add((Endereco)resultados.get(i));    
    }

    resultados =  mapDadosUsuario.get("CARTAO");

//    for(int i = 0; i < resultados.size(); i++) {
//      cartoes.add((Cartao)resultados.get(i));    
//    }
    
    resultados =  mapDadosUsuario.get("cuponsPromocionais");
    
    for(int i = 0; i < resultados.size(); i++) {
      cuponsPromocionais.add((Cupom)resultados.get(i));    
    }
    
    resultados =  mapDadosUsuario.get("cuponsTroca");
    

    for(int i = 0; i < resultados.size(); i++) {
      cuponsTroca.add((Cupom)resultados.get(i));    
    }

    Cliente cliente = (Cliente) resultado.getResultado(); 
    
    if(resultado.getErro())
      request.setAttribute("erro", mensagem);
    else
      request.setAttribute("sucesso", mensagem);
    
    if(operacao.equals("CONSULTAR") || operacao.equals("SALVAR") ){
        if(!resultado.getErro()){
//          request.getSession().setAttribute("clientes", (Cliente) cliente);
//          request.getSession().setAttribute("enderecos", (ArrayList<Endereco>) enderecos);
//          request.getSession().setAttribute("cartoes", (ArrayList<Cartao>) cartoes);
//          request.getSession().setAttribute("cuponsPromocionais", (ArrayList<Cupom>) cuponsPromocionais);
//          request.getSession().setAttribute("cuponsTroca",(ArrayList<Cupom>) cuponsTroca);
        } 
    }
    try {
      if(operacao.equals("CONSULTAR")){
        if(formName.equals("loginCliente")){  
          System.out.println("loginCliente");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSONDadosUsuario);
            response.sendRedirect("checkout.jsp");
        } else {
          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          response.getWriter().write(JSONDadosUsuario);
        }
      } 
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
