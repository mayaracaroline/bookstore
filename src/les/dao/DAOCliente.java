package les.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.Cartao;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Genero;
import dominio.Telefone;
import dominio.TipoTelefone;
import dominio.Usuario;
import util.ConnectionFactory;
import util.Resultado;

public class DAOCliente extends AbstractDAO implements IDAO {

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Cliente cliente = (Cliente) entidade;
    conexao = ConnectionFactory.getConnection();
    PreparedStatement preparedStatement = null;
  
    String sql = "INSERT INTO clientes (cli_cpf, cli_genero, cli_data_nascimento, "
        + "cli_nome, cli_sobrenome, cli_telefone, cli_email, cli_senha, "
        + "cli_status, cli_data_cadastro, cli_ddd, cli_tipo_telefone) "
        + "VALUES(?, ?, ?, ?, ?, ?, ?, ? ,? , ?, ?, ?);";
    
    try {
      preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, cliente.getCpf());
      preparedStatement.setString(2, cliente.getGenero().toString());
      preparedStatement.setDate(3, Date.valueOf(cliente.getDataNascimento()));
      preparedStatement.setString(4, cliente.getNome());
      preparedStatement.setString(5, cliente.getSobrenome());
      preparedStatement.setString(6, cliente.getTelefone().getNumero());
      preparedStatement.setString(7, cliente.getUsuario().getUsername());
      preparedStatement.setString(8, cliente.getUsuario().getPassword());
      preparedStatement.setBoolean(9, true);
      preparedStatement.setDate(10, Date.valueOf(cliente.getDataCadastro()));
      preparedStatement.setString(11, cliente.getTelefone().getDdd());
      preparedStatement.setString(12, cliente.getTelefone().getTipo().toString());
      
      preparedStatement.execute();
      
      ResultSet rs = preparedStatement.getGeneratedKeys();
      BigInteger id = BigInteger.ZERO;
      
      while(rs.next()) {
        id = rs.getBigDecimal(1).toBigInteger();
      }
      
      cliente.setId(id.intValue());
      DAOEndereco daoEndereco = new DAOEndereco();
      
      if (cliente.getEnderecoResidencial() != null) {
        Resultado rsEndResidencial = daoEndereco.salvar(cliente.getEnderecoResidencial());
        Endereco endereco = (Endereco) rsEndResidencial.getResultado();
        cliente.getEnderecoResidencial().setId(endereco.getId().intValue());
      }
      
      if (cliente.getEnderecoEntrega() != null) {
        Resultado rsEndEntrega = daoEndereco.salvar(cliente.getEnderecoEntrega());
        Endereco endereco = (Endereco) rsEndEntrega.getResultado();
        cliente.getEnderecoEntrega().setId(endereco.getId().intValue());
      }
      if (cliente.getEnderecoCobranca() != null) {
        Resultado rsEndCobranca = daoEndereco.salvar(cliente.getEnderecoCobranca());
        Endereco endereco = (Endereco) rsEndCobranca.getResultado();
        cliente.getEnderecoCobranca().setId(endereco.getId().intValue());
      }  
      
      DAOClientes_Endereco daoCliEndereco = new DAOClientes_Endereco();
      
      daoCliEndereco.salvar(cliente);
      
      DAOCartao daoCartao = new DAOCartao();
      Resultado rsCartao = daoCartao.salvar(cliente.getCartao());
      Cartao cartao = (Cartao) rsCartao.getResultado();
      cliente.setCartao(cartao);
      
      DAOCartoesCliente daoCartoesCli = new DAOCartoesCliente();
      
      daoCartoesCli.salvar(cliente);      

      resultado.sucesso("Cliente salvo com sucesso");
      
    } catch (Exception e) {
      resultado.erro("Erro ao salvar cliente");
      e.printStackTrace();
    } finally {
      ConnectionFactory.closeConnection(preparedStatement, conexao);
    }
    
    resultado.setResultado(cliente);
    return resultado;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Cliente cliente = (Cliente) entidade;
    conexao = ConnectionFactory.getConnection();
    PreparedStatement pst = null;
    String sql = "SELECT * FROM clientes WHERE cli_id = ? ";
    
    try {
      pst = conexao.prepareStatement(sql);
      pst.setInt(1, cliente.getId().intValue());
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        cliente.setId(rs.getInt(1));
        cliente.setCpf(rs.getString("cli_cpf"));
        cliente.setGenero(Genero.valueOf(rs.getString("cli_genero")));
        cliente.setDataNascimento(LocalDate.parse(rs.getDate("cli_data_nascimento").toString()));
        cliente.setNome(rs.getString("cli_nome"));
        cliente.setSobrenome(rs.getString("cli_sobrenome"));
        Telefone telefone = new Telefone();
        telefone.setDdd(rs.getString("cli_ddd"));
        telefone.setNumero(rs.getString("cli_telefone"));
        telefone.setTipo(TipoTelefone.valueOf(rs.getString("cli_tipo_telefone")));
        cliente.setTelefone(telefone);
        
        Usuario usuario = new Usuario();
        usuario.setUsername(rs.getString("cli_email"));
        usuario.setPassword(rs.getString("cli_senha"));
        cliente.setUsuario(usuario);
        
      }
      
      DAOClientes_Endereco daoCliEnd = new DAOClientes_Endereco();
      Resultado rsEndCli = daoCliEnd.consultar(cliente);
      
      List<Endereco> enderecos = new ArrayList<>();      
      
      for(EntidadeDominio end : rsEndCli.getListaResultado()) {
        enderecos.add((Endereco) end);
      };
      
      DAOEndereco daoEndereco = new DAOEndereco();
      Resultado rsEnderecoResidencial = daoEndereco.consultar(enderecos.get(0));
      
      Resultado rsEnderecoEntrega = daoEndereco.consultar(enderecos.get(1));
      
      Resultado rsEnderecoCobranca = daoEndereco.consultar(enderecos.get(2));      
      
      cliente.setEnderecoResidencial((Endereco)rsEnderecoResidencial.getResultado());
      cliente.setEnderecoEntrega((Endereco)rsEnderecoEntrega.getResultado());
      cliente.setEnderecoCobranca((Endereco)rsEnderecoCobranca.getResultado());
     
      resultado.sucesso("Consulta realizada com sucesso");
      resultado.setResultado(cliente);
    } catch (Exception e) {
      resultado.erro("Erro ao consultar cliente");
      e.printStackTrace();
    }  finally {
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
