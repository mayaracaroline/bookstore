package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import les.command.CommandAlterar;
import les.command.CommandConsultar;
import les.command.CommandExcluir;
import les.command.CommandInativar;
import les.command.CommandSalvar;
import les.command.ICommand;
import util.Resultado;
import viewhelper.IViewHelper;
import viewhelper.VHBloqueio;
import viewhelper.VHCadastrarProduto;
import viewhelper.VHCliente;

/**
 * Servlet implementation class CadastrarProdutoController
 */
@WebServlet({ 
      "/Pages/lumino/CadastrarProduto", 
      "/Pages/lumino/cadastro", 
      "/Pages/lumino/ConsultaProduto", 
      "/Pages/lumino/ExcluiProduto", 
      "/Pages/lumino/AlterarProduto", 
      "/Pages/lumino/InativaProduto",
      "/Pages/lumino/CadastraCliente", 
      "/Pages/lumino/ConsultaCliente",
      "/Pages/lumino/carrinho"
    })

public class CadastrarProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, IViewHelper> mapViewHelper;
	private Map<String, ICommand> mapCommand;
	private Carrinho carrinho;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarProdutoController() {
         	
    	mapViewHelper = new HashMap<String, IViewHelper>();
    	mapViewHelper.put("/livraria/Pages/lumino/CadastrarProduto", new VHCadastrarProduto());
    	mapViewHelper.put("/livraria/Pages/lumino/ConsultaProduto", new VHCadastrarProduto());
    	mapViewHelper.put("/livraria/Pages/lumino/ExcluiProduto", new VHCadastrarProduto());
    	mapViewHelper.put("/livraria/Pages/lumino/AlterarProduto", new VHCadastrarProduto());
    	mapViewHelper.put("/livraria/Pages/lumino/InativaProduto", new VHCadastrarProduto());
    	mapViewHelper.put("/livraria/Pages/lumino/CadastraCliente", new VHCliente());
    	mapViewHelper.put("/livraria/Pages/lumino/ConsultaCliente", new VHCliente());
    	mapViewHelper.put("/livraria/Pages/lumino/carrinho", new VHBloqueio());
    	
    	mapCommand = new HashMap<String, ICommand>();
    	mapCommand.put("SALVAR", new CommandSalvar());
    	mapCommand.put("CONSULTAR", new CommandConsultar());
    	mapCommand.put("EXCLUIR", new CommandExcluir());
    	mapCommand.put("ALTERAR", new CommandAlterar());
    	mapCommand.put("INATIVAR", new CommandInativar());
    }
    
    Carrinho getCarrinho() {
      
      if (null == carrinho ) {
        
        Carrinho carrinho = new Carrinho();
        ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<>();
        carrinho.setItensCarrinho(itensCarrinho);
        this.carrinho = carrinho;
      }
      
      return carrinho;
    }
    

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
	  if(getServletContext().getAttribute("bloqueio") == null) {
      HashMap<String, Carrinho> mapProdutosBloqueados = new HashMap<>();     
      getServletContext().setAttribute("bloqueio", mapProdutosBloqueados);
    }  

		String operacao = request.getParameter("operacao");
		String vh = request.getRequestURI();
		IViewHelper viewHelper = mapViewHelper.get(vh);
		ICommand command = mapCommand.get(operacao);	
		EntidadeDominio entidade = viewHelper.getEntidade(request);
		Resultado resultado = command.executar(entidade);
		viewHelper.setView(resultado, request, response);
		
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 */
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
