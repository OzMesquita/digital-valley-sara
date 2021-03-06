package br.com.n2s.sara.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOUsuario;
import br.com.n2s.sara.dao.DAOUsuarioSemCadastro;
import br.com.n2s.sara.model.NivelUsuario;
import dao.UsuarioDAO;
import model.Pessoa;
import model.Usuario;
import dao.DAOFactory;
import util.Constantes;
import util.Facade;

public class AutenticadoFiltro implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();
		if (path.endsWith("/autentica")){
			chain.doFilter(request, response);
		}else{
			HttpSession session = ((HttpServletRequest) request).getSession();
			if(request.getParameter("token") != null && request.getParameter("id") != null){
				String token = request.getParameter("token");
				int id = Integer.parseInt(request.getParameter("id"));
				Pessoa user = Facade.buscarPessoaPorId(id);
				if (token.equals(user.getUsuario().getTokenUsuario()) && id == user.getId() && !token.equals("null")) {
					session.setAttribute("usuario", user.getUsuario());
					//Parte que n�o faz parte do filtro original
					UsuarioDAO userDAO = DAOFactory.criarUsuarioDAO();
					br.com.n2s.sara.model.Usuario userSara = new br.com.n2s.sara.model.Usuario();
					if (br.com.n2s.sara.util.Facade.buscarUsuarioPorCPF(user.getCpf()) == null){
						userSara.setCpf(user.getCpf());
						userSara.setEmail(user.getEmail());
						userSara.setNome(user.getNome());
						userSara.setSobrenome(" ");
						userSara.setTipo(NivelUsuario.AUTOR);
						br.com.n2s.sara.dao.DAOUsuario daoUser = new DAOUsuario();
						daoUser.create(userSara);
					}else {
						userSara = br.com.n2s.sara.util.Facade.buscarUsuarioPorCPF(user.getCpf());
					}
					session.setAttribute("usuarioSara", userSara);	
					//Filtro normal
					chain.doFilter(request, response);
				}else {
					((HttpServletResponse) response).sendRedirect("http://n2s.russas.ufc.br"+Constantes.getAppGuardiaoUrl() + "/");
				}
			}else if(session.getAttribute("usuarioSara") != null && ((br.com.n2s.sara.model.Usuario)session.getAttribute("usuarioSara")).getCpf().equals(((Usuario)session.getAttribute("usuario")).getPessoa().getCpf()) &&
					session.getAttribute("usuario")!= null && DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Usuario)session.getAttribute("usuario")).getPessoa().getId())!=null &&
					((Usuario)session.getAttribute("usuario")).getTokenUsuario().equals(DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Usuario)session.getAttribute("usuario")).getPessoa().getId()))){
				chain.doFilter(request, response);
			}else {
				((HttpServletResponse) response).sendRedirect("http://n2s.russas.ufc.br"+Constantes.getAppGuardiaoUrl() + "/");
			}
		}				
	}

	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}