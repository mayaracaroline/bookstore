<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login | E-Shopper</title>
    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/prettyPhoto.css" />" rel="stylesheet">
    <link href="<c:url value="/css/price-range.css" />" rel="stylesheet">    
    <link href="<c:url value="/css/animate.css" />" rel="stylesheet">  
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">  
    <link href="<c:url value="/css/responsive.css" />" rel="stylesheet"> 
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<header id="header"><!--header-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-md-4 clearfix">
						<div class="logo pull-left">
							<a href="index.html"><img src="images/home/logo.png" alt="" /></a>
						</div>
						<div class="btn-group pull-right clearfix">
						</div>
					</div>
					<div class="col-md-8 clearfix">
						<div class="shop-menu clearfix pull-right">
							<ul class="nav navbar-nav">
								<li><a href="../../area-do-cliente/lumino/meus-pedidos.html"><i class="fa fa-user"></i>Minha conta</a></li>
								<li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Meu carrinho</a></li>
								<li><a href="login.html"><i class="fa fa-lock"></i> Login</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="index.html">Home</a></li>
								<li class="dropdown"><a href="#">Produtos</a>
                                                      </li> 
							
                                </li> 
			
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login com sua conta</h2>
						<form action="/livraria/Pages/lumino/autenticaCliente" method="POST">
                            <input type="hidden" name="formName" value="loginCliente">
							<input name="email" type="email" placeholder="E-mail" />
							<input name="senha" type="password" placeholder="Senha" />
							<span>
							<button name="operacao" value="CONSULTAR" type="submit" class="btn btn-default">Login</button>                              
						</form>
                        <c:if test="${not empty usuario}">
                            <div class="alert alert-danger" role="alert">
                            ${usuario}
                        </div>
                        </c:if>

					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OU</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>Cadastre-se</h2>
						<form action="/livraria/Pages/lumino/loginCliente">
							<input type="text" placeholder="Name"/>
							<input type="email" placeholder="Email Address"/>
							<input type="password" placeholder="Password"/>
							<button type="submit" class="btn btn-default">Cadastre-se</button>
						</form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
  
	<jsp:include page= "./footer.jsp" />
	<jsp:include page= "./scripts.jsp" />
  
</body>
</html>