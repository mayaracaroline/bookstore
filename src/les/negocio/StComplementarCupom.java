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
    System.out.println("StComplementarCupom");
    
    String mensagem = "";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    double valorTotalAbater = 0;
    
    //Cartão 1 e 2
    double valorCartao1 = pedido.getPagamento().get(0).getValor();
    double valorCartao2 = pedido.getPagamento().get(1).getValor();
    DAOCupom daoCupom = new DAOCupom();
    
    // Complementa cupom promocional (busca por id)
    Resultado resCupom = daoCupom.consultar(pedido.getCupomPromocional());
    Cupom cupomPromocional = (Cupom) resCupom.getListaResultado().get(0);
    
    //Complementa lista de pedido de trocas
    ArrayList<Cupom> cuponsTrocaSelecionados = new ArrayList<>();
    for (int i = 0; i < pedido.getCuponsTroca().size(); i++) {
      
      Resultado resCupomTroca = daoCupom.consultar(pedido.getCuponsTroca().get(i));
      Cupom cupomTroca = new Cupom();
      cupomTroca = (Cupom) resCupomTroca.getListaResultado().get(i);
      cuponsTrocaSelecionados.add(cupomTroca);

    }

    return mensagem;
  }
  

}
