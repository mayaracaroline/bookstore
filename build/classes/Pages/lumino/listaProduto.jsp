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
							
				<form role="form" action="/livraria/AlterarProduto" method="POST">
								
				  <div class="form-group">
								
					<c:forEach var="item" items="${resultado}">
									
					<input type="hidden" name="codigo" value="${item.id}" class="form-control" maxLength="5" >									
					<label>Título</label>
					<input name="titulo"  value="${item.titulo}" class="form-control" >
					<label>Autor:</label>
					<input name="autor" value="${item.autor}" class="form-control" >
					<label>Ano publicação:</label>
					<input name="ano" value="${item.ano}" class="form-control" >
					<label id="genero">Genero:</label>
					<c:forEach var="categoria" items="${item.categorias}">	
						<li> Genero: ${categoria.id} - ${categoria.descricao} </li><br>
					</c:forEach>
									
					<label id="genero">Alterar Genero:</label>
					<div class="checkbox">
    				  <label>
    					<input  name="genero" type="checkbox" value="1"> Administração
    				  </label>
    				</div>
					<div class="checkbox">
						<label>
							<input  name="genero" type="checkbox" value="2"> Artes e fotografia
						</label>
					</div>
					<div class="checkbox">
					   <label>
						  <input  name="genero" type="checkbox" value="3">Autoajuda
					   </label>
					</div>
					<div class="checkbox">
    					<label>
    						<input  name="genero" type="checkbox" value="4">Biografia
    					</label>
					</div>
					<div class="checkbox">
					   <label>
						  <input  name="genero" type="checkbox" value="5">Contos e crônicas
					   </label>
					</div>
					<div class="checkbox">
						<label>
							<input  name="genero" type="checkbox" value="6">Didáticos
						</label>
					</div>
					<div class="checkbox">
						<label>
							<input  name="genero" type="checkbox" value="7">Direito
						</label>
					</div>
					<div class="checkbox">
						<label>
							<input  name="genero" type="checkbox" value="8">Ficção fantasiosa
						</label>
					</div>
					<div class="checkbox">
						<label>
							<input  name="genero" type="checkbox" value="9">HQs
						</label>
					</div>
					<div class="checkbox">
						<label>
							<input  name="genero" type="checkbox" value="10">Poesia
						</label>
					</div>
					<div class="checkbox">													
    					<label>Editora:</label>
    					<input name="editora" value="${item.editora}" class="form-control" >
    					<label>Edição:</label>
    					<input name="edicao" value="${item.edicao}" class="form-control" >
    					<label>ISBN:</label>
    					<input name="isbn" value="${item.isbn}" class="form-control" maxlength="13" >
    					<label>Sinopse:</label>
    					<textarea name="sinopse" value="" class="form-control" rows="3" >
    						${item.sinopse}
    					</textarea>
    					<label>Altura:</label>
    					<input name="altura" value="${item.altura}" class="form-control" >
    					<label>Largura:</label>
    					<input name="largura" value="${item.largura}" class="form-control" >
    					<label>Peso:</label>
    					<input name="peso" value="${item.largura}" class="form-control" >
    					<label>Profundidade:</label>
    					<input name="profundidade" value="${item.profundidade}" class="form-control" >
    					<label>Quantidade de páginas:</label>
    					<input name="quantidadePaginas" value="${item.quantidadePaginas}" class="form-control" >
    					<label>Código de barras:</label>
    					<input name="codBarras" value="${item.codigoBarras}" class="form-control" >
    					<div class="form-group checkbox">
      					   <label id="statusLivro" >
        					<input id="status" name="ativo" type="checkbox">
        					<c:choose>
        						<c:when test="${not item.ativo}">
        							
        							<li><b>Status:</b> Inativo</li>
        																				
        						</c:when>
        						<c:when test="${item.ativo}">
        							<li><b>Status:</b> Ativo</li>
        						</c:when>
        					</c:choose>
                          </label>
    					</div>
    				  <div class="form-group">
  						<label>Categoria de ativação</label>
  						<li>Categoria ${item.categoriaAtivacao}</li>
  						<select name="categoriaAtivacao" class="form-control" >
  							<option value="1">Categoria 1</option>
  							<option value="2">Categoria 2</option>
  							<option value="3">Categoria 3</option>
  							<option value="4">Categoria 4</option>
  						</select>
    				 </div>
					<div class="form-group">
						<label>Justificativa ativação:</label>
						<input name="motivoAtivacao" value="${item.justificativaAtivacao}" class="form-control" >									
					</div>

					<div class="form-group">
						<button type="reset" class="btn btn-default">Cancelar</button>
						<button name="operacao" value="ALTERAR" type="submit" class="btn btn-primary">Alterar</button>
					</div>																
				</div>	
              </c:forEach>					
			 </form>	
              <c:if test="${ fn:length(resultado) < 1 }" >
                <div class="alert  alert-info" role="alert">
                  A pesquisa não retornou nenhum resultado <br>
                  Tente informar outros parâmetros para consulta.
                </div>                                
              </c:if>
			</div>							
                <c:forEach var="mensagem" items="${requestScope.erro}">
      <div class="alert alert-danger" role="alert">
        ${mensagem}
      </div>                      
    </c:forEach>
							
			<!--<c:forEach var="mensagem" items="${requestScope.sucesso}">
				<div class="alert alert-success" role="alert">
					${mensagem}
				</div>											
			</c:forEach> -->
		</div>			
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
