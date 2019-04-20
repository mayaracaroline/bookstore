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
    <title>Detalhes do Produto | E-Shopper</title>
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
    <jsp:useBean id="carrinho" class="controller.CarrinhoController"/>
    
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
								<li class="dropdown"><a href="#">Produtos</a></li> 					
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
	
	<section>
		<div class="container">
			<div class="row">
					<div class="col-sm-3">
							<div class="left-sidebar">
								<h2>Categoria</h2>
								<div class="panel-group category-products" id="accordian"><!--category-productsr-->
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordian" href="#mens">
													Administração
												</a>
											</h4>
										</div>
									</div>						
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="#womens">Artes e Fotografia</a>
											</h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Autoajuda</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Biografia</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Contos e crônicas</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Didáticos</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Direito</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Ficção Fantasiosa</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Ficção Científica</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">HQs</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Poesia</a></h4>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><a href="#">Romance</a></h4>
										</div>
									</div>
								</div><!--/category-products-->

						<div class="brands_products"><!--brands_products-->
                            <input type="hidden" value="productDetails"/>
							<h2>Autores</h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
									<li><a href="#"> J.K.Howlling</a></li>
									<li><a href="#"> Jojo Moyes</a></li>
									<li><a href="#"> Dan Brown</a></li>
									<li><a href="#"> Jenny Han</a></li>
									<li><a href="#"> George Orwell</a></li>
								</ul>
							</div>
						</div><!--/brands_products-->	
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
                  <form action="/livraria/Pages/lumino/carrinho" method="POST">
					<div class="product-details"><!--product-details-->
                      
						<div class="col-sm-5">
							<div class="view-product">
								<img src="images/shop/product12.jpg" alt="" />
							</div>
							<div id="similar-product" class="carousel slide" data-ride="carousel">
							</div>
						 </div>
						 <div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<h2>${livro.titulo}</h2>
                                <input type="hidden" name="titulo" value="${livro.titulo}">
								<p>Ray Bradbury</p>
								<p>Web ID: 1089772</p>
								<img src="images/product-details/rating.png" alt="" />
								<span>
									<span>R$59,00</span>
									<label>Quantidade:</label>
									<input name="quantidade" type="text" value="1" />
									<button
                                      name="operacao"
                                      value="SALVAR"
                                      type="submit" 
                                      class="btn btn-fefault cart"
                                    >
										<i class="fa fa-shopping-cart"></i>
										Adicionar
									</button>
								</span>
								<p><b>Disponibilidade:</b> Em estoque</p>
								<p><b>Condição:</b> Novo</p>
								<a href=""><img src="images/product-details/share.png" class="share img-responsive"  alt="" /></a>
							 </div><!--/product-information-->                              
						  </div>
					 </div><!--/product-details-->
							
							<div class="tab-pane fade active in" id="reviews" >              
								<div class="col-sm-12">
        							<c:forEach var="mensagem" items="${sessionScope.errosBloqueio}">
        								<div class="alert alert-danger" role="alert">
        									${mensagem}
        								</div>											
        							</c:forEach>
									<ul>
										<li><a href=""><i class="fa fa-user"></i>Sinopse</a></li>
									</ul>
                                    <input name="codigo" type="hidden" value="${livro.id}">                                    
									<p>${livro.sinopse}</p>
										 <table class="table">
												<thead class="thead-dark">
														<tr>
															<th scope="col">Características</th>
														</tr>		
												</thead>
												<tbody>
													<tr>	
														<td>Autor</td>
														<td>${livro.autor}</td>
                                                                              
													</tr>
													<tr>
														<td>Altura</td>
														<td>${livro.altura}cm</td>
													</tr>
													<tr>
														<td>Largura</td>
														<td>${livro.largura}</td>	
													</tr>
													<tr>
														<td>Peso</td>
														<td>${livro.peso}</td>	
													</tr>
													<tr>
														<td>Profundidade</td>
														<td>${livro.profundidade}</td>	
													</tr>
													<tr>
														<td>Ano da edição</td>
														<td name="ano" value="${livro.ano}">${livro.ano}</td>										
													</tr>
													<tr>
														<td>Categorias</td>
                                                        <td>
														  <c:forEach var="categoria" items="${livro.categorias}">
															${categoria.descricao};
                                                            <input type="hidden" name="genero" value="${categoria.id}">
														  </c:forEach>
                              							</td>																
													</tr>
													<tr>
														<td>Editora</td>
														<td>${livro.editora}</td>															
													</tr>
													<tr>
														<td>ISBN</td>
														<input type="hidden" name="isbn" value="${livro.isbn}" >${livro.isbn}</td>	
													</tr>
													<tr>
														<td>Quantidade de páginas</td>
														<td>${livro.quantidadePaginas}</td>	
													</tr>														
												</tbody>
											</table>
                                            <input type="hidden" name="autor" value="${livro.autor}"/>
                                            <input type="hidden" name="altura" value="${livro.altura}"/>
                                            <input type="hidden" name="largura" value="${livro.largura}">
                                            <input type="hidden" name="peso" value="${livro.peso}">
                                            <input type="hidden" name="profundidade" value="${livro.profundidade}">
                                            <input type="hidden" name="ano" value="${livro.ano}">
                                            <input type="hidden" name="genero" value="${categoria.descricao}">
                                            <input type="hidden" name="editora" value="${livro.editora}">
                                            <input type="hidden" name="isbn" value="${livro.isbn}" >
                                            <input type="hidden" name="quantidadePaginas" value="${livro.quantidadePaginas}">
						</div>	
                    </form>			
				</div>
			</div>
		</div>
	</section>
	 
	<jsp:include page= "./footer.jsp" />
	<jsp:include page= "./scripts.jsp" />

</body>
</html>