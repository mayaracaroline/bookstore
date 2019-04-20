package les.negocio;




import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dominio.Bloqueio;
import dominio.Carrinho;

public class StValidarItensCarrinhoComTempoExpirado implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    ServletContext servletContext = (ServletContext) context.getMergedJobDataMap().get("servletContext");
    

    Map<String, Bloqueio> produtosBloqueados = 
        (Map<String, Bloqueio>) servletContext.getAttribute("bloqueio");
 

    for(Map.Entry<String, Bloqueio> entry : produtosBloqueados.entrySet()) {
      Bloqueio bloqueioCarrinho = entry.getValue();
      Carrinho carrinho = bloqueioCarrinho.getCarrinho();
      LocalDateTime horarioAtual = LocalDateTime.now();
      LocalDateTime horarioBloqueio = bloqueioCarrinho.getHorarioBloqueio();
      long diferenca = Duration.between(horarioBloqueio, horarioAtual ).getSeconds();
      if(diferenca > 15 && carrinho.isStatus()) {        
        System.out.println("Retirando do carrinho ");
        carrinho.setStatus(false);
        System.out.println(diferenca);        
      }
      
    }
    
    

    
  }

 
}
