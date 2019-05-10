package servico;

import java.util.HashMap;

import dominio.Endereco;
import dominio.EntidadeDominio;
import util.Resultado;

public class CalcularFrete {
  
  public Resultado calcularFrete(EntidadeDominio entidade) {
    Resultado resultado = new Resultado();
    Endereco endereco = (Endereco) entidade;
    String cep = endereco.getCep();
    
    
    HashMap<String, String> mapValorFretePorRegiao = new HashMap<>();
    
    // 0 - Grande São Paulo
    // 1 - Interior de São Paulo
    // 2 - RJ e ES
    // 3 - MG
    // 4 - BA e SE
    // 5 - PE, AL, PB, RN
    // 6 - CE, PI, MA, AP, AM, RR, AC 
    // 7 - DF, GO, RO, TO, MT, MS
    // 8 - PR e SC
    // 9 - RS
    
    mapValorFretePorRegiao.put("0", "10");
    mapValorFretePorRegiao.put("1", "15");
    mapValorFretePorRegiao.put("2", "20");
    mapValorFretePorRegiao.put("3", "25");
    mapValorFretePorRegiao.put("4", "25");
    mapValorFretePorRegiao.put("5", "25");
    mapValorFretePorRegiao.put("6", "25");
    mapValorFretePorRegiao.put("7", "25");
    mapValorFretePorRegiao.put("8", "25");
    mapValorFretePorRegiao.put("9", "25");
    
    String regiao = cep.substring(0, 1);
    
    String frete = mapValorFretePorRegiao.get(regiao);
    
    resultado.sucesso(frete);
    return resultado;
  }

}
