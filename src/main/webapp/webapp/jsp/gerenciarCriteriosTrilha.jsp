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
	
	<% 	
    	Usuario usuario = (Usuario) session.getAttribute("usuarioSara");
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
					<li><a class="" href="eventosCoordenados.jsp"> <i
							class="icon_tools"></i> <span>Gerenciar</span>

					</a></li>
					
					<% case AVALIADOR: %>
					<li><a class="" href="avaliacao.jsp"> <i
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
					<h3 class="page-header"><i class="fa fa-table"></i> Crit�rios de Avalia��o</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Crit�rios de Avalia��o</li>
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
                              Crit�rios de Avalia��o Existentes
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>                               
                                 <th><i class="icon_documents_alt"></i> Crit�rio</th>
                                 <th></th>
                                 <th></th>
                              </tr>
                              
							 <%	                              	 
                              	 for(int i = 0; i < listaCriterioTrilha.size(); i++){
	                
	               					%>
                                      <tr>
                                         <td><%=listaCriterioTrilha.get(i).getNome()%></td>
                                         <td><form action="visualizarCriterioTrilha.jsp" method="post"> 
	                           					<input type="hidden" value="<%= listaCriterioTrilha.get(i).getIdCriterioTrilha()%>" name="idCriterioTrilha"> 
	                           					<button class="btn btn-primary" type="submit">Visualizar</button>
	                       					</form> 
                   						</td>
                   						<td>
                   							<form action="SelecionarCriterioTrilha" method="post"> 
	                           					<input type="hidden" value="<%= listaCriterioTrilha.get(i).getIdCriterioTrilha()%>" name="idCriterioTrilha"> 
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
                             Crit�rios de Avalia��o
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>                               
                                 <th><i class="icon_documents_alt"></i> Crit�rio</th>
                                 <th><i class="icon_balance"></i> Peso</th>
                                 <th></th>
                                 <th></th>
                              </tr>
                              
                              <%
                          	
                		        for(int i=0; i < criterios.size(); i++){
	               			 	%>
                                      <tr>
                                         <td><%=criterios.get(i).getDescricao()%></td>
                                         <td><%=criterios.get(i).getPeso()%></td>
		                   				 <td><form action="editarCriterio.jsp" method="post"> 
		                           				<input type="hidden" value="<%= criterios.get(i).getIdCriterio()%>" name="idCriterio"> 
		                           				<button class="btn btn-primary" type="submit">Alterar</button>
		                       				</form> 
		                   				</td>
		                   
		                   				<td><form action="RemoverCriterio" method="post" id="formRemover" onsubmit="return confirm('Deseja remover este crit�rio?');"> 
		                           				<input type="hidden" value="<%= criterios.get(i).getIdCriterio()%>" name="idCriterio"> 
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
            <button class="btn btn-primary" type="submit" name="addCri">Adicionar Crit�rio</button>
        </form>
        </center>

              <!-- page end-->


  </section>
  <!-- container section start -->
	
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