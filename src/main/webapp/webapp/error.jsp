<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
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
<link
	href="../assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css"
	rel="stylesheet" />
<link href="../assets/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" />
<!-- easy pie chart-->
<link href="../assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
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
	<section id="container" class=""> <header
		class="header dark-bg">
	<div class="toggle-nav">
		<div class="icon-reorder tooltips"
			data-original-title="Toggle Navigation" data-placement="bottom">
			<i class="icon_menu"></i>
		</div>
	</div>

	<!--logo start--> <a href="indexAutor.jsp" class="logo">SARA</a> <!--logo end-->

	<div class="top-nav notification-row">
		<!-- notificatoin dropdown start-->
		<ul class="nav pull-right top-menu">

			<!-- alert notification end-->
			<!-- user login dropdown start-->
			<li class="dropdown"><a data-toggle="dropdown"
				class="dropdown-toggle" href="#"> <span class="profile-ava">
						<img alt="" src="img/avatar1_small.jpg">
				</span> <span class="username"><%=usuario.getNome()%></span> <b
					class="caret"></b>
			</a>
				<ul class="dropdown-menu extended logout">
					<div class="log-arrow-up"></div>
					<li><a id="guardiao"
						href="<%=Constantes.getAppGuardiaoUrl()%>">Guardião</a></li>
					<li><a id="sair"
						href="<%=Constantes.getAppGuardiaoUrl()%>/logout">Sair</a></li>
				</ul></li>
			<!-- user login dropdown end -->
		</ul>
		<!-- notificatoin dropdown end-->
	</div>
	</header> <!--header end--> <!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">
			<li class="active"><a class="" href="indexAutor.jsp"> <i
					class="icon_house_alt"></i> <span>Home</span>
			</a> <%
 	switch (usuario.getTipo()) {

 	case ADMINISTRADOR:
 %>
			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_pencil"></i> <span>Administrador</span> <span
					class="menu-arrow arrow_carrot-right"></span>
			</a>
				<ul class="sub">
					<li><a class="" href="formCadastroEvento.jsp">Criar Evento</a></li>
				</ul></li>

			<%
				case COORDENADOR_EVENTO:
				case COORDENADOR_TRILHA:
			%>
			<li><a class="" href="eventosCoordenados.jsp"> <i
					class="icon_tools"></i> <span>Gerenciar</span>

			</a></li>

			<%
				case AVALIADOR:
			%>
			<li><a class="" href="avaliacao.jsp"> <i
					class="icon_document_alt"></i> <span>Avaliar</span>

			</a></li>

			<%
				case AUTOR:
			%>
			<li><a class="" href="trabalhosAutor.jsp"> <i
					class="icon_documents_alt"></i> <span>Meus Trabalhos</span>

			</a></li>
			<%
				}
			%>
		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end--> <!--main content start--> <section
		id="main-content"> <section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa-table"></i> ERRO 500
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
				<li><i class="icon_document_alt"></i> ERRO 500</li>
			</ol>
		</div>
	</div>
	<!-- page start-->

	<div class="row">
		<div class="col-lg-12">
			<section class="panel"> <header class="panel-heading">
			Erro Encontrado </header>
			<div class="panel-body">
				<div class="form">
					<form action="indexAutor.jsp">
						<%@ page isErrorPage="true"%>
						<%@ page autoFlush="true" %>
						<h3>Desculpe um erro ocorreu</h3>
						A exceção é: 
						<%=exception%>
						<br /> <input type="submit" value="Voltar ao Inicio" />
					</form>

				</div>
			</div>
			</section>
		</div>
	</div>
	</section> <!-- page end--> </section>