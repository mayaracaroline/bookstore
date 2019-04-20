package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Cartao;
import dominio.EntidadeDominio;
import util.Resultado;

public class DAOCartao extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Cartao cartao = (Cartao) entidade;
    
    String sql = "INSERT INTO cartoes (car_nome_titular, car_numero, car_ban_id, car_cod_seguranca, car_preferencial) VALUES (?, ?, ?, ?, ?)";
    
    try {
      
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setString(1, cartao.getNomeTitular());
      pst.setString(2, cartao.getNumero());
      pst.setInt(3, cartao.getBandeira().getId().intValue());
      pst.setInt(4, cartao.getCodSeguranca());
      pst.setBoolean(5, cartao.isPreferencial());
      
      pst.execute();
      
      BigInteger id = BigInteger.ZERO;
      ResultSet rs = pst.getGeneratedKeys();
      
      while(rs.next()) {
        id = rs.getBigDecimal(1).toBigInteger();
      }
      
      cartao.setId(id.intValue());
      
      resultado.setResultado(cartao);
      resultado.sucesso("Cartao salvo com sucesso");
      
    } catch (Exception e) {
      resultado.erro("Erro ao cadastrar cartão");
      e.printStackTrace();
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

}
