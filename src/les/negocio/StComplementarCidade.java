package les.negocio;

import dominio.Cidade;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Estado;
import les.dao.DAOCidade;
import util.Resultado;

public class StComplementarCidade implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    Cliente cliente = (Cliente) entidade;
    DAOCidade daoCidade = new DAOCidade();
    Resultado resultado = new Resultado();
    try {
      
      if(cliente.getEnderecoResidencial().getCidade() != null) {
        Cidade cidade = new Cidade();
        
        Resultado resCidadeResidencial = daoCidade
            .consultar(cliente.getEnderecoResidencial().getCidade());
        
        cidade = (Cidade) resCidadeResidencial.getResultado();        
        cliente.getEnderecoResidencial().setCidade(cidade);
            
        Estado estado = new Estado();
        estado = cidade.getEstado();
        cliente.getEnderecoResidencial().setEstado(estado);
      }
      if(cliente.getEnderecoEntrega().getCidade() != null) {
        Cidade cidade = new Cidade();
        Resultado resCidadeEntrega = daoCidade
            .consultar(cliente.getEnderecoEntrega().getCidade());
        cidade = (Cidade)resCidadeEntrega.getResultado();
        cliente.getEnderecoEntrega().setCidade(cidade);
        
        Estado estado = new Estado();
        estado = cidade.getEstado();
        cliente.getEnderecoEntrega().setEstado(estado);
      }     
      if(cliente.getEnderecoCobranca().getCidade() != null) {
        Cidade cidade = new Cidade();
        Resultado resCidadeCobranca = daoCidade
            .consultar(cliente.getEnderecoCobranca().getCidade());
        
        cidade = (Cidade)resCidadeCobranca.getResultado();
        cliente.getEnderecoCobranca().setCidade(cidade);
        
        Estado estado = new Estado();
        estado = cidade.getEstado();
        cliente.getEnderecoCobranca().setEstado(estado);
      }
      
    } catch (Exception e) {
      resultado.erro("Erro");
      mensagem = "Erro ao complementar cidade";
      e.printStackTrace();
    }
     
     entidade = cliente;
     resultado.setResultado(cliente);
     resultado.sucesso("SUCESSO");
         
    return mensagem;
  }

}
