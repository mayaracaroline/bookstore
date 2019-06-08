<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "./sidebar.jsp" /> 
	
  <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">Meus pedidos</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Área do Administrador</h1>
			</div>
	  </div><!--/.row-->
    
	  <div class="panel panel-default">
        <div class="panel-heading">Meus pedidos</div>
        <div class="panel-body">     
          <table class="table">
            <thead class="thead-dark">
              <tr>
                <th scope="col">Pedido</th>
                <th scope="col">Preço</th>               
                <th scope="col">Item</th>
                <th scope="col">Status</th>
                <th scope="col">Ações</th>
              </tr>
            </thead>
            <tbody>
              
              

                    <c:forEach var="item" items="${pedidos[0].carrinho.itensCarrinho}"> 
                    <tr> 
                      <td>${pedidos[0].codigoIdentificador}</td>
                      <td>${item.produto.preco}</td>
                      <td>
                        <strong>Título:</strong> ${item.produto.titulo}
                      </td>
                      <td>
                        <c:choose>
                          <c:when test="${item.status == 1}">
                            Em processamento
                          </c:when>  
                           <c:when test="${item.status == 2}">
                            Aprovado
                          </c:when> 
                          <c:when test="${item.status == 3}">
                            Reprovado
                          </c:when> 
                          <c:when test="${item.status == 4}">
                            Em transporte
                          </c:when> 
                          <c:when test="${item.status == 5}">
                            Entregue
                          </c:when> 
                          <c:when test="${item.status == 6}">
                            Em troca
                          </c:when> 
                          <c:when test="${item.status == 7}">
                            Troca autorizada
                          </c:when> 
                          <c:when test="${item.status == 8}">
                            Trocado
                          </c:when> 
                          <c:when test="${item.status == 9}">
                            Devolução solicitada
                          </c:when> 
                          <c:when test="${item.status == 10}">
                            Devolução em análise
                          </c:when> 
                          <c:when test="${item.status == 11}">
                            Devolução autorizada
                          </c:when> 
                          <c:when test="${item.status == 12}">
                            Devolução concluída
                          </c:when>                
                        </c:choose>                     
                      </td>
                      <td>
                        <c:choose>
                          <c:when test="${item.status == 6}">
                            Autorizar troca <br>
                            <i class="fa fa-check"></i><a href="/livraria/Pages/lumino/detalhePedido?operacao=APROVARTROCA&idPedido=${pedidos[0].id}&idItemPedido=${item.id}&qtdProduto=1">  Retornar ao estoque </a> <br>
                            <i class="fa fa-times"></i><a href="/livraria/Pages/lumino/detalhePedido?operacao=APROVARTROCA&idPedido=${pedidos[0].id}&idItemPedido=${item.id}&qtdProduto=0">  Não retornar ao estoque</a> <br>
                          
                          </c:when>
                          <c:otherwise>
                            <a href="#"> - </a>
                          </c:otherwise>
                        </c:choose>                
                      </td>
                    </c:forEach>
                   </tr>                     
            </tbody>
          </table>
  
        </div>
      </div>

   </div>

  

<jsp:include page= "./scripts.jsp" />
</body>
</html>