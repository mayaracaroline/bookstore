package les.negocio;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import dominio.TipoCupom;
import les.dao.DAOCupom;
import sun.misc.UUEncoder;
import util.Resultado;

public class StComplementarCupom implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    
    String mensagem = "";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Cupom cupomPromocional = pedido.getCupomPromocional();
    DAOCupom daoCupom = new DAOCupom();
    
    // Complementa cupom promocional (busca por id)
    if(!pedido.getCupomPromocional().getId().equals(BigInteger.ZERO)) {
      Resultado resCupom = daoCupom.consultar(pedido.getCupomPromocional());
      cupomPromocional = (Cupom) resCupom.getListaResultado().get(0);      
    }
    
    System.out.println("StComplementar: "+pedido.getCuponsTroca().size());
    
    //Complementa lista de pedido de trocas
    ArrayList<Cupom> cuponsTrocaSelecionados = new ArrayList<>();
    for (int i = 0; i < pedido.getCuponsTroca().size(); i++) {
      Resultado resCupomTroca = daoCupom.consultarPorId(pedido.getCuponsTroca().get(i));
      Cupom cupomTroca = new Cupom();
      cupomTroca = (Cupom) resCupomTroca.getResultado();
      cuponsTrocaSelecionados.add(cupomTroca);

    }
    
    pedido.setCupomPromocional(cupomPromocional);
    pedido.setCuponsTroca(cuponsTrocaSelecionados);
    
    return mensagem;
  }
  

}
