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
				<li class="active">Gerenciar clientes</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
			 <h1 class="page-header">Consultar</h1>
			</div>
		</div><!--/.row-->
				
				<div class="panel ">
					<div class="panel-body">
                      <div class="panel panel-default">
                        <div class="panel-heading">Cliente</div>
                        <div class="panel-body">
                        </div>
                      </div>
						
						<form role="form" action="/livraria/CadastraCliente" method="POST">
            
                            <div class="panel panel-info">
                              <div class="panel-heading">Dados Pessoais</div>
                              <div class="panel-body">
                                <div class="form-group">
                                  <label>Nome:</label>
                                  <input name="nome" class="form-control" value="${cliente.nome}">
                                  <label>Sobrenome:</label>
                                  <input name="sobrenome" class="form-control" value="${cliente.sobrenome}" >                    
                                  <label>Data de Nascimento:</label>
                                  <input name="data-nasc" type="date" class="form-control">
                                  <label>Gênero:</label>
                                  <select name="genero" class="form-control">
                                    <option value="${cliente.genero}">Feminino</option>
                                  </select>
                                  <label>CPF</label>
                                  <input value="${cliente.cpf}" name="cpf" class="form-control" onkeypress="mascara(this, '###.###.###-##')" maxlength="14">
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
                                    <option value="RESIDENCIAL">${cliente.telefone.tipo}</option>
                                  </select>
                                  <input type="text" name="ddd" class="form-control" value="${cliente.telefone.ddd}">
                                  <input type="text" name="telefone" class="form-control" value="${cliente.telefone.numero}"> 
                              </div>
                            </div> 
                           </div> <!-- Panel - Contatos -->
                            <div class="panel panel-info">
                              <div class="panel-heading">Dados de Usuário</div>
                              <div class="panel-body">
                                <div class="form-group">
                                  <label>E-mail:</label>
                                  <input name="email" class="form-control" value="${cliente.usuario.username}">
                              </div>
                            </div> 
                           </div> <!-- Panel - Dados de usuário -->  
                                                       
                           <div class="panel panel-info">
                            <div class="panel-heading">Endereço Residencial</div>
                              <div class="panel-body">
                                <div class="form-group">
                                   <label>Tipo de residência: *</label>
                                    <select name="tipo-residencia0" class="form-control"  >
                                      <option selected >${cliente.enderecoResidencial.tipoResidencia}</option>                                     
                                    </select>
                                    <label>Tipo do logradouro: *</label>
                                    <select name="tipo-logradouro0" class="form-control" >
                                      <option value="4">${cliente.enderecoResidencial.tipoLogradouro.tipo}</option>
                                    </select>
                                  <label for="logradouro0">
                                    Logradouro: *
                                    <input name="logradouro0" class="form-control" type="text" value="${cliente.enderecoResidencial.logradouro}" >
                                  </label>
                                  <label for="numero0">
                                    Nº: *
                                    <input name="numero0" class="form-control" type="text" value="${cliente.enderecoResidencial.numero}" size="2">
                                  </label>
                                  <label for="bairro0">
                                      Bairro: *
                                      <input name="bairro0" class="form-control" type="text" value="${cliente.enderecoResidencial.bairro}" >
                                  </label>
                                  <label>
                                   CEP: *
                                    <input name="cep0" class="form-control" type="text" value="${cliente.enderecoResidencial.cep}">
                                  </label><br>
                                  <label>País: *</label>
                                  <select name="pais0" class="form-control" >
                                    <option value="1" selected>${cliente.enderecoResidencial.pais.nome}</option>
                                  </select>   
                                  <label>UF: *</label>
                                  <select name="estado0" class="form-control" >
                                    <option value="1" selected>${cliente.enderecoResidencial.cidade.estado.nome}</option>
                                  </select>
                                    <label>Cidade: *</label>
                                    <select name="cidade0" class="form-control" >
                                      <option selected value="1">${cliente.enderecoResidencial.cidade.nome}</option>
                                    </select>                                  
                                  <label>Observações:</label>
                                  <input class="form-control" type="text" name="observacoes0" value="${cliente.enderecoResidencial.observacao}">
                                </div>
                              </div> 
                             </div> <!-- Panel - Endereço Residencial -->
                           
                             <div class="panel panel-info endereco-entrega">
                              <div class="panel-heading">Endereço Entrega</div>
                                <div class="panel-body">
                                  <div class="form-group">
                                     <label>Tipo de residência: *</label>
                                      <select name="tipo-residencia1" class="form-control"  >
                                         <option selected >${cliente.enderecoEntrega.tipoResidencia}</option>   
                                      </select>
                                      <label>Tipo do logradouro: *</label>
                                      <select name="tipo-logradouro1" class="form-control" >
                                        <option value="0">${cliente.enderecoResidencial.tipoLogradouro.tipo}</option>
                                      </select>
                                    <label for="logradouro1">
                                      Logradouro: *
                                      <input name="logradouro1" class="form-control" type="text"  value="${cliente.enderecoEntrega.logradouro}" >
                                    </label>
                                    <label for="numero1">
                                      Nº: *
                                      <input name="numero1" class="form-control" type="text"  value="${cliente.enderecoEntrega.numero}" size="2">
                                    </label>
                                    <label for="bairro1">
                                        Bairro: *
                                        <input name="bairro1" class="form-control" type="text" value="${cliente.enderecoEntrega.bairro}" >
                                    </label><br>
                                    <label>
                                      CEP: *
                                      <input name="cep1" class="form-control" type="text" value="${cliente.enderecoEntrega.cep}">
                                    </label>
                                    <label>País: *</label>
                                    <select name="pais1" class="form-control" >
                                      <option value="1" selected>${cliente.enderecoEntrega.pais.nome}</option>
                                    </select>   
                                    <label>UF: *</label>
                                    <select name="estado1" class="form-control" >
                                      <option value="1" selected>${cliente.enderecoEntrega.cidade.estado.nome}</option>
                                    </select>
                                    <label>Cidade: *</label>
                                    <select name="cidade1" class="form-control" >
                                      <option  value="1"selected>${cliente.enderecoEntrega.cidade.nome}</option>
                                    </select>
                                    <label>Observações:</label>
                                    <input class="form-control" type="text" name="observacoes1" value="${cliente.enderecoEntrega.observacao}">
                                  </div>
                                </div> 
                             </div> <!-- Panel - Endereço Entrega -->                              
                             <div class="panel panel-info endereco-cobranca">
                              <div class="panel-heading">Endereço Cobrança</div>
                                <div class="panel-body">
                                  <div class="form-group">
                                     <label>Tipo de residência: *</label>
                                      <select name="tipo-residencia2" class="form-control"  >
                                        <option>${cliente.enderecoCobranca.tipoResidencia}</option>
                                      </select>
                                      <label>Tipo do logradouro: *</label>
                                      <select name="tipo-logradouro2" class="form-control" >
                                        <option value="10">${cliente.enderecoCobranca.tipoLogradouro.tipo}</option>
                                      </select>
                                    <label for="logradouro2">
                                      Logradouro: *
                                      <input name="logradouro2" class="form-control" type="text"  value="${cliente.enderecoCobranca.logradouro}" >
                                    </label>
                                    <label for="numero2">
                                      Nº: *
                                      <input name="numero2" class="form-control" type="text"  value="${cliente.enderecoCobranca.numero}" size="2">
                                    </label>
                                    <label for="bairro2">
                                      Bairro: *
                                      <input name="bairro2" class="form-control" type="text" value="${cliente.enderecoCobranca.bairro}" >
                                    </label><br>
                                  <label>
                                      CEP: *
                                      <input name="cep2" class="form-control" type="text" value="${cliente.enderecoCobranca.cep}">
                                  </label>                                    
                                    <label>País: *</label>
                                    <select value="1" name="pais2" class="form-control" >
                                      <option value="2" selected>${cliente.enderecoCobranca.pais.nome}</option>
                                    </select>   
                                    <label>UF: *</label>
                                    <select name="estado2" class="form-control" >
                                      <option value="1" selected>${cliente.enderecoCobranca.cidade.estado.nome}</option>
                                    </select>
                                    <label>Cidade: *</label>
                                    <select name="cidade2" class="form-control" >
                                      <option selected>${cliente.enderecoCobranca.cidade.nome}</option>
                                    </select>                                    
                                    <label>Observações:</label>
                                    <input class="form-control" type="text" name="observacoes2" value="${cliente.enderecoCobranca.observacao}" >
                                  </div>
                                </div> 
                             </div> <!-- Panel - Endereço Cobrança -->   
                             <div class="panel panel-info">
                              <div class="panel-heading">Dados do cartão</div>
                                <div class="panel-body">
                                    <label for="nome-titular">Nome do titular: * </label> <br>
                                    <input name="nome-titular" class="form-control" type="text"  value="${cliente.cartao.nomeTitular}" >
                                    <label for="numero-cartao">
                                      Número: *
                                      <input name="numero-cartao" class="form-control" type="text"  value="${cliente.cartao.numero}">
                                    </label> <br>
                                    <label for="cod-seguranca">
                                      Código de Segurança: *
                                      <input name="cod-seguranca" class="form-control" type="text" value="${cliente.cartao.codSeguranca}" >
                                    </label><br>                                                                      
                                  <div class="form-group">
                                     <label>Selecione a bandeira: *
                                       <select name="bandeira" class="form-control"  >
                                         <option>${cliente.cartao.bandeira.nome}</option>
                                       </select>
                                     </label>
                                  </div>
                                  <div class="checkbox">
                                     <label for="checkbox-endereco-preferencial">
                                       <input id="preferencial" name="checkbox-endereco-preferencial" type="checkbox" value=""> Preferencial                                     </label><br><br>
                                     </label>
                                     <script>
                                     	const checkbox = document.getElementById("preferencial");
                                     	if(${cartao.preferencial}) {
                                     		checkbox.setAttribute('checked', 'checked');
                                     	}
                                     </script>
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
	
<script src="../../js/jquery-1.11.1.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
	<script src="../../js/chart.min.js"></script>
	<script src="../../js/chart-data.js"></script>
	<script src="../../js/easypiechart.js"></script>
	<script src="../../js/easypiechart-data.js"></script>
	<script src="../../js/bootstrap-datepicker.js"></script>
	<script src="../../js/custom.js"></script>
	
</body>
</html>
