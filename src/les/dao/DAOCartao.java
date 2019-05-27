package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominio.Bandeira;
import dominio.Cartao;
import dominio.EntidadeDominio;
import util.ConnectionFactory;
import util.Resultado;

public class DAOCartao extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Cartao cartao = (Cartao) entidade;
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    String sql = "INSERT INTO cartoes (car_nome_titular, car_numero, car_ban_id, car_cod_seguranca, car_preferencial) VALUES (?, ?, ?, ?, ?)";
    
    try {
      
      pst = conexao.prepareStatement(sql);
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
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
    
    return resultado;
  } 

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Cartao cartao = (Cartao) entidade;
    Resultado resultado = new Resultado();
    String sql = "SELECT * FROM cartoes WHERE car_id = ?";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, cartao.getId().intValue());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        cartao.setCodSeguranca(rs.getInt("car_cod_seguranca"));
        Bandeira bandeira = new Bandeira();
        bandeira.setId(rs.getInt("car_ban_id"));
        cartao.setBandeira(bandeira);
        cartao.setNomeTitular(rs.getString("car_nome_titular"));
        cartao.setNumero(rs.getString("car_numero"));
        cartao.setPreferencial(rs.getBoolean("car_preferencial"));
              
      }
      
      //Complementar dados bandeira
      String sql1 = "SELECT * FROM bandeiras WHERE ban_id = ?";
      PreparedStatement pst1 = conexao.prepareStatement(sql1);
      pst1.setInt(1, cartao.getBandeira().getId().intValue());
      ResultSet rs1 = pst1.executeQuery();
      
      while(rs1.next()) {
        String a = rs1.getString("ban_nome");
        cartao.getBandeira().setNome(a); 
      }

      pst1.close();
      resultado.sucesso("Cartão encontrado");
      resultado.setResultado(cartao);
      
    } catch (Exception e) {
      resultado.erro("Cartão não encontrado");
      e.printStackTrace();
    }  finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
    
    return resultado;
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
