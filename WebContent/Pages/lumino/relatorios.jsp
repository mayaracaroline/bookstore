<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page = "./sidebar.jsp" /> 

    <script type="text/javascript">
    var quantidadeCategoriaPorGeneroFeminino = []
    var quantidadeCategoriaPorGeneroMasculino = []
    var quantidadeCategoriaPorFaixaEtaria1 = []
    var quantidadeCategoriaPorFaixaEtaria2 = []
    var quantidadeCategoriaPorFaixaEtaria3 = []
    var quantidadeCategoriaPorFaixaEtaria4 = []
    var randomScalingFactor = function(){ return Math.round(Math.random()*1000)};
	var barChartData = {
			labels : ["Administração","Artes e Fotografia","Autoajuda",
						"Biografia","Contos e crônicas","Didádicos",
						"Direito", "Ficção Fantasiosa", "HQs", "Poesia", "Romance"],
			
			datasets : [
				{
					fillColor : "rgba(220,220,220,0.5)",
					strokeColor : "rgba(220,220,220,0.8)",
					highlightFill: "rgba(220,220,220,0.75)",
					highlightStroke: "rgba(220,220,220,1)",
					data : quantidadeCategoriaPorGeneroFeminino
				},
				{
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 0.8)",
					highlightFill : "rgba(48, 164, 255, 0.75)",
					highlightStroke : "rgba(48, 164, 255, 1)",
					data : quantidadeCategoriaPorGeneroMasculino
				}
			]
		}
	
	var barChartDataIdade = {
			labels : ["Administração","Artes e Fotografia","Autoajuda",
						"Biografia","Contos e crônicas","Didádicos",
						"Direito", "Ficção Fantasiosa", "HQs", "Poesia", "Romance"],
			
			datasets : [
				{
					fillColor : "rgba(220,20,60,0.5)",
					strokeColor : "rgba(220,20,60,0.8)",
					highlightFill: "rgba(220,20,60,0.75)",
					highlightStroke: "rgba(220,20,60,1)",
					data : quantidadeCategoriaPorFaixaEtaria1
				},
				{
					fillColor : "rgba(255,20,147, 0.2)",
					strokeColor : "rgba(199,21,133, 0.8)",
					highlightFill : "rgba(199,21,133, 0.75)",
					highlightStroke : "rgba(199,21,133, 1)",
					data : quantidadeCategoriaPorFaixaEtaria2
				},
				{
					fillColor : "rgba(0,0,128,0.5)",
					strokeColor : "rgba(25,25,112,0.8)",
					highlightFill: "rgba(25,25,112,0.75)",
					highlightStroke: "rgba(25,25,112,1)",
					data : quantidadeCategoriaPorFaixaEtaria3
				},
				{
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 0.8)",
					highlightFill : "rgba(48, 164, 255, 0.75)",
					highlightStroke : "rgba(48, 164, 255, 1)",
					data : quantidadeCategoriaPorFaixaEtaria4
				}
			]
		}
	
		window.onload = function () {
  		var chart2 = document.getElementById("bar-chart").getContext("2d");
  		window.myBar = new Chart(chart2).Bar(barChartData, {
  		responsive: true,
  		scaleLineColor: "rgba(0,0,0,.2)",
  		scaleGridLineColor: "rgba(0,0,0,.05)",
  		scaleFontColor: "#c5c7cc"
  		});
  		var chart2 = document.getElementById("faixa-etaria").getContext("2d");
  		window.myBar = new Chart(chart2).Bar(barChartDataIdade, {
  		responsive: true,
  		scaleLineColor: "rgba(0,0,0,.2)",
  		scaleGridLineColor: "rgba(0,0,0,.05)",
  		scaleFontColor: "#c5c7cc"
  		});
	}
	
    </script>
    <c:forEach var="entry" items="${requestScope.categoria}">
      <script>
      	quantidadeCategoriaPorGeneroFeminino.push(${entry.value[0] })
      	quantidadeCategoriaPorGeneroMasculino.push(${entry.value[1]})
      </script> 
    </c:forEach>
    
   <c:forEach var="entry" items="${requestScope.idade}">
      <script>
      	
      	quantidadeCategoriaPorFaixaEtaria1.push(${entry.value[0]})
      	quantidadeCategoriaPorFaixaEtaria2.push(${entry.value[1]})
      	quantidadeCategoriaPorFaixaEtaria3.push(${entry.value[2]})
      	quantidadeCategoriaPorFaixaEtaria4.push(${entry.value[3]})

      </script> 
    </c:forEach>

  <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
      <ol class="breadcrumb">
         <li>
              <a href="#">
            <em class="fa fa-home"></em>
            </a>
           </li>
         <li class="active">Gerenciar produtos</li>
      </ol>
    </div><!--/.row-->
    
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Área do administrador</h1>
      </div>
    </div><!--/.row-->    
    <div class="panel panel-default">
      <div class="panel-heading">Relatórios</div>
      <div class="panel-body">
        <div class="col-md-12">
        
           <form action="/livraria/Pages/lumino/relatorios">
              <label> Data de Início</label>
              <input type="date" name="dataInicio"> 
              <input type="hidden" name="tag" value="categoria">
              <label>Data de Fim</label>
              <input type="date" name="dataFim"> 
              <input class="btn btn-primary" value="CONSULTAR" name="operacao" type="submit">

                

                  <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            Quantidade de pedidos por categoria e gênero
            <ul class="pull-right panel-settings panel-button-tab-right">
              <li class="dropdown"><a class="pull-right dropdown-toggle" data-toggle="dropdown" href="#">
                <em class="fa fa-cogs"></em>
              </a>
                <ul class="dropdown-menu dropdown-menu-right">
                  <li>
                    <ul class="dropdown-settings">
                      <li><a href="#">
                        <em class="fa fa-cog"></em> Settings 1
                      </a></li>
                      <li class="divider"></li>
                      <li><a href="#">
                        <em class="fa fa-cog"></em> Settings 2
                      </a></li>
                      <li class="divider"></li>
                      <li><a href="#">
                        <em class="fa fa-cog"></em> Settings 3
                      </a></li>
                    </ul>
                  </li>
                </ul>
              </li>
            </ul>
            <span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
          <div class="panel-body">
            <div class="canvas-wrapper">
              <canvas class="main-chart" id="bar-chart" height="200" width="600"></canvas>
              <div style="margin-left:350px; margin-bottom:25px;">Categorias</div>
              <div style="display:flex; flex-direction:rows; ">
                <div style="width:20px; heigth:20px; background-color:rgba(220,220,220,0.5); margin-right:10px"><p></p></div> Feminino<br>
                <div style="width:20px; heigth:20px; background-color:rgba(48, 164, 255, 1); margin-left:20px;margin-right:10px" ><p></p></div> Masculino
              </div>
            </div>
          </div>
        </div>
      </div>
    </div><!--/.row-->  
              
              

           </form>
           
           <form action="/livraria/Pages/lumino/relatorios">
              <label> Data de Início</label>
              <input type="date" name="dataInicio"> 
              <input type="hidden" name="tag" value="idade">
              <label>Data de Fim</label>
              <input type="date" name="dataFim"> 
              <input class="btn btn-primary" value="CONSULTAR" name="operacao" type="submit">
     <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            Quantidade de pedidos por categoria e faixa etária
            <ul class="pull-right panel-settings panel-button-tab-right">
              <li class="dropdown"><a class="pull-right dropdown-toggle" data-toggle="dropdown" href="#">
                <em class="fa fa-cogs"></em>
              </a>
                <ul class="dropdown-menu dropdown-menu-right">
                  <li>
                    <ul class="dropdown-settings">
                      <li><a href="#">
                        <em class="fa fa-cog"></em> Settings 1
                      </a></li>
                      <li class="divider"></li>
                      <li><a href="#">
                        <em class="fa fa-cog"></em> Settings 2
                      </a></li>
                      <li class="divider"></li>
                      <li><a href="#">
                        <em class="fa fa-cog"></em> Settings 3
                      </a></li>
                    </ul>
                  </li>
                </ul>
              </li>
            </ul>
            <span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
          <div class="panel-body">
            <div class="canvas-wrapper">
              <canvas class="main-chart" id="faixa-etaria" height="200" width="600"></canvas>
              <div style="margin-left:350px; margin-bottom:25px;">Categoria</div>
              <div style="display:flex; flex-direction:rows; ">
                <div style="width:20px; heigth:20px; background-color:rgba(220,20,60,0.5); margin-right:10px"><p></p></div> <20<br>
                <div style="width:20px; heigth:20px; background-color:rgba(255,20,147, 1); margin-left:20px;margin-right:10px" ><p></p></div> 21-40
                <div style="width:20px; heigth:20px; background-color:rgba(0,0,128,0.5); margin-left:20px;margin-right:10px"><p></p></div> 41-60<br>     
                <div style="width:20px; heigth:20px; background-color:rgba(0,0,128,0.5); margin-left:20px; margin-right:10px"><p></p></div> >61<br>           
              </div>
            </div>
          </div>
        </div>
      </div>
    </div><!--/.row-->  
           </form>
         </div>                         
      </div><!-- /.panel--> 
    </div><!-- /.panel-->
  </div><!-- /.col-->

  
  <jsp:include page= "./scripts.jsp" />
  
</body>
</html>
