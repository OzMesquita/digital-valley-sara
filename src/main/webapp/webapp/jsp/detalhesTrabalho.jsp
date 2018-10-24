<%@page import="br.com.n2s.sara.util.Facade"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.dao.DAOSubmissao"%>
<%@page import="br.com.n2s.sara.dao.DAOAvaliaTrabalho"%>
<%@page import="br.com.n2s.sara.dao.DAOTrabalho"%>
<%@page import="br.com.n2s.sara.dao.DAOEvento"%>
<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="br.com.n2s.sara.model.*" %>
<%@page import="br.com.n2s.sara.util.Constantes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

	<script type="text/javascript" src="../js/jquery.maskedinput.js"></script>
    <script type="text/javascript" src="../js/jquery-validate.js"></script>
    <script type="text/javascript" src="../js/jquery.mask.min.js"></script>
	<script src="../js/validacao.js" type="text/javascript"></script>
</head>
    <body>
    
    <!-- container section start -->
  	<section id="container" class="">
     
	<% 
		Usuario usuario = (Usuario) session.getAttribute("usuarioSara");
		
		int idTrabalho = Integer.parseInt(request.getParameter("idTrabalho")); 	
		Trabalho trabalho = new DAOTrabalho().getTrabalho(idTrabalho);
		session.setAttribute("trabalho", trabalho);
		
		AvaliaTrabalho avaliaTrabalho = new DAOAvaliaTrabalho().getAvaliaTrabalho(idTrabalho);
		List<Usuario> coAutores = new ArrayList<Usuario>(); 
		coAutores = new DAOSubmissao().getAutores(idTrabalho);
        
    %>
      
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
                                <a id="sair" href="<%=Constantes.getAppGuardiaoUrl()%>/logout">Sair</a>
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
					<h3 class="page-header"><i class="fa fa-table"></i> Submissão</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Submissão</li>
					</ol>
				</div>
			</div>
      
      <!-- page start-->
              
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              Submissão
                          </header>
                        <table class="table table-striped table-advance table-hover">
	                        <tbody>
				                    
				                   	<tr>
				                    	<td>
									        <form action="submissaoFinal.jsp" id="form" method="post" >
									         	
									         	
									         	<h4>Título:</h4>
									         	<p><input type="text" size="100" value="<%= trabalho.getTitulo() %>" disabled="disabled"></p>
									            
									            <% if (!trabalho.getResumo().equals("")) { %>
									           	
									           		<h4>Resumo/Abstract</h4>
									            	<p> <textarea cols="100" rows="10" disabled="disabled"><%= trabalho.getResumo()%></textarea></p>
									            
									            <% } %>

												<% if (!trabalho.getPalavrasChaves().equals("")) { %>									            	
									            	
									            	<h4>Palavras-chave</h4> 
									           		<p> <input type="text" size="100" value="<%= trabalho.getPalavrasChaves() %>" disabled="disabled"></p>
									           	
									           	 <% } %>
									           	
									           	<% if (Facade.periodoAtual(trabalho.getTrilha()).getDescricao().equals(DescricaoPeriodo.SUBMISSAO_FINAL)) { %>
									           	
									           		<h4>Feedback do Avaliador</h4>
									           		<p> <textarea cols="100" rows="10" disabled="disabled"><%= avaliaTrabalho.getFeedback()%></textarea></p>
									           	
									           	<% } %>
									           	
									           	<h4>Status: <%= trabalho.getStatus() %> </h4> 
									            
									            <% if (Facade.periodoAtual(trabalho.getTrilha()).getDescricao().equals(DescricaoPeriodo.SUBMISSAO_FINAL) && trabalho.getStatus().equals(StatusTrabalho.ACEITO)) { %>
									            
								          			<input type="hidden" name="idTrilha" value="<%= trabalho.getTrilha().getIdTrilha() %>" />
									           		<input type="hidden" name="idEvento" value="<%= trabalho.getTrilha().getEvento().getIdEvento() %>" />
									            	<input type="hidden" name="idTrabalho" value="<%= trabalho.getIdTrabalho()%>" />
									            	<button class="btn btn-primary" type = "submit">Submeter Versão Final</button>
									            
									            <% } %>
								        	</form>
								        	<br>
								        	<form action="DownloadTrabalho" method="post" >                  					 
	                   							<input type="hidden" value="<%= trabalho.getIdTrabalho() %>" name="idTrabalho">  
	                  						<button class="btn btn-primary" type = "submit">Download Trabalho</button>
	               					 	</form>
				                   		</td>
				                   </tr>
	                       </tbody>
	                   </table>
                     </section>
                  </div>
              </div>
         </section>
         
              <!-- page end-->
  </section>
</section>
  <!-- container section start -->
    
    <!-- javascripts -->
    
    <!-- verificação geral -->
    <script  type="text/javascript">
    	function verificacao(){
    		var cpfs = document.getElementById("cpf");
    		for (var i=0;i <cpfs.length;i++){
    			for(var j=1+1; j<cpfs; i++){
    				if(j=i){
    					break;
    				}else{
    					var cpf1 = cpfs[i];
    					var cpf2 = cpfs[j];
    					console(cpf1);console(cpf2);
    					if (cpf1.value == cpf2.value){
    						alert("ERRO: CPFS REPETIDOS! POR FAVOR VERIFICAR.");
    					}
    				}
    			}
    		}
    		CPF();
    	}    
    </script>
    
    <!-- verificação do arquivo -->
    <script>	
    function tamanho (){
    	var tamanhoArquivo = parseInt(document.getElementById("file_Input").files[0].size);
      	if(tamanhoArquivo > 5242880 ){
      			 alert("TAMANHO DO ARQUIVO EXCEDE O PERMITIDO (5MB)!");
                document.getElementById('form').reset();
            }
    };
    
    var _validFileExtensions = [".pdf"];    
    function Validate(oForm) {
        var arrInputs = oForm.getElementsByTagName("input");
        for (var i = 0; i < arrInputs.length; i++) {
            var oInput = arrInputs[i];
            if (oInput.type == "file") {
                var sFileName = oInput.value;
                if (sFileName.length > 0) {
                    var blnValid = false;
                    for (var j = 0; j < _validFileExtensions.length; j++) {
                        var sCurExtension = _validFileExtensions[j];
                        if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                            blnValid = true;
                            break;
                        }
                    }
                    
                    if (!blnValid) {
                        alert("Desculpe, " + sFileName + " é inválido, as extensões permitidas são: " + _validFileExtensions.join(", "));
                        return false;
                    }
                }
            }
        }
      
        return true;
    }
    function Cpf(v){

    	v=v.replace(/\D/g,"")

    	v=v.replace(/(\d{3})(\d)/,"$1.$2")

    	v=v.replace(/(\d{3})(\d)/,"$1.$2")



    	v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2")

    	return v

    }
    
    function validarCPF(cpf) {	
    	cpf = cpf.replace(/[^\d]+/g,'');	
    	if(cpf == '') return false;	
    	// Elimina CPFs invalidos conhecidos	
    	if (cpf.length != 11 || 
    		cpf == "00000000000" || 
    		cpf == "11111111111" || 
    		cpf == "22222222222" || 
    		cpf == "33333333333" || 
    		cpf == "44444444444" || 
    		cpf == "55555555555" || 
    		cpf == "66666666666" || 
    		cpf == "77777777777" || 
    		cpf == "88888888888" || 
    		cpf == "99999999999")
    			alert("CPF INVÁLIDO");		
    	// Valida 1o digito	
    	add = 0;	
    	for (i=0; i < 9; i ++)		
    		add += parseInt(cpf.charAt(i)) * (10 - i);	
    		rev = 11 - (add % 11);	
    		if (rev == 10 || rev == 11)		
    			rev = 0;	
    		if (rev != parseInt(cpf.charAt(9)))		
    			return false;		
    	// Valida 2o digito	
    	add = 0;	
    	for (i = 0; i < 10; i ++)		
    		add += parseInt(cpf.charAt(i)) * (11 - i);	
    	rev = 11 - (add % 11);	
    	if (rev == 10 || rev == 11)	
    		rev = 0;	
    	if (rev != parseInt(cpf.charAt(10)))
    		alert("CPF INVÁLIDO");;		
    	return true;   
    }
    
	 function CPF(){
    	  var mensagem = 'CPF Inválido'
    	  if ( validarCPF(document.getElementById('cpf').value) === true ) {
    	    mensagem = 'CPF Válido'
    	  }

    	  alert(mensagem);
    };
    </script>
    
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
	<!-- este é o script para gerar os campos do autor -->
	<script>
     var  autorList = {
	    'init': function()
	    {
	        this.divAutorList = document.getElementById('divAutorList');
	        this.divAutorBase = document.getElementById('divAutorBase');
	    },
	    
	    'insert': function()
	    {
	        var newDiv = this.divAutorBase.cloneNode(true);
	        this.divAutorList.appendChild(newDiv);
	    },
	    
	    'remove': function(el)
	    {
	        el.parentNode.removeChild(el);
	    }
		};
	autorList.init();
	</script>
	
	
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
