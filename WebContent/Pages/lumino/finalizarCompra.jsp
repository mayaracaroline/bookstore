<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Alternativa | Bookstore </title>
	<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/font-awesome.min.css" />" rel="stylesheet">
	<link href="<c:url value="/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/css/styles.css" />" rel="stylesheet">
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-9 col-lg-offset-2 main">		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Não foi possível finalizar a compra!</h1>
			</div>
		</div><!--/.row-->
				
				<div class="panel panel-default">
					<div class="panel-heading">Os seguintes erro ocorreram:</div>
						<div class="panel-body">
							<div class="col-md-6">							
										
							<c:forEach var="mensagem" items="${sessionScope.erro}">
								<div class="alert alert-danger" role="alert">
									${mensagem}
								</div>											
							</c:forEach>
                            
                                 <a href="/livraria/Pages/lumino/checkout.jsp">Voltar</a>
								</div>															
							</div>
						</div>
					</div>
				</div><!-- /.panel-->
			</div><!-- /.col-->
		</div><!-- /.row -->
	</div><!--/.main-->
	
	<jsp:include page= "./scripts.jsp" />
	
</body>
</html>
