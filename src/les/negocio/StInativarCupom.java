package les.negocio;

import java.math.BigInteger;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import les.dao.DAOCupom;

public class StInativarCupom implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    String mensagem = "";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    DAOCupom daoCupom = new DAOCupom();
    if(pedido.getCupomPromocional().getId().equals(BigInteger.ZERO)) {
      daoCupom.inativar(pedido.getCupomPromocional());
    }
    
    for (int i = 0; i < pedido.getCuponsTroca().size(); i++) {
      Cupom cupomAdesativar = pedido.getCuponsTroca().get(i);
      daoCupom.inativar(cupomAdesativar);  
    }
       
    return mensagem;
  }

}
