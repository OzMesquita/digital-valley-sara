package br.com.n2s.sara.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UsuarioDAO;
import model.Usuario;
import dao.DAOFactory;
import util.Facade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Autenticacao extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("json")!=null){
			String json = request.getParameter("json");
			Gson gson = new GsonBuilder().create();
			Usuario user = gson.fromJson(json, Usuario.class);
			Usuario usuario = Facade.buscarPorLogin(user.getLogin());
			usuario.setToken(DAOFactory.criarUsuarioDAO().buscarToken(user.getPessoa().getId()));
			if(user.getLogin().equals(usuario.getLogin()) && user.getToken().equals(usuario.getToken())){
				UsuarioDAO userDAO = DAOFactory.criarUsuarioDAO();
				userDAO.salvarTokenUsuario(Facade.buildToken(), usuario.getPessoa().getId());
				response.setStatus(200);
			}else{
				response.setStatus(403);
			}
		}else{
			response.setStatus(403);
		}
	}

}
