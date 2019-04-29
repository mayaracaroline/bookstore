package les.negocio;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import les.dao.DAOCupom;

public class StInativarCupom implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    PedidoDeCompra pedido = new PedidoDeCompra();
    DAOCupom daoCupom = new DAOCupom();
    
    daoCupom.inativar(pedido.getCupomPromocional());
    for (int i = 0; i < pedido.getCuponsTroca().size(); i++) {
      Cupom cupomAdesativar = pedido.getCuponsTroca().get(i);
      daoCupom.inativar(cupomAdesativar);  
    }
       
    return mensagem;
  }

}
