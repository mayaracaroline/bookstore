package les.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.PedidoDeCompra;
import dominio.TipoCupom;

public class StGerarCupomTroca implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    ItemCarrinho item = pedido.getCarrinho().getItensCarrinho().get(0);
    List<Cupom> cuponsTroca = new ArrayList<>();
    Cupom cupom = new Cupom();
    LocalDate hoje = LocalDate.now();
    
    cupom.setValor(item.getProduto().getPreco());
    cupom.setIdCliente(pedido.getIdCliente().intValue());
    cupom.setTipo(TipoCupom.TROCA);
    cupom.setStatus(true);
    cupom.setDataDeValidade(hoje.plusMonths(12));
    cupom.setDataOcorrencia(hoje);
    
    StGerarCodigoCupom stCupom = new StGerarCodigoCupom();
    
    stCupom.processar(cupom);
    cuponsTroca.add(cupom);
    pedido.setCuponsTroca(cuponsTroca);
    
    return "";
  }

}
