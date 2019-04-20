<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page = "./sidebar.jsp" /> 
		
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
      	<h1 class="page-header">Inativar produto</h1>
      </div>
    </div><!--/.row-->		
    <div class="panel panel-default">
    	<div class="panel-heading">Livros</div>
    	<div class="panel-body">
    		<div class="col-md-6">
    		  <form role="form" action="/livraria/Pages/lumino/InativaProduto" method="POST">
    			<div class="form-group">
    			 <label>Código:</label>
    		     <input name="codigo" class="form-control" >									
    			 <label>Categoria de inativação</label>
    			 <select name="categoriaInativacao" class="form-control">
      		    <option>Selecione</option>
      		    <option value="1">Categoria 1</option>
      		    <option value="2">Categoria 2</option>
      		    <option value="3">Categoria 3</option>
    			 </select>
    			</div>
    			<div class="form-group">
    			   <label>Justificativa inativação:</label>
    			   <input name="motivoInativacao" class="form-control" >
    			</div>
    			<div class="form-group">
    			   <button type="reset" class="btn btn-default">Cancelar</button>
    			   <button name="operacao" value="INATIVAR" type="submit" class="btn btn-primary">Inativar</button>																
    			</div>
           </form>
    	   </div>													
      </div><!-- /.panel-->	
    </div><!-- /.panel-->
  </div><!-- /.col-->

	
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
