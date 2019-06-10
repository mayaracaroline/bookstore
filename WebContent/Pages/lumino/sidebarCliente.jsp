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
				<a class="navbar-brand" href="#"><span>Alternativa</span>Bookstore</a>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
				<img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">${sessionScope.clientes.nome}</div>
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
    <div class="divider"></div>
		<ul class="nav menu">
        <li class="active"><a href="areaCliente.jsp"><em class="fa fa-home">&nbsp;</em> Home</a></li>
				<li class="parent "><a data-toggle="collapse" href="#sub-item-1">
						<em class="fa fa-user">&nbsp;</em> Meus dados <span data-toggle="collapse" href="#sub-item-1" 
							class="icon pull-right"><em class="fa fa-plus"
						></em></span>
						</a>
						<ul class="children collapse" id="sub-item-1">
							<li><a class="" href="meusDados.jsp">
								<span class="fa fa-address-card-o">&nbsp;</span>Dados Pessoais
							</a></li>
							<li><a class="" href="alterarEndereco.jsp">
									<span class="fa fa-map-marker">&nbsp;</span> Endereço
								</a></li>
							<li><a class="" href="alterarSenha.jsp">
								<span class="fa fa-key">&nbsp;</span> Alterar senha
							</a></li>
							<li><a class="" href="meusCartoes.jsp">
									<span class="fa fa-credit-card-alt">&nbsp;</span> Meus cartões
								</a>
							</li>
						</ul>
					</li>
				  <li class="parent"><a href="/livraria/Pages/lumino/pedido?operacao=CONSULTAR"><em class="fa fa-file-text-o">&nbsp;</em> Meus pedidos</a></li>

				<li><a href="loginCliente.jsp"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
			</ul>
	</div><!--/.sidebar-->
