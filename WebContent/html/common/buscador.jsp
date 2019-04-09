<%@ page import="com.isp.seeds.model.Contenido, com.seeds.web.utils.*, com.seeds.web.controller.*" %>
<%@ page import="java.util.*" %>

    <%
    Map<String, String> valores = new HashMap<String, String>();
    valores.put(ParameterNames.ACTION, Actions.DETALLE_PERFIL);    
    %>   

<div id="buscador-form">
	
		<form action="<%=ControllerPath.CONTENIDO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.BUSCAR%>"/>
			
			<input type="text"
					name="<%=ParameterNames.NOMBRE%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE) %>"
					placeholder="Nombre"/>		

			<input name="<%=ParameterNames.FECHA_MIN%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MIN) %>"
					placeholder="fechaDesde"/>
					
			<input name="<%=ParameterNames.FECHA_MAX%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MAX) %>"
					placeholder="fechaHASTA"/>
					
			<input name="<%=ParameterNames.ID_CONTENIDO%>" type="text"
					value="<%=ParameterUtils.getParameter(request, ParameterNames.ID_CONTENIDO) %>"
					placeholder="id"/>
					
			<input id="buscarButton"type="submit" name="buscar" value="Buscar"/>
		</form>
	</p>
</div>
<div id="buscador-results">
<h1>Resultados de la búsqueda</h1>
<p><fmt:message key = "resultados" bundle="${messages}"/></p>



	<%
		valores.clear();
		valores.put(ParameterNames.ACTION, Actions.DETALLE_PERFIL);
	
		List<Contenido> resultados = (List<Contenido>) request.getAttribute(AttributeNames.RESULTADOS);
		
		if (resultados!=null && !resultados.isEmpty()) {
			%>
			<p>Resultados:</p>
			<ul><%
			for (Contenido resultado: resultados) {
				valores.put(ParameterNames.ID_CONTENIDO, resultado.getIdContenido().toString());

				%>
				<a class="a_sinsub" href="<%=ParameterUtils.URLBuilder(ControllerPath.CONTENIDO, valores)%>"><div class="thumbDiv">
					<li><%=resultado.getNombre().toString()%>
					<br/>
					<%=resultado.getFechaAlta().toString()%> - <%=resultado.getFechaMod().toString()%>
					<br/>
					<%if(resultado.getIdAutor()!=null){%>
						<%=resultado.getIdAutor().toString()%>
						<br/>
					<%}%>
					</li>
				</div>
				</a>
				<%
			}
			%></ul><%
		}
	%>
	
	<a href="<%=ParameterUtils.URLBuilder(ControllerPath.CONTENIDO, valores)%>">Siguientes</a>

</div>	
