package les.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dominio.Cartao;
import dominio.Cliente;
import dominio.Cupom;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.TipoCupom;
import dominio.TipoLogradouro;
import dominio.Usuario;
import util.Resultado;

public class DAOUsuario extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Usuario usuario = (Usuario) entidade;

    String sql = "SELECT cli_id FROM clientes WHERE cli_email = ?";
    HashMap<String, ArrayList<EntidadeDominio>> mapUsuario = new HashMap<>();
    ArrayList<EntidadeDominio> enderecos = new ArrayList<>();
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setString(1, usuario.getUsername());
      ResultSet rs = pst.executeQuery();
      int idCliente = -1;
      
      while(rs.next()) {
        idCliente = rs.getInt("cli_id");
      }
      
      Cliente cliente = new Cliente();
      cliente.setId(idCliente);
      
      DAOClientes_Endereco DAOClientesEndereco = new DAOClientes_Endereco();
      Resultado resultadoDAOEndCliente = DAOClientesEndereco.consultar(cliente);
      enderecos = (ArrayList<EntidadeDominio>) resultadoDAOEndCliente.getListaResultado();
     
      DAOEndereco  daoEndereco = new DAOEndereco();  
      
      for (int i = 0; i < enderecos.size(); i++) {
        Endereco novoEndereco = new Endereco();
        Endereco end = new Endereco();
        TipoLogradouro tpl = new TipoLogradouro ();
        end.setTipoLogradouro(tpl);
        end = (Endereco) enderecos.get(i);
        // Monta um novo endereco de acordo com o id 
        novoEndereco = (Endereco) daoEndereco
            .consultar(end)
            .getResultado();
        enderecos.set(i, novoEndereco);     
      }
      
      DAOCartoesCliente daoCartoes = new DAOCartoesCliente();
      Resultado resultadoCli = daoCartoes.consultar(cliente);
      ArrayList<EntidadeDominio> cartoes = new ArrayList<>();
      DAOCartao daoCartao = new DAOCartao();
      for (int i = 0; i < resultadoCli.getListaResultado().size(); i++) {
        Cartao umCartao = new Cartao();
        Cartao novoCartao = new Cartao();
        umCartao = (Cartao) resultadoCli.getListaResultado().get(i);
        novoCartao = (Cartao) daoCartao
            .consultar(umCartao)
            .getResultado();
        cartoes.add(novoCartao);        
      }
      
      DAOCupom daoCupom = new DAOCupom();
      Resultado resultadoCupom = daoCupom.consultar(cliente);
      List<EntidadeDominio> cupons = resultadoCupom.getListaResultado();
      ArrayList<EntidadeDominio> cuponsTroca = new ArrayList<>();
      ArrayList<EntidadeDominio> cuponsPromocionais = new ArrayList<>();
      
      for(int i = 0; i < cupons.size(); i++) {
        Cupom cupom = (Cupom) cupons.get(i);
        
        if (cupom.getTipo().equals(TipoCupom.PROMOCIONAL)) {
          cuponsPromocionais.add(cupom);
        } else if (cupom.getTipo().equals(TipoCupom.TROCA)) {
          cuponsTroca.add(cupom);
        }        
      }
      
      pst.close();
      mapUsuario.put("ENDERECO", enderecos);
      mapUsuario.put("CARTAO", cartoes);
      mapUsuario.put("CUPOMPROMOCIONAL", cuponsPromocionais);
      mapUsuario.put("CUPOMTROCA", cuponsTroca);
      
      resultado.setContagem(1);
      resultado.setMapResultado(mapUsuario);
      resultado.setResultado(cliente);
      System.out.println(cliente.getId().intValue());
      resultado.sucesso("SUCESSO");
                 
    } catch (Exception e) {
      resultado.erro("Erro ao autenticar usuário");
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
  
  public boolean buscarUsuario (EntidadeDominio entidade) {
    Usuario usuario = (Usuario) entidade;
    String sql = "SELECT cli_email, cli_senha FROM clientes  WHERE  cli_email = ?";
    
    try {
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setString(1, usuario.getUsername());
      
      ResultSet rs = pst.executeQuery();
      
      if(!rs.isBeforeFirst()) {
        return false;
      } 
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return true;
    
  }
  
  public Usuario buscarUsuarioEsenha(EntidadeDominio entidade) {
    
    Usuario usuario = (Usuario) entidade;
    Usuario novoUsuario = new Usuario();
    
    String sql = "SELECT cli_email, cli_senha FROM clientes  WHERE  cli_email = ?";
    
    try {
      
      PreparedStatement pst = conexao.prepareStatement(sql);
      pst.setString(1, usuario.getUsername());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {      
        novoUsuario.setUsername(rs.getString("cli_email"));
        novoUsuario.setPassword(rs.getString("cli_senha"));
      }
      
    } catch (Exception e) {
      
      e.printStackTrace();
    }
        
    return novoUsuario;
  }
  
}
