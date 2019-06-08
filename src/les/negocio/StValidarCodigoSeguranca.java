package les.negocio;

import java.math.BigInteger;

import dominio.Cartao;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import les.dao.DAOCartao;

public class StValidarCodigoSeguranca implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    StringBuilder mensagem = new StringBuilder();
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Cartao novoCartao1 = new Cartao();
    Cartao novoCartao2 = new Cartao();
    Cartao cartao1 = (Cartao) pedido.getPagamento().get(0).getFormaDePagamento();
    Cartao cartao2 = (Cartao) pedido.getPagamento().get(1).getFormaDePagamento();
    
    DAOCartao daoCartao = new DAOCartao();

    if(!cartao1.getId().equals(BigInteger.ZERO)) {
      novoCartao1.setId(cartao1.getId().intValue());
      daoCartao.consultar(novoCartao1);
      
      if(cartao1.getCodSeguranca() != novoCartao1.getCodSeguranca()) {
        System.out.println(cartao1.getCodSeguranca() + " " + novoCartao1.getCodSeguranca());
        mensagem.append("Código de segurança do cartão 1 não é correspondente ao cadastrado\n");
      }
    }
    
    if(!cartao2.getId().equals(BigInteger.ZERO)) {
      novoCartao2.setId(cartao2.getId().intValue());
      daoCartao.consultar(novoCartao2);
      
      if(cartao2.getCodSeguranca() != novoCartao2.getCodSeguranca()) {
        mensagem.append("Código de segurança do cartão 2 não é correspondente ao cadastrado\n");
      }
    }
      
    return mensagem.toString();
  }

}
