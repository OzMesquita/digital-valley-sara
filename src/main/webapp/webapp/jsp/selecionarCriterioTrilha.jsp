<%@page import="br.com.n2s.sara.dao.DAOTrilha"%>
<%@page import="br.com.n2s.sara.dao.DAOItem"%>
<%@page import="br.com.n2s.sara.dao.DAOCriterio"%>
<%@page import="br.com.n2s.sara.dao.DAOCriterioTrilha"%>
<%@page import="br.com.n2s.sara.model.Item"%>
<%@page import="java.util.List"%>
<%@page import="br.com.n2s.sara.model.Criterio"%>
<%@page import="java.time.LocalDate"%>
<%@page import="br.com.n2s.sara.model.Trilha"%>
<%@page import="br.com.n2s.sara.model.CriterioTrilha"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%
			String key = request.getParameter("criterioTrilha");
			CriterioTrilha criterioTrilha = (CriterioTrilha) session.getAttribute(key);
			
			/*Criando cópia do Critério Trilha*/
			
			CriterioTrilha criterioTrilhaCopia = new CriterioTrilha();
			criterioTrilhaCopia.setNome(criterioTrilha.getNome());
			criterioTrilhaCopia.setDataCriacao(LocalDate.now());
			
			DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
			daoCriterioTrilha.create(criterioTrilhaCopia);
			criterioTrilhaCopia.setIdCriterioTrilha(daoCriterioTrilha.getLastId()); // Fazer essa busca de último id inserido de outra forma
			
			/*Copiando os critérios e os itens*/
			
			DAOCriterio daoCriterio = new DAOCriterio();
			List<Criterio> criterios = daoCriterio.obterCriteriosPorTrilha(criterioTrilha.getIdCriterioTrilha());
			
			DAOItem daoItem = new DAOItem();
			
			for(Criterio c : criterios){
				Criterio criterio = new Criterio();
				criterio.setCriterioTrilha(criterioTrilhaCopia);
				criterio.setDescricao(c.getDescricao());
				criterio.setPeso(c.getPeso());
				daoCriterio.create(criterio);
				
				criterio.setIdCriterio(daoCriterio.getLastId());
				
				List<Item> itens = daoItem.readById(c.getIdCriterio());
				
				for(Item i : itens){
					i.setCriterio(criterio);
					daoItem.create(i);
				}
			}
			
			Trilha trilha = (Trilha) session.getAttribute("trilha");
        	session.setAttribute("trilha", trilha);
			
        	trilha.setCriterioTrilha(criterioTrilhaCopia);
        	DAOTrilha daoTrilha = new DAOTrilha();
			daoTrilha.update(trilha);
		%>
		
		<jsp:forward page="gerenciaCriterios.jsp"></jsp:forward>
</body>
</html>