<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Cadastrar Livro</title>
	<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/font-awesome.min.css" />" rel="stylesheet">
	<link href="<c:url value="/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/css/styles.css" />" rel="stylesheet">
    <script src="../../js/main.js"></script>
	
	<!--Custom Font-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
 
</head>
<body>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span></button>
				<a class="navbar-brand" href="#"><span>Alternativa</span>Admin</a>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
				<img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">Username</div>
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		<ul class="nav menu">
			<li class="active"><a href="index.html"><em class="fa fa-dashboard">&nbsp;</em> Relatório</a></li>
			<li class="parent "><a data-toggle="collapse" href="#sub-item-1">
					<em class="fa fa-calendar">&nbsp;</em> Gerenciar Clientes <span data-toggle="collapse" href="#sub-item-1" 
						class="icon pull-right"><em class="fa fa-plus"
					></em></span>
					</a>
					<ul class="children collapse" id="sub-item-1">
						<li><a class="" href="alterar-clientes.html">
							<span class="fa fa-arrow-right">&nbsp;</span> Alterar
						</a></li>
						<li><a class="" href="cadastrar-clientes.html">
								<span class="fa fa-arrow-right">&nbsp;</span> Cadastrar
							</a>
                        </li>
						<li><a class="" href="consultar-clientes.html">
							<span class="fa fa-arrow-right">&nbsp;</span> Consultar
						</a></li>
					</ul>
				</li>
			<li class="parent ">
                    <a data-toggle="collapse" href="#sub-item-2">
  					 <em class="fa fa-navicon">&nbsp;</em> Gerenciar Produtos 
                     <span data-toggle="collapse" href="#sub-item-2" class="icon pull-right">
                       <em class="fa fa-plus"></em>
                     </span>
					</a>
					<ul class="children collapse" id="sub-item-2">
						<li>
                          <a class="" href="cadastraProduto.jsp">
							<span class="fa fa-arrow-right">&nbsp;</span> Cadastrar
						  </a>
                        </li>
                        <li>
                          <a class="" href="./alteraProduto.jsp">
                            <span class="fa fa-arrow-right">&nbsp;</span> Alterar
                          </a>
                        </li>
						<li><a class="" href="./consultaProduto.jsp">
							<span class="fa fa-arrow-right">&nbsp;</span> Consultar
						</a></li>												
						<li><a class="" href="./excluiProduto.jsp">
							<span class="fa fa-arrow-right">&nbsp;</span> Excluir
						</a></li>
						<li><a class="" href="./inativaProduto.jsp">
							<span class="fa fa-arrow-right">&nbsp;</span> Inativa
						</a></li>
					</ul>
				</li>
				<li class="parent "><a data-toggle="collapse" href="#sub-item-3">
						<em class="fa fa-navicon">&nbsp;</em> Gerenciar Estoque <span data-toggle="collapse" href="#sub-item-3" 
							class="icon pull-right"><em class="fa fa-plus"
						></em></span>
						</a>
						<ul class="children collapse" id="sub-item-3">
							<li><a class="" href="alterar-estoque.html">
								<span class="fa fa-arrow-right">&nbsp;</span> Alterar
							</a></li>
							<li><a class="" href="cadastrar-estoque.html">
									<span class="fa fa-arrow-right">&nbsp;</span> Cadastrar
								</a></li>
							<li><a class="" href="consultar-estoque.html">
								<span class="fa fa-arrow-right">&nbsp;</span> Consultar
							</a></li>
						</ul>
					</li>
					<li class="parent "><a data-toggle="collapse" href="#sub-item-4">
						<em class="fa fa-navicon">&nbsp;</em> Gerenciar Pedidos <span data-toggle="collapse" href="#sub-item-4" 
					class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-4">
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> Em processamento
					</a></li>
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> Aprovado
					</a></li>
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> Reprovado
					</a></li>
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> Em trânsito
					</a></li>
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> Entregue
					</a></li>
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> Em troca
					</a></li>
					<li><a class="" href="#">
							<span class="fa fa-arrow-right">&nbsp;</span> Troca autorizada
					</a></li>
					<li><a class="" href="#">
						<span class="fa fa-arrow-right">&nbsp;</span> Trocado
					</a></li>
				</ul>
			</li>
			<li><a href="login.html"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
		</ul>
	</div><!--/.sidebar-->