package les.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.Cartao;
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
    Resultado resultado = new Resultado();
    ArrayList<EntidadeDominio> cartoes = new ArrayList<EntidadeDominio>();
    Cliente cliente = (Cliente) entidade;
    String sql = "SELECT * FROM cartoes_cliente WHERE ctc_cli_id = ?";
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setInt(1, cliente.getId().intValue());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        Cartao cartaoEncontrado = new Cartao();
        cartaoEncontrado.setId(rs.getInt("ctc_car_id"));
        DAOCartao daoCartao = new DAOCartao();
        
        Resultado resCartao = daoCartao.consultar(cartaoEncontrado);
        cartaoEncontrado = (Cartao) resCartao.getResultado();
        
        cartoes.add((Cartao)cartaoEncontrado);
      }
      
      resultado.setListaResultado(cartoes);
      resultado.sucesso("Lista de cartões");
      
    } catch (Exception e) {
      resultado.sucesso("Erro ao consultar em DAOCartoesCliente");
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
