package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cidade;
import dominio.EntidadeDominio;
import dominio.Pais;
import util.Resultado;

public class DAOPais extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Pais pais = (Pais) entidade;
    List<EntidadeDominio> paises = new ArrayList<>();
    int contagem = 0;
    
    String sql ="";
    PreparedStatement preparedStatement;
    ResultSet rs;
    Pais pais2 = new Pais();
    if(pais.getId().equals(BigInteger.ZERO)) {
      sql = "SELECT * FROM pais"; 
      
      try {
        
        preparedStatement = conexao.prepareStatement(sql);       
        rs = preparedStatement.executeQuery();
        
        while(rs.next()) {
          
          pais2.setId(rs.getInt("pais_id"));
          pais2.setNome(rs.getString("pai_pais"));
          
          paises.add(pais2);
          contagem++;
          
        }
        
      } catch (Exception e) {
        resultado.erro("Erro ao realizar consulta de Cidades");
        e.printStackTrace();
      } finally {      
        try {
          conexao.close();
        } catch (SQLException e) {
          // LOGGING
          e.printStackTrace();
        }
      }
      
    } else {
      sql = "SELECT * FROM pais WHERE pai_id = ?;";

      try {
        
        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, pais.getId().intValue());
        rs = preparedStatement.executeQuery();
        
        while(rs.next()) {
          
          pais2.setId(rs.getInt("pai_id"));
          pais2.setNome(rs.getString("pai_pais"));
          contagem++;
          
        }
              
      } catch (Exception e) {
        resultado.erro("Erro ao realizar consulta");
        e.printStackTrace();
      }
      
      
    }
    
    if (paises.isEmpty()) {
      resultado.setResultado(pais2);
    } else {
      resultado.setListaResultado(paises);      
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
