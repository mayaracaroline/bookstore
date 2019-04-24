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
    <title>Checkout | E-Shopper</title>
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
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href=""><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
								<li><a href=""><i class="fa fa-envelope"></i> info@domain.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href=""><i class="fa fa-facebook"></i></a></li>
								<li><a href=""><i class="fa fa-twitter"></i></a></li>
								<li><a href=""><i class="fa fa-linkedin"></i></a></li>
								<li><a href=""><i class="fa fa-dribbble"></i></a></li>
								<li><a href=""><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-md-4 clearfix">
						<div class="logo pull-left">
							<a href="index.html"><img src="images/home/logo.png" alt="" /></a>
						</div>
						<div class="btn-group pull-right clearfix">
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
									USA
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="">Canada</a></li>
									<li><a href="">UK</a></li>
								</ul>
							</div>
							
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
									DOLLAR
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="">Canadian Dollar</a></li>
									<li><a href="">Pound</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-8 clearfix">
						<div class="shop-menu clearfix pull-right">
							<ul class="nav navbar-nav">
								<li><a href=""><i class="fa fa-user"></i> Account</a></li>
								<li><a href=""><i class="fa fa-star"></i> Wishlist</a></li>
								<li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li>
								<li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li>
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
								<li class="dropdown"><a href="#">Shop<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="shop.html">Products</a></li>
										<li><a href="product-details.html">Product Details</a></li> 
										<li><a href="checkout.html" class="active">Checkout</a></li> 
										<li><a href="cart.html">Cart</a></li> 
										<li><a href="login.html">Login</a></li> 
                                    </ul>
                                </li> 
								<li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="blog.html">Blog List</a></li>
										<li><a href="blog-single.html">Blog Single</a></li>
                                    </ul>
                                </li> 
								<li><a href="404.html">404</a></li>
								<li><a href="contact-us.html">Contact</a></li>
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
				  <li class="active">Check out</li>
				</ol>
			</div><!--/breadcrums-->

			<div class="step-one">
				<h2 class="heading">Step1</h2>
			</div>

			<div class="shopper-informations">
				<div class="review-payment">
					<h2>Itens no Carrinho</h2>
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
								  <button type="submit" class="cart_quantity_delete" onclick="excluirItemCarrinho(${itemCarrinho.produto.id})"><i class="fa fa-times"></i></button>
								</td>
							  </tr>
						   </c:forEach>
							<tr>
								<td colspan="4">
                                </td>
								<td colspan="2">
									<table class="table table-condensed total-result">
										<tr>
											<td>Cart Sub Total</td>
											<td>R$59,00</td>
										</tr>
										<tr>
											<td>Exo Tax</td>
											<td>R$2,00</td>
										</tr>
										<tr class="shipping-cost">
											<td>Cupons</td>
											<td>Free</td>										
										</tr>
										<tr>
											<td>Total</td>
											<td><span>$61</span></td>
										</tr>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</div>			
				<div class="row">
                  <div class="col-sm-12">
						<div class="shopper-informations">
							<div class="review-payment">
								<h2>Endereço</h2>
							</div>
							<div class="panel panel-warning">
								<div class="panel-heading end-preferencial">
									<strong>Endereço de entrega</strong> 
								</div>
								<div class="panel-body">
									<select name="endereco-entrega" class="form-control">
										<option class="cep-entrega" value="${enderecoEntrega.cep}">
											${enderecoEntrega.tipoLogradouro.tipo} ${enderecoEntrega.logradouro}, ${enderecoEntrega.numero} - 
											${enderecoEntrega.numero} - ${enderecoEntrega.cidade} ${enderecoEntrega.estado}
										<option>
									</select>
									<!-- Fazer requisição para VHCliente com parametro Consultar e formName carrinho
									Colocar um atributo na requisição ou sessão
									Exemplo: endereço do cliente	-->	
									<!-- Trigger the modal with a button -->
									<a class="btn btn-primary" href="#" data-toggle="modal" data-target="#myModal">Escolher outro endereço</a>									
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="row">
                  <div class="col-sm-12">
						<div class="review-payment">
							<h2>Formas de Pagamento</h2>
						</div>	
						<div class="panel panel-warning" >
							<div class="panel-heading end-preferencial">
								<strong>Cartão 1</strong> 
							</div>
							<div class="panel-body">
								<div class="input-group">
								  <span class="input-group-addon" id="basic-addon1">Nome do titular</span>
								  <input name="nome-titular" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
								<select name="numero-cartao" class="form-control">
									<option>
										${cartao.bandeira} | ${enderecoEntrega.numero}
									<option>
								</select>
								<div class="input-group">
								  <span class="input-group-addon" id="basic-addon1">Cod. de segurança</span>
								  <input name="cod-seguranca" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
								<div class="input-group">
								  <span class="input-group-addon" id="basic-addon1">R$</span>
								  <input name="valor1" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="panel panel-warning">
							<div class="panel-heading end-preferencial">
								<strong>Cartão 2</strong> 
							</div>
							<div class="panel-body">
								<div class="input-group">
								  <span class="input-group-addon" id="basic-addon1">Nome do titular</span>
								  <input name="nome-titular" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>	
								<select name="numero-cartao" class="form-control">
									<option>
										${cartao.bandeira} | ${enderecoEntrega.numero}
									<option>
								</select>										
								<div class="input-group">
								  <span class="input-group-addon" id="basic-addon1">Cod. de segurança</span>
								  <input name="cod-seguranca" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
								<div class="input-group">
								  <span class="input-group-addon" id="basic-addon1">R$</span>
								  <input name="valor2" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<!-- Trigger the modal with a button -->
						<a class="btn btn-primary" href="#" data-toggle="modal" data-target="#modalAdicionaCartao">Adicionar outro cartão </a>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="review-payment">
								<h2>Cupons</h2>
							</div>
								<div class="panel panel-default">
									<ul class="list-group">
										<li class="list-group-item">
											<input type="radio" id="cartao1" name="cuponsPromo">
											<strong>Tipo: </strong>Promocional<br>
											<strong>Código:</strong> HACXKF4BIPCD<br>
											<strong>Valor:</strong>R$50,00<br>
											<label>Validade:</label> 15/03/2019<br>
										</li>
										<li class="list-group-item">
												<input type="radio" id="cartao2" name="cuponsPromo">
												<strong>Tipo: </strong>Promocional<br>
												<strong>Código:</strong> HACXKF4BIPCD<br>
												<strong>Valor:</strong>R$50,00<br>
												<label>Validade:</label> 15/03/2019<br>
										</li>
										<li class="list-group-item">
											<input type="checkbox" id="cartao3" name="cuponsTroca">
											<strong>Tipo: </strong>Troca<br>
											<strong>Código:</strong> HACXKF4BIPCD<br>
											<strong>Valor:</strong>R$50,00<br>
											<label>Validade:</label> 25/08/2019<br>
										</li>
										<li class="list-group-item">
											<input type="checkbox" id="cartao4" name="cuponsDiferenca">
											<strong>Tipo: </strong>Diferença<br>
											<strong>Código:</strong> HACXKF4BIPCD<br>
											<strong>Valor:</strong>R$50,00<br>
											<label>Validade:</label> 25/08/2019<br>
										</li>               														
									</ul>
								</div>
						</div>
                        <button  name="operacao" value="SALVAR" type="submit" class="btn btn-fefault cart right">
                           <i class="fa fa-shopping-cart"></i> Adicionar
                        </button>
					</div>
				</div>
			</div>
		</div>
	</section> <!--/#cart_items-->
	
	<!-- MODAL ESCOLHER ENDEREÇO -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Escolha o endereço1</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							
							<div class="row">
                               <div class="col-sm-9"> 
    								<label for="escolher-endereco0">
    									<input type="radio" id="end0" name="escolher-endereco0" checked>
    									<h4>Endereço Residencial </h4>
    									<p>	
    										${enderecoResidencial.tipoLogradouro.tipo} ${enderecoResidencial.logradouro}, ${enderecoResidencial.numero} - 
    										${enderecoResidencial.numero} - ${enderecoResidencial.cidade} ${enderecoResidencial.estado}
    									</p>		
    								</label>
                               </div>	
							</div>
                            <div class="row">
    							<div class="col-sm-9">
    								<label for="escolher-endereco1">
    									<input type="radio" id="end2" name="escolher-endereco1">
    									<h4>Endereço Cobrança</h4>
    									<p>
    										${enderecoCobranca.tipoLogradouro.tipo} ${enderecoCobranca.logradouro}, ${enderecoCobranca.numero} - 
    										${enderecoCobranca.numero} - ${enderecoCobranca.cidade} ${enderecoCobranca.estado}
    									</p>
    								</label>						
    							</div>
                           </div>
					 </div>
				  </div>
			 </div>
				<div class="modal-footer">
						<button type="button" class="btn btn-info" data-dismiss="modal" data-toggle="modal" data-target="#adicionarEndereco">
							Adicionar outro endereço
						</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div> 
	
	<!-- ////// MODAL - ESCOLHER ENDEREÇO //// -->

	<!-- MODAL ADICIONAR OUTRO ENDEREÇO -->

	<div id="adicionarEndereco" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Adicionar endereço</h4>
				</div>
				<div class="modal-body">
					<div class="row">
                      <div class="col-sm-9">
						<label for="descricaoEndereco" data-toggle="tooltip" data-placement="right" title="Ex: Casa, Trabalho">
							Descrição do endereço *:<br>
							<input type="text" name="descricaoEndereco">
						</label><br>
						<label>Tipo de residência: *</label>
						<select name="tipo-residencia0" class="form-control"  >
						  <option value="SELECIONE">Selecione</option>
						  <option value="CASA">Casa</option>
						  <option value="APARTAMENTO">Apartamento</option>
						  <option value="CONDOMINIO">Condomínio</option>
						  <option value="SITIO">Sítio</option>
						  <option value="CHACARA">Chacára</option>                                     
						</select>
                      </div>
					</div>
					<div class="row">
                       <div class="col-sm-9">
						<label>Tipo do logradouro: *</label>
						<select name="tipo-logradouro4">
							<option value="">Selecione</option>
							<option value="1">Rua</option>
							<option value="2">Avenida</option>
							<option value="3">Estrada</option>                                      
							<option value="1">Rua</option>
						</select>
                       </div>					
					</div>
					<div class="row">
                      <div class="col-sm-12">
						<label for="logradouro4">
							Logradouro: *
							<input name="logradouro4" id="logradouro4" class="form-control" type="text" readonly="readonly">
						</label>
						 <label for="bairro4">
							Bairro: *
							<input name="bairro4" id="bairro4" class="form-control" type="text" value="" readonly="readonly">
						</label> 
						<label for="cidade4">
							Cidade: *
							<input name="cidade4" id="cidade4" class="form-control" type="text" value="" readonly="readonly" >
						</label>
						<label for="estado4">
							UF: *
							<input name="estado4" id="estado4" class="form-control" type="text" value="" readonly="readonly">
						</label>
						<label for="numero4">
							Nº: *
							<input name="numero4" id="numero4" class="form-control" type="text" value="" size="2">
						</label> 
						 <br>
						<label>País: *</label>
						  <select name="pais4" class="form-control" >
							<option value="1" selected readonly>Brasil</option>
						  </select>          
						<label>Observações:</label>
						<input class="form-control" type="text" name="observacoes0" placeholder="Ex: Ap. 25, Fundos etc." >	
					  </div>
                    </div>
					<div class="row">
                      <div class="col-sm-9">
                        <label>
                          <input type="checkbox" name="adicionarPerfil" id="adicionarPerfil">
						  Adicionar endereço ao perfil
                        </label>								
						<div class="alert alert-success" role="alert" id="success">
							Endereço salvo com sucesso.
						</div>
                      </div>
					</div>
                  
				</div>
				<div class="modal-footer">
                    <button type="button" class="btn btn-info" onclick="showAlert('success')" id="salvarEndereco">Salvar</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- ////// MODAL - ADICIONAR OUTRO ENDEREÇO //// -->
	
	<!-- MODAL ADICIONAR OUTRO ENDEREÇO -->

	<div id="modalAdicionaCartao" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Adicionar cartão</h4>
				</div>
				<div class="modal-body">
					<div class="row">
                      <div class="col-sm-9">
						<label for="descricaoEndereco" data-toggle="tooltip" data-placement="right" title="Ex: Casa, Trabalho">
							<label for="nome-titular">Nome do titular: * </label> <br>
							<input name="nome-titular" class="form-control" type="text"  value="" >
							<label for="numero-cartao">
							  Número: *
							  <input name="numero-cartao" class="form-control" type="text"  value="">
							</label> <br>
							<label for="cod-seguranca">
							  Código de Segurança: *
							  <input name="cod-seguranca" class="form-control" type="text" value="" >
							</label><br>                                                                      
						  <div class="form-group">
							 <label>Selecione a bandeira: *
							   <select name="bandeira" class="form-control"  >
								 <option value="">Selecione</option>
								 <option value="1">Mastercard</option>
								 <option value="2">Visa</option>
							   </select>
							 </label>
						  </div>
						  <div class="checkbox">
							 <label for="checkbox-endereco-preferencial">
							   <input name="checkbox-endereco-preferencial" type="checkbox" value=""> Preferencial                                     </label><br><br>
							 </label>
							 <label>
								<input type="checkbox" name="adicionarPerfil" id="adicionarPerfil">
								Adicionar cartão ao perfil
							 </label>	
						  </div>
                      </div>
					</div>
					<div class="row">
                      <div class="col-sm-9">
						<div class="alert alert-success" role="alert" id="salvarCartao">
							Cartão salvo com sucesso.
						</div>
                      </div>
					</div>
                  
				</div>
				<div class="modal-footer">
                    <button type="button" class="btn btn-info" onclick="showAlert('salvarCartao')" id="salvarCartao">Salvar</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- ////// MODAL - ADICIONAR OUTRO ENDEREÇO //// -->	
	
	<jsp:include page= "./footer.jsp" />
	<jsp:include page= "./scripts.jsp" />
	
</body>
</html>