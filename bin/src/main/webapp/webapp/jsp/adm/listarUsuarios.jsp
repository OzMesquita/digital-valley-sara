<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.NivelUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.util.Constantes"%>

<%
	List<Usuario> usuarios = (List<Usuario>) request.getAttribute("pessoas");
String tipo = (String) request.getAttribute("tipo");
String nome = request.getAttribute("nome") != null && !"".equals(request.getAttribute("nome"))
		? (String) request.getAttribute("nome")
		: "";
String mensagem = (String) session.getAttribute("msg");
Integer quantidadeDePaginas = (Integer) request.getAttribute("quantidadeDePaginas");
Integer paginaAtual = (Integer) request.getAttribute("paginaAtual");
String url = (String) request.getAttribute("url");
%>

<!-- Adicionando o conteudo da página  -->

<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-table"></i> Alterar Permissões de Usuário
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="indexAutor.jsp">Home</a></li>
					<li><i class="icon_document_alt"></i>Alterar Permissões</li>
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
			<div class="col-lg-12" id="margem">
				<form class="form-inline "
					action="ListarUsuarios" method="get">
					<div class="form-group mx-sm-3 mb-2">
						<input type="text" name="nome" class="form-control" id="nome"
							placeholder="Digite um nome" value="<%=nome%>">
					</div>
					<button type="submit" class="btn btn-primary mb-2">Buscar</button>
				</form>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>CPF</th>
								<th>Nome</th>
								<th>E-mail</th>
								<th>Nível</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (Usuario user : usuarios) {
							%>
							<tr>
								<td><%=user.getCpf()%></td>
								<td><a
									href="adm/editarPermissaoUsuario.jsp?cpfUsuario=<%=user.getCpf()%>">
										<%-- <td><a href="adm/editarNivelUsuario.jsp?cpfUsuario=<%=user.getCpf()%>"> --%>
										<%=user.getNome()%></a>
								<td><%=user.getEmail()%></td>
								<td><%=user.getTipo()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				
			<div class="text-center">
					<div class="btn-group" role="group" aria-label="Basic example">
						<%
							if (quantidadeDePaginas > 1) {
							if (paginaAtual > 1) {
						%>
						<a
							href="<%=Constantes.getJSP_URL()%>ListarUsuarios?nome=<%=nome%>&tipo=<%=tipo%>&pagina=<%=(paginaAtual - 1)%>"
							class="btn btn-secondary"> </a>
						<%
							}
						for (int i = 1; i <= quantidadeDePaginas; i++) {
						%>

						<%
							if (i == paginaAtual) {
						%><a href="<%=Constantes.getJSP_URL()%>ListarUsuarios?pagina=<%=i%>"
							class="btn btn-secondary"><%=i%></a>
						<%
							} else {
						%>
						<a
							href="<%=Constantes.getJSP_URL()%>ListarUsuarios?nome=<%=nome%>&tipo=<%=tipo%>&pagina=<%=i%>"
							class="btn btn-secondary"><%=i%></a>

						<%
							}
						%>
						<%
							}
						if (paginaAtual < quantidadeDePaginas) {
						%>
						<a
							href="<%=Constantes.getJSP_URL()%>ListarUsuarios?nome=<%=nome%>&tipo=<%=tipo%>&pagina=<%=(paginaAtual + 1)%>"
							class="btn btn-secondary">>></a>
						<%
							}
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</section>
</section>