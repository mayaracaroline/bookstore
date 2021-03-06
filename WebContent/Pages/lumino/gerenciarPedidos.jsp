<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page = "./sidebar.jsp" /> 
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">Gerenciar clientes</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Área do  administrador</h1>
			</div>
		</div><!--/.row-->
				
		<div class="panel panel-default">
			<div class="panel-heading">Pedidos</div>
				<div class="panel-body">
					<div class="col-sm-6">
          <table class="table">
            <thead class="thead-dark">
              <tr>
                <th scope="col">Pedido</th>
                <th scope="col">Status</th>
                <th scope="col">Ações</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="pedido" items="${pedidos}">
              <tr>
                  <td>${pedido.codigoIdentificador}</td>
                  <td>
                    <c:choose>
                      <c:when test="${pedido.status == 1}">
                        Em processamento
                      </c:when>  
                       <c:when test="${pedido.status == 2}">
                        Aprovado
                      </c:when> 
                      <c:when test="${pedido.status == 3}">
                        Reprovado
                      </c:when> 
                      <c:when test="${pedido.status == 4}">
                        Em transporte
                      </c:when> 
                      <c:when test="${pedido.status == 5}">
                        Entregue
                      </c:when> 
                      <c:when test="${pedido.status == 6}">
                        Em troca
                      </c:when> 
                      <c:when test="${pedido.status == 7}">
                        Autorizar troca
                        
                      </c:when> 
                      <c:when test="${pedido.status == 8}">
                        Trocado
                      </c:when> 
                      <c:when test="${pedido.status == 9}">
                        Devolução solicitada
                      </c:when> 
                      <c:when test="${pedido.status == 10}">
                        Devolução em análise
                      </c:when> 
                      <c:when test="${pedido.status == 11}">
                        Devolução autorizada
                      </c:when> 
                      <c:when test="${pedido.status == 12}">
                        Devolução concluída
                      </c:when>                
                    </c:choose>
                  </td>
                  <td>
                    <c:choose>
					  <c:when test="${pedido.status == 2}">
                        <a href="/livraria/Pages/lumino/pedido?operacao=COLOCAREMTRANSPORTE&formName=gerenciarPedidos&codigoPedido=${pedido.id}"> Colocar em transporte</a> <br>
                      </c:when>
					  <c:when test="${pedido.status == 4}">
                        <span> Entregue <span> <br>
                        <a href="/livraria/Pages/lumino/pedido?operacao=CONFIRMARENTREGA&formName=gerenciarPedidos&codigoPedido=${pedido.id}"> Confirmar entrega</a> <br>
                      </c:when>
                      <c:when test="${pedido.status == 5}">
                        -
                      </c:when>
                      <c:when test="${pedido.status == 6}">
                        <a href="/livraria/Pages/lumino/pedido?operacao=CONSULTAR&formName=gerenciarItemPedido&codigoPedido=${pedido.id}"> Ver detalhes </a>
                      </c:when>
                      <c:otherwise>
                        <a href="/livraria/Pages/lumino/pedido?operacao=CONSULTAR&formName=gerenciarItemPedido&codigoPedido=${pedido.id}"> Ver detalhes </a>
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
		</div><!-- /.panel-->
	</div><!--/.main-->
	
	<jsp:include page= "./scripts.jsp" />
	
</body>
</html>
