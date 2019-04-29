package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import les.command.CommandAlterar;
import les.command.CommandConsultar;
import les.command.CommandExcluir;
import les.command.CommandInativar;
import les.command.CommandSalvar;
import les.command.ICommand;
import les.negocio.StValidarItensCarrinhoComTempoExpirado;
import util.Resultado;
import viewhelper.IViewHelper;
import viewhelper.VHBloqueio;
import viewhelper.VHCadastrarProduto;
import viewhelper.VHCliente;
import viewhelper.VHPedidoDeCompra;

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
      "/Pages/lumino/carrinho",
      "/Pages/lumino/finalizarCompra"
    })

  public class CadastrarProdutoController extends HttpServlet implements ServletContextListener {
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
    	mapViewHelper.put("/livraria/Pages/lumino/finalizarCompra", new VHPedidoDeCompra());
    	
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

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("Contexto destru�do");
    
  }

  @Override
  public void init() {
    System.out.println("contextInitialized");

    HashMap<String, Bloqueio> mapProdutosBloqueados = new HashMap<>();     
    getServletContext().setAttribute("bloqueio", mapProdutosBloqueados);


    HashMap<String, Carrinho> mapProdutosDesbloqueados = new HashMap<>();     
    getServletContext().setAttribute("desbloqueio", mapProdutosDesbloqueados);

    SchedulerFactory shedFact = new StdSchedulerFactory();
    try {
           Scheduler scheduler = shedFact.getScheduler();
           scheduler.start();
         JobDataMap jobDataMap = new JobDataMap();
         jobDataMap.put("servletContext", getServletContext());
           JobDetail job = JobBuilder.newJob(StValidarItensCarrinhoComTempoExpirado.class)
                         .withIdentity("validadorJOB", "grupo01")
                         .usingJobData(jobDataMap)
                         .build();
           Trigger trigger = TriggerBuilder.newTrigger()
                         .withIdentity("validadorTRIGGER","grupo01")
                         .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                         .build();
           scheduler.scheduleJob(job, trigger);
  } catch (SchedulerException e) {
      e.printStackTrace();
  }
      
      
    
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // TODO Auto-generated method stub
    
  }
  



}
