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
				<li class="active">Gerenciar estoque</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Cadastrar</h1>
			</div>
		</div><!--/.row-->
				
				<div class="panel panel-default">
					<div class="panel-heading">Cliente</div>
					<div class="panel-body">
						<div class="col-md-6">
							<form role="form" action="/livraria/Pages/lumino/cadastraEstoque">
								
								<div class="form-group">
									<label>Código de barras:</label>
									<input name="codBarras" class="form-control" >
                                    <label>Quantidade:</label>
                                    <input name="quantidade" class="form-control" >									
								</div>
								<div class="form-group">
									<button type="reset" class="btn btn-default">Cancelar</button>
									<button name="operacao" value="SALVAR" type="submit" class="btn btn-primary">Salvar</button>
                                    																
								</div>						
									
								</div>
							</form>
						</div>
					</div>
				</div><!-- /.panel-->
			</div><!-- /.col-->
		</div><!-- /.row -->
	</div><!--/.main-->
	
	<jsp:include page= "./scripts.jsp" />
	
</body>
</html>
