<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "./sidebarCliente.jsp" /> 
	
  <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">Minha área</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Área do cliente</h1>
			</div>
	  </div><!--/.row-->
    
	  <div class="panel panel-default">
        <div class="panel-heading">Novidades</div>
        <div class="panel-body">
  
          <p>Está tudo meio quieto por aqui! :)</p>
  
        </div>
      </div>

   </div>

  

<jsp:include page= "./scripts.jsp" />
</body>
</html>