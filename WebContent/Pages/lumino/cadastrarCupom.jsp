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
