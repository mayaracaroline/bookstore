package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.EntidadeDominio;
import dominio.Usuario;
import les.command.CommandAlterar;
import les.command.CommandConsultar;
import les.command.CommandExcluir;
import les.command.CommandInativar;
import les.command.CommandSalvar;
import les.command.ICommand;
import util.Conexao;
import util.Resultado;
import viewhelper.IViewHelper;
import viewhelper.VHUsuario;

/**
 * Servlet implementation class ControllerAutenticacao
 */
@WebFilter(
    {
      "/ControllerAutenticacao", 
      "/Pages/lumino/loginCliente",
      "/Pages/lumino/loginAdmin", 
      "/Pages/lumino/comprar",
      "/Pages/lumino/autenticaCliente" 
    })
public class ControllerAutenticacao implements Filter  {
	private static final long serialVersionUID = 1L;
	private Map<String, IViewHelper> mapVHelper;
	private Map<String, ICommand> mapaCommand;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAutenticacao() {
      mapVHelper = new HashMap<String, IViewHelper>();
      mapaCommand = new HashMap<String, ICommand>();
      
      mapVHelper.put("/livraria/Pages/lumino/autenticaCliente", new VHUsuario());      

      mapaCommand.put("SALVAR", new CommandSalvar());
      mapaCommand.put("CONSULTAR", new CommandConsultar());
      mapaCommand.put("EXCLUIR", new CommandExcluir());
      mapaCommand.put("ALTERAR", new CommandAlterar());
      mapaCommand.put("INATIVAR", new CommandInativar());
    }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
      throws IOException, ServletException {
    
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    boolean usuarioLogado = false;
    String vh = req.getRequestURI();
    if(mapVHelper.containsKey(vh)) {
      
      String operacao = request.getParameter("operacao");
      
      IViewHelper viewHelper = mapVHelper.get(vh);
      ICommand command = mapaCommand.get(operacao);  
      EntidadeDominio entidade = viewHelper.getEntidade(req);
      Resultado resultado = command.executar(entidade);
      System.out.println(resultado.getContagem());
      if(!resultado.getErro() && resultado.getContagem() > 0) {
        Cookie logado = new Cookie("clienteLogado","true");
        res.addCookie(logado);
        viewHelper.setView(resultado, req, res);
        return;
      } else {
        req.getSession().setAttribute("usuario", "Usuário ou senha inválidos");     
      }      
    }
   
    
    if(req.getCookies()!=null){
      for(Cookie cookie : req.getCookies()){
        if(cookie.getName().equals("clienteLogado")) {
          System.out.println("clienteLogado");
          usuarioLogado = true;
          res.sendRedirect("checkout.jsp");

          break;
        }
      }
    }
    if(!usuarioLogado){
        //Redireciona para a página de login
        res.sendRedirect("loginCliente.jsp");
      }
    
    
  }

  @Override
  public void init(FilterConfig request) throws ServletException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    
  }

}
