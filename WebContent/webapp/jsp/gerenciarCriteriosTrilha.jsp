<%@page import="br.com.n2s.sara.dao.DAOCriterioTrilha"%>
<%@page import="br.com.n2s.sara.dao.DAOCriterio"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.CriterioTrilha"%>
<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.Criterio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>SARA</title>

    <!-- Bootstrap CSS -->    
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="../css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="../css/elegant-icons-style.css" rel="stylesheet" />
    <link href="../css/font-awesome.min.css" rel="stylesheet" />    
    <!-- full calendar css-->
    <link href="../assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
	<link href="../assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" />
    <!-- easy pie chart-->
    <link href="../assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <!-- owl carousel -->
    <link rel="stylesheet" href="../css/owl.carousel.css" type="text/css">
	<link href="../css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
    <!-- Custom styles -->
	<link rel="stylesheet" href="../css/fullcalendar.css">
	<link href="../css/widgets.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/style-responsive.css" rel="stylesheet" />
	<link href="../css/xcharts.min.css" rel=" stylesheet">	
	<link href="../css/jquery-ui-1.10.4.min.css" rel="stylesheet">
    <!-- =======================================================
        Theme Name: NiceAdmin
        Theme URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
        Author: BootstrapMade
        Author URL: https://bootstrapmade.com
    ======================================================= -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	
<%-- 	<center>
        <%
			//PEGAR TODOS OS CRITERIOS CADASTRADOS DA TRILHA
			
			Trilha trilha = (Trilha) session.getAttribute("trilha");
        	session.setAttribute("trilha", trilha);
			
			CriterioController critCon = new CriterioController();
			CriterioTrilhaController criterioTrilhaController = new CriterioTrilhaController();
			
        	//List<Criterio> criterios = critCon.listar();
        	
      		List<CriterioTrilha> listCriterioTrilha = criterioTrilhaController.listar();
        	
      		if(trilha.getCriterioTrilha() == null){%>
        	
	        	<h1>Selecionar Critério Existente</h1>
	        	<table border="1">
	            <tr>
	                <th>Critérios Existentes</th>
	            </tr>
		<% 
				for(int i = 0; i < listCriterioTrilha.size(); i++){
	                
	               session.setAttribute("critTri"+listCriterioTrilha.get(i).getIdCriterioTrilha(), listCriterioTrilha.get(i));
	               %>
	               
	               <tr>
	                   <td><%=listCriterioTrilha.get(i).getNome()%></td>
	                   <td><form action="visualizarCriterio.jsp" method="post"> 
	                           <input type="hidden" value="critTri<%= listCriterioTrilha.get(i).getIdCriterioTrilha()%>" name="criterioTrilha"> 
	                           <button type="submit">Visualizar</button>
	                       </form> 
	                   </td>
	                   <td><form action="selecionarCriterioTrilha.jsp" method="post"> 
	                           <input type="hidden" value="critTri<%= listCriterioTrilha.get(i).getIdCriterioTrilha()%>" name="criterioTrilha"> 
	                           <button type="submit">Selecionar</button>
	                       </form> 
	                   </td>
	            <%}%>
	       
				</table>
				
				<h1>Criar Novos Critérios</h1>
			
       <% 	}else{
    	   		List<Criterio> criterios = critCon.obterCriteriosPorTrilha(trilha.getCriterioTrilha().getIdCriterioTrilha());
        %>
        
		        <h1>Criar Novos Critérios</h1>
		        
		        <%if(!criterios.isEmpty()){ %>
		        <table border="1">
		            <tr>
		                <th>Critérios</th>
		            </tr>
			<% 
		            for(int i=0; i < criterios.size(); i++){
		                
		               session.setAttribute("crit"+criterios.get(i).getIdCriterio(), criterios.get(i));
		               %>
		               
		               <tr>
		                   <td><%=criterios.get(i).getDescricao()%></td>
		                   <td><form action="alterarCriterio.jsp" method="post"> 
		                           <input type="hidden" value="crit<%= criterios.get(i).getIdCriterio()%>" name="criterio"> 
		                           <button type="submit">Alterar</button>
		                       </form> 
		                   </td>
		                   
		                   <td><form action="removerCriterio.jsp" method="post" id="formRemover" onsubmit="return confirm('Deseja remover este critério?');"> 
		                           <input type="hidden" value="crit<%= criterios.get(i).getIdCriterio()%>" name="criterio"> 
		                           <button type="submit">Remover</button>
		                       </form> 
		                   </td>
		                   
		            <%}%>
		       <% }
        }%>
	</table>
    	
    	<br />
    	
       	<p>
        <form action="adicionarCriterio.jsp" method="post">
            <input type="submit" name="addCri" value="Adicionar Critério">
        </form>
        </p>
        
        <br />
        
       	<br /> <br /> <br />
       	
		<input type="button" value="Voltar" onClick="history.go(-1)">       
    </center> --%>

    <% 	
    	Usuario usuario = (Usuario) session.getAttribute("usuario");
	%>
    
  <!-- container section start -->
  <section id="container" class="">
     
      
        <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
            </div>

            <!--logo start-->
            <a href="indexAutor.jsp" class="logo">SARA</a>
            <!--logo end-->

            <div class="top-nav notification-row">                
                <!-- notificatoin dropdown start-->
                <ul class="nav pull-right top-menu">
                    
                    <!-- alert notification end-->
                    <!-- user login dropdown start-->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt="" src="img/avatar1_small.jpg">
                            </span>
                            <span class="username"><%= usuario.getNome()%></span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li>
                                <a href="sairConta.jsp"><i class="icon_key_alt"></i> Sair</a>
                            </li>
                        </ul>
                    </li>
                    <!-- user login dropdown end -->
                </ul>
                <!-- notificatoin dropdown end-->
            </div>
      </header>
      <!--header end-->

          <!--sidebar start-->
    <aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu">
					<li class="active"><a class="" href="indexAutor.jsp"> <i
							class="icon_house_alt"></i> <span>Home</span>
					</a>
					
					<%      
					switch(usuario.getTipo()){ 
					
					case ADMINISTRADOR: %>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_pencil"></i> <span>Administrador</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li><a class="" href="formCadastroEvento.jsp">Criar Evento</a></li>
						</ul></li>
					
					<% case COORDENADOR_EVENTO: 
					   case COORDENADOR_TRILHA: %>
					<li><a class="" href="indexCoordTrilha.jsp"> <i
							class="icon_tools"></i> <span>Gerenciar</span>

					</a></li>
					
					<% case AVALIADOR: %>
					<li><a class="" href="indexAvaliador.jsp"> <i
							class="icon_document_alt"></i> <span>Avaliar</span>

					</a></li>
					
					<% case AUTOR: %>
					<li><a class="" href="trabalhosAutor.jsp"> <i
							class="icon_documents_alt"></i> <span>Meus Trabalhos</span>

					</a></li>
					<%}%>
				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
      <!--sidebar end-->
      
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i> Critérios de Avaliação</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Critérios de Avaliação</li>
					</ol>
				</div>
			</div>
      
      <!-- page start-->
              <%
      			Trilha trilha = (Trilha) session.getAttribute("trilha");
              	session.setAttribute("trilha", trilha);
      			
              	DAOCriterio daoCriterio = new DAOCriterio();
              	DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
      			
      			
          	
        		List<CriterioTrilha> listaCriterioTrilha = daoCriterioTrilha.read();
              	if(trilha.getCriterioTrilha() == null){%>
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              Critérios de Avaliação Existentes
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>                               
                                 <th><i class="icon_documents_alt"></i> Critério</th>
                                 <th></th>
                                 <th></th>
                              </tr>
                              
							 <%	                              	 
                              	 for(int i = 0; i < listaCriterioTrilha.size(); i++){
	                
	              	 				session.setAttribute("critTri"+listaCriterioTrilha.get(i).getIdCriterioTrilha(), listaCriterioTrilha.get(i));
	               					%>
                                      <tr>
                                         <td><%=listaCriterioTrilha.get(i).getNome()%></td>
                                         <td><form action="visualizarCriterio.jsp" method="post"> 
	                           					<input type="hidden" value="critTri<%= listaCriterioTrilha.get(i).getIdCriterioTrilha()%>" name="criterioTrilha"> 
	                           					<button class="btn btn-primary" type="submit">Visualizar</button>
	                       					</form> 
                   						</td>
                   						<td>
                   							<form action="selecionarCriterioTrilha.jsp" method="post"> 
	                           					<input type="hidden" value="critTri<%= listaCriterioTrilha.get(i).getIdCriterioTrilha()%>" name="criterioTrilha"> 
	                           					<button class="btn btn-primary" type="submit">Selecionar</button>
	                       					</form>
                   						</td>
                                      </tr>                              			 
                              <% } %>
                              
                                 </tbody>
                                 </table>
                               </section>
                           </div>
                       </div>
                       <% 		}else{
                    	   			
                        			List<Criterio> criterios = daoCriterio.obterCriteriosPorTrilha(trilha.getCriterioTrilha().getIdCriterioTrilha());
                        			if(!criterios.isEmpty()){ %>
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              Criar Novos Critérios
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>                               
                                 <th><i class="icon_documents_alt"></i> Critério</th>
                                 <th><i class="icon_balance"></i> Peso</th>
                                 <th></th>
                                 <th></th>
                              </tr>
                              
                              <%
                          	
                		        for(int i=0; i < criterios.size(); i++){
                		                
                			    	session.setAttribute("crit"+criterios.get(i).getIdCriterio(), criterios.get(i));
	               					%>
                                      <tr>
                                         <td><%=criterios.get(i).getDescricao()%></td>
                                         <td><%=criterios.get(i).getPeso()%></td>
		                   				 <td><form action="alterarCriterio.jsp" method="post"> 
		                           				<input type="hidden" value="crit<%= criterios.get(i).getIdCriterio()%>" name="criterio"> 
		                           				<button class="btn btn-primary" type="submit">Alterar</button>
		                       				</form> 
		                   				</td>
		                   
		                   				<td><form action="removerCriterio.jsp" method="post" id="formRemover" onsubmit="return confirm('Deseja remover este critério?');"> 
		                           				<input type="hidden" value="crit<%= criterios.get(i).getIdCriterio()%>" name="criterio"> 
		                           				<button class="btn btn-primary" type="submit">Remover</button>
		                       				</form> 
		                   				</td>
                                      </tr>                              			 
                              <% } %>
                              
                                 </tbody>
                                 </table>
                               </section>
                           </div>
                       </div>
                        	  <%}
                       }
                              %>
                                 
       	<center>
        <form action="adicionarCriterio.jsp" method="post">
            <button class="btn btn-primary" type="submit" name="addCri">Adicionar Critério</button>
        </form>
        </center>

              <!-- page end-->


  </section>
  <!-- container section start -->
	
	<script>
        /* $(".formRemover").onClick(function(){
        
        this.addEventListener('submit', function(e) {   
        var form = this;
         e.preventDefault();
          swal({
                title: "Deseja Remover?",
                text: "Este critério será excluído.",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#4cd964",
                confirmButtonText: "Sim, quero remover.", 
                cancelButtonText: "Não",       
                closeOnConfirm: false
            },
            function(isConfirm) {
                if (isConfirm) {
                    swal({
                        title: "Critério Removido!",			  
				        timer: 1000,
				        type: "success",
				        showConfirmButton: false
                    }, function() {
                        form.submit();
                    });
                    
                } 
            });
        });
        }); */
        
        //document.getElementById("formRemover").onsubmit = function() {confirmarRemocao()};

        function confirmarRemocao(){
        	decisao = confirm("Deseja remover?");
        	
        	if(decisao){
        		return true;
        	}else{
        		return false;
        	}
        }
        
        <!-- javascripts -->
        <script src="../js/jquery.js"></script>
    	<script src="../js/jquery-ui-1.10.4.min.js"></script>
        <script src="../js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js"></script>
        <!-- bootstrap -->
        <script src="../js/bootstrap.min.js"></script>
        <!-- nice scroll -->
        <script src="../js/jquery.scrollTo.min.js"></script>
        <script src="../js/jquery.nicescroll.js" type="text/javascript"></script>
        <!-- charts scripts -->
        <script src="../assets/jquery-knob/js/jquery.knob.js"></script>
        <script src="../js/jquery.sparkline.js" type="text/javascript"></script>
        <script src="../assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
        <script src="../js/owl.carousel.js" ></script>
        <!-- jQuery full calendar -->
        <script src="../js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
    	<script src="../assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
        <!--script for this page only-->
        <script src="../js/calendar-custom.js"></script>
    	<script src="../js/jquery.rateit.min.js"></script>
        <!-- custom select -->
        <script src="../js/jquery.customSelect.min.js" ></script>
        <!--custome script for all page-->
        <script src="../js/scripts.js"></script>
        <!-- chartjs -->
        <script src="../assets/chart-master/Chart.js"></script>
        <!-- custom script for this page-->
        <script src="../js/sparkline-chart.js"></script>
        <script src="../js/easy-pie-chart.js"></script>
    	<script src="../js/jquery-jvectormap-1.2.2.min.js"></script>
    	<script src="../js/jquery-jvectormap-world-mill-en.js"></script>
    	<script src="../js/xcharts.min.js"></script>
    	<script src="../js/jquery.autosize.min.js"></script>
    	<script src="../js/jquery.placeholder.min.js"></script>
    	<script src="../js/gdp-data.js"></script>	
    	<script src="../js/morris.min.js"></script>
    	<script src="../js/sparklines.js"></script>	
    	<script src="../js/charts.js"></script>
    	<script src="../js/jquery.slimscroll.min.js"></script>
      <script>

          //knob
          $(function() {
            $(".knob").knob({
              'draw' : function () { 
                $(this.i).val(this.cv + '%')
              }
            })
          });

          //carousel
          $(document).ready(function() {
              $("#owl-slider").owlCarousel({
                  navigation : true,
                  slideSpeed : 300,
                  paginationSpeed : 400,
                  singleItem : true

              });
          });

          //custom select box

          $(function(){
              $('select.styled').customSelect();
          });
    	  
    	  /* ---------- Map ---------- */
    	$(function(){
    	  $('#map').vectorMap({
    	    map: 'world_mill_en',
    	    series: {
    	      regions: [{
    	        values: gdpData,
    	        scale: ['#000', '#000'],
    	        normalizeFunction: 'polynomial'
    	      }]
    	    },
    		backgroundColor: '#eef3f7',
    	    onLabelShow: function(e, el, code){
    	      el.html(el.html()+' (GDP - '+gdpData[code]+')');
    	    }
    	  });
    	});
    		
    	$(function () {
            var data = {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [
                {
                    label: "My First dataset",
                    fillColor: "rgba(220,220,220,0.2)",
                    strokeColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: [65, 59, 80, 81, 56, 55, 40]
                },
                {
                    label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.2)",
                    strokeColor: "rgba(151,187,205,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: [28, 48, 40, 19, 86, 27, 90]
                }
                ]
            };
       
            var option = {
            responsive: true,
            };
       
            // Get the context of the canvas element we want to select
            var ctx = document.getElementById("teste").getContext('2d');
            var myLineChart = new Chart(ctx).Line(data, option); //'Line' defines type of the chart.
        });

      </script>    
</body>
</html>