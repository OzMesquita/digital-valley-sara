<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8-ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>Cadastro de Eventos</title>

<link href="../SweetAlert/sweetalert.css" rel="stylesheet">

<!-- Bootstrap CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="../css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="../css/elegant-icons-style.css" rel="stylesheet" />
<link href="../css/font-awesome.min.css" rel="stylesheet" />
<!-- Custom styles -->
<link href="../css/style.css" rel="stylesheet">
<link href="../css/style-responsive.css" rel="stylesheet" />

</head>
<body>
		<%
			Usuario usuario = (Usuario) session.getAttribute("usuario"); 
			Trilha trilha = (Trilha) session.getAttribute("trilha");
    		session.setAttribute("trilha", trilha);
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
					<h3 class="page-header"><i class="fa fa-table"></i>Cadastro de Critérios</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
						<li><i class="icon_document_alt"></i>Cadastro de Critérios</li>
					</ol>
				</div>
			</div>
      
      				<!-- Form validations -->
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading"> Cadastrar Critério de Avaliação </header>
							<div class="panel-body">
								<div class="form">
									<form action="confirmarCriterio.jsp" method="post" id = "formEnviar">
							            
							            				
										<div class="form-validate form-horizontal" id="feedback_form">
										
											<%
								        	if(trilha.getCriterioTrilha() == null){ %>
								    			
								    			<center><h2><b>Critério Trilha</b></h2></center>
								    				
												<div class="form-group ">
													<label for="cemail" class="control-label col-lg-2">Nome 
														<span class="required">*</span>
													</label>
													<div class="col-lg-6">
														<input class="form-control " id="subject" type="text"
															name="nomeCriterioTrilha" required />
													</div>
												</div>								    				
								    				
								    		<%} %>
								
											<center><h2><b>Critério</b></h2></center>
											    		
											<div class="form-group">
												<label for="ccomment" class="control-label col-lg-2">Descrição
													<span class="required">*</span>
												</label>
												<div class="col-lg-10">
													<textarea class="form-control " id="ccomment"
														name="descricaoCriterio" required></textarea>
												</div>
											</div>

											<div class="form-group ">
												<label for="cemail" class="control-label col-lg-2">Peso 
													<span class="required">*</span>
												</label>
												<div class="col-lg-6">
													<input class="form-control " id="subject" type="number" value="0"
														name="pesoCriterio" required />
												</div>
											</div>

											<div class="form-group">
												<div class="col-lg-offset-2 col-lg-10">
													<button id = "sucesso" class="btn btn-primary" type="submit"onclick = "validation();">Salvar</button>
												</div>
											</div>
										</div>
									</form>
								</div>

							</div>
						</section>
					</div>
				</div>
    
    <script type="text/javascript">
    	var cont = 1;
    	
	    function maisCampos(){
	    	var clone = document.getElementById('origem').cloneNode(true);
	    	var destino = document.getElementById('destino');
	    	destino.appendChild(clone);

	    	var camposClonados = clone.getElementsByTagName('input');
	    	for(i=0; i<camposClonados.length;i++){
	    		camposClonados[i].value = '';
	    		camposClonados[i].name = "peso" + cont;
	    	}
	    	
	    	camposClonados = clone.getElementsByTagName('textarea');
	    	for(i=0; i<camposClonados.length;i++){
	    		camposClonados[i].value = '';
	    		camposClonados[i].name = "descricao" + cont;
	    	}
	    	
	    	cont = cont + 1;
	    }
	    
 	    function removerCampos(id){
 	    	cont = cont - 1;
	   	    var node1 = document.getElementById('destino');
	    	node1.removeChild(node1.childNodes[cont]);
	    } 
    </script>

	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script type="text/javascript" src = "../js/jquery.mask.min.js"/></script>
	<script type="text/javascript">$("#cpf").mask("000.000.000-09");</script>
	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="../js/jquery.scrollTo.min.js"></script>
	<script src="../js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- jquery validate js -->
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

	<!-- custom form validation script for this page-->
	<script src="../js/form-validation-script.js"></script>
	<!--custome script for all page-->
	<script src="../js/scripts.js"></script>
	<script src="../SweetAlert/sweetalert.min.js"></script>
	<script>
        document.querySelector('#formEnviar').addEventListener('submit', function(e) {
        var form = this;
         e.preventDefault();          
                    swal({
                        title: "Sucesso!",			  
				        timer: 1000,
				        type: "success",
				        showConfirmButton: false
                    }, function() {
                        form.submit();
                    });

            });
    </script>
</body>
</html>