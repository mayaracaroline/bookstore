package viewhelper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dominio.EntidadeDominio;
import dominio.Relatorio;
import util.Resultado;

public class VHRelatorio implements IViewHelper {

  @Override
  public EntidadeDominio getEntidade(HttpServletRequest request) {
    Relatorio relatorio = new Relatorio();
    
    String strDataInicio = null != request.getParameter("dataInicio") && 
        !"".equals(request.getParameter("dataInicio")) 
        ? request.getParameter("dataInicio") : LocalDate.MIN.toString();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dataInicio = LocalDate.parse(strDataInicio,formatter);

    String strDataFim = null != request.getParameter("dataFim") && 
        !"".equals(request.getParameter("dataFim")) 
        ? request.getParameter("dataFim") : LocalDate.MIN.toString();
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dataFim = LocalDate.parse(strDataFim,formatter1);      
    
    relatorio.setDataInicio(dataInicio);
    relatorio.setDataFim(dataFim);
    
    return relatorio;
  }

  @Override
  public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {

    HashMap<Integer, Integer[]> generosPorCategoria = new HashMap<>();
    
    generosPorCategoria = resultado.getMapResultadoInteger();
    request.setAttribute("grafico", generosPorCategoria);
    RequestDispatcher rd = request.getRequestDispatcher("/Pages/lumino/relatorios.jsp");
    try {
      rd.forward(request, response);
    } catch (ServletException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Gson gson = new Gson();
    
    String JSONDadosGrafico = gson.toJson(generosPorCategoria);
    
  }

}
