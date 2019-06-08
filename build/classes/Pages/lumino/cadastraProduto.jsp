<%@page import="dominio.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "./sidebar.jsp" />      
<meta charset="utf-8">
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
  <div class="row">
    <ol class="breadcrumb">
      <li>
        <a href="#">
    	 <em class="fa fa-home"></em>
    	</a>
      </li>
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
  		<form role="form" id="form" action="/livraria/Pages/lumino/CadastrarProduto" method="POST">

  		  <div class="form-group">
            
			<label>Código:</label>
			<input name="codigo" class="form-control" >									
			<label>Título</label>
			<input  id="titulo" name="titulo" class="form-control" >
			<label>Autor:</label>
			<input name="autor" class="form-control" >
			<label>Ano publicação:</label>
			<input name="ano" class="form-control" >
			<label>Categoria:</label>
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
			 <label>
			   <input  name="genero" type="checkbox" value="11">Romance
			 </label>
			</div>
			<label>Editora:</label>
			<input name="editora" class="form-control" >
			<label>Edição:</label>
			<input name="edicao" class="form-control" >
			<label>ISBN:</label>
			<input name="isbn" class="form-control" maxlength="13" >
			<label>Sinopse:</label>
			<textarea name="sinopse" value="" class="form-control" rows="3"></textarea>
			<label>Altura:</label>
			<input name="altura" class="form-control" >
			<label>Largura:</label>
			<input name="largura" class="form-control" >
			<label>Peso:</label>
			<input name="peso" class="form-control" >
			<label>Profundidade:</label>
			<input name="profundidade" class="form-control" >
			<label>Quantidade de páginas:</label>
			<input name="quantidadePaginas" class="form-control" >
			<label>Código de barras:</label>
			<input name="codBarras" class="form-control" >
            <label>Preço:</label>
            <input name="preco" class="form-control" >      
			<div class="form-group checkbox">
			 <label>
			   <input name="ativo" type="checkbox">Ativo
			 </label>
			</div>
			<div class="form-group">
			 <label>Categoria de ativação</label>
			  <select name="categoriaAtivacao" class="form-control">
			   <option></option>
			   <option value="1">Categoria 1</option>
			   <option value="2">Categoria 2</option>
			   <option value="3">Categoria 3</option>
			  </select>
			</div>
			<div class="form-group">
			 <label>Justificativa ativação:</label>
			 <input name="motivoAtivacao" class="form-control" >									
			</div>

			<button type="reset" class="btn btn-default">Cancelar</button>
			<button name="operacao" value="SALVAR" type="submit" class="btn btn-primary">Salvar</button>
      																
  		  </div> <!-- .form-group geral -->						  			 	 
        </form> 

  
       </div><!-- /.col-->    		
      </div><!-- /.panel-body-->
  	</div><!-- /.panel-default -->
</div><!--/.main-->

<!-- </div>
</div> -->
	
    <script src="../../js/jquery-1.11.1.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
	<script src="../../js/chart.min.js"></script>
	<script src="../../js/chart-data.js"></script>
	<script src="../../js/easypiechart.js"></script>
	<script src="../../js/easypiechart-data.js"></script>
	<script src="../../js/bootstrap-datepicker.js"></script>
	<script src="../../js/custom.js"></script>
	
</body>
</html>
