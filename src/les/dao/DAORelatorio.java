package les.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
    
    Relatorio relatorio = (Relatorio) entidade;
    Resultado resultado = new Resultado();
    if(relatorio.getTag().equals("categoria")) {
      resultado = consultarPorCategoria(entidade);
    } else if(relatorio.getTag().equals("idade")) {
      resultado = consultarPorFaixaEtaria(entidade);
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
  
  public Resultado consultarPorCategoria(EntidadeDominio entidade) {
    String sql = "SELECT * FROM itens_pedido A INNER JOIN pedidos B ON A.itp_ped_id = B.ped_id INNER JOIN livros C ON A.itp_pro_cod_barras = C.liv_cod_barras JOIN generos_livro D ON C.liv_id = D.glv_livro_id JOIN clientes E ON B.ped_cli_id = cli_id WHERE B.ped_data_solicitacao BETWEEN ? AND ? ;";
    Resultado resultado = new Resultado();
    PreparedStatement pst = null;
    Relatorio relatorio = (Relatorio) entidade;
    conexao = ConnectionFactory.getConnection();
    HashMap<Integer, Integer[]>  generoPorCategoria = new HashMap<>();
    
    Integer[] faixasEtarias1 = {0,0};
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
    
    generoPorCategoria.put(1, faixasEtarias1);
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
  
  public Resultado consultarPorFaixaEtaria(EntidadeDominio entidade) {
    String sql = "SELECT * FROM itens_pedido A INNER JOIN pedidos B ON A.itp_ped_id = B.ped_id INNER JOIN livros C ON A.itp_pro_cod_barras = C.liv_cod_barras JOIN generos_livro D ON C.liv_id = D.glv_livro_id JOIN clientes E ON B.ped_cli_id = cli_id WHERE B.ped_data_solicitacao BETWEEN ? AND ? ;";
    Resultado resultado = new Resultado();
    PreparedStatement pst = null;
    Relatorio relatorio = (Relatorio) entidade;
    conexao = ConnectionFactory.getConnection();
    HashMap<Integer, Integer[]>  generoPorFaixaEtaria = new HashMap<>();
    int ano= LocalDate.now().getYear();
    Integer[] faixasEtarias1 = {0,0, 0, 0};
    Integer[] faixasEtarias2 = {0,0, 0, 0};
    Integer[] faixasEtarias3 = {0,0, 0, 0};
    Integer[] faixasEtarias4 = {0,0, 0, 0};
    Integer[] faixasEtarias5 = {0,0, 0, 0};
    Integer[] faixasEtarias6 = {0,0, 0, 0};
    Integer[] faixasEtarias7 = {0,0, 0, 0};
    Integer[] faixasEtarias8 = {0,0, 0, 0};
    Integer[] faixasEtarias9 = {0,0, 0, 0};
    Integer[] faixasEtarias10 = {0,0, 0, 0};
    Integer[] faixasEtarias11= {0,0, 0, 0};
    
    generoPorFaixaEtaria.put(1, faixasEtarias1);
    generoPorFaixaEtaria.put(2, faixasEtarias2);
    generoPorFaixaEtaria.put(3, faixasEtarias3);
    generoPorFaixaEtaria.put(4, faixasEtarias4);
    generoPorFaixaEtaria.put(5, faixasEtarias5);
    generoPorFaixaEtaria.put(6, faixasEtarias6);
    generoPorFaixaEtaria.put(7, faixasEtarias7);
    generoPorFaixaEtaria.put(8, faixasEtarias8);
    generoPorFaixaEtaria.put(9, faixasEtarias9);
    generoPorFaixaEtaria.put(10, faixasEtarias10);
    generoPorFaixaEtaria.put(11, faixasEtarias11);
      
    try {
      pst = conexao.prepareStatement(sql);
      
      pst.setDate(1, Date.valueOf(relatorio.getDataInicio()));
      pst.setDate(2, Date.valueOf(relatorio.getDataFim()));
      
      ResultSet rs = pst.executeQuery();
      
      while(rs.next()) {
        int categoria = rs.getInt("glv_genero_id");
        Integer[] faixaEtaria = generoPorFaixaEtaria.get(categoria);
          int anoNasc = LocalDate.parse(rs.getDate("cli_data_nascimento").toString()).getYear();
          int idade = ano - anoNasc;
          if(idade <= 20) {
            faixaEtaria[0]++;
              
          } else if (idade <= 40) {
            faixaEtaria[1]++;
          } else if (idade <= 60) {
            faixaEtaria[2]++;
          } else {
            faixaEtaria[3]++;
          }  
          
      }
      
      resultado.setMapResultadoInteger(generoPorFaixaEtaria);
      resultado.sucesso("Gráfico gerado com sucesso");
      
    } catch (Exception e) {

      ConnectionFactory.closeConnection(pst, conexao);
    }
    
    return resultado;
  
  }
}
