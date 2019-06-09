<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page = "./sidebar.jsp" /> 

    <script type="text/javascript">
    var quantidadeCategoriaPorGeneroFeminino = []
    var quantidadeCategoriaPorGeneroMasculino = []
    var randomScalingFactor = function(){ return Math.round(Math.random()*1000)};
	var barChartData = {
			labels : ["Administração","Artes e Fotografia","Autoajuda","Biografia","Contos e crônicas","Didádicos","Direito"],
			
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
		window.onload = function () {
  		var chart2 = document.getElementById("bar-chart").getContext("2d");
  		window.myBar = new Chart(chart2).Bar(barChartData, {
  		responsive: true,
  		scaleLineColor: "rgba(0,0,0,.2)",
  		scaleGridLineColor: "rgba(0,0,0,.05)",
  		scaleFontColor: "#c5c7cc"
  		});
	}
	
    </script>
    <c:forEach var="entry" items="${requestScope.grafico}">
      <script>
      	quantidadeCategoriaPorGeneroFeminino.push(${entry.value[0] * 100})
      	quantidadeCategoriaPorGeneroMasculino.push(${entry.value[1] * 100})
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
              
              <label>Data de Fim</label>
              <input type="date" name="dataFim"> 
              <input class="btn btn-primary" value="CONSULTAR" name="operacao" type="submit">

                

                  <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            Bar Chart
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
