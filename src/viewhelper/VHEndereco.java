package viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;
import util.Formatter;
import util.Resultado;

public class VHEndereco implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    Endereco endereco = new Endereco();
    
    List<Endereco> enderecos = new ArrayList<Endereco>();
    String tipoResidencia = null != request.getParameter("tipo-residencia0") 
        && !"".equals(request.getParameter("tipo-residencia0"))
        ?  request.getParameter("tipo-residencia0") : "SELECIONE";

    int idTipoLogradouro = null != request.getParameter("tipo-logradouro0") 
        && !"".equals(request.getParameter("tipo-logradouro0"))
        && Formatter.isNumeric(request.getParameter("tipo-logradouro0").trim()) 
        ?  Integer.parseInt(request.getParameter("tipo-logradouro0")) : 0;
   
    String logradouro = null != request.getParameter("logradouro0") ? 
        request.getParameter("logradouro0") : "";

    int numero = null != request.getParameter("numero0") 
        && !"".equals(request.getParameter("numero0"))
        && Formatter.isNumeric(request.getParameter("numero0").trim()) 
        ?  Integer.parseInt(request.getParameter("numero0")) : 0;
        
    String bairro = null != request.getParameter("bairro0") 
            && !"".equals(request.getParameter("bairro0"))
            ?  request.getParameter("bairro0") : "";  
            
    String estado = Formatter.formatString(request.getParameter("estado0"));     
      
    String pais = Formatter.formatString(request.getParameter("pais0"));             
    
    String observacoes = null != request.getParameter("observacoes0") 
        && !"".equals(request.getParameter("observacoes0"))
        ?  request.getParameter("observacoes0") : "";
    
    String cep = null != request.getParameter("cep0") 
        && !"".equals(request.getParameter("cep0"))
        ?  request.getParameter("cep0") : "";
        
    String cidade = Formatter.formatString(request.getParameter("cidade0"));
    
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
    
    
    // Endereço de cobrança
    Endereco endCobranca = new Endereco();
    tipoResidencia = null != request.getParameter("tipo-residencia2") 
        && !"".equals(request.getParameter("tipo-residencia2"))
        ?  request.getParameter("tipo-residencia2") : "SELECIONE";

    idTipoLogradouro = null != request.getParameter("tipo-logradouro2") 
        && !"".equals(request.getParameter("tipo-logradouro2"))
        && Formatter.isNumeric(request.getParameter("tipo-logradouro2").trim()) 
        ?  Integer.parseInt(request.getParameter("tipo-logradouro2")) : 0;
   
    logradouro = null != request.getParameter("logradouro2") ? 
        request.getParameter("logradouro2") : "";

    numero = null != request.getParameter("numero2") 
        && !"".equals(request.getParameter("numero2"))
        && Formatter.isNumeric(request.getParameter("numero2").trim()) 
        ?  Integer.parseInt(request.getParameter("numero2")) : 0;
        
    bairro = null != request.getParameter("bairro2") 
            && !"".equals(request.getParameter("bairro2"))
            ?  request.getParameter("bairro2") : "";  
            
    estado = Formatter.formatString(request.getParameter("estado2"));     
      
    pais = Formatter.formatString(request.getParameter("pais2") );             
    
    observacoes = null != request.getParameter("observacoes2") 
        && !"".equals(request.getParameter("observacoes2"))
        ?  request.getParameter("observacoes2") : "";
    
    cep = null != request.getParameter("cep2") 
        && !"".equals(request.getParameter("cep2"))
        ?  request.getParameter("cep2") : "";
        
    cidade = Formatter.formatString(request.getParameter("cidade2"));
    
    tipoLogradouro = new TipoLogradouro();
    tipoLogradouro.setId(idTipoLogradouro);              
    
    endCobranca.setBairro(bairro);
    endCobranca.setCep(cep);
    endCobranca.setCidade(cidade);
    endCobranca.setEstado(estado);
    endCobranca.setLogradouro(logradouro);
    endCobranca.setNumero(numero);
    endCobranca.setObservacao(observacoes);
    endCobranca.setPais(pais);
    endCobranca.setTipoLogradouro(tipoLogradouro);
    endCobranca.setTipoResidencia(TipoResidencia.valueOf(tipoResidencia));
    
    // Endereço de entrega
    Endereco endEntrega = new Endereco();
    tipoResidencia = null != request.getParameter("tipo-residencia1") 
        && !"".equals(request.getParameter("tipo-residencia1"))
        ?  request.getParameter("tipo-residencia1") : "SELECIONE";

    idTipoLogradouro = null != request.getParameter("tipo-logradouro1") 
        && !"".equals(request.getParameter("tipo-logradouro1"))
        && Formatter.isNumeric(request.getParameter("tipo-logradouro1").trim()) 
        ?  Integer.parseInt(request.getParameter("tipo-logradouro1")) : 0;
   
    logradouro = null != request.getParameter("logradouro2") ? 
        request.getParameter("logradouro1") : "";

    numero = null != request.getParameter("numero1") 
        && !"".equals(request.getParameter("numero1"))
        && Formatter.isNumeric(request.getParameter("numero1").trim()) 
        ?  Integer.parseInt(request.getParameter("numero1")) : 0;
        
    bairro = null != request.getParameter("bairro1") 
            && !"".equals(request.getParameter("bairro1"))
            ?  request.getParameter("bairro1") : "";  
            
    estado = Formatter.formatString(request.getParameter("estado1") );     
      
    pais = Formatter.formatString(request.getParameter("pais1"));             
    
    observacoes = null != request.getParameter("observacoes1") 
        && !"".equals(request.getParameter("observacoes1"))
        ?  request.getParameter("observacoes1") : "";
    
    cep = null != request.getParameter("cep1") 
        && !"".equals(request.getParameter("cep1"))
        ?  request.getParameter("cep1") : "";
        
    cidade = Formatter.formatString(request.getParameter("cidade1"));
    
    tipoLogradouro = new TipoLogradouro();
    tipoLogradouro.setId(idTipoLogradouro);              
    
    endEntrega.setBairro(bairro);
    endEntrega.setCep(cep);
    endEntrega.setCidade(cidade);
    endEntrega.setEstado(estado);
    endEntrega.setLogradouro(logradouro);
    endEntrega.setNumero(numero);
    endEntrega.setObservacao(observacoes);
    endEntrega.setPais(pais);
    endEntrega.setTipoLogradouro(tipoLogradouro);
    endEntrega.setTipoResidencia(TipoResidencia.valueOf(tipoResidencia));      
    
    enderecos.add(0, endereco);
    enderecos.add(1, endEntrega);
    enderecos.add(2, endCobranca);
    
    enderecos.get(0).setTipoEndereco("RESIDENCIAL");
    enderecos.get(1).setTipoEndereco("ENTREGA");
    enderecos.get(2).setTipoEndereco("COBRANÇA");
    
    enderecos.get(0).setDescricao("ENDEREÇO RESIDENCIAL");
    enderecos.get(1).setDescricao("ENDEREÇO ENTREGA");
    enderecos.get(2).setDescricao("ENDEREÇO COBRANÇA");
    
    String cepEntrega = Formatter.formatString(request.getParameter("cep1").trim().replace("-", ""));
    
    endereco.setCep(cepEntrega);
    
    return endereco;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {

    String frete = resultado.getMensagem();
    String operacao = request.getParameter("operacao");
    String formName = request.getParameter("formName");
    try {
      request.getSession().removeAttribute("frete");
      if(operacao.equals(null)) {
        request.getSession().removeAttribute("frete");
        response.sendRedirect("carrinho.jsp");
      }
      if(operacao.equals("CALCULARFRETE")) {
        request.getSession().setAttribute("frete", frete);
        if(formName.equals("checkout")) {
          response.sendRedirect("checkout.jsp");
        } else {
          response.sendRedirect("carrinho.jsp");
        }             
      }

    } catch (Exception e) {
      // TODO: handle exception
    }
    
  }

}
