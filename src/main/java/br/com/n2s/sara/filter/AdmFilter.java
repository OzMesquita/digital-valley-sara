package br.com.n2s.sara.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Constantes;

/**
 * Servlet Filter implementation class AdmFilter
 */
@WebFilter("/AdmFilter")
public class AdmFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = (HttpSession) (((HttpServletRequest)request).getSession());
		Usuario usuario = (Usuario)session.getAttribute("usuarioSara");
		if (usuario.getTipo().equals(NivelUsuario.ADMINISTRADOR)) {
			chain.doFilter(request, response);
		}else {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Acesso não autorizado!\nRestrito ao administrador do sistema.");
			((HttpServletResponse)response).sendRedirect(Constantes.getAppUrl()+"webapp/jsp/indexAutor.jsp");
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
