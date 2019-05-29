package les.negocio;

import dominio.Endereco;
import dominio.EntidadeDominio;
import les.dao.DAOEndereco;
import util.Resultado;

public class StComplementarEndereco implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    Endereco enderecoEntrega  = (Endereco) entidade;
    DAOEndereco daoEndereco = new DAOEndereco();
    Resultado resultadoEndereco;
    resultadoEndereco = daoEndereco.consultar(enderecoEntrega);
    enderecoEntrega = (Endereco) resultadoEndereco.getResultado();  
    
    return mensagem;
  }
   

}
