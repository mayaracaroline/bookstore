package les.negocio;

import java.math.BigInteger;
import java.time.LocalDate;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import dominio.TipoCupom;
import les.dao.DAOCupom;
import sun.misc.UUEncoder;

public class StValidarValorExcendenteAoPagamento implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
      double valorTotalAbater = 0;
      DAOCupom daoCupom = new DAOCupom();
      String mensagem = "";
      
      for(int i =0; i< pedido.getCuponsTroca().size() ; i++ ) {
        valorTotalAbater += pedido.getCuponsTroca().get(i).getValor();

      }
      
      double valorCartao1 = pedido.getPagamento().get(0).getValor();
      double valorCartao2 = pedido.getPagamento().get(1).getValor();
      
      
      if(!pedido.getCupomPromocional().getId().equals(BigInteger.ZERO)) {
        valorTotalAbater += pedido.getCupomPromocional().getValor();
      }
      
      valorTotalAbater += valorCartao1 + valorCartao2;
      
      double totalAPagar = pedido.getValorTotal() + pedido.getFrete();
      double diferencaCupons = totalAPagar - valorTotalAbater;
     
      if (diferencaCupons < 0) {
        //gerar outro com diferença
        Cupom novoCupom = new Cupom();
        novoCupom.setCodigo(new UUEncoder().toString().substring(0,16));
        novoCupom.setDataDeValidade(LocalDate.now().plusMonths(3));
        novoCupom.setIdCliente(pedido.getIdCliente().intValue());
        novoCupom.setTipo(TipoCupom.TROCA);
        novoCupom.setStatus(true);
        novoCupom.setValor(diferencaCupons * - 1);
        daoCupom.salvar(entidade);       
      }
      System.out.println("StValidarValorExcedente");
      
    return mensagem;
  }

}
