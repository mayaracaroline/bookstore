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
				<h1 class="page-header">Resultado da pesquisa</h1>
			</div>
		</div><!--/.row-->
				
				<div class="panel panel-default">
					<div class="panel-heading">Livros</div>
						<div class="panel-body">
							<div class="col-md-6">
							
							<form role="form" action="/livraria/CadastrarProduto" method="POST">
								<input type="hidden" id="FormName" name="FormName" value="CADASTRAR_PRODUTO" />
								
								<div class="form-group">
								
								<c:forEach var="item" items="${resultado}">
									<label>Código:</label>
									<input name="codigo" value="${item.id}" class="form-control" maxLength="">									
									<label>Título</label>
									<input name="titulo"  value="${item.titulo}" class="form-control" disabled>
									<label>Autor:</label>
									<input name="autor" value="${item.autor}" class="form-control" disabled>
									<label>Ano publicação:</label>
									<input name="ano" value="${item.ano}" class="form-control" disabled>
									<label id="genero">Genero:</label>
									<c:forEach var="categoria" items="${item.categorias}">	
										<li> Genero: ${categoria.id} - ${categoria.descricao} </li>
									</c:forEach>
																	
									<label>Editora:</label>
									<input name="editora" value="${item.editora}" class="form-control" disabled>
									<label>Edição:</label>
									<input name="edicao" value="${item.edicao}" class="form-control" disabled>
									<label>ISBN:</label>
									<input name="isbn" value="${item.isbn}" class="form-control" maxlength="13" disabled>
									<label>Sinopse:</label>
									<textarea name="sinopse" value="" class="form-control" rows="3" disabled>
										${item.sinopse}
									</textarea>
									<label>Altura:</label>
									<input name="altura" value="${item.altura}" class="form-control" disabled>
									<label>Largura:</label>
									<input name="largura" value="${item.largura}" class="form-control" disabled>
									<label>Peso:</label>
									<input name="peso" value="${item.largura}" class="form-control" disabled>
									<label>Profundidade:</label>
									<input name="profundidade" value="${item.profundidade}" class="form-control" disabled>
									<label>Quantidade de páginas:</label>
									<input name="quantidadePaginas" value="${item.quantidadePaginas}" class="form-control" disabled>
									<label>Código de barras:</label>
									<input name="codBarras" value="${item.codigoBarras}" class="form-control" disabled>
									<label>Preço:</label>
                                    <input name="preco" value="${item.preco}" class="form-control" >  
                                    <div class="form-group checkbox">
										<label id="statusLivro" disabled>
											<input id="status" name="ativo" type="checkbox">
											
												<c:if test="${not item.ativo}">
													
													<li><b>Status:</b> Inativo</li>															
												</c:if>
												<c:if test="${item.ativo}">
													<li><b>Status:</b> Ativo</li>
												</c:if>										
										</label>
									</div>
									<div class="form-group">
										<label>Categoria de ativação</label>
										<select name="categoriaAtivacao" class="form-control" disabled>
											<option value="1">Categoria ${item.categoriaAtivacao}</option>
										</select>
									</div>
									<div class="form-group">
										<label>Justificativa ativação:</label>
										<input name="motivoAtivacao" value="${item.justificativaAtivacao}" class="form-control" disabled>									
									</div>

									<div class="form-group">
										<button type="reset" class="btn btn-default">Cancelar</button>
										<button name="operacao" value="CONSULTAR" type="submit" class="btn btn-primary">CONSULTAR</button>
									</div>																
								</div>						
								</form>	
								</div>

								</c:forEach>							
							
							<c:if test="${ fn:length(resultado) < 1 }" >
								<div class="alert  alert-info" role="alert">
									A pesquisa não retornou nenhum resultado <br>
									Tente informar outros parâmetros para consulta.
								</div>																
							</c:if>
							
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
