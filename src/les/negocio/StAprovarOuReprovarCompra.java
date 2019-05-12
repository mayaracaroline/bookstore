package les.negocio;

import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class StAprovarOuReprovarCompra implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    Random random = new Random();
    Integer analisarPedido = random.nextInt(10);
    
    if(analisarPedido % 2 == 0) {
      //aprovar pedido
    } else {
      //reprovar pedido
    }
    
  }

}
