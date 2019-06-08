package les.negocio;

import dominio.DadosEntrega;
import dominio.Endereco;
import dominio.EntidadeDominio;
import les.dao.DAOEndereco;
import util.Resultado;

public class StComplementarEndereco implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    DadosEntrega dadosEntrega = (DadosEntrega) entidade;
    Endereco enderecoEntrega  = dadosEntrega.getEnderecoEntrega();
    DAOEndereco daoEndereco = new DAOEndereco();
    Resultado resultadoEndereco;
    resultadoEndereco = daoEndereco.consultar(enderecoEntrega);
    enderecoEntrega = (Endereco) resultadoEndereco.getResultado(); 
    dadosEntrega.setEnderecoEntrega(enderecoEntrega);;
    
    return mensagem;
  }
   

}
