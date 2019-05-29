<%@page import="dominio.ItemCarrinho"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Carrinho de Compras | Alternativa</title>
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
      </div>
      <div class="col-md-8 clearfix">
        <div class="shop-menu clearfix pull-right">
          <ul class="nav navbar-nav">
            <li><a href="../../area-do-cliente/lumino/meus-pedidos.html"><i class="fa fa-user"></i> Minha conta</a></li>
            <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Meu carrinho(${sessionScope.carrinho.quantidadeProdutos})</a></li>
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
            <li class="dropdown"><a href="shop.html">Produtos</a></li> 
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

<section id="cart_items">
<div class="container">
  <div class="breadcrumbs">
    <ol class="breadcrumb">
      <li><a href="#">Home</a></li>
      <li class="active">Carrinho de compra</li>
    </ol>
  </div>
 <form action="/livraria/Pages/lumino/comprar" METHOD="POST"> 
  <div class="table-responsive cart_info">
    <table class="table table-condensed">
      <thead>
        <tr class="cart_menu">
          <td class="image">Item</td>
          <td class="description">Descrição</td>
          <td class="price">Preço</td>
          <td class="quantity">Quantidade</td>
          <td class="total">Total</td>
          <td></td>
        </tr>
      </thead>
      <tbody>
       <c:forEach var="itemCarrinho" items="${sessionScope.carrinho.itensCarrinho}">
          <tr data-cod="${itemCarrinho.produto.id}" >
            <td class="cart_product">
              <a href=""><img src="images/cart/one.png" alt=""></a>
            </td>
            <td class="cart_description">
              <h4><a href="">${itemCarrinho.produto.titulo}</a></h4>
              <p>Web ID: 1089772</p>
            </td>
            <td class="cart_price">
              <p>${itemCarrinho.produto.preco}</p>
            </td>
            <td class="cart_quantity">
              <div class="cart_quantity_button">
                <input class="cart_quantity_input" type="text" name="quantidade" min="1" autocomplete="off" size="2" value="${itemCarrinho.quantidade}" onchange="alterarQuantidadeItensCarrinho(${itemCarrinho.produto.id},this.value)">
              </div>
            </td>
            <td class="cart_total">
              <p class="cart_total_price">R$${itemCarrinho.produto.preco * itemCarrinho.quantidade }</p>
            </td>
            <td class="cart_delete">
              <button id="buttonComprar" type="button" class="cart_quantity_delete" onclick="excluirItemCarrinho(${itemCarrinho.produto.id})"><i class="fa fa-times"></i></button>
            </td>
          </tr>
       </c:forEach>
 
      </tbody>
    </table>
  </div>
</div>
</section> <!--/#cart_items-->

<section id="do_action">
  <div class="container">
    <div class="heading">
      <h3>Calcular frete</h3>
    </div>
    <div class="row">
      <div class="col-sm-3">
          <div class="input-group">
            <span class="input-group-addon">CEP</span>
            <input type="text" id="frete" class="form-control" value="" name="cep1" onblur="calcularFrete(this.value, 'carrinho')">
          </div>                                                                
          <div class="input-group">
            <span class="input-group-addon">R$</span>
            <input type="text" id="frete" class="form-control" value="${sessionScope.frete}" readonly>
            <span class="input-group-addon">.00</span>  
          </div>   
          <input type="hidden"  value="${sessionScope.frete}" name="tipo-entrega" readonly>             
      </div> 
    </div>   <!-- row -->
 
        
        <button type="submit" style="margin-bottom:30px" class="btn btn-primary">Comprar</button>
       </form>
    </div>
  </div> <!--container-->
</section><!--/#do_action-->
<section id="do_action">
 

            
        </div> <!-- chose-area-->
        </div> <!--col-sm-4-->
        
      </div>   <!-- row --> 
    </div> <!--container-->
  </section><!--/#do_action-->
  <section id="do_action">
      <div class="container">


      </div>

    
	<jsp:include page= "./footer.jsp" />
	<jsp:include page= "./scripts.jsp" />
    <script src="<c:url value="/js/checkout.js"/>"></script>
</body>
</html>