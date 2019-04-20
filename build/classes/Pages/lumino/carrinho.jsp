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
            <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Meu carrinho(${fn:length(sessionScope.carrinho.itensCarrinho)})</a></li>
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
          <tr>
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
                <a class="cart_quantity_up" href="/livraria/Pages/lumino/carrinho?operacao=ALTERAR&codigo=${itemCarrinho.produto.id}"> + </a>
                <input class="cart_quantity_input" type="text" name="quantity" min="1" autocomplete="off" size="2" value="${itemCarrinho.quantidade}" readOnly>
                <a href="/livraria/Pages/lumino/carrinho?operacao=ALTERAR&codigo=${itemCaprrinho.produto.id}" class="cart_quantity_down"> - </a>
              </div>
            </td>
            <td class="cart_total">
              <p class="cart_total_price">R$${itemCarrinho.produto.preco * itemCarrinho.quantidade }</p>
            </td>
            <td class="cart_delete">
              <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
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
    
      
      <div class="col-sm-12">
        <div class="total_area">
            <label for="cep">CEP:</label><br>
            <input type="text" name="cep" id="cep">             
            <button class="btn btn-primary" type="button" id="calcular" onclick="showAlert('calculo-frete')">Calcular</button>
            <div class="alert alert-info" id="calculo-frete" role="alert">
              <input type="radio" name="tipo-entrega" id="economica">
              <label for="economica">Econômica - até 6 dias úteis - R$ 34,93</label><br>
              <input type="radio" name="tipo-entrega" id="express">
              <label for="express">Express - até 3 dias úteis - R$ 54,93</label>    
          </div>
        </div>
        </div> 
    </div>   <!-- row -->
    <div class="heading">
      <h3>Cupons</h3>
    </div> 
    <div class="row">
        <div class="col-sm-12">
          <div class="total_area">
            <ul class="list-group">
              <ul class="list-group-item ">
                <input type="radio" id="cartao1" name="cuponsPromo">
                <strong>Tipo: </strong>Promocional<br>
                <strong>Código:</strong> HACXKF4BIPCD<br>
                <strong>Valor:</strong>R$50,00<br>
                <label>Validade:</label> 15/03/2019<br>
              </ul>
              <ul class="list-group-item ">
                  <input type="radio" id="cartao2" name="cuponsPromo">
                  <strong>Tipo: </strong>Promocional<br>
                  <strong>Código:</strong> HACXKF4BIPCD<br>
                  <strong>Valor:</strong>R$50,00<br>
                  <label>Validade:</label> 15/03/2019<br>
                </ul>
              <ul class="list-group-item">
                <input type="checkbox" id="cartao3" name="cuponsTroca">
                <strong>Tipo: </strong>Troca<br>
                <strong>Código:</strong> HACXKF4BIPCD<br>
                <strong>Valor:</strong>R$50,00<br>
                <label>Validade:</label> 25/08/2019<br>
              </ul>
              <ul class="list-group-item">
                <input type="checkbox" id="cartao4" name="cuponsDiferenca">
                <strong>Tipo: </strong>Diferença<br>
                <strong>Código:</strong> HACXKF4BIPCD<br>
                <strong>Valor:</strong>R$50,00<br>
                <label>Validade:</label> 25/08/2019<br>
              </ul>                           
            </ul>
          </div>
        </div>
        
              <button style="margin-bottom:30px" class="btn btn-primary">Finalizar compra</button>
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
	<script src="<c:url value="/js/shop.js"/>"></script>

</body>
</html>