package les.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cidade;
import dominio.EntidadeDominio;
import dominio.Estado;
import util.Resultado;

public class DAOCidade extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Cidade cidade = (Cidade) entidade;
    List<EntidadeDominio> cidades = new ArrayList<>();
    Cidade cid = new Cidade();
    int contagem = 0;
    
    String sql ="";
    ResultSet rs;
    if(cidade.getId().equals(BigInteger.ZERO)) {
      sql = "SELECT * FROM cidade"; 

      try {
        
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        
        rs = preparedStatement.executeQuery();
        
        while(rs.next()) {
          
          cid.setId(rs.getInt("cid_id"));
          cid.setNome(rs.getString("cid_nome"));
          
          
          Estado estado = new Estado();
          estado.setId(rs.getInt("cid_est_id"));
          
          DAOEstado daoEstado = new DAOEstado();
          
          Resultado resEstado = new Resultado();
          resEstado = daoEstado.consultar(estado);
          estado = (Estado) resEstado.getResultado();
                    
          cid.setEstado(estado);
          
          cidades.add(cid);
          contagem++;
   
        }
        
      } catch (Exception e) {
        resultado.erro("Erro ao realizar consulta de Cidades");
        e.printStackTrace();
      } 
      
    } else {
      sql = "SELECT * FROM cidade WHERE cid_id = ?";
      
      try {
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, cidade.getId().intValue());
        
        rs = preparedStatement.executeQuery();
        
        while(rs.next()) {
          cid.setId(rs.getInt("cid_id"));
          cid.setNome(rs.getString("cid_nome"));
          
          Estado estado = new Estado();
          estado.setId(rs.getInt("cid_est_id"));
          
          DAOEstado daoEstado = new DAOEstado();
          
          Resultado resEstado = new Resultado();
          resEstado = daoEstado.consultar(estado);
          estado = (Estado) resEstado.getResultado();
                    
          cid.setEstado(estado);       
          contagem++;  
        }
        
      } catch (Exception e) {
        resultado.erro("Erro ao consultar cidade");
        e.printStackTrace();
      } 
    }
    
    if (contagem == 1) {
      resultado.setResultado(cid);      
    } else {
      resultado.setListaResultado(cidades);
    }
    
    resultado.sucesso("Consulta realizada com sucesso");
    resultado.setContagem(contagem);
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
