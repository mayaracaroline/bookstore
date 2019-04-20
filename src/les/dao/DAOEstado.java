package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Pais;
import util.Resultado;

public class DAOEstado extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Estado estado = (Estado) entidade;
    List<EntidadeDominio> estados = new ArrayList<>();
    int contagem = 0;
    
    String sql ="";
    PreparedStatement preparedStatement;
    ResultSet rs;
    Estado estadoEncontrado = new Estado();
    
    if(estado.getId().equals(BigInteger.ZERO)) {
      sql = "SELECT * FROM estado"; 
      
      try {
        
        preparedStatement = conexao.prepareStatement(sql);  
        rs = preparedStatement.executeQuery();
        
        while(rs.next()) {
          
          estadoEncontrado.setId(rs.getInt("est_id"));
          estadoEncontrado.setNome(rs.getString("est_nome"));
                 
          estados.add(estadoEncontrado);
          contagem++;
          
        }
        
      } catch (Exception e) {
        resultado.erro("Erro ao realizar consulta de estado");
        e.printStackTrace();
      }
      
    } else { // Busca por Id
      sql = "SELECT * FROM estado WHERE est_id = ? ;";

      try {
        
        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, estado.getId().intValue());
        rs = preparedStatement.executeQuery();
        
        while(rs.next()) {
          
          estadoEncontrado.setId(rs.getInt("est_id"));
          estadoEncontrado.setNome(rs.getString("est_nome"));
          
          contagem++;
          
        }
              
      } catch (Exception e) {
        resultado.erro("Erro ao realizar consulta");
        e.printStackTrace();
      } 
      
      
    }
    
    if (estados.isEmpty()) {
      resultado.setResultado(estadoEncontrado);
    } else {
      resultado.setListaResultado(estados);      
    }
    
    resultado.setContagem(contagem);
    resultado.sucesso("Consulta realizado com sucesso");
    
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
