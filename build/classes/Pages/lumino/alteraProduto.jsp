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
        
   
    <div class="panel panel-default">
      <div class="panel-heading">Livros</div>
      <div class="panel-body">
        <div class="col-md-6">
          <div class="form-group">    
            <form role="form" action="/livraria/Pages/lumino/AlterarProduto" method="POST">     
                <input type="hidden" name="codigo" value="${livro.id}" class="form-control" maxLength="5" >                 
                <label>Título</label>
                <input name="titulo"  value="${livro.titulo}" class="form-control" >
                <label>Autor:</label>
                <input name="autor" value="${livro.autor}" class="form-control" >
                <label>Ano publicação:</label>
                <input name="ano" value="${livro.ano}" class="form-control" >
                  
                <label>Editora:</label>
                <input name="editora" value="${livro.editora}" class="form-control" >
                <label>Edição:</label>
                <input name="edicao" value="${livro.edicao}" class="form-control" >
                <label>ISBN:</label>
                <input name="isbn" value="${livro.isbn}" class="form-control" maxlength="13" >
                <label>Sinopse:</label>
                <textarea name="sinopse" value="" class="form-control" rows="3" >
                  ${livro.sinopse}
                </textarea>
                <label>Altura:</label>
                <input name="altura" value="${livro.altura}" class="form-control" >
                <label>Largura:</label>
                <input name="largura" value="${livro.largura}" class="form-control" >
                <label>Peso:</label>
                <input name="peso" value="${livro.largura}" class="form-control" >
                <label>Profundidade:</label>
                <input name="profundidade" value="${livro.profundidade}" class="form-control" >
                <label>Quantidade de páginas:</label>
                <input name="quantidadePaginas" value="${livro.quantidadePaginas}" class="form-control" >
                <label>Código de barras:</label>
                <input name="codBarras" value="${livro.codigoBarras}" class="form-control" >       
                <label id="genero">Genero literário</label> <br>
                <label>Atual:</label>
                <c:forEach var="categoria" items="${livro.categorias}">
                  ${categoria.descricao};
                </c:forEach><br>
                <h4><span class="label label-info">Selecione:</span></h4> 
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
                <c:choose>
                  <c:when test="${not livro.ativo}">
                    <label>Status:</label> 
                    <div class="form-group checkbox">
                    <label>
                      <input id="status" name="ativo" type="checkbox"> Ativo
                    </label>
                    </div> 
                    <label id="labelCategoriaAtivacao">Categoria de Inativação</label><br>
                    <label>Atual</label>:<span id="categoriaAtual"> ${livro.categoriaInativacao}</span>
                    <select id="categoriaAtivacao" name="categoriaAtivacao" class="form-control" >
                      <option value="1">Categoria 1</option>
                      <option value="2">Categoria 2</option>
                      <option value="3">Categoria 3</option>
                      <option value="4">Categoria 4</option>
                    </select>
                    <label id="labelMotivoAtivacao">Justificativa Inativação:</label>
                    <input id="inputMotivoAtivacao" name="motivoInativacao" value="${livro.justificativaAtivacao}" class="form-control" >                           
                    <input type="hidden" name="categoriaAtivacao" value="${livro.categoriaAtivacao}">
                    <input type="hidden" name="motivoAtivacao" value="${livro.justificativaAtivacao}">  
                  </c:when>
                  <c:when test="${livro.ativo}">
                    
                    <label>Status:</label> 
                    <div class="form-group checkbox">
                    <label>
                      <input id="status" name="ativo" type="checkbox" checked>Ativo
                    </label>
                    </div>  
                                
                    <label id="labelCategoriaAtivacao">Categoria de Ativação</label><br>
                    <label>Atual</label>:<span id="categoriaAtual"> ${livro.categoriaAtivacao}</span>
                    <select id="categoriaAtivacao" name="categoriaAtivacao" class="form-control" >
                      <option value="1">Categoria 1</option>
                      <option value="2">Categoria 2</option>
                      <option value="3">Categoria 3</option>
                      <option value="4">Categoria 4</option>
                    </select>
                    <label id="labelMotivoAtivacao">Justificativa ativação:</label>
                    <input id="inputMotivoAtivacao" name="motivoAtivacao" value="${livro.justificativaAtivacao}" class="form-control" >                  
                    <input type="hidden" name="categoriaInativacao" value="${livro.categoriaInativacao}">
                    <input type="hidden" name="motivoAtivacao" value="${livro.justificativaInativacao}">  
                  </c:when>
                </c:choose>
                <br>
                <button type="reset" class="btn btn-default">Cancelar</button>
                <button name="operacao" value="ALTERAR" type="submit" class="btn btn-primary">Alterar</button>
            </form> 
          </div>          
          <c:forEach var="mensagem" items="${sessionScope.erro}">
            <div class="alert alert-danger" role="alert">
              ${mensagem}
            </div>                      
          </c:forEach>                                
          <c:if test="${sessionScope.livro == null}" >
            <div class="alert  alert-info" role="alert">
              A pesquisa não retornou nenhum resultado <br>
              Tente informar outros parâmetros para consulta.
            </div>                                
          </c:if>   
        </div>
      </div>
    </div>
  </div>
  
  <jsp:include page= "./scripts.jsp" />
  
</body>
</html>
              
                    </form>						 							
  				</div>  					
  			</div>
  		</div>
  	</div><!-- /.panel-->
	
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
