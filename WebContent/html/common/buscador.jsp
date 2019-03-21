<%@ page import="com.seeds.web.utils.*, com.seeds.web.controller.*" %>
<%@ page import="java.util.*" %>       

<div id="buscador-form">
	
		<form action="<%=ControllerPath.USUARIO%>" method="post">	
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
<h1>Resultados de l abiusqueda</h1>
		
	<%		
		List<String> resultados = (List<String>) request.getAttribute(AttributeNames.RESULTADOS);	
		if (resultados!=null && !resultados.isEmpty()) {
			%>
			<p>Resultados:</p>
			<ul><%
			for (String resultado: resultados) {
				%>
				<div class="thumbDiv">
					<li><%=resultado%></li>
				</div>
				<%
			}
			%></ul><%
		}
	%>
</div>	
