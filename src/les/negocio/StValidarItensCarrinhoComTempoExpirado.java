package les.negocio;




import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.ServletContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.Livro;

public class StValidarItensCarrinhoComTempoExpirado implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    ServletContext servletContext = (ServletContext) context.getMergedJobDataMap().get("servletContext");
    

    Map<String, Bloqueio> produtosBloqueados = 
        (Map<String, Bloqueio>) servletContext.getAttribute("bloqueio");
    
    Map<String, Carrinho> produtosDesbloqueados = 
        (Map<String, Carrinho>) servletContext.getAttribute("desbloqueio"); 

    for(Map.Entry<String, Bloqueio> entry : produtosBloqueados.entrySet()) {
      Bloqueio bloqueioCarrinho = entry.getValue();
      Carrinho carrinho = bloqueioCarrinho.getCarrinho();
      LocalDateTime horarioAtual = LocalDateTime.now();
      LocalDateTime horarioBloqueio = bloqueioCarrinho.getHorarioBloqueio();
      long diferenca = Duration.between(horarioBloqueio, horarioAtual ).getSeconds();
      if(diferenca > 15 ) {        
        System.out.println("Retirando do carrinho ");
        produtosBloqueados.remove(entry.getKey());
        entry.getValue().getSessao().removeAttribute("carrinho");
        produtosDesbloqueados.put(entry.getValue().getSessao().getId(), entry.getValue().getCarrinho());
        Livro livro = (Livro) produtosDesbloqueados.get(entry.getValue()
            .getSessao().getId()).getItensCarrinho()
            .get(0).getProduto();
        
        System.out.println("O livro: " + livro.getTitulo() + " foi retirado do carrinho, "
            + "pois excedeu o tempo de " + diferenca + " segundos e a compra não foi efetivada. ");
     
      }
      
    }
    
    

    
  }

 
}
