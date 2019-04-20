package les.negocio;

import java.time.LocalDate;

import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Genero;

public class StValidarDadosObrigatoriosCliente implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {

    Cliente cliente = (Cliente) entidade;
    
    StringBuilder mensagem = new StringBuilder();
    
    if(cliente.getGenero().equals(Genero.valueOf("SELECIONE"))){
      mensagem.append("Informe o gênero \n");
    }
    
    if(cliente.getNome().equals("")){
      mensagem.append("É necessário informar o nome\n");
    }

    if(cliente.getDataNascimento().equals(LocalDate.of(1800, 01, 01))){
      mensagem.append("A data de nascimento é um campo obrigatório\n");
    }
    
    if(cliente.getCpf().equals("")){
      mensagem.append("É necessário informar o CPF \n");
    }
    
    if( "".equals(cliente.getTelefone().getDdd()) ||
        "".equals(cliente.getTelefone().getNumero()) ||
        "".equals(cliente.getTelefone().getTipo())){     
      mensagem.append("Informe um telefone (DDD, número e título) \n");
    }
    
    if(cliente.getUsuario().getUsername().equals("")){
      mensagem.append("É necessário informar e-mail para acesso\n");
    }
    
    if(cliente.getUsuario().getPassword().equals("")){
      mensagem.append("Informe uma senha\n");
    } 
    
    if(mensagem.length() == 0){
      return null;
    }
    return mensagem.toString();
  }
    

}
