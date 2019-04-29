package les.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import dominio.Cliente;
import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.TipoCupom;
import util.Resultado;

public class DAOCupom extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Cliente cliente = (Cliente) entidade;
    Resultado resultado = new Resultado();
    ArrayList<EntidadeDominio> cupons = new ArrayList<>();
    LocalDate hoje = LocalDate.now();
    
    String sql = "SELECT * FROM cupons WHERE cup_cli_id = 35 AND cup_status = 1  AND cup_data_validade >= (SELECT CURDATE());";
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        Cupom cupom = new Cupom();
        cupom.setId(rs.getInt("cup_id"));
        cupom.setCodigo(rs.getString("cup_codigo"));
        cupom.setTipo(TipoCupom.valueOf(rs.getString("cup_tipo")));
        cupom.setValor(rs.getDouble("cup_valor"));
        cupom.setStatus(rs.getBoolean("cup_status"));
        String data = rs.getDate("cup_data_validade").toString();
        cupom.setDataDeValidade(LocalDate.parse(data));
        cupons.add(cupom);
      }
      

      resultado.setListaResultado(cupons);
      resultado.sucesso("Consulta realizada com sucesso");
      
      
    } catch (Exception e) {
      resultado.erro("Erro ao consultar cupom");
      e.printStackTrace();
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
