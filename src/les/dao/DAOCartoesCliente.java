package les.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dominio.Cliente;
import dominio.EntidadeDominio;
import util.Resultado;

public class DAOCartoesCliente extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Cliente cliente = (Cliente) entidade;
    
    String sql = "INSERT INTO cartoes_cliente VALUES(?,?,?)";
    
    try {
      
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1,cliente.getId().intValue());
      pst.setInt(2, cliente.getCartao().getId().intValue());
      pst.setBoolean(3, cliente.getCartao().isPreferencial());
      
      pst.execute();
      
      resultado.setResultado(cliente);
      resultado.sucesso("Salvo com sucesso: cartoes_cliente");
    } catch (Exception e) {
      resultado.erro("Erro ao salvar: cartoes_cliente");
    } 
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
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
