package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Cidade;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Pais;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;
import util.Resultado;

public class DAOEndereco extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Endereco endereco = (Endereco) entidade;
    
    String sql = "INSERT INTO enderecos(end_logradouro,end_bairro, end_cep, end_descricao, end_observacao, end_tipo_residencia, end_tipo, end_tpl_id, end_cid_id, end_est_id, end_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
    
    try {
      
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setString(1, endereco.getLogradouro());
      pst.setString(2, endereco.getBairro());
      pst.setString(3, endereco.getCep());
      pst.setString(4, endereco.getDescricao());
      pst.setString(5, endereco.getObservacao());
      pst.setString(6, endereco.getTipoResidencia().toString());
      pst.setString(7, endereco.getTipoEndereco());
      pst.setInt(8, endereco.getTipoLogradouro().getId().intValue());
      pst.setInt(9, endereco.getCidade().getId().intValue());
      pst.setInt(10, endereco.getEstado().getId().intValue());
      pst.setBoolean(11, true);
      
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
    }
    
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Endereco endereco = (Endereco) entidade;
    Resultado resultado = new Resultado();
    
    String sql = "SELECT * FROM enderecos WHERE end_id = ? AND end_status = true";
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, endereco.getId().intValue());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        endereco.setBairro(rs.getString("end_bairro"));
        endereco.setCep(rs.getString("end_cep"));
        
        Cidade cidade = new Cidade();
        cidade.setId(rs.getInt("end_cid_id"));
        DAOCidade daoCidade = new DAOCidade();
        
        cidade = (Cidade) daoCidade.consultar(cidade).getResultado();
        
        endereco.setCidade(cidade);
        
        endereco.setDescricao(rs.getString("end_descricao"));
        endereco.setEstado(cidade.getEstado());
        endereco.setLogradouro(rs.getString("end_logradouro"));
        endereco.setNumero(93); //acrescentar campo número
        endereco.setObservacao(rs.getString("end_observacao"));
        
        Pais pais = new Pais();
        pais.setId(1); // Refatorar: associar endereco ao estado
        
        DAOPais daoPais = new DAOPais();
        
        pais = (Pais) daoPais.consultar(pais).getResultado();
        
        endereco.setPais(pais);
        endereco.setTipoEndereco(rs.getString("end_tipo"));
        
        TipoLogradouro tipoLogradouro = new TipoLogradouro(); //Criar DAO para tipo logradouro
        tipoLogradouro.setId(1);
        tipoLogradouro.setTipo("RUA");
        endereco.setTipoLogradouro(tipoLogradouro);
        endereco.setTipoResidencia(TipoResidencia.valueOf(rs.getString("end_tipo_residencia")));

      }
      
      resultado.setResultado(endereco);
      resultado.sucesso("Consulta realizada com sucesso");
      
    } catch (Exception e) {
      resultado.erro("Erro ao consultar endereço");
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
