<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page = "./sidebar.jsp" />
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">Gerenciar produtos</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Exclusão produto</h1>
			</div>
		</div><!--/.row-->
				
				<div class="panel panel-default">
					<div class="panel-heading">Livros</div>
						<div class="panel-body">
							<div class="col-md-6">
							
							<form role="form" action="/livraria/CadastrarProduto" method="POST">							
								
								<div class="form-group">
							
							<c:forEach var="mensagem" items="${requestScope.sucesso}">
								<div class="alert alert-success" role="alert">
									${mensagem}
								</div>											
							</c:forEach>
								</div>			
							<c:forEach var="mensagem" items="${requestScope.erro}">
								<div class="alert alert-danger" role="alert">
									${mensagem}
								</div>											
							</c:forEach>
								</div>															
							</div>
						</div>
					</div>
				</div><!-- /.panel-->
			</div><!-- /.col-->
		</div><!-- /.row -->
	</div><!--/.main-->
	
<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/custom.js"></script>
	
</body>
</html>
