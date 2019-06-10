<%@page import="dominio.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "./sidebar.jsp" />      
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">Gerenciar cupons</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
			 <h1 class="page-header">Cadastrar</h1>
			</div>
		</div><!--/.row-->
				
				<div class="panel ">
					<div class="panel-body">
                      <div class="panel panel-default">
                        <div class="panel-heading">Cupom</div>
                        <div class="panel-body">
                          <form role="form" action="/livraria/Pages/lumino/CadastraCupom" method="POST">                                                           
                                  <label>Tipo do cupom:</label>
                                  <select name="tipo-cupom" class="form-control">
                                    <option value="SELECIONE">Selecione</option>
                                    <option value="TROCA">Troca</option>
                                    <option value="PROMOCIONAL">Promocional</option>
                                  </select>
                                  <label>Valor:</label>
                                  <input type="text" name="valor" class="form-control">
                                  <label>Id cliente:</label>
                                  <input type="text" name="idCliente" class="form-control">
                              </div>
                           </div> <!-- Panel - Dados Pessoais -->
                          
                           <button type="reset"  class="btn btn-default">Cancelar</button>
                           <button type="submit" class="btn btn-primary" name="operacao" value="SALVAR">Salvar</button>
              
                        </form>
                        
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
