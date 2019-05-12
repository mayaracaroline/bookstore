package les.negocio;




import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dominio.Bloqueio;
import dominio.Carrinho;
import dominio.ItemCarrinho;
import dominio.Livro;
import dominio.Produto;

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
      if(diferenca > 300) {        
        System.out.println("Retirando do carrinho ");
        
        // Remove carrinho dos itens bloqueados
        produtosBloqueados.remove(entry.getKey());
        
        Carrinho carrinhoSessao = (Carrinho) entry.getValue().getCarrinho();
        
        // Monta um novo carrinho para colocar na lista de carrinhos desbloqueados
        Carrinho carrinhoDesbloqueado = new Carrinho();
        ArrayList<ItemCarrinho> itensDesbloqueados = new ArrayList<>();
        
        // Monta lista de carrinhos
        for( ItemCarrinho item : carrinhoSessao.getItensCarrinho()) {
          ItemCarrinho itemCarrinho = new ItemCarrinho();
          Livro produtoSessao  = (Livro) item.getProduto();
          
          Livro livro = new Livro();
          livro.setId(item.getProduto().getId().intValue());
          livro.setTitulo(produtoSessao.getTitulo());
          livro.setAtivo(false);
          
          itemCarrinho.setProduto(livro);
          itemCarrinho.setQuantidade(item.getQuantidade());
          
          itensDesbloqueados.add(itemCarrinho);
        }
        
        carrinhoDesbloqueado.setItensCarrinho(itensDesbloqueados);
        
        // Coloca o carrinho na lista de carrinhos desbloqueados
        produtosDesbloqueados.put(entry.getValue().getSessao().getId(),carrinhoDesbloqueado);
        
        // Remove carrinho da sessão do usuário
        entry.getValue().getSessao().removeAttribute("carrinho");

        Livro livro = (Livro) produtosDesbloqueados.get(entry.getValue()
            .getSessao().getId()).getItensCarrinho()
            .get(0).getProduto();
        
        System.out.println("O livro: " + livro.getTitulo() + " foi retirado do carrinho, "
            + "pois excedeu o tempo de " + diferenca + " segundos e a compra não foi efetivada. ");
     
      }
      
    }
    
    

    
  }

 
}
