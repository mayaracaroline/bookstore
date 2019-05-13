package fachada;	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.Bloqueio;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Livro;
import dominio.PedidoDeCompra;
import dominio.Usuario;
import les.dao.DAOCliente;
import les.dao.DAOLivro;
import les.dao.DAOPedidoDeCompra;
import les.dao.DAOUsuario;
import les.dao.IDAO;
import les.negocio.IStrategy;
import les.negocio.StComplementarCupom;
import les.negocio.StComplementarGeneroLiterario;
import les.negocio.StConsultarQuantidadeEstoque;
import les.negocio.StGerarCodigoCompra;
import les.negocio.StInativarCupom;
import les.negocio.StValidarCarrinhoExpirado;
import les.negocio.StValidarCepInformado;
import les.negocio.StValidarClienteLogado;
import les.negocio.StValidarDadosObrigatoriosCliente;
import les.negocio.StValidarDadosObrigatoriosCompra;
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
import servico.CalcularFrete;
import servico.CarrinhoServico;
import util.Resultado;

public class Fachada implements IFachada  {
	
	private Map<String, List<IStrategy>> rnsProduto;
	private Map<String, List<IStrategy>> rnsCliente;
	private Map<String, List<IStrategy>> rnsBloqueioProduto;
	private Map<String, List<IStrategy>> rnsAutenticarUsuario;
	private Map<String, List<IStrategy>> rnsValidarDadosCompra;
	private Map<String, List<IStrategy>> rnsValidarEndereco;
	private Map<String, IDAO> mapDAO;
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private List<IStrategy> listStrategySalvarProduto;
	private List<IStrategy> listStrategyConsultarProduto;
	private List<IStrategy> listStrategyExcluirProduto;
	private List<IStrategy> listStrategyAlterarProduto;
	private List<IStrategy> listStrategyInativarProduto;
	
	private List<IStrategy> listStrategySalvarCliente;
	private List<IStrategy> listStrategyConsultarCliente;
	private List<IStrategy> listStrategySalvarBloqueioProduto;
	private List<IStrategy> listStrategyAlterarBloqueioProduto;
	private List<IStrategy> listStrategyExcluirBloqueioProduto;
	
	private List<IStrategy> listStrategySalvarCompra;
	
	 private List<IStrategy> listStrategyAutenticarUsuario;
	 
	 private List<IStrategy> listStrategyCalcularFrete; 
	 private List<IStrategy> listStrategyConsultarCompra; 
	 
	public Fachada() {
	  rns = new HashMap<String, Map<String, List<IStrategy>>>();
		rnsProduto = new HashMap<String, List<IStrategy>>();
		rnsCliente = new HashMap<String, List<IStrategy>>();
		rnsBloqueioProduto = new HashMap<String, List<IStrategy>>();
    rnsAutenticarUsuario = new HashMap<String, List<IStrategy>>();
    rnsValidarDadosCompra = new HashMap<String, List<IStrategy>>();
    rnsValidarEndereco = new HashMap<String, List<IStrategy>>(); 
		
		mapDAO = new HashMap<String, IDAO>();
		
		mapDAO.put("LIVRO", new DAOLivro());
		mapDAO.put("CLIENTE", new DAOCliente());
		mapDAO.put("USUARIO", new DAOUsuario());
		mapDAO.put("PEDIDODECOMPRA", new DAOPedidoDeCompra());

		listStrategySalvarProduto = new ArrayList<IStrategy>();
		listStrategyConsultarProduto = new ArrayList<IStrategy>();
		listStrategyExcluirProduto = new ArrayList<IStrategy>();
		listStrategyAlterarProduto = new ArrayList<IStrategy>();
		listStrategyInativarProduto = new ArrayList<IStrategy>();
    listStrategySalvarBloqueioProduto = new ArrayList<IStrategy>(); 
    listStrategyAlterarBloqueioProduto = new ArrayList<IStrategy>();
    listStrategyExcluirBloqueioProduto = new ArrayList<IStrategy>();
    listStrategyCalcularFrete = new ArrayList<IStrategy>();
		
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
		
		listStrategySalvarBloqueioProduto.add(new StConsultarQuantidadeEstoque());
		listStrategySalvarBloqueioProduto.add(new StValidarExistenciaCarrinhoSessao());
		
		listStrategyAlterarBloqueioProduto.add(new StValidarQuantidadeAIncluirOuExcluirCarrinho());
		
		listStrategySalvarCliente = new ArrayList<IStrategy>();
    listStrategySalvarCliente.add(new StValidarDadosObrigatoriosCliente());
		
    listStrategyConsultarCliente = new ArrayList<IStrategy>();				
		listStrategyConsultarCliente.add(new StValidarIdConsultaCliente());
		
		listStrategyAutenticarUsuario = new ArrayList<IStrategy>();
		listStrategyAutenticarUsuario.add(new StValidarUsuarioExistente());
		
		listStrategySalvarCompra = new ArrayList<IStrategy>();
    listStrategyConsultarCompra = new ArrayList<IStrategy>();
		
		listStrategySalvarCompra.add(new StValidarClienteLogado());
		listStrategySalvarCompra.add(new StValidarCarrinhoExpirado());
		listStrategySalvarCompra.add(new StValidarDadosObrigatoriosCompra()); 
    listStrategySalvarCompra.add(new StValidarFormaDePagamento());
    listStrategySalvarCompra.add(new StComplementarCupom());
    listStrategySalvarCompra.add(new StValidarValorCompra());
    listStrategySalvarCompra.add(new StValidarValorMinimoParaPagamentoComCartao());
    listStrategySalvarCompra.add(new StValidarValorExcendenteAoPagamento());
    listStrategySalvarCompra.add(new StInativarCupom()); 
    listStrategySalvarCompra.add(new StGerarCodigoCompra());

		listStrategyCalcularFrete.add(new StValidarCepInformado());

		
		rnsProduto.put("SALVAR", listStrategySalvarProduto);
		rnsProduto.put("CONSULTAR", listStrategyConsultarProduto);
		rnsProduto.put("EXCLUIR", listStrategyExcluirProduto);
		rnsProduto.put("ALTERAR", listStrategyAlterarProduto);
		rnsProduto.put("INATIVAR", listStrategyInativarProduto);
		
		rnsBloqueioProduto.put("SALVAR", listStrategySalvarBloqueioProduto);
		rnsBloqueioProduto.put("ALTERAR", listStrategyAlterarBloqueioProduto);
		rnsBloqueioProduto.put("EXCLUIR", listStrategyExcluirBloqueioProduto);
		
		rnsCliente.put("SALVAR", listStrategySalvarCliente);
		rnsCliente.put("CONSULTAR", listStrategyConsultarCliente);
		
		rnsAutenticarUsuario.put("CONSULTAR",listStrategyAutenticarUsuario);
		
		rnsValidarDadosCompra.put("SALVAR", listStrategySalvarCompra);
		rnsValidarDadosCompra.put("CONSULTAR", listStrategyConsultarCompra);
		
		rnsValidarEndereco.put("CALCULARFRETE", listStrategyCalcularFrete);
		
    rns.put(Livro.class.getSimpleName().toUpperCase(), rnsProduto);
    rns.put(Cliente.class.getSimpleName().toUpperCase(), rnsCliente);
    rns.put(Bloqueio.class.getSimpleName().toUpperCase(), rnsBloqueioProduto);
    rns.put(Usuario.class.getSimpleName().toUpperCase(), rnsAutenticarUsuario);
    rns.put(PedidoDeCompra.class.getSimpleName().toUpperCase(), rnsValidarDadosCompra);
    rns.put(Endereco.class.getSimpleName().toUpperCase(), rnsValidarEndereco);
				
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
	
}
