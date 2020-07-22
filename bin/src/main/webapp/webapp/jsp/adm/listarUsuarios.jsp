<%@page import="br.com.n2s.sara.model.Usuario"%>
<%@page import="br.com.n2s.sara.model.NivelUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.util.Constantes" %>

<%
	List<Usuario> usuarios = (List<Usuario>) request.getAttribute("pessoas");
	String tipo = (String) request.getAttribute("tipo");
	String nome = request.getAttribute("nome") != null && !"".equals(request.getAttribute("nome")) ? (String) request.getAttribute("nome") : "";
	String mensagem = (String) session.getAttribute("msg");
	Integer quantidadeDePaginas = (Integer) request.getAttribute("quantidadeDePaginas");
	Integer paginaAtual = (Integer) request.getAttribute("paginaAtual");
	String url = (String) request.getAttribute("url");
%>

<!-- Adicionando o conteudo da página  -->

<div class="container">
		<div class="row">
			<form class="form-inline" action="<%= Constantes.getAdmUrl() %>/listar_usuarios"
					method="get">
			  <div class="form-group mb-2">
			    <input type="text" readonly class="form-control-plaintext" id="staticEmail2" value="Nome:">
			  </div>
			  <div class="form-group mx-sm-3 mb-2">
			    <label for="inputPassword2" class="sr-only">Password</label>
			    <input type="text" name="nome" class="form-control" id="inputPassword2" placeholder="Digite um nome" value="<%=nome %>">
			  </div>
			  <div class="form-group mx-sm-3 mb-2">
			   <select id="tipo" name="tipo"
									class="form-control">
					<option value="todos"
						<%="todos".equals(tipo) ? "selected" : ""%>>
							Todos</option>
					<option value="alunos"
						<%="alunos".equals(tipo) ? "selected" : ""%>>Alunos</option>
					<option value="servidores"
						<%="servidores".equals(tipo) ? "selected" : ""%>>
							Servidores</option>
				</select>
			</div>
			  <button type="submit" class="btn btn-primary mb-2">Buscar</button>
			</form>
		</div>
		<div class="table-responsive">
			<table
				class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<th>CPF</th>
					<th>Nome</th>
					<th>E-mail</th>
					<th>Nível</th>
				</thead>
				<tbody>
					<%
						for (Usuario user : usuarios) {
					%>
					<tr>
						<td><%=user.getCpf()%></td>
						<td><%=user.getNome()%></a></td>
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
					href="<%=url%>/listar_usuarios?nome=<%=nome %>&tipo=<%=tipo %>&pagina=<%=(paginaAtual - 1)%>" class="btn btn-secondary">
						<< </a>
				<%
					}
						for (int i = 1; i <= quantidadeDePaginas; i++) {
				%>

				<%
					if (i == paginaAtual) {
				%><a
					href="<%=url%>/listar_usuarios?pagina=<%=i%>" class="btn btn-secondary"><%=i%></a>
				<%
					} else {
				%>
				<a href="<%=url%>/listar_usuarios?nome=<%=nome %>&tipo=<%=tipo %>&pagina=<%=i%>" class="btn btn-secondary"><%=i%></a>
				
				<%
					}
				%>
				<%
					}
						if (paginaAtual < quantidadeDePaginas) {
				%>
				<a
					href="<%=url%>/listar_usuarios?nome=<%=nome %>&tipo=<%=tipo %>&pagina=<%=(paginaAtual + 1)%>" class="btn btn-secondary">>></a>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>