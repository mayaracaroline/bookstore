package servico.carrinho;

import dominio.Cliente;
import dominio.DadosEntrega;
import dominio.Endereco;
import dominio.EntidadeDominio;
import les.dao.DAOClientes_Endereco;
import les.dao.DAOEndereco;
import servico.IServico;
import util.Resultado;

public class EnderecoAdicionar implements IServico {

  @Override
  public Resultado executarServico(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    DadosEntrega dadosEntrega = (DadosEntrega) entidade;
    Endereco endereco = dadosEntrega.getEnderecoEntrega();
    DAOEndereco daoEndereco = new DAOEndereco();
    Resultado resEnd = daoEndereco.salvar(endereco);
    
    endereco = (Endereco) resEnd.getResultado();
    
    DAOClientes_Endereco daoCliEnd = new DAOClientes_Endereco();
    
    Cliente cliente = new Cliente();
    cliente.setId(dadosEntrega.getId().intValue());
    cliente.setEnderecoEntrega(endereco);
    
    daoCliEnd.salvar(cliente);
    
    resultado.setResultado(endereco);
    
    return resultado;
  }
  

}
