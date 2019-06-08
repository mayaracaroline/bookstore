package les.negocio;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.catalina.tribes.util.UUIDGenerator;

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
      LocalDate dataValidade = LocalDate.MIN;
      
      double totalAPagar = pedido.getValorTotal() + pedido.getFrete();
      double valorCartao1 = pedido.getPagamento().get(0).getValor();
      double valorCartao2 = pedido.getPagamento().get(1).getValor();
      
      double valorTotalEmCartoes = valorCartao1 + valorCartao2;
      
      if(valorTotalEmCartoes > totalAPagar) {
        return "O valor do pagamento em cartão é superior ao total da compra\n";
      }
      
      
      for(int i = 0; i< pedido.getCuponsTroca().size() ; i++ ) {
        valorTotalAbater += pedido.getCuponsTroca().get(i).getValor();
        dataValidade = dataValidade.isBefore(pedido.getCuponsTroca().get(i).getDataDeValidade())  ?
            pedido.getCuponsTroca().get(i).getDataDeValidade() :
              dataValidade;
      }
     
      
      if(!pedido.getCupomPromocional().getId().equals(BigInteger.ZERO)) {
        valorTotalAbater += pedido.getCupomPromocional().getValor();
        dataValidade = dataValidade.isBefore(pedido.getCupomPromocional().getDataDeValidade())  ?
            pedido.getCupomPromocional().getDataDeValidade() :
              dataValidade;
      }
      
      valorTotalAbater += valorCartao1 + valorCartao2;     
      
      
      double diferencaCupons = totalAPagar - valorTotalAbater;

      if (diferencaCupons < 0) {
        //gerar outro com diferença
        Cupom novoCupom = new Cupom();
        novoCupom.setCodigo(UUID.randomUUID().toString().substring(0,16));
        novoCupom.setDataDeValidade(dataValidade);
        novoCupom.setIdCliente(pedido.getIdCliente().intValue());
        novoCupom.setTipo(TipoCupom.DIFERENCA);
        novoCupom.setStatus(true);
        novoCupom.setValor(diferencaCupons * -1);
        daoCupom.salvar(novoCupom);       
      }
      
    return mensagem;
  }

}
