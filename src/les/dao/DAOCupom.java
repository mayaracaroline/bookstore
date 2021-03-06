package les.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.catalina.tribes.util.UUIDGenerator;

import dominio.Cliente;
import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.TipoCupom;
import util.ConnectionFactory;
import util.Resultado;

public class DAOCupom extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Cupom cupom = (Cupom) entidade;
    String sql = "INSERT INTO cupons (cup_cli_id, cup_status, cup_codigo, cup_tipo, cup_data_validade, cup_valor) values (?,?,?,?,?,?);";
    Resultado resultado = new Resultado();
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    UUID uuid = UUID.randomUUID();
    String randomCode = uuid.toString();
    String codigoCupom = randomCode.substring(0,16).toUpperCase();
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, cupom.getIdCliente());
      pst.setBoolean(2, true);
      pst.setString(3, codigoCupom);
      pst.setString(4, cupom.getTipo().toString());
      pst.setDate(5, Date.valueOf(cupom.getDataDeValidade().toString()));
      pst.setDouble(6, cupom.getValor());
      
      pst.execute();      
      
      resultado.setResultado(cupom);
      resultado.sucesso("Cupom salvo com sucesso");
    } catch (Exception e) {
      resultado.erro("Falhar ao salvar cupom");
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
    
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Cupom cupom;
    Cliente cliente;
    Resultado resultado = new Resultado();
    ArrayList<EntidadeDominio> cupons = new ArrayList<>();
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    String sql = "SELECT * FROM cupons WHERE cup_cli_id = ? AND cup_status = 1  AND cup_data_validade >= (SELECT CURDATE());";
    
    try {
      pst = conexao.prepareStatement(sql);
      
      if (entidade instanceof Cliente ) {
        cliente = (Cliente) entidade;
        pst.setInt(1, cliente.getId().intValue());
      } else {
        cupom = (Cupom) entidade;
        pst.setInt(1, cupom.getIdCliente());
      }
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        Cupom novoCupom = new Cupom();
        novoCupom.setId(rs.getInt("cup_id"));
        novoCupom.setCodigo(rs.getString("cup_codigo"));
        novoCupom.setTipo(TipoCupom.valueOf(rs.getString("cup_tipo")));
        novoCupom.setValor(rs.getDouble("cup_valor"));
        novoCupom.setStatus(rs.getBoolean("cup_status"));
        String data = rs.getDate("cup_data_validade").toString();
        novoCupom.setDataDeValidade(LocalDate.parse(data));
        novoCupom.setIdCliente(rs.getInt("cup_cli_id"));
        cupons.add(novoCupom);
      }
      
      resultado.setListaResultado(cupons);
      resultado.sucesso("Consulta realizada com sucesso");
      
      
    } catch (Exception e) {
      resultado.erro("Erro ao consultar cupom");
      e.printStackTrace();
    } finally {
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
    Cupom cupom = (Cupom) entidade;
    String sql = "UPDATE cupons SET cup_status = 0 WHERE cup_id = ?;";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    Resultado resultado = new Resultado();
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, cupom.getId().intValue());
      pst.executeUpdate();
      pst.close();
      resultado.setResultado(cupom);
      resultado.sucesso("Sucesso");
    } catch (Exception e) {
      resultado.erro("Erro ao alterar cupons");
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
    
    return resultado;
  }
  
  public Resultado consultarPorId(EntidadeDominio entidade) {
    Cupom cupom;
    Resultado resultado = new Resultado();
    ArrayList<EntidadeDominio> cupons = new ArrayList<>();
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    String sql = "SELECT * FROM cupons WHERE cup_id = ?;";
    
    try {
      pst = conexao.prepareStatement(sql);
      
        cupom = (Cupom) entidade;
        pst.setInt(1, cupom.getId().intValue());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        Cupom novoCupom = new Cupom();
        novoCupom.setId(rs.getInt("cup_id"));
        novoCupom.setCodigo(rs.getString("cup_codigo"));
        novoCupom.setTipo(TipoCupom.valueOf(rs.getString("cup_tipo")));
        novoCupom.setValor(rs.getDouble("cup_valor"));
        novoCupom.setStatus(rs.getBoolean("cup_status"));
        String data = rs.getDate("cup_data_validade").toString();
        novoCupom.setDataDeValidade(LocalDate.parse(data));
        novoCupom.setIdCliente(rs.getInt("cup_cli_id"));
        cupons.add(novoCupom);
      }
      
      resultado.setResultado(cupons.get(0));
      resultado.sucesso("Consulta realizada com sucesso");
      
      
    } catch (Exception e) {
      resultado.erro("Erro ao consultar cupom");
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
    
    return resultado;
  }

}
