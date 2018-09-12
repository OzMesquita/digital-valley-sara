<%@page import="br.com.n2s.sara.dao.DAOItem"%>
<%@page import="java.util.List"%>

<%@page import="br.com.n2s.sara.model.Item"%>
<%@page import="br.com.n2s.sara.model.Criterio"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Critério</title>
</head>
<body>
		<%
			String c = request.getParameter("criterio");
			Criterio criterio = (Criterio) session.getAttribute(c);
			session.setAttribute("criterio", criterio);
			//int idCrit = Integer.parseInt(crit.split("crit")[1]);
			
			
			/* ArrayList<String> itens = new ArrayList<String>();
			itens.add("Item 1"); itens.add("Item 2"); itens.add("Item 3"); itens.add("Item 4"); itens.add("Item 5"); */
			
			//Criterio criterio = critCon.buscar(idCrit);	
			//System.out.println(criterio.getIdCriterio());
			List<Item> itens = new DAOItem().readById(criterio.getIdCriterio());
			
			%>
			
			<center>
			
			<h1> Critério </h1>
				
	        <form action="EditarCriterio" method="post">
	        
	            <p>Descrição: <textarea name="descricaoCrit" rows="1" cols="30" required><%=criterio.getDescricao()%></textarea></p>
	            <p>Peso: <input type="text" name="pesoCrit" value="<%=criterio.getPeso()%>" > </p>
	            
	            <h2> Itens do Critério </h2>
	            
	            <% if(itens.size() != 0){ %>
	            
		            <div id="itens" align="center">
		            	<%
		            		for(int i = 0; i < itens.size()-1; i++){  %>
								<p>Descrição: <textarea id="descricao<%=i%>" name="descricao<%=i%>" rows="1" cols="30"><%=itens.get(i).getDescricao()%></textarea>
		            			<p>Peso: <input type="number" id="peso<%=i%>" name="peso<%=i%>" value="<%=itens.get(i).getPeso()%>"> </p>	
		            			<input type="button" value="Remover Item" name="<%=i%>" onclick="removerItem(<%=i%>);"></p>
		            			<br />			
						<%	}  %>
					</div>
					
					<div id="origem" align="center">
								<p>Descrição: <textarea id="descricao<%=(itens.size()-1)%>" name="descricao<%=(itens.size()-1)%>" rows="1" cols="30"><%=itens.get(itens.size()-1).getDescricao()%></textarea>
		            			<p>Peso: <input type="number" id="peso<%=(itens.size()-1)%>" name="peso<%=(itens.size()-1)%>" value="<%=itens.get(itens.size()-1).getPeso()%>"> </p>
		            			<input type="button" value="Remover Item" name="<%=(itens.size()-1)%>" onclick="removerItem(<%=(itens.size()-1)%>);"></p>
		            			<br />
					</div>
					
					<div id="destino">
					</div>
					
					<input type="button" value="+" onclick="maisCampos();">
		            <input type="button" value="-" onclick="removerCampos(this);">
		            
				<% }else{ %>
					
					<div id="origem" align="center">
            			<p>Descrição: <textarea id="descricao0" name="descricao0" rows="1" cols="30"></textarea></p>
            			<p>Peso: <input type="number" id="peso0" name="peso0" value="0"> </p>
					</div>		
					
					<div id="destino">
					</div>			
					
					<input type="button" value="+" onclick="maisCampos();">
		            <input type="button" value="-" onclick="removerCampos(this);">
					
				<%}%>
	            
				<br /> <br />     
	            <p><button type="submit">Alterar Critério</button></p>
	        </form>

	        
	    </center>
	    
	    <script type="text/javascript">
	    	var cont = 0;
	    	var flag = false;
	    	var numUltItem = 0;
	    	
		    function maisCampos(){
		    	var clone = document.getElementById('origem').cloneNode(true);
		    	var destino = document.getElementById('destino');
		    	destino.appendChild(clone);
	
		    	var camposClonados = clone.getElementsByTagName('input');
		    	for(i=0; i<camposClonados.length;i++){
		    		if(camposClonados[i].value != "Remover Item"){
		    			camposClonados[i].value = '';
		    			
		    			if(flag==false){
			    			numUltItem = parseInt(camposClonados[i].name.split("peso")[1]); 
			    			cont = numUltItem + 1;
			    			flag =  true;
			    		}
			    		camposClonados[i].name = "peso" + cont;
		    		}else{ //BOTÃO "REMOVER ITEM"
			    		//camposClonados[i].setAttribute("onclick", "removerItem("+(parseInt(camposClonados[i].name))+");");
		    			camposClonados[i].setAttribute("onclick", "removerItem("+cont+");");
		    		}
		    	}
		    	
		    	camposClonados = clone.getElementsByTagName('textarea');
		    	for(i=0; i<camposClonados.length;i++){
		    		camposClonados[i].value = '';
		    		camposClonados[i].name = "descricao" + cont;
		    	}
		    	
		    	cont = cont + 1;
		    }
		    
	 	    function removerCampos(id){
	 	    	cont = cont - 1;
		   	    var node1 = document.getElementById('destino');
		    	node1.removeChild(node1.childNodes[cont-numUltItem]);
		    } 
	 	    
	 	    function removerItem(x){
	 	    	/* var origem = document.getElementById('origem');
	 	    	var itens = document.getElementById('itens');
	 	    	origem = origem.getElementsByTagName('input');
	 	    	itens = itens.getElementsByTagName('input');
	 	    	var item = 0; */
			
	 	    	//document.write(x);
	
	 	    	/* REMOVER ITEM X DO CRITERIO */
	 	    	
	 	    	
	 	    	
	 	    	/* for(i = 0; i < origem.length; i++){
	 	    		if(id == "o" && origem[i].value == "Remover Item"){
	 	    			item = parseInt(valor);
	 	    		}else if(x == "i" && itens[i].value == "Remover Item"){
	 	    			item = parseInt(valor);
	 	    		}
	 	    	} */
	 	    	
	 	    }
	    </script>

</body>
</html>