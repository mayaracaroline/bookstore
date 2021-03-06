package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import util.ConnectionFactory;
import util.Resultado;

public class DAOClientes_Endereco extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Cliente cliente = (Cliente) entidade; 
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    String sql = "INSERT INTO clientes_endereco VALUES(?, ?, ?);";
    
    try {
      
      if(cliente.getEnderecoResidencial() != null) {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, cliente.getId().intValue());
        pst.setInt(3, cliente.getEnderecoResidencial().getId().intValue());
        pst.setString(2, cliente.getEnderecoResidencial().getTipoEndereco());
        pst.execute();
        pst.close();
  
      }
      
      if(cliente.getEnderecoEntrega() != null) {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, cliente.getId().intValue());
        pst.setInt(3, cliente.getEnderecoEntrega().getId().intValue());
        pst.setString(2, cliente.getEnderecoEntrega().getTipoEndereco());
        pst.execute(); 
        pst.close();
      }
      
      if(cliente.getEnderecoCobranca() != null) {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, cliente.getId().intValue());
        pst.setInt(3, cliente.getEnderecoCobranca().getId().intValue());
        pst.setString(2, cliente.getEnderecoCobranca().getTipoEndereco());
        pst.execute(); 
      }     
      
      resultado.setResultado(cliente);
      resultado.sucesso("Salvo com sucesso: CLIENTES_ENDERECO");           
      
    } catch (Exception e) {
      resultado.erro("Erro salvar: CLIENTES_ENDERECO");
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(pst, conexao);
    }
        
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Cliente cliente = (Cliente) entidade;
    Resultado resultado = new Resultado();
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    
    String sql = "SELECT * from clientes_endereco WHERE cle_cli_id = ? ";
    
    try {
      
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, cliente.getId().intValue());
      
      ResultSet rs = pst.executeQuery();
      List<EntidadeDominio> enderecos = new ArrayList<>();
      while(rs.next()) {
        Endereco endereco = new Endereco();
        
        endereco.setId(rs.getInt("cle_end_id"));        
        enderecos.add(endereco); 
        
      }
      
      resultado.setListaResultado(enderecos);
      resultado.sucesso("Consulta realizada com sucesso: clientes_endereco");
      
    } catch (Exception e) {
      resultado.erro("Erro ao consultar: clientes_endereco");
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
