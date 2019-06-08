package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cartao;
import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.Pagamento;
import dominio.PedidoDeCompra;
import util.ConnectionFactory;
import util.Resultado;

public class DAOPagamentosCartao extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    String sql = "INSERT INTO pagamentos_cartao (pca_ped_id, pca_car_id, pca_valor, pca_quantidade_parcelas )VALUES (?, ?, ?, ?)";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Resultado resultado = new Resultado();
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    try {
      pst = conexao.prepareStatement(sql);
      
      List<EntidadeDominio> cartoes = new ArrayList<EntidadeDominio>();
      
      for(Pagamento pagamento : pedido.getPagamento()) {    
        Cartao cartao = (Cartao) pagamento.getFormaDePagamento();
        
        if(!cartao.getId().equals(BigInteger.ZERO)) {
          pst.setInt(1, pedido.getId().intValue());
          pst.setInt(2, cartao.getId().intValue());
          pst.setDouble(3, pagamento.getValor());
          pst.setInt(4, pagamento.getQuantidadeParcelas());
          
          pst.execute();
          
          cartoes.add(cartao);
          
        }

      }
        
      resultado.sucesso("Sucesso");
      resultado.setListaResultado(cartoes);

    } catch (SQLException e) {
      resultado.erro("Falha ao salvar pagamento com cartoes");
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
        
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado alterar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado excluir(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado inativar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultarPorId(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

}
