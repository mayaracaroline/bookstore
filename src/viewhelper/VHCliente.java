package viewhelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Bandeira;
import dominio.Cartao;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Genero;
import dominio.Livro;
import dominio.Pais;
import dominio.Telefone;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;
import dominio.TipoTelefone;
import dominio.Usuario;
import util.Numero;
import util.Resultado;

public class VHCliente implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    Cliente cliente = new Cliente();
    List<Endereco> enderecos = new ArrayList<Endereco>();
    
    int codigo = null != request.getParameter("codigo") 
        && !"".equals(request.getParameter("codigo"))
        && Numero.isNumeric(request.getParameter("codigo").trim()) 
        ?  Integer.parseInt(request.getParameter("codigo")) : 0;
    
    String nome = null != request.getParameter("nome") && 
        !"".equals(request.getParameter("nome"))
        ? request.getParameter("nome") : "" ;
        
    String sobrenome = null != request.getParameter("sobrenome") && 
        !"".equals(request.getParameter("sobrenome"))
        ? request.getParameter("sobrenome") : "" ;        
        
    String strDataNasc = null != request.getParameter("data-nasc") && 
        !"".equals(request.getParameter("data-nasc")) 
        ? request.getParameter("data-nasc") : "1800-01-01";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dataNasc = LocalDate.parse(strDataNasc,formatter);
             
    String genero = null != request.getParameter("genero") &&
       !"".equals(request.getParameter("genero")) 
       ? request.getParameter("genero") : "SELECIONE";   
    
    String cpf = null != request.getParameter("cpf") &&
       !"".equals(request.getParameter("cpf")) 
       ? request.getParameter("cpf").replace(".","").replace("-", "").trim() : "";
       
    Telefone telefone = new Telefone();
    
    String tipoTelefone = null != request.getParameter("tipo-telefone") &&
        !"".equals(request.getParameter("tipo-telefone"))
        ? request.getParameter("tipo-telefone") : "SELECIONE";
        
    String ddd = null != request.getParameter("ddd") 
        && !"".equals(request.getParameter("ddd"))
        ? request.getParameter("ddd").replace("(", "").replace(")", "").trim() : ""; 
    String numeroTelefone = null!= request.getParameter("telefone") 
        && !"".equals(request.getParameter("telefone")) 
        ? request.getParameter("telefone") : "";
        
    LocalDate agora = LocalDate.now();
    LocalDate dataCadastro = LocalDate.of(agora.getYear(), agora.getMonthValue(), agora.getDayOfMonth());
       
    telefone.setDdd(ddd);
    telefone.setNumero(numeroTelefone);
    telefone.setTipo(TipoTelefone.valueOf(tipoTelefone));
        
    String strIsCobranca = request.getParameter("checkbox-endereco-cobranca");
    String strIsEntrega = request.getParameter("checkbox-endereco-entrega");
    boolean isCobranca = null == strIsCobranca ? false : true;
    boolean isEntrega = null == strIsEntrega ? false : true;

    Endereco endereco = new Endereco();
    
    String tipoResidencia = null != request.getParameter("tipo-residencia0") 
        && !"".equals(request.getParameter("tipo-residencia0"))
        ?  request.getParameter("tipo-residencia0") : "SELECIONE";

    int idTipoLogradouro = null != request.getParameter("tipo-logradouro0") 
        && !"".equals(request.getParameter("tipo-logradouro0"))
        && Numero.isNumeric(request.getParameter("tipo-logradouro0").trim()) 
        ?  Integer.parseInt(request.getParameter("tipo-logradouro0")) : 0;
   
    String logradouro = null != request.getParameter("logradouro0") ? 
        request.getParameter("logradouro0") : "";

    int numero = null != request.getParameter("numero0") 
        && !"".equals(request.getParameter("numero0"))
        && Numero.isNumeric(request.getParameter("numero0").trim()) 
        ?  Integer.parseInt(request.getParameter("numero0")) : 0;
        
    String bairro = null != request.getParameter("bairro0") 
            && !"".equals(request.getParameter("bairro0"))
            ?  request.getParameter("bairro0") : "";  
            
    int idEstado = null != request.getParameter("estado0") 
      && !"".equals(request.getParameter("estado0"))
      && Numero.isNumeric(request.getParameter("estado0").trim()) 
      ?  Integer.parseInt(request.getParameter("estado0")) : 0;     
      
    int idPais = null != request.getParameter("pais0") 
      && !"".equals(request.getParameter("pais0"))
      && Numero.isNumeric(request.getParameter("pais0").trim())
      ?  Integer.parseInt(request.getParameter("pais0")) : 0;             
    
    String observacoes = null != request.getParameter("observacoes0") 
        && !"".equals(request.getParameter("observacoes0"))
        ?  request.getParameter("observacoes0") : "";
    
    String cep = null != request.getParameter("cep0") 
        && !"".equals(request.getParameter("cep0"))
        ?  request.getParameter("cep0") : "";
        
    int idCidade = null != request.getParameter("cidade0") 
        && !"".equals(request.getParameter("cidade0"))
        && Numero.isNumeric(request.getParameter("cidade0").trim())
        ?  Integer.parseInt(request.getParameter("cidade0")) : 0;
        
    Estado estado = new Estado();
    estado.setId(idEstado);
    
    Pais pais = new Pais();
    pais.setId(idPais);
    
    Cidade cidade = new Cidade();
    cidade.setId(idCidade);
    cidade.setEstado(estado);
    
    TipoLogradouro tipoLogradouro = new TipoLogradouro();
    tipoLogradouro.setId(idTipoLogradouro);        
    
    endereco.setBairro(bairro);
    endereco.setCep(cep);
    endereco.setCidade(cidade);
    endereco.setEstado(estado);
    endereco.setLogradouro(logradouro);
    endereco.setNumero(numero);
    endereco.setObservacao(observacoes);
    endereco.setPais(pais);
    endereco.setTipoLogradouro(tipoLogradouro);
    endereco.setTipoResidencia(TipoResidencia.valueOf(tipoResidencia));          
    
    if(isCobranca && isEntrega) {
        
      for (int i = 0; i < 3; i++) {
        Endereco end1 = new Endereco();
        end1.setBairro(bairro);
        end1.setCep(cep);
        end1.setCidade(cidade);
        end1.setLogradouro(logradouro);
        end1.setNumero(numero);
        end1.setObservacao(observacoes);
        end1.setPais(pais);
        end1.setTipoLogradouro(tipoLogradouro);
        end1.setTipoResidencia(TipoResidencia.valueOf(tipoResidencia)); 
        enderecos.add(end1);
      }      
    } else if (!isCobranca && isEntrega) {
    
      for (int i = 0; i < 2; i++ ) {
        Endereco end1 = new Endereco();
        end1.setBairro(bairro);
        end1.setCep(cep);
        end1.setCidade(cidade);
        end1.setLogradouro(logradouro);
        end1.setNumero(numero);
        end1.setObservacao(observacoes);
        end1.setPais(pais);
        end1.setTipoLogradouro(tipoLogradouro);
        end1.setTipoResidencia(TipoResidencia.valueOf(tipoResidencia)); 
        enderecos.add(end1);
        enderecos.add(i,end1);
      }
       
      Endereco endCobranca = new Endereco();
      tipoResidencia = null != request.getParameter("tipo-residencia2") 
          && !"".equals(request.getParameter("tipo-residencia2"))
          ?  request.getParameter("tipo-residencia2") : "SELECIONE";

      idTipoLogradouro = null != request.getParameter("tipo-logradouro2") 
          && !"".equals(request.getParameter("tipo-logradouro2"))
          && Numero.isNumeric(request.getParameter("tipo-logradouro2").trim()) 
          ?  Integer.parseInt(request.getParameter("tipo-logradouro2")) : 0;
     
      logradouro = null != request.getParameter("logradouro2") ? 
          request.getParameter("logradouro2") : "";

      numero = null != request.getParameter("numero2") 
          && !"".equals(request.getParameter("numero2"))
          && Numero.isNumeric(request.getParameter("numero2").trim()) 
          ?  Integer.parseInt(request.getParameter("numero2")) : 0;
          
      bairro = null != request.getParameter("bairro2") 
              && !"".equals(request.getParameter("bairro2"))
              ?  request.getParameter("bairro2") : "";  
              
      idEstado = null != request.getParameter("estado2") 
        && !"".equals(request.getParameter("estado2"))
        && Numero.isNumeric(request.getParameter("estado2").trim()) 
        ?  Integer.parseInt(request.getParameter("estado2")) : 0;     
        
      idPais = null != request.getParameter("pais2") 
        && !"".equals(request.getParameter("pais2"))
        && Numero.isNumeric(request.getParameter("pais2").trim())
        ?  Integer.parseInt(request.getParameter("pais2")) : 0;             
      
      observacoes = null != request.getParameter("observacoes2") 
          && !"".equals(request.getParameter("observacoes2"))
          ?  request.getParameter("observacoes2") : "";
      
      cep = null != request.getParameter("cep2") 
          && !"".equals(request.getParameter("cep2"))
          ?  request.getParameter("cep2") : "";
          
      idCidade = null != request.getParameter("cidade2") 
          && !"".equals(request.getParameter("cidade2"))
          && Numero.isNumeric(request.getParameter("cidade2").trim())
          ?  Integer.parseInt(request.getParameter("cidade2")) : 0;
          
      estado = new Estado();
      estado.setId(idEstado);
      
      pais = new Pais();
      pais.setId(idPais);
      
      cidade = new Cidade();
      cidade.setId(idCidade);
      cidade.setEstado(estado);
      
      tipoLogradouro = new TipoLogradouro();
      tipoLogradouro.setId(idTipoLogradouro);              
      
      endereco.setBairro(bairro);
      endCobranca.setCep(cep);
      endCobranca.setCidade(cidade);
      endCobranca.setEstado(estado);
      endCobranca.setLogradouro(logradouro);
      endCobranca.setNumero(numero);
      endCobranca.setObservacao(observacoes);
      endCobranca.setPais(pais);
      endCobranca.setTipoLogradouro(tipoLogradouro);
      endCobranca.setTipoResidencia(TipoResidencia.valueOf(tipoResidencia));
      
      enderecos.add(2, endCobranca);
      
    } else if (isCobranca && !isEntrega) {
      enderecos.add(0,endereco);
      enderecos.add(1,endereco);
      enderecos.add(2,endereco);

      Endereco endEntrega = new Endereco();
      tipoResidencia = null != request.getParameter("tipo-residencia1") 
          && !"".equals(request.getParameter("tipo-residencia1"))
          ?  request.getParameter("tipo-residencia1") : "SELECIONE";

      idTipoLogradouro = null != request.getParameter("tipo-logradouro1") 
          && !"".equals(request.getParameter("tipo-logradouro1"))
          && Numero.isNumeric(request.getParameter("tipo-logradouro1").trim()) 
          ?  Integer.parseInt(request.getParameter("tipo-logradouro1")) : 0;
     
      logradouro = null != request.getParameter("logradouro2") ? 
          request.getParameter("logradouro1") : "";

      numero = null != request.getParameter("numero1") 
          && !"".equals(request.getParameter("numero1"))
          && Numero.isNumeric(request.getParameter("numero1").trim()) 
          ?  Integer.parseInt(request.getParameter("numero1")) : 0;
          
      bairro = null != request.getParameter("bairro1") 
              && !"".equals(request.getParameter("bairro1"))
              ?  request.getParameter("bairro1") : "";  
              
      idEstado = null != request.getParameter("estado1") 
        && !"".equals(request.getParameter("estado1"))
        && Numero.isNumeric(request.getParameter("estado1").trim()) 
        ?  Integer.parseInt(request.getParameter("estado1")) : 0;     
        
      idPais = null != request.getParameter("pais1") 
        && !"".equals(request.getParameter("pais1"))
        && Numero.isNumeric(request.getParameter("pais1").trim())
        ?  Integer.parseInt(request.getParameter("pais1")) : 0;             
      
      observacoes = null != request.getParameter("observacoes1") 
          && !"".equals(request.getParameter("observacoes1"))
          ?  request.getParameter("observacoes1") : "";
      
      cep = null != request.getParameter("cep1") 
          && !"".equals(request.getParameter("cep1"))
          ?  request.getParameter("cep1") : "";
          
      idCidade = null != request.getParameter("cidade1") 
          && !"".equals(request.getParameter("cidade1"))
          && Numero.isNumeric(request.getParameter("cidade1").trim())
          ?  Integer.parseInt(request.getParameter("cidade1")) : 0;
          
      estado = new Estado();
      estado.setId(idEstado);
      
      pais = new Pais();
      pais.setId(idPais);
      
      cidade = new Cidade();
      cidade.setId(idCidade);
      cidade.setEstado(estado);
      
      tipoLogradouro = new TipoLogradouro();
      tipoLogradouro.setId(idTipoLogradouro);              
      
      endereco.setBairro(bairro);
      endEntrega.setCep(cep);
      endEntrega.setCidade(cidade);
      endEntrega.setEstado(estado);
      endEntrega.setLogradouro(logradouro);
      endEntrega.setNumero(numero);
      endEntrega.setObservacao(observacoes);
      endEntrega.setPais(pais);
      endEntrega.setTipoLogradouro(tipoLogradouro);
      endEntrega.setTipoResidencia(TipoResidencia.valueOf(tipoResidencia));      
      
      enderecos.add(1, endEntrega);
    }  else {
      
      enderecos.add(0,endereco);
      
      for (int i = 1; i < 3; i++) {

        tipoResidencia = null != request.getParameter("tipo-residencia"+i) 
            && !"".equals(request.getParameter("tipo-residencia"+i))
            ?  request.getParameter("tipo-residencia"+i) : "SELECIONE";
  
        idTipoLogradouro = null != request.getParameter("tipo-logradouro"+i) 
            && !"".equals(request.getParameter("tipo-logradouro"+i))
            && Numero.isNumeric(request.getParameter("tipo-logradouro"+i).trim()) 
            ?  Integer.parseInt(request.getParameter("tipo-logradouro"+i)) : 0;
       
        logradouro = null != request.getParameter("logradouro"+i) ? 
            request.getParameter("logradouro"+i) : "";
  
        numero = null != request.getParameter("numero"+i) 
            && !"".equals(request.getParameter("numero"+i))
            && Numero.isNumeric(request.getParameter("numero"+i).trim()) 
            ?  Integer.parseInt(request.getParameter("numero"+i)) : 0;
            
        bairro = null != request.getParameter("bairro"+i) 
                && !"".equals(request.getParameter("bairro"+i))
                ?  request.getParameter("bairro"+i) : "";  
                
        idEstado = null != request.getParameter("estado"+i) 
          && !"".equals(request.getParameter("estado"+i))
          && Numero.isNumeric(request.getParameter("estado"+i).trim()) 
          ?  Integer.parseInt(request.getParameter("estado"+i)) : 0;     
          
        idPais = null != request.getParameter("pais"+i) 
          && !"".equals(request.getParameter("pais"))
          && Numero.isNumeric(request.getParameter("pais"+i).trim())
          ?  Integer.parseInt(request.getParameter("pais"+i)) : 0;             
        
        observacoes = null != request.getParameter("observacoes"+i) 
            && !"".equals(request.getParameter("observacoes"+i))
            ?  request.getParameter("observacoes"+i) : "";
        
        cep = null != request.getParameter("cep"+i) 
            && !"".equals(request.getParameter("cep"+i))
            ?  request.getParameter("cep"+i) : "";
            
        idCidade = null != request.getParameter("cidade"+i) 
            && !"".equals(request.getParameter("cidade"+i))
            && Numero.isNumeric(request.getParameter("cidade"+i).trim())
            ?  Integer.parseInt(request.getParameter("cidade"+i)) : 0;
            
            estado = new Estado();
            estado.setId(idEstado);
            
            pais = new Pais();
            pais.setId(idPais);
            
            cidade = new Cidade();
            cidade.setId(idCidade);
            cidade.setEstado(estado);
            
            tipoLogradouro = new TipoLogradouro();
            tipoLogradouro.setId(idTipoLogradouro);              
            
            Endereco end = new Endereco();
            end.setBairro(bairro);
            end.setCep(cep);
            end.setCidade(cidade);
            end.setLogradouro(logradouro);
            end.setNumero(numero);
            end.setObservacao(observacoes);
            end.setPais(pais);
            end.setTipoLogradouro(tipoLogradouro);
            end.setTipoResidencia(TipoResidencia.valueOf(tipoResidencia));
            
            enderecos.add(i,end);
        
      }
  
    }
    
    enderecos.get(0).setTipoEndereco("RESIDENCIAL");
    enderecos.get(1).setTipoEndereco("ENTREGA");
    enderecos.get(2).setTipoEndereco("COBRANÇA");
    
    enderecos.get(0).setDescricao("ENDEREÇO RESIDENCIAL");
    enderecos.get(1).setDescricao("ENDEREÇO ENTREGA");
    enderecos.get(2).setDescricao("ENDEREÇO COBRANÇA");
    
    Usuario usuario = new Usuario(); 
    
    String username = null != request.getParameter("email")
        && !"".equals(request.getParameter("email"))
        ? request.getParameter("email") : "";
    String password = null != request.getParameter("senha")
        && !"".equals(request.getParameter("senha")) 
        ? request.getParameter("senha") : "";
   
    usuario.setUsername(username);
    usuario.setPassword(password);
        
    Cartao cartao = new Cartao();
    Bandeira bandeira = new Bandeira();
    
    String nomeTitular = null != request.getParameter("nome-titular")
        && !"".equals(request.getParameter("nome-titular"))
        ? request.getParameter("nome-titular") : "";
    
    String numeroCartao = null != request.getParameter("numero-cartao")
        && !"".equals(request.getParameter("numero-cartao"))
        ? request.getParameter("numero-cartao") : "";
    
    int codBandeira = null != request.getParameter("bandeira") 
        && !"".equals(request.getParameter("bandeira"))
        && Numero.isNumeric(request.getParameter("bandeira")) 
        ? Integer.parseInt(request.getParameter("bandeira")) : 0;

    int codSeguranca = null != request.getParameter("cod-seguranca") 
        && !"".equals(request.getParameter("cod-seguranca"))
        && Numero.isNumeric(request.getParameter("cod-seguranca")) 
        ? Integer.parseInt(request.getParameter("cod-seguranca")) : 0;
        
    boolean cartaoPreferencial = null == request.getParameter("checkbox-cartao-preferencial") ? false : true;
    
    bandeira.setId(codBandeira);
    cartao.setBandeira(bandeira);
    cartao.setCodSeguranca(codSeguranca);
    cartao.setNomeTitular(nomeTitular);
    cartao.setNumero(numeroCartao);
    cartao.setPreferencial(cartaoPreferencial);    
    
    cliente.setEnderecoResidencial(enderecos.get(0));
    cliente.setEnderecoEntrega(enderecos.get(1));
    cliente.setEnderecoCobranca(enderecos.get(2));
    cliente.setCartao(cartao);
    cliente.setGenero(Genero.valueOf(genero));
    cliente.setCpf(cpf);
    cliente.setDataNascimento(dataNasc);
    cliente.setNome(nome);
    cliente.setSobrenome(sobrenome);
    cliente.setUsuario(usuario);
    cliente.setTelefone(telefone);
    cliente.setDataCadastro(dataCadastro);
    cliente.setId(codigo);
    
    return cliente;
  }  

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    String operacao = request.getParameter("operacao");
    String mensagem[] = resultado.getMensagem().split("\n");
    
    if(resultado.getErro())
      request.setAttribute("erro", mensagem);
    else
      request.setAttribute("sucesso", mensagem);
    
    if(operacao.equals("SALVAR")){
      if(resultado.getErro()){
        request.setAttribute("cliente", (Cliente) resultado.getListaResultado().get(0));
      }
    } else if(operacao.equals("CONSULTAR")){
      if(!resultado.getErro()){
        if(resultado.getResultado() != null){
          request.setAttribute("cliente", (Cliente) resultado.getResultado());
        }else{
          request.setAttribute("resultado",  resultado.getListaResultado());
        }
      } else if(operacao.equals("EXCLUIR")){
        if(resultado.getErro()){
          request.setAttribute("resultado", (Cliente) resultado.getResultado());
        } 
      } else if(operacao.equals("INATIVAR")){
        if(resultado.getErro()){
          request.setAttribute("resultado", (Cliente) resultado.getResultado());
        } 
      }
    }
    try {
      if(operacao.equals("SALVAR")){
        request.setAttribute("cliente", (Cliente) resultado.getResultado());
        RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/visualizar.jsp");
        rd.forward(request, response);
      }
      else if(operacao.equals("CONSULTAR")){
        if(resultado.getResultado() != null){         
//          RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/cadastraProduto.jsp");
          RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/visualizarCliente.jsp");
          rd.forward(request, response);
        } else if(resultado.getListaResultado() != null){
          RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/listaProduto.jsp");
          rd.forward(request, response);
        } else {
          RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/listaProduto.jsp");
          rd.forward(request, response);
        }
      } else if(operacao.equals("EXCLUIR")){
        request.setAttribute("cliente", (Cliente) resultado.getResultado());
        RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/produtoExcluido.jsp");
        rd.forward(request, response);
      } else if(operacao.equals("ALTERAR")){
        request.setAttribute("cliente", (Cliente) resultado.getResultado());
        RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/produtoAlterado.jsp");
        rd.forward(request, response);
      } else if(operacao.equals("INATIVAR")){
        request.setAttribute("cliente", (Cliente) resultado.getResultado());
        RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/produtoAlterado.jsp");
        rd.forward(request, response);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

}
