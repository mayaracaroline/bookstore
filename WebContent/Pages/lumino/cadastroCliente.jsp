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
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">Gerenciar clientes</li>
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
                        <div class="panel-heading">Cliente</div>
                        <div class="panel-body">
                        </div>
                      </div>
						
						<form role="form" action="/livraria/Pages/lumino/CadastraCliente" method="POST">
                            <input type="hidden" name="formName" value="cadastroCliente">
                            <div class="panel panel-info">
                              <div class="panel-heading">Dados Pessoais</div>
                              <div class="panel-body">
                                <div class="form-group">
                                  <label>Nome:</label>
                                  <input name="nome" class="form-control" >
                                  <label>Sobrenome:</label>
                                  <input name="sobrenome" class="form-control" >                    
                                  <label>Data de Nascimento:</label>
                                  <input name="data-nasc" type="date" class="form-control">
                                  <label>Gênero:</label>
                                  <select name="genero" class="form-control">
                                    <option value="SELECIONE">Selecione</option>
                                    <option value="FEMININO">Feminino</option>
                                    <option value="MASCULINO">Masculino</option>
                                    <option value="INDEFINIDO">Não desejo informar</option>
                                  </select>
                                  <label>CPF</label>
                                  <input name="cpf" class="form-control" onkeypress="mascara(this, '###.###.###-##')" maxlength="14">
                              </div>
                            </div> 
                           </div> <!-- Panel - Dados Pessoais -->
                            <div class="panel panel-info">
                              <div class="panel-heading">Contatos</div>
                              <div class="panel-body">
                                <div class="form-group">
                                  <label>Telefone</label><br>
                                  <label>Tipo:</label>
                                  <select name="tipo-telefone" class="form-control">
                                    <option value="SELECIONE">Selecione</option>
                                    <option value="RESIDENCIAL">Residencial</option>
                                    <option value="COMERCIAL">Comercial</option>
                                    <option value="CELULAR">Celular</option>
                                  </select>
                                  <input type="text" name="ddd" class="form-control" placeholder="(11)">
                                  <input type="text" name="telefone" class="form-control" placeholder="99592-6070"> 
                              </div>
                            </div> 
                           </div> <!-- Panel - Contatos -->
                            <div class="panel panel-info">
                              <div class="panel-heading">Dados de Usuário</div>
                              <div class="panel-body">
                                <div class="form-group">
                                  <label>E-mail:</label>
                                  <input name="email" class="form-control" >
                                  <label>Senha:</label>
                                  <input name="senha" type="password" class="form-control" >
                              </div>
                            </div> 
                           </div> <!-- Panel - Dados de usuário -->  
                                                       
                           <div class="panel panel-info">
                            <div class="panel-heading">Endereço Residencial</div>
                              <div class="panel-body">
                                <div class="form-group">
                                   <label>Tipo de residência: *</label>
                                    <select id="tipo-residencia0" name="tipo-residencia0" class="form-control"  >
                                      <option value="SELECIONE">Selecione</option>
                                      <option value="CASA">Casa</option>
                                      <option value="APARTAMENTO">Apartamento</option>
                                      <option value="CONDOMINIO">Condomínio</option>
                                      <option value="SITIO">Sítio</option>
                                      <option value="CHACARA">Chacára</option>                                     
                                    </select> 
                                    
                                  <label for="cep0">
                                    CEP: *
                                    <input id="cep0" name="cep0" id="cep0" class="form-control" onchange="complementarEndereco(0)" type="text" value="">
                                  </label><br>
                                  <label>Tipo do logradouro: *</label> <br>
                                  <label>
                                  <select id="tipo-logradouro0" name="tipo-logradouro0" class="form-control" >
                                    <option value="">Selecione</option>
                                    <option value="1">Rua</option>
                                    <option value="2">Avenida</option>
                                    <option value="3">Estrada</option>                                      
                                  </select>
                                  </label><br>
                                  <label for="logradouro0">
                                    Logradouro: *
                                    <input name="logradouro0" id="logradouro0" class="form-control" type="text" readonly="readonly">
                                  </label><br>
                                  <label for="bairro0">
                                      Bairro: *
                                      <input name="bairro0" id="bairro0" class="form-control" type="text" value="" readonly="readonly">
                                  </label> <br>
                                  <label for="cidade0">
                                      Cidade: *
                                      <input name="cidade0" id="cidade0" class="form-control" type="text" value="" readonly="readonly" >
                                  </label><br>
                                  <label for="estado0">
                                      UF: *
                                      <input name="estado0" id="estado0" class="form-control" type="text" value="" readonly="readonly">
                                  </label><br>
                                  <label for="numero0">
                                    Nº: *
                                    <input name="numero0" id="numero0" class="form-control" type="text" value="" size="2">
                                  </label> <br>
                                   <br>
                                  <label>País: *</label>
                                  <select name="pais0" class="form-control" >
                                    <option value="BRASIL" selected>Brasil</option>
                                  </select>   
                                          
                                  <label>Observações:</label>
                                  <input class="form-control" type="text" name="observacoes0" id="observacoes0" placeholder="Ex: Ap. 25, Fundos etc." >
                                  <div class="checkbox">
                                     <label>
                                       <input name="checkbox-endereco-entrega" type="checkbox"  onclick="preencherEnderecoEntrega()" value=""> Considerar também para entrega
                                     </label><br><br>
                                     <label>
                                       <input name="checkbox-endereco-cobranca" type="checkbox" onclick="preencherEnderecoCobranca()" value=""> Considerar também para cobrança
                                     </label>
                                  </div>
                                </div>
                              </div> 
                             </div> <!-- Panel - Endereço Residencial -->
                           
                             <div class="panel panel-info endereco-entrega">
                              <div class="panel-heading">Endereço Entrega</div>
                                <div class="panel-body">
                                  <div class="form-group">
                                     <label>Tipo de residência: *</label>
                                      <select name="tipo-residencia1" id="tipo-residencia1" class="form-control"  >
                                        <option value="">Selecione</option>
                                        <option value="CASA">Casa</option>
                                        <option value="APARTAMENTO">Apartamento</option>
                                      </select>
                                      <label>
                                        CEP: *
                                        <input name="cep1" id="cep1" class="form-control" onchange="complementarEndereco(1)" type="text" value="">
                                      </label> <br>                                      
                                      <label>Tipo do logradouro: *</label>
                                      <select name="tipo-logradouro1" id="tipo-logradouro1" class="form-control" >
                                      <option value="">Selecione</option>
                                      <option value="1">Rua</option>
                                      <option value="2">Avenida</option>
                                      <option value="3">Estrada</option> 
                                      </select>
                                    <label for="logradouro1">
                                      Logradouro: *
                                      <input name="logradouro1" id="logradouro1" class="form-control" type="text"  value="" readonly>
                                    </label><br>
                                    <label for="bairro1">
                                        Bairro: *
                                        <input name="bairro1" id="bairro1" class="form-control" type="text" value="" readonly>
                                    </label><br>
                                    <label for="cidade1">
                                      Cidade: *
                                      <input name="cidade1" id="cidade1" class="form-control" type="text" value="" readonly="readonly">
                                     </label><br>                                     
                                    <label for="estado1">UF: *
                                      <input name="estado1" id="estado1" class="form-control" type="text" value="" readonly="readonly">
                                    </label><br>
                                    <label for="numero1">
                                      Nº: *
                                      <input name="numero1"  id="numero1" class="form-control" type="text"  value="" size="2">
                                    </label><br>                                      
                                    <label>País: *</label>
                                    <select name="pais1" class="form-control" >
                                      <option value="BRASIL" selected>Brasil</option>
                                    </select>                                     
                                    <label>Observações:</label>
                                    <input class="form-control" type="text" name="observacoes1" id="observacoes1" placeholder="Ex: Ap. 25, Fundos etc." >
                                  </div>
                                </div> 
                             </div> <!-- Panel - Endereço Entrega -->                              
                             <div class="panel panel-info endereco-cobranca">
                              <div class="panel-heading">Endereço Cobrança</div>
                                <div class="panel-body">
                                  <div class="form-group">
                                     <label>Tipo de residência: *</label>
                                      <select name="tipo-residencia2" id="tipo-residencia2" class="form-control"  >
                                        <option value="">Selecione</option>                                  
                                        <option  value="CASA">Casa</option>
                                        <option value="APARTAMENTO">Apartamento</option>
                                      </select>
                                      <label>
                                        CEP: *
                                        <input name="cep2" id="cep2" class="form-control" onchange="complementarEndereco(2)" type="text" value="" >
                                      </label><br>                                        
                                      <label>Tipo do logradouro: *</label>
                                      <select name="tipo-logradouro2" id="tipo-logradouro2" class="form-control" >
                                      <option value="">Selecione</option>
                                      <option value="1">Rua</option>
                                      <option value="2">Avenida</option>
                                      <option value="3">Estrada</option> 
                                      </select>
                                    <label for="logradouro2">
                                      Logradouro: *
                                      <input name="logradouro2" id="logradouro2" class="form-control" type="text"  value="" readonly>
                                    </label><br>
                                    <label for="bairro2">
                                      Bairro: *
                                      <input name="bairro2" id="bairro2" class="form-control" type="text" value="" readonly>
                                    </label><br>                                    
                                    <label for="cidade2">
                                      Cidade: *
                                      <input name="cidade2" id="cidade2" class="form-control" type="text" value="" readonly>
                                    </label><br>                                     
                                    <label>UF: *
                                      <input name="estado2" id="estado2" class="form-control" type="text" value="" readonly>
                                    </label><br>
                                    <label for="numero2">
                                      Nº: *
                                      <input name="numero2" id="numero2" class="form-control" type="text"  value="" size="2">
                                    </label><br> 
                                    <label>País: *</label>
                                    <select name="pais2" class="form-control" >
                                      <option value="BRASIL" selected>Brasil</option>
                                    </select>                                                                                                            
                                    <label>Observações:</label>
                                    <input class="form-control" type="text" name="observacoes2" id="observacoes2" placeholder="Ex: Ap. 25, Fundos etc." >
                                  </div>
                                </div> 
                             </div> <!-- Panel - Endereço Cobrança -->   
                             <div class="panel panel-info">
                              <div class="panel-heading">Dados do cartão</div>
                                <div class="panel-body">
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
                                     <label for="checkbox-cartao-preferencial">
                                       <input name="checkbox-cartao-preferencial" type="checkbox" value=""> Preferencial                                     </label><br><br>
                                     </label>
                                  </div>  
                                </div> 
                             </div> <!-- Panel - Cartão -->                   						            
							
							   <button type="reset"  class="btn btn-default">Cancelar</button>
							   <button type="submit" class="btn btn-primary" name="operacao" value="SALVAR">Salvar</button>
							
							</form>
						</div>
					</div>
				</div><!-- /.panel-->
			</div><!-- /.col-->
		</div><!-- /.row -->
	</div><!--/.main-->
  
  <script>
  	
  	
  	function complementarEndereco(number) {
  		let cep = document.getElementById('cep'+number)
  		if(validaCep(cep.value)) {
  	  	  	fetch('http://viacep.com.br/ws/'+ cep.value +'/json/')
  	  	  	.then(response => {
  	  	  		response.json()
  	  	  		.then((address) => {
  	  				meu_callback(address, number)  	  				
  	  	  		})
  	  	  	})	
  			
  		} else {
          //CEP não Encontrado.
          limpa_formulario_cep(number);
          alert("CEP inválido.");
        }

  	}
  	
  	
  	
  	function limpa_formulario_cep(number) {
        //Limpa valores do formulário de cep.
        document.getElementById('cep'+number).value=""
		document.getElementById('logradouro'+number).value= ""
		document.getElementById('bairro'+number).value= ""
		document.getElementById('cidade'+number).value= ""
		document.getElementById('estado'+number).value= ""
    }

    function meu_callback(conteudo, number) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
				document.getElementById('logradouro'+number).value= conteudo.logradouro
  				document.getElementById('bairro'+number).value= conteudo.bairro
  				document.getElementById('cidade'+number).value= conteudo.localidade
  				document.getElementById('estado'+number).value= conteudo.uf
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep(number);
            alert("CEP não encontrado.");
        }
    }
    
    function validaCep(cep) {
    	return cep.split('').length === 8;
    }
  	
  	

  </script>
	
	<jsp:include page= "./scripts.jsp" />
	
</body>
</html>
