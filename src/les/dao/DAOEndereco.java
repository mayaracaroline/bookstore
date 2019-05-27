package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Pais;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;
import util.ConnectionFactory;
import util.Resultado;

public class DAOEndereco extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Endereco endereco = (Endereco) entidade;
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    String sql = "INSERT INTO enderecos(end_logradouro,end_bairro, end_cep, end_descricao, end_observacao, end_tipo_residencia, end_tipo, end_tpl_id, end_cidade, end_estado, end_status,end_numero, end_pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?);";
    
    try {
      
      pst = conexao.prepareStatement(sql);
      pst.setString(1, endereco.getLogradouro());
      pst.setString(2, endereco.getBairro());
      pst.setString(3, endereco.getCep());
      pst.setString(4, endereco.getDescricao());
      pst.setString(5, endereco.getObservacao());
      pst.setString(6, endereco.getTipoResidencia().toString());
      pst.setString(7, endereco.getTipoEndereco());
      pst.setInt(8, endereco.getTipoLogradouro().getId().intValue());
      pst.setString(9, endereco.getCidade());
      pst.setString(10, endereco.getEstado());
      pst.setBoolean(11, true);
      pst.setInt(12, endereco.getNumero());
      pst.setString(13,endereco.getPais());
      
      pst.execute();
      
      
      ResultSet rs = pst.getGeneratedKeys();
      BigInteger id = BigInteger.ZERO;
      
      while(rs.next()) {
        id = rs.getBigDecimal(1).toBigInteger();
      }
      
      endereco.setId(id.intValue());
      
      resultado.setResultado(endereco);
      resultado.sucesso("Endereço salvo com sucesso");
            
      
    } catch (Exception e) {
      resultado.erro("Erro ao salvar endereço");
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
    
    
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Endereco endereco = (Endereco) entidade;
    Resultado resultado = new Resultado();
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    String sql = "SELECT * FROM enderecos WHERE end_id = ? AND end_status = true";
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, endereco.getId().intValue());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        endereco.setBairro(rs.getString("end_bairro"));
        endereco.setCep(rs.getString("end_cep"));    
        endereco.setCidade(rs.getString("end_cidade"));
        endereco.setDescricao(rs.getString("end_descricao"));
        endereco.setEstado(rs.getString("end_estado"));
        endereco.setLogradouro(rs.getString("end_logradouro"));
        endereco.setNumero(rs.getInt("end_numero")); 
        endereco.setObservacao(rs.getString("end_observacao"));
        
        endereco.setPais(rs.getString("end_pais"));
        endereco.setTipoEndereco(rs.getString("end_tipo"));
        
        int a = rs.getInt("end_tpl_id");
        TipoLogradouro tpl = new TipoLogradouro ();
        tpl.setId(a);
        endereco.setTipoLogradouro(tpl);
        DAOTipoLogradouro DAOtipoLogradouro = new DAOTipoLogradouro();
        Resultado res = DAOtipoLogradouro.consultar(endereco.getTipoLogradouro());
        TipoLogradouro tpTipoLogradouro = (TipoLogradouro) res.getResultado();
        TipoLogradouro tipoLogradouro = new TipoLogradouro();
        tipoLogradouro.setId(tpTipoLogradouro.getId().intValue());
        tipoLogradouro.setTipo(tpTipoLogradouro.getTipo());
        endereco.setTipoLogradouro(tipoLogradouro);
        endereco.setTipoResidencia(TipoResidencia.valueOf(rs.getString("end_tipo_residencia")));

      }
      
      resultado.setResultado(endereco);
      resultado.sucesso("Consulta realizada com sucesso");
      
    } catch (Exception e) {
      resultado.erro("Erro ao consultar endereço");
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

}
