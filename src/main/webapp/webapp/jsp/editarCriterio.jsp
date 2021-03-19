<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.dao.DAOCriterio"%>
<%@page import="br.com.n2s.sara.dao.DAOItem"%>
<%@page import="br.com.n2s.sara.model.Criterio"%>
<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.Item"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>

<%
	String idCriterio = request.getParameter("idCriterio");
	Criterio criterio = new DAOCriterio().getCriterio(Integer.parseInt(idCriterio));
	session.setAttribute("criterio", criterio);
	List<Item> itensCriterio = new DAOItem().readById(criterio.getIdCriterio());
	Trilha trilha = new Trilha();
	//trilha = new DAOTrilha().getTrilha( );
%>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i> Gerenciar Critério
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
					<li><i class="icon_document_alt"></i>Gerenciar Critério</li>
				</ol>
			</div>
		</div>
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
		<!-- page start-->

		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> Gerenciar Critério </header>

					<table class="table table-striped table-advance table-hover">
						<tbody>
							<tr>
								<th><i class="icon_documents_alt"></i> Descrição</th>
								<th><i class="icon_document_alt"></i> Nota</th>
								<th></th>
							</tr>

							<%
								for (int i = 0; i < itensCriterio.size(); i++) {
							%>

							<tr>
								<td><%=itensCriterio.get(i).getDescricao()%></td>
								<td><%=itensCriterio.get(i).getPeso()%></td>
								<td><form action="RemoverItemCriterio" method="post"
										onsubmit="return confirm('Deseja remover este item do critério?');">
										<input type="hidden"
											value="<%=itensCriterio.get(i).getIdItem()%>" name="idItem">
										<button class="btn btn-primary" type="submit">Remover
											Item</button>
									</form></td>
								<!-- 
								<td><form action="editarCriterio.jsp" method="post"
										onsubmit="return confirm('Deseja editar este item do critério?');">
										<input type="hidden"
											value="<%=itensCriterio.get(i).getIdItem()%>" name="idItem">
										<button class="btn btn-primary" type="submit">Editar
											Item</button>
									</form></td>
								-->
							</tr>

							<%
								}
							%>

						</tbody>
					</table>
				</section>
			</div>
		</div>
		<!-- page end-->


		<center>
			<form action="adicionarItemCriterio.jsp" method="post">
				<button class="btn btn-primary" type="submit">Adicionar
					Item</button>
			</form>
		</center>

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
