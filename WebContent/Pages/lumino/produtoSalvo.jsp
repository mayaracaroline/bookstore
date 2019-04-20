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
				<h1 class="page-header">Cadastrar</h1>
			</div>
		</div><!--/.row-->
				
				<div class="panel panel-default">
					<div class="panel-heading">Livros</div>
					<div class="panel-body">
						<div class="col-md-6">
			
							<c:forEach var="msg" items="${requestScope.sucesso}" >
								<div class="alert alert-success" role="alert">
											Salvo com sucesso!
								</div>											
							</c:forEach>
							<c:forEach var="mensagem" items="${requestScope.erro}">
								<div class="alert alert-danger" role="alert">
											${mensagem}
								</div>											
							</c:forEach>					
														
							<div id="sucesso" class="alert alert-info" role="alert">
								Código: ${livro.id} <br>
								Título: ${livro.titulo} 
							</div>
							
							<c:choose>
								<c:when test="${fn:length(requestScope.erro) > 0}"> 
									<script>
										document.getElementById('sucesso').style.display="none";
									</script>
								</c:when>
								<c:when test="${fn:length(requestScope.sucesso) > 0}">
									<script>
										document.getElementById('sucesso').style.display="none";
									</script>
								</c:when>
							</c:choose>										
							
													
						</div>

								<script>
								</script>
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
