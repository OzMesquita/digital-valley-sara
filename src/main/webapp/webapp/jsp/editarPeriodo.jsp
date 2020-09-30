<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.Periodo"%>
<%@page import="br.com.n2s.sara.dao.DAOPeriodo"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>
<%
	Trilha trilha = (Trilha) session.getAttribute("trilha");
String idPeriodo = request.getParameter("idPeriodo");
Periodo periodo = new DAOPeriodo().getPeriodo(Integer.parseInt(idPeriodo));
session.setAttribute("periodo", periodo);
LocalDate time = LocalDate.now();
String dia_atual = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
%>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i> Gerenciar Trilha
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
					<li><i class="icon_document_alt"></i> Gerenciar Trilha</li>
				</ol>
			</div>
		</div>

		<!-- page start-->
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
					<header class="panel-heading"> Alterar Período </header>
					<div class="panel-body">
						<div class="form">
							<form action="EditarPeriodo" method="POST">
								<p>
									Tipo: <select name="descricao">
										<option value="SUBMISSAO_MANUSCRITO">Período de
											Submissão</option>
										<option value="AVAL">Período de Aval do orientador</option>
										<option value="AVALIACAO">Período de Avaliaçâo</option>
										<option value="SUBMISSAO_FINAL">Período de Submissôes
											Finais</option>
										<option value="RESULTADO_FINAL">Divulgaçâo de
											Resultados</option>
										<option value="RECURSO">Recurso</option>
										<option value="AVALIACAO_RECURSO">Avaliação dos
											Recursos</option>
									</select>
								<p>
									Data de Inicio: <input type="date" name="dataInicial" required>
								</p>
								<p>
									Data de Fim: <input type="date" name="dataFinal" required>
								</p>
								<input type="submit" value="Alterar">
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>
	</section>
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
