package les.dao;



import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.PedidoDeCompra;
import dominio.TipoCupom;
import util.ConnectionFactory;
import util.Resultado;

public class DAOPagamentoCupom extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    String sql = "INSERT INTO pagamentos_cupom(pgc_ped_id, pgc_cup_id, pgc_valor) VALUES (?, ?, ?)";
    PedidoDeCompra pedido = (PedidoDeCompra) entidade;
    Resultado resultado = new Resultado();
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    try {
      pst = conexao.prepareStatement(sql);
      
      for(Cupom cupom : pedido.getCuponsTroca()) {        
        pst.setInt(1, pedido.getId().intValue());
        pst.setInt(2, cupom.getId().intValue());
        pst.setDouble(3, cupom.getValor());
        
        pst.execute();
      }
      
      Cupom cupomPromocional = pedido.getCupomPromocional();
      
      if(!cupomPromocional.getId().equals(BigInteger.ZERO)) {
        pst.setInt(1, pedido.getId().intValue());
        pst.setInt(2, cupomPromocional.getId().intValue());
        pst.setDouble(3, cupomPromocional.getValor());
        
        pst.execute();
      }
      
      List<EntidadeDominio> cupons = new ArrayList<EntidadeDominio>();
      
      for (Cupom cupom : pedido.getCuponsTroca()) {
        cupons.add(cupom);
      }

      resultado.sucesso("Sucesso");
      resultado.setListaResultado(cupons);

    } catch (SQLException e) {
      resultado.erro("Falha ao salvar pagamento com cupons");
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
