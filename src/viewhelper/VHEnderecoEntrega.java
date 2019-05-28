package viewhelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dominio.DadosEntrega;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;
import util.Formatter;
import util.Resultado;

public class VHEnderecoEntrega implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    Endereco endEntrega = new Endereco();
             
    String tipoResidencia = null != request.getParameter("tipo-residencia1") 
        && !"".equals(request.getParameter("tipo-residencia1"))
        ?  request.getParameter("tipo-residencia1") : "SELECIONE";

    int idTipoLogradouro = null != request.getParameter("tipo-logradouro1") 
        && !"".equals(request.getParameter("tipo-logradouro1"))
        && Formatter.isNumeric(request.getParameter("tipo-logradouro1").trim()) 
        ?  Integer.parseInt(request.getParameter("tipo-logradouro1")) : 0;
   
    String logradouro = null != request.getParameter("logradouro1") ? 
        request.getParameter("logradouro1") : "";

    int numero = null != request.getParameter("numero1") 
        && !"".equals(request.getParameter("numero1"))
        && Formatter.isNumeric(request.getParameter("numero1").trim()) 
        ?  Integer.parseInt(request.getParameter("numero1")) : 0;
        
   String bairro = null != request.getParameter("bairro1") 
            && !"".equals(request.getParameter("bairro1"))
            ?  request.getParameter("bairro1") : "";  
            
   String estado = Formatter.formatString(request.getParameter("estado1") );     
      
   String pais = Formatter.formatString(request.getParameter("pais1"));             
    
   String observacoes = null != request.getParameter("observacoes1") 
        && !"".equals(request.getParameter("observacoes1"))
        ?  request.getParameter("observacoes1") : "";
        
   String cep = Formatter.formatString(request.getParameter("cep1").trim().replace("-", ""));
        
   String cidade = Formatter.formatString(request.getParameter("cidade1"));
    
   TipoLogradouro tipoLogradouro = new TipoLogradouro();
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
        
    return endEntrega;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    String frete = resultado.getMensagem();
    String operacao = request.getParameter("operacao");
    String formName = request.getParameter("formName");
    
    DadosEntrega dadosEntrega = new DadosEntrega();
    dadosEntrega = (DadosEntrega) resultado.getResultado();
    Gson gson = new Gson();
    
    String JSONDadosEntrega = gson.toJson(dadosEntrega);    

    try {
      
      if(operacao.equals(null)) {
        request.getSession().removeAttribute("frete");
        response.sendRedirect("carrinho.jsp");
      }
      if(operacao.equals("CALCULARFRETE")) {
        
        request.getSession().removeAttribute("frete");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONDadosEntrega);
        
      }

    } catch (Exception e) {
      // TODO: handle exception
    }
    
  }

}
