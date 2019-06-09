package les.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import dominio.EntidadeDominio;
import dominio.Relatorio;
import util.ConnectionFactory;
import util.Resultado;

public class DAORelatorio extends AbstractDAO implements IDAO{

  @Override
  public Resultado salvar(EntidadeDominio entidade) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Resultado consultar(EntidadeDominio entidade) {
    String sql = "SELECT * FROM itens_pedido A INNER JOIN pedidos B ON A.itp_ped_id = B.ped_id INNER JOIN livros C ON A.itp_pro_cod_barras = C.liv_cod_barras JOIN generos_livro D ON C.liv_id = D.glv_livro_id JOIN clientes E ON B.ped_cli_id = cli_id WHERE B.ped_data_solicitacao BETWEEN ? AND ? ;";
    Resultado resultado = new Resultado();
    PreparedStatement pst = null;
    Relatorio relatorio = (Relatorio) entidade;
    conexao = ConnectionFactory.getConnection();
    HashMap<Integer, Integer[]>  generoPorCategoria = new HashMap<>();
    
    Integer[] genero1 = {0,0};
    Integer[] genero2 = {0,0};
    Integer[] genero3 = {0,0};
    Integer[] genero4 = {0,0};
    Integer[] genero5 = {0,0};
    Integer[] genero6 = {0,0};
    Integer[] genero7 = {0,0};
    Integer[] genero8 = {0,0};
    Integer[] genero9 = {0,0};
    Integer[] genero10 = {0,0};
    Integer[] genero11 = {0,0};
    
    generoPorCategoria.put(1, genero1);
    generoPorCategoria.put(2, genero2);
    generoPorCategoria.put(3, genero3);
    generoPorCategoria.put(4, genero4);
    generoPorCategoria.put(5, genero5);
    generoPorCategoria.put(6, genero6);
    generoPorCategoria.put(7, genero7);
    generoPorCategoria.put(8, genero8);
    generoPorCategoria.put(9, genero9);
    generoPorCategoria.put(10, genero10);
    generoPorCategoria.put(11, genero11);
      
    try {
      pst = conexao.prepareStatement(sql);
      
      pst.setDate(1, Date.valueOf(relatorio.getDataInicio()));
      pst.setDate(2, Date.valueOf(relatorio.getDataFim()));
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        int categoria = rs.getInt("glv_genero_id");
        Integer[] genero = generoPorCategoria.get(categoria);
          if(rs.getString("cli_genero").equals("FEMININO")) {
             genero[0]++;
              
          } else if (rs.getString("cli_genero").equals("MASCULINO")) {
              genero[1]++;
          }
      }
      
      resultado.setMapResultadoInteger(generoPorCategoria);
      resultado.sucesso("Gráfico gerado com sucesso");
      
    } catch (Exception e) {

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
