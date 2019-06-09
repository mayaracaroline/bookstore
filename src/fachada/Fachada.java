package fachada;	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.Bloqueio;
import dominio.Cliente;
import dominio.DadosEntrega;
import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.Livro;
import dominio.PedidoDeCompra;
import dominio.Relatorio;
import dominio.Usuario;
import les.command.CommandAprovarTroca;
import les.command.CommandCalcularFrete;
import les.command.CommandCarrinhoAdicionar;
import les.command.CommandCarrinhoAlterar;
import les.command.CommandCarrinhoExcluir;
import les.command.CommandColocarItemEmTroca;
import les.command.CommandConfirmaEntrega;
import les.command.CommandEnderecoAdicionar;
import les.dao.DAOCliente;
import les.dao.DAOEstoque;
import les.dao.DAOLivro;
import les.dao.DAOPedidoDeCompra;
import les.dao.DAORelatorio;
import les.dao.DAOUsuario;
import les.dao.IDAO;
import les.negocio.IStrategy;
import les.negocio.StComplementarCupom;
import les.negocio.StComplementarEndereco;
import les.negocio.StComplementarGeneroLiterario;
import les.negocio.StComplementarPedidoDeCompra;
import les.negocio.StComplementarProduto;
import les.negocio.StConsultarQuantidadeEstoque;
import les.negocio.StGerarCodigoCompra;
import les.negocio.StGerarCupomTroca;
import les.negocio.StInativarCupom;
import les.negocio.StValidarCarrinhoExpirado;
import les.negocio.StValidarCepInformado;
import les.negocio.StValidarClienteLogado;
import les.negocio.StValidarDadosObrigatoriosCliente;
import les.negocio.StValidarDadosObrigatoriosCompra;
import les.negocio.StValidarDadosObrigatoriosEstoque;
import les.negocio.StValidarDadosObrigatoriosLivro;
import les.negocio.StValidarExistencia;
import les.negocio.StValidarExistenciaCarrinhoSessao;
import les.negocio.StValidarFormaDePagamento;
import les.negocio.StValidarIdConsultaCliente;
import les.negocio.StValidarIdInserido;
import les.negocio.StValidarLivroExclusaoEAlteracao;
import les.negocio.StValidarMotivoAtivacao;
import les.negocio.StValidarMotivoCategoriaInativacao;
import les.negocio.StValidarQuantidadeAIncluirOuExcluirCarrinho;
import les.negocio.StValidarUsuarioExistente;
import les.negocio.StValidarValorCompra;
import les.negocio.StValidarValorExcendenteAoPagamento;
import les.negocio.StValidarValorMinimoParaPagamentoComCartao;
import les.negocio.itemPedido.StValidarCodigoItemPedido;
import servico.CalcularFrete;
import servico.CarrinhoServico;
import servico.IServico;
import servico.PedidoServico;
import servico.carrinho.CarrinhoAdicionar;
import servico.carrinho.CarrinhoAlterar;
import servico.carrinho.CarrinhoExcluir;
import servico.carrinho.EnderecoAdicionar;
import servico.itemCarrinho.ItemCarrinhoAprovarTroca;
import servico.itemCarrinho.ItemCarrinhoColocarEmTroca;
import util.Resultado;

public class Fachada implements IFachada  {
  
  /* *
   * Mapa de regras de negocio que possui como chave 
   * o nome da entidade e como valor um outro mapa o
   * qual armazena em sua estrutura: operacao a ser
   * realizada e regras de negocio por operacao 
   * */
  private Map<String, Map<String, List<IStrategy>>> rns;
  
  /* *
   * Mapa de regras de negocio que possui como chave 
   * a operacao a ser realizada e como valor uma 
   * lista de regras de negócios representadas por
   * Strategys  para cada operacao especifica. 
   * */
  
	private Map<String, List<IStrategy>> rnsProduto;
	private Map<String, List<IStrategy>> rnsCliente;
	private Map<String, List<IStrategy>> rnsBloqueioProduto;
	private Map<String, List<IStrategy>> rnsAutenticarUsuario;
	private Map<String, List<IStrategy>> rnsValidarDadosCompra;
	private Map<String, List<IStrategy>> rnsValidarEndereco;
	private Map<String, List<IStrategy>> rnsGerenciarPedido;
	private Map<String, List<IStrategy>> rnsEstoque;
	private Map<String, List<IStrategy>> rnsRelatorio;
	
	/* Listas de regras de negocio relacionadas a entidade
	 * Produto: */
	private List<IStrategy> listStrategySalvarProduto;
	private List<IStrategy> listStrategyConsultarProduto;
	private List<IStrategy> listStrategyExcluirProduto;
	private List<IStrategy> listStrategyAlterarProduto;
	private List<IStrategy> listStrategyInativarProduto;

	/* Listas de regras de negocio relacionadas a entidade
   * Cliente: */
	private List<IStrategy> listStrategySalvarCliente;
	private List<IStrategy> listStrategyConsultarCliente;
	
  /* Listas de regras de negocio relacionadas a entidade
   * Bloqueio: */	
	private List<IStrategy> listStrategySalvarBloqueioProduto;
	private List<IStrategy> listStrategyAlterarBloqueioProduto;
	private List<IStrategy> listStrategyExcluirBloqueioProduto;
	
	/* Listas de regras de negocio relacionadas a entidade
	 * PedidoDeCompra: */
	private List<IStrategy> listStrategySalvarCompra;
  private List<IStrategy> listStrategyConsultarCompra; 
  private List<IStrategy> listStrategyAlterarPedido;  
  
  /* Listas de regras de negocio relacionadas a entidade
   * ItemCarrinho: */
  private List<IStrategy> listStrategyAlteraStatusItemPedido;
  private List<IStrategy> listStrategyAprovarTrocaItemPedido;
  /* Listas de regras de negocio relacionadas a entidade
   * Usuario: */
  private List<IStrategy> listStrategyAutenticarUsuario;
  
  /* Listas de regras de negocio relacionadas a entidade
   * Endereco: */	 
	private List<IStrategy> listStrategyCalcularFrete; 
	
  /* Listas de regras de negocio relacionadas a entidade
   * Estoque: */ 
	private List<IStrategy> listStrategyEstoque;
	    
  /* Listas de regras de negocio relacionadas a entidade
   * Relatorio: */ 
  private List<IStrategy> listStrategyConsultarRelatorio;

	/* Mapa que possui como chave o nome da entidade 
	 * e como valor a instancia da DAO especifica */
  private Map<String, IDAO> mapDAO;
  
  /* Mapa que possui como chave o nome da command 
   * e como valor a instancia do servico especifico */  
  private Map<String, IServico> mapServico;
	 
	public Fachada() {
	  
    /* Regras de negócio por entidade */	  
	  rns = new HashMap<String, Map<String, List<IStrategy>>>();
		rnsProduto = new HashMap<String, List<IStrategy>>();
		rnsCliente = new HashMap<String, List<IStrategy>>();
		rnsBloqueioProduto = new HashMap<String, List<IStrategy>>();
    rnsAutenticarUsuario = new HashMap<String, List<IStrategy>>();
    rnsValidarDadosCompra = new HashMap<String, List<IStrategy>>();
    rnsValidarEndereco = new HashMap<String, List<IStrategy>>(); 
    rnsGerenciarPedido = new HashMap<String, List<IStrategy>>();
    rnsEstoque = new HashMap<String, List<IStrategy>>();
    rnsRelatorio = new HashMap<String, List<IStrategy>>();
    
    /* Regras de negócio por operacao X entidade */
		listStrategySalvarProduto = new ArrayList<IStrategy>();
		listStrategyConsultarProduto = new ArrayList<IStrategy>();
		listStrategyExcluirProduto = new ArrayList<IStrategy>();
		listStrategyAlterarProduto = new ArrayList<IStrategy>();
		listStrategyInativarProduto = new ArrayList<IStrategy>();
    listStrategySalvarBloqueioProduto = new ArrayList<IStrategy>(); 
    listStrategyAlterarBloqueioProduto = new ArrayList<IStrategy>();
    listStrategyExcluirBloqueioProduto = new ArrayList<IStrategy>();
    listStrategyCalcularFrete = new ArrayList<IStrategy>();
    listStrategySalvarCliente = new ArrayList<IStrategy>();
    listStrategyConsultarCliente = new ArrayList<IStrategy>();  
    listStrategySalvarCompra = new ArrayList<IStrategy>();
    listStrategyConsultarCompra = new ArrayList<IStrategy>();
    listStrategyAlterarPedido = new ArrayList<IStrategy>();
    listStrategyAlteraStatusItemPedido = new ArrayList<IStrategy>(); 
    listStrategyAprovarTrocaItemPedido = new ArrayList<IStrategy>(); 
    listStrategyEstoque = new ArrayList<IStrategy>(); 
    listStrategyConsultarRelatorio = new ArrayList<IStrategy>();
    
    /* Regras de negócio: PRODUTO */
		listStrategySalvarProduto.add(new StValidarDadosObrigatoriosLivro());
		listStrategySalvarProduto.add(new StValidarMotivoAtivacao());
		listStrategySalvarProduto.add(new StComplementarGeneroLiterario());
		listStrategySalvarProduto.add(new StValidarExistencia());		
		listStrategyConsultarProduto.add(new StValidarIdInserido());
		listStrategyConsultarProduto.add(new StValidarLivroExclusaoEAlteracao());		
		listStrategyExcluirProduto.add(new StValidarIdInserido());
		listStrategyExcluirProduto.add(new StValidarLivroExclusaoEAlteracao());		
		listStrategyAlterarProduto.add(new StValidarIdInserido());
		listStrategyAlterarProduto.add(new StValidarLivroExclusaoEAlteracao());
		listStrategyInativarProduto.add(new StValidarIdInserido());
		listStrategyInativarProduto.add(new StValidarLivroExclusaoEAlteracao());
		listStrategyInativarProduto.add(new StValidarMotivoCategoriaInativacao());
		
		/* Regras de negócio: BLOQUEIO (pedido) */
		listStrategySalvarBloqueioProduto.add(new StConsultarQuantidadeEstoque());
		listStrategySalvarBloqueioProduto.add(new StValidarExistenciaCarrinhoSessao());
		listStrategyAlterarBloqueioProduto.add(new StValidarCarrinhoExpirado());
		listStrategyAlterarBloqueioProduto.add(new StValidarQuantidadeAIncluirOuExcluirCarrinho());
		
		/* Regras de negócio: CLIENTE */
    listStrategySalvarCliente.add(new StValidarDadosObrigatoriosCliente());		
		listStrategyConsultarCliente.add(new StValidarIdConsultaCliente());
		
		/* Regras de negócio: USUARIO */
		listStrategyAutenticarUsuario = new ArrayList<IStrategy>();
		listStrategyAutenticarUsuario.add(new StValidarUsuarioExistente());
		
		/* Regras de negócio: PEDIDO DE COMPRA */	
		listStrategySalvarCompra.add(new StValidarClienteLogado());
		listStrategySalvarCompra.add(new StValidarCarrinhoExpirado());
		//listStrategySalvarCompra.add(new StValidarCodigoSeguranca());
		listStrategySalvarCompra.add(new StValidarDadosObrigatoriosCompra()); 
    listStrategySalvarCompra.add(new StValidarFormaDePagamento());
    listStrategySalvarCompra.add(new StComplementarCupom());
    listStrategySalvarCompra.add(new StValidarValorCompra());
    listStrategySalvarCompra.add(new StValidarValorMinimoParaPagamentoComCartao());
    listStrategySalvarCompra.add(new StValidarValorExcendenteAoPagamento());
    listStrategySalvarCompra.add(new StInativarCupom()); 
    listStrategySalvarCompra.add(new StGerarCodigoCompra());
    
    /* Regras de negócio: ITEM PEDIDO */ 
    listStrategyAlteraStatusItemPedido.add(new StValidarCodigoItemPedido());
    listStrategyAlteraStatusItemPedido.add(new StComplementarPedidoDeCompra());
    listStrategyAprovarTrocaItemPedido.add(new StValidarCodigoItemPedido());
    listStrategyAprovarTrocaItemPedido.add(new StComplementarPedidoDeCompra());
    listStrategyAprovarTrocaItemPedido.add(new StGerarCupomTroca());
    
    /* Regras de negócio: ENDERECO */
    listStrategyCalcularFrete.add(new StComplementarEndereco());    
		listStrategyCalcularFrete.add(new StValidarCepInformado());
		
    /* Regras de negócio: ESTOQUE */
    listStrategyEstoque.add(new StValidarDadosObrigatoriosEstoque()); 		
    listStrategyEstoque.add(new StComplementarProduto());
		
    /* Regras de negócio por operacao: */
		
		/* PRODUTO */
		rnsProduto.put("SALVAR", listStrategySalvarProduto);
		rnsProduto.put("CONSULTAR", listStrategyConsultarProduto);
		rnsProduto.put("EXCLUIR", listStrategyExcluirProduto);
		rnsProduto.put("ALTERAR", listStrategyAlterarProduto);
		rnsProduto.put("INATIVAR", listStrategyInativarProduto);
		
		/* BLOQUEIO PRODUTO */
		rnsBloqueioProduto.put("SALVAR", listStrategySalvarBloqueioProduto);
		rnsBloqueioProduto.put("ALTERAR", listStrategyAlterarBloqueioProduto);
		rnsBloqueioProduto.put("EXCLUIR", listStrategyExcluirBloqueioProduto);
		
		/* CLIENTE */
		rnsCliente.put("SALVAR", listStrategySalvarCliente);
		rnsCliente.put("CONSULTAR", listStrategyConsultarCliente);
		
		/* USUARIO */
		rnsAutenticarUsuario.put("CONSULTAR",listStrategyAutenticarUsuario);
		
		/* DADOS ENTREGA */
		rnsValidarEndereco.put("CALCULARFRETE", listStrategyCalcularFrete);
		rnsValidarEndereco.put("ENDERECOADICIONAR", listStrategyCalcularFrete);
		
    /* ESTOQUE */
    rnsEstoque.put("SALVAR", listStrategyEstoque);
    
    /* RELATORIO */
    rnsRelatorio.put("CONSULTAR", listStrategyConsultarRelatorio);
		
		/* PEDIDO DE COMPRA */
		rnsGerenciarPedido.put("SALVAR", listStrategySalvarCompra);
		rnsGerenciarPedido.put("CONSULTAR", listStrategyConsultarCompra);
		rnsGerenciarPedido.put("COLOCAREMTRANSPORTE", listStrategyAlterarPedido);
		rnsGerenciarPedido.put("CONFIRMARENTREGA", listStrategyAlterarPedido);
		rnsGerenciarPedido.put("COLOCARITEMEMTROCA",listStrategyAlteraStatusItemPedido);
		rnsGerenciarPedido.put("APROVARTROCA",listStrategyAprovarTrocaItemPedido);
		
		/* Regras de negócio por entidade */
    rns.put(Livro.class.getSimpleName().toUpperCase(), rnsProduto);
    rns.put(Cliente.class.getSimpleName().toUpperCase(), rnsCliente);
    rns.put(Bloqueio.class.getSimpleName().toUpperCase(), rnsBloqueioProduto);
    rns.put(Usuario.class.getSimpleName().toUpperCase(), rnsAutenticarUsuario);
    rns.put(PedidoDeCompra.class.getSimpleName().toUpperCase(), rnsGerenciarPedido);
    rns.put(DadosEntrega.class.getSimpleName().toUpperCase(), rnsValidarEndereco);
    rns.put(Estoque.class.getSimpleName().toUpperCase(), rnsEstoque);
    rns.put(Relatorio.class.getSimpleName().toUpperCase(), rnsRelatorio);
//  rns.put(PedidoDeCompra.class.getSimpleName().toUpperCase(), rnsValidarDadosCompra);    
    
    /* Mapa de servico por command */
    mapServico = new HashMap<String, IServico>();
    mapServico.put(CommandCalcularFrete.class.getSimpleName(), new CalcularFrete());
    mapServico.put(CommandCarrinhoAdicionar.class.getSimpleName(), new CarrinhoAdicionar()); 
    mapServico.put(CommandCarrinhoAlterar.class.getSimpleName(), new CarrinhoAlterar());
    mapServico.put(CommandCarrinhoExcluir.class.getSimpleName(), new CarrinhoExcluir());
    mapServico.put(CommandConfirmaEntrega.class.getSimpleName(), new CarrinhoAdicionar());
    mapServico.put(CommandEnderecoAdicionar.class.getSimpleName(), new EnderecoAdicionar());
    mapServico.put(CommandColocarItemEmTroca.class.getSimpleName(), new ItemCarrinhoColocarEmTroca());
    mapServico.put(CommandAprovarTroca.class.getSimpleName(), new ItemCarrinhoAprovarTroca());
    
    /* Mapa de DAO por entidade */
    mapDAO = new HashMap<String, IDAO>();
    mapDAO.put("LIVRO", new DAOLivro());
    mapDAO.put("CLIENTE", new DAOCliente());
    mapDAO.put("USUARIO", new DAOUsuario());
    mapDAO.put("PEDIDODECOMPRA", new DAOPedidoDeCompra());
    mapDAO.put("ESTOQUE", new DAOEstoque());
    mapDAO.put("RELATORIO", new DAORelatorio());
				
	}
	
	public Resultado validarStrategys(EntidadeDominio entidade, String operacao) {
		Resultado resultado = new Resultado();
		String mensagem = "";
		String mensagens = "";
		String nmClasse = entidade.getClass().getSimpleName().toUpperCase();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		List<IStrategy> listStrategy  = regrasOperacao.get(operacao);
		
		for (IStrategy iStrategy : listStrategy) {
			
			mensagem = iStrategy.processar(entidade);
			
			if(mensagem != null){
			  System.out.println(mensagem);
				mensagens += mensagem;
			}
		}
		
		if("".equals(mensagens)){
			resultado.sucesso("SUCESSO");
		}else{
			List<EntidadeDominio> l = new ArrayList<>();
			l.add(entidade);
			resultado.setListaResultado(l);
			resultado.erro(mensagens);
		}
		
		return resultado;
	}

	
	public Resultado salvar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		
		resultado = validarStrategys(entidade, "SALVAR");
	
		if (!resultado.getErro()) {
			IDAO dao = mapDAO.get(entidade.getClass().getSimpleName().toUpperCase());
			resultado = dao.salvar(entidade);
		}
		
		return resultado;
	}
	
	public Resultado consultar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		
		resultado = validarStrategys(entidade, "CONSULTAR");

		if (!resultado.getErro()) {
			IDAO dao = mapDAO.get(entidade.getClass().getSimpleName().toUpperCase());
			resultado = dao.consultar(entidade);
		}
		
		return resultado;	
	}
		
	public Resultado excluir(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		
		resultado = validarStrategys(entidade, "EXCLUIR");

		if (!resultado.getErro()) {
			IDAO dao = mapDAO.get(entidade.getClass().getSimpleName().toUpperCase());
			resultado = dao.excluir(entidade);
		}
		
		return resultado;	
		
	}
		
	public Resultado alterar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		
		resultado = validarStrategys(entidade, "ALTERAR");

		if (!resultado.getErro()) {
			IDAO dao = mapDAO.get(entidade.getClass().getSimpleName().toUpperCase());
			resultado = dao.alterar(entidade);
			
		}
		
		return resultado;	
		
	}
	
	public Resultado inativar(EntidadeDominio entidade) {
	
		Resultado resultado = new Resultado();
		resultado = validarStrategys(entidade, "INATIVAR");

		if (!resultado.getErro()) {
			IDAO dao = mapDAO.get(entidade.getClass().getSimpleName().toUpperCase());
			resultado = dao.inativar(entidade);
		}
		
		return resultado;
	}

  @Override
  public Resultado adicionarAoCarrinho(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    resultado = validarStrategys(entidade, "SALVAR");
    
    if (!resultado.getErro()) {
      
       CarrinhoServico servico = new CarrinhoServico();
       resultado = servico.adicionar(entidade);
    }
    
    return resultado;    
    
  }	
  
  public Resultado alterarItensCarrinho(EntidadeDominio entidade) {
      Resultado resultado = new Resultado();
      resultado = validarStrategys(entidade, "ALTERAR");
      if (!resultado.getErro()) {
        
         CarrinhoServico servico = new CarrinhoServico();
         resultado = servico.alterarQuantidadeItens(entidade);
      }
      
      return resultado;     
  }
  
  public Resultado excluirItensCarrinho(EntidadeDominio entidade) {
    
    Resultado resultado = new Resultado();
    resultado = validarStrategys(entidade, "EXCLUIR");
    
    if (!resultado.getErro()) {
      
       CarrinhoServico servico = new CarrinhoServico();
       resultado = servico.excluirItens(entidade);
    }
    
    return resultado; 
    
  }
 

  @Override
  public Resultado calcularFrete(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    resultado = validarStrategys(entidade, "CALCULARFRETE");
    
    if (!resultado.getErro()) {
       CalcularFrete servico = new CalcularFrete();
       resultado = servico.calcularFrete(entidade);
    }
    
    return resultado; 
  }

  @Override
  public Resultado colocarEmTransporte(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    resultado = validarStrategys(entidade, "COLOCAREMTRANSPORTE");
    
    if (!resultado.getErro()) {
      PedidoServico servico = new PedidoServico();
       resultado = servico.colocarEmTransporte(entidade);
    }
    
    return resultado; 
  }

  @Override
  public Resultado confirmarEntrega(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    resultado = validarStrategys(entidade, "CONFIRMARENTREGA");
    
    if (!resultado.getErro()) {
      PedidoServico servico = new PedidoServico();
       resultado = servico.confirmarEntrega(entidade);
    }
    
    return resultado; 
  }

  @Override
  public Resultado chamarServico(EntidadeDominio entidade, String command) {
    
    Resultado resultado = new Resultado();
    String operacao = command.toUpperCase().substring(7);
    
    resultado = validarStrategys(entidade, operacao );
    
    if (!resultado.getErro()) {
      IServico servico = mapServico.get(command);
       resultado = servico.executarServico(entidade);
    }
    
    return resultado; 
  }

  @Override
  public Resultado consultarPorId(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();    
    resultado = validarStrategys(entidade, "CONSULTARPORID");
    
    if (!resultado.getErro()) {
      IDAO dao = mapDAO.get(entidade.getClass().getSimpleName().toUpperCase());
      resultado = dao.consultarPorId(entidade);
    }
    
    return resultado; 
  }
  	
}
