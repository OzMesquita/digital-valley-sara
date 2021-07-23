<%@page import="br.com.n2s.sara.model.SessaoTematica"%>
<%@page import="br.com.n2s.sara.dao.DAOSessaoTematica"%>
<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.NivelUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>

<%

	DAOSessaoTematica daoSessaoTematica = new DAOSessaoTematica();
	List<SessaoTematica> sessoes = daoSessaoTematica.getAll();

%>

<!-- Adicionando o conteudo da página  -->

<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i> Sessões Temáticas
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
					<li><i class="icon_document_alt"></i>Sessões Temáticas</li>
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

		<%
			if (session.getAttribute("feedbackSucesso") != null) {
		%>
		<div class="alert alert-success" role="alert">
			<%=session.getAttribute("feedbackSucesso")%>
		</div>

		<%
			}
		session.setAttribute("feedbackSucesso", null);
		%>
		
		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>Evento</th>
								<th>Sessão Temática</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (SessaoTematica sessao : sessoes) {
							%>
							<tr>
								<td><%=sessao.getEvento() != null? sessao.getEvento().getNome():"Não informado"%></td>
								<td><%=sessao.getNome()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
	</section>
</section>