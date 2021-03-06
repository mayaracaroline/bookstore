package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominio.EntidadeDominio;
import dominio.TipoLogradouro;
import util.ConnectionFactory;
import util.Resultado;

public class DAOTipoLogradouro extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    TipoLogradouro tipoLogradouro = (TipoLogradouro) entidade;
    String sql = "SELECT * FROM tipo_logradouro WHERE tpl_id = ?";
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, tipoLogradouro.getId().intValue());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        tipoLogradouro.setTipo(rs.getString("tpl_nome"));
      }

      resultado.setResultado(tipoLogradouro);
      resultado.sucesso("Consulta realizada com sucesso");
    } catch (Exception e) {
      resultado.erro("Erro ao consultar tipo de logradouro");
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultarPorId(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

}
