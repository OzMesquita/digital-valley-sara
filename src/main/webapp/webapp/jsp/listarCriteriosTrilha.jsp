<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@page import="br.com.n2s.sara.dao.DAOCriterioTrilha"%>
<%@page import="br.com.n2s.sara.dao.DAOCriterio"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.CriterioTrilha"%>
<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.Criterio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i> Crit�rios de Avalia��o da Trilha atual
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
					<li><i class="icon_document_alt"></i>Crit�rios de Avalia��o da Trilha atual</li>
				</ol>
			</div>
		</div>

		<!-- page start-->
		<%
		Trilha trilha = (Trilha) session.getAttribute("trilha");
		session.setAttribute("trilha", trilha);
		
		DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
		List<Criterio> listaCriterioTrilha = daoCriterioTrilha.getCriterioPorTrilha(trilha);
		%>

		<%
			if (session.getAttribute(Constantes.getSESSION_MGS()) != null) {
		%>
		<div class="alert alert-success" role="alert">
			<%=session.getAttribute(Constantes.getSESSION_MGS())%>
			<%
				session.setAttribute(Constantes.getSESSION_MGS(), null);
			%>
		</div>
		<%
			}
		%>
		<%
			if (session.getAttribute(Constantes.getSESSION_MGS_ERROR()) != null) {
		%>
		<div class="alert alert-danger" role="alert">
			<%=session.getAttribute(Constantes.getSESSION_MGS_ERROR())%>
			<%
				session.setAttribute(Constantes.getSESSION_MGS_ERROR(), null);
			%>
		</div>
		<%
			}
		%>
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading">Lista de Crit�rios desta Trilha </header>
					<table class="table table-striped table-advance table-hover">
						<tbody>
							<tr>
								<th><i class="icon_documents_alt"></i> Crit�rio</th>
								<th><i class="icon_balance"></i> Descri��o</th>
								<th><i class="icon_balance"></i> Peso</th>
								<th></th>
								<th></th>
							</tr>

							<%
								for (int i = 0; i < listaCriterioTrilha.size(); i++) {
							%>
							<tr>
								<td><%=listaCriterioTrilha.get(i).getNome()%></td>
								<td><%=listaCriterioTrilha.get(i).getDescricao()%></td>
								<td><%=listaCriterioTrilha.get(i).getPeso()%></td>
							<%
								}
							%>

						</tbody>
					</table>
				</section>
			</div>
		</div>

		<center>
			<div class="row">
				<div class="col-lg-12">
					<form action="gerenciarCriteriosTrilha.jsp" method="post">
						<button class="btn btn-primary" type="submit" name="voltar">Voltar</button>
					</form>
				</div>
			</div>
		</center>

		<!-- page end-->

	</section>
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
<script src="../js/owl.carousel.js"></script>
<!-- jQuery full calendar -->
<script src="../js/fullcalendar.min.js"></script>
<!-- Full Google Calendar - Calendar -->
<script src="../assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
<!--script for this page only-->
<script src="../js/calendar-custom.js"></script>
<script src="../js/jquery.rateit.min.js"></script>
<!-- custom select -->
<script src="../js/jquery.customSelect.min.js"></script>
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
<script src="../SweetAlert/sweetalert.min.js"></script>

<script>
	document.querySelector('#formEnviar').addEventListener('submit',
			function(e) {
				var form = this;
				e.preventDefault();
				swal({
					title : "Sucesso!",
					timer : 1000,
					type : "success",
					showConfirmButton : false
				}, function() {
					form.submit();
				});

			});
</script>