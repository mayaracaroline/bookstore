package les.negocio;

import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.UnableToInterruptJobException;

import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import les.dao.DAOEstoque;
import les.dao.DAOItensPedido;
import les.dao.DAOPedidoDeCompra;
import util.Resultado;

public class StAprovarOuReprovarCompra implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    Random random = new Random();
    Integer analisarPedido = random.nextInt(10);
    DAOPedidoDeCompra daoPedido = new DAOPedidoDeCompra();
    if(analisarPedido % 2 == 0) {
      
      daoPedido.aprovarCompra(); 
      System.out.println("Aprovar");
    } else {
      
      DAOItensPedido daoItens = new DAOItensPedido();
      Resultado resultado = daoItens.consultarPedidosEmProcessamento();
      DAOEstoque daoEstoque = new DAOEstoque();
      
      // Devolve os itens reprovados ao estoque
      for(EntidadeDominio it : resultado.getListaResultado()) {
        ItemCarrinho item = (ItemCarrinho) it;
        daoEstoque.adicionarAoEstoque(item);
      }
      
      daoPedido.reprovarCompra();
      System.out.println("Reprovar");
    }
    
    try {
      context.getScheduler().shutdown();
    } catch (SchedulerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

}
