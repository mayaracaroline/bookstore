package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import dominio.Usuario;
import util.Conexao;

/**
 * Servlet implementation class ControllerAutenticacao
 */
@WebFilter(
    {
      "/ControllerAutenticacao", 
      "/Pages/lumino/loginCliente",
      "/Pages/lumino/loginAdmin", 
      "/Pages/lumino/comprar"      
    })
public class ControllerAutenticacao implements Filter  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAutenticacao() {
        super();
        // TODO Auto-generated constructor stub
    }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
      throws IOException, ServletException {

    System.out.println("Filtro");
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    boolean usuarioLogado = false;
    
    if (req.getRequestURI().equals("/livraria/Pages/lumino/loginCliente")) {
      System.out.println("/livraria/Pages/lumino/loginCliente");  
      String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        String sql = "SELECT cli_senha FROM clientes WHERE cli_email = ? ";
        Conexao conn = new Conexao();
        
        try {
          PreparedStatement pst = conn.getConnection().prepareStatement(sql);
          pst.setString(1, email);
          
          ResultSet rs = pst.executeQuery();
          
          String senhaEncontrada = "";
          
          while(rs.next()) {
            senhaEncontrada = rs.getString("cli_senha");
          }
          
          Usuario usuario = new Usuario();
          usuario.setUsername(email);
          usuario.setPassword(senha);
          
          if(senha.equals(senhaEncontrada)) {
            System.out.println("senhaEncontrada");
            //colocar objeto de cliente na requisição
            Cookie logado = new Cookie("clienteLogado","true");
            res.addCookie(logado);
            res.sendRedirect("carrinho.jsp");
          } else {
            System.out.println("Senha inválida");
            req.getSession().setAttribute("usuario", "Usuário ou senha inválida");
            res.sendRedirect("loginCliente.jsp");
          }
          
          
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
   
    }
    
    if(req.getCookies()!=null){
      System.out.println("req.getCookies()!=null");
      for(Cookie cookie : req.getCookies()){
        if(cookie.getName().equals("clienteLogado")) {
          System.out.println("cookie");
          usuarioLogado = true;
          // Redireciona para o checkout - Cliente logado 
          res.sendRedirect("carrinho.jsp");

          break;
        }
      }
    }
    if(!usuarioLogado){
      System.out.println("!usuarioLogado");
        
        //Redireciona para a página de login
        res.sendRedirect("loginCliente.jsp");
//        request.getRequestDispatcher("loginCliente.jsp")
//        .forward(request, response);
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
