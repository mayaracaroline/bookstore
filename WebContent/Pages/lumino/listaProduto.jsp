<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page = "./sidebar.jsp" />        
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	  <div class="row">
      <ol class="breadcrumb">
          <li>
            <a href="#"><em class="fa fa-home"></em></a>
          </li>
          <li class="active">Gerenciar produtos</li>
      </ol>
	  </div><!--/.row-->
		
		<div class="row">
		  <div class="col-lg-12">
			   <h2 class="page-header">Resultado da pesquisa</h2>
			</div>
		</div><!--/.row-->
    <div class="row">
      <div class="col-sm-12">
        
        <div class="panel panel-info">
          <div class="panel-heading">
            <div class="text-title">
              <em class="fa fa-book"></em>
              <label>Título:</label>${livro.titulo}
            </div>  
            <span class="pull-right clickable panel-toggle"><em class="fa fa-toggle-up"></em></span></div>
          <div class="panel-body">
            <p>
              <label>Status</label>:                
              <c:choose> 
                <c:when test="${!livro.ativo}">
                  Inativo  <br>
                </c:when>
                <c:otherwise>
                  Ativo<br>
                </c:otherwise>
              </c:choose>
              <label>Cód. Barras</label>: ${livro.codigoBarras}  <br>
              <label>Categorias</label>:          
              <c:forEach var="categoria" items="${livro.categorias}">
                ${categoria.descricao}; 
              </c:forEach>  <br>
              <label>Autor</label>: ${livro.autor}<br>
              <label>Ano publicação</label>: ${livro.ano}<br>
              <label>Editora</label>: ${livro.editora} <br>
              <a href="/livraria/Pages/lumino/ConsultaProduto?operacao=CONSULTAR&codigo=${livro.id}&page=listaProduto">Ver mais</a>
            </p>
          </div>
        </div>
      </div>
    </div>				
	
	<jsp:include page= "./scripts.jsp" />
	
</body>
</html>
