package dominio;

import java.util.List;

public class PedidoDeCompra extends EntidadeDominio {
  
  List<IFormaDePagamento> formasDePagamento;
  List<ItemCarrinho> itens;
  Endereco enderecoDeEntrega;
  Double frete;
   
}
