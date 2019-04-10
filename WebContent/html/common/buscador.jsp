<%@ page import="com.isp.seeds.model.Contenido, com.seeds.web.utils.*, com.seeds.web.controller.*" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <%
    Map<String, String> valores = new HashMap<String, String>();
    valores.put(ParameterNames.ACTION, Actions.DETALLE_PERFIL);    
    %>

<div id="buscador-form">
	
		<form action="<%=ControllerPath.CONTENIDO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.BUSCAR%>"/>
			
			<input type="text"
					name="<%=ParameterNames.NOMBRE%>" 
					value="${requestScope.nombre}" 
					placeholder='<fmt:message key="buscador.nombre" bundle="${messages}"/>'/>
					<!-- placeholder="Nombre" />-->

			<input name="<%=ParameterNames.FECHA_MIN%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MIN) %>"
					placeholder="fechaDesde"/>
					
			<input name="<%=ParameterNames.FECHA_MAX%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MAX) %>"
					placeholder="fechaHASTA"/>
					
			<input name="<%=ParameterNames.ID_CONTENIDO%>" type="text"
					value="<%=ParameterUtils.getParameter(request, ParameterNames.ID_CONTENIDO) %>"
					placeholder="id"/>
			
			<input id="buscarButton" type="submit" value='<fmt:message key="buscar" bundle="${messages}"/>'>
			
		</form>
	</p>
</div>
<div id="buscador-results">

	<%
		valores.clear();
		valores.put(ParameterNames.ACTION, Actions.DETALLE_PERFIL);
	
		List<Contenido> resultados = (List<Contenido>) request.getAttribute(AttributeNames.RESULTADOS);
		
		if (resultados!=null && !resultados.isEmpty()) {
			%>
			<h1><fmt:message key = "busqueda.titulo" bundle="${messages}"/></h1>
			
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
	<br/><br/><br/><br/>
	
	<!-- Total de resultados  -->
<p>
	<c:if test="${not empty total}">
		<fmt:message key="Encontrados" bundle="${messages}">
			<fmt:param value="${total}"></fmt:param>
		</fmt:message>		
	</c:if>
</p>

<!-- Resultados -->
<c:if test="${not empty resultados}">
	<!-- Listado -->
	
	<ul>
		<c:forEach items="${resultados}" var="contenido">
			<c:url var="urlDetalle" scope="page" value="transportista">
				<c:param name="action" value="<%=Actions.DETALLE_PERFIL%>"/>
				<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${contenido.idContenido}"/>
			</c:url>			
			<li><a href="${urlDetalle}">${contenido.nombre}</a></li>			
		</c:forEach>
	</ul>

	<!-- Paginacion  -->
	<p><center>
		
	<c:url var="urlBase" value="contenido" scope="page">
		<c:param name="action" value="<%=Actions.BUSCAR%>"/>
		<c:param name="<%=ParameterNames.NOMBRE%>" value="${nombre}"/>
		<c:param name="<%=ParameterNames.FECHA_MIN%>" value="${nombre}"/>
		<c:param name="<%=ParameterNames.FECHA_MAX%>" value="${fecha_min}"/>
		<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${idContenido}"/>
		<!--  y asi todos los parametros de la busqueda anterior ... -->
	</c:url>

	<!-- A la primera pagina -->
	<c:if test="${page > 1}">
		<a href="${urlBase}&page=1">
			<fmt:message key="primera" bundle="${messages}"/>
		</a>
		&nbsp;&nbsp;
	</c:if>

	<!-- A la anterior pagina -->
	<c:if test="${page > 1}">
		<a href="${urlBase}&page=${page - 1}">
			<fmt:message key="anterior" bundle="${messages}"/>
		</a>
		&nbsp;&nbsp;
	</c:if>

	<c:if test="${totalPages > 1}">	
	
		<c:forEach begin="${firstPagedPage}" end="${lastPagedPage}" var="i">
			<c:choose>
			  <c:when test="${page != i}">
					&nbsp;<a href="${urlBase}&page=${i}"><b>${i}</b></a>&nbsp;
			  </c:when>
			  <c:otherwise>
					&nbsp;<b>${i}</b>&nbsp;
			  </c:otherwise>
			</c:choose>
		</c:forEach>

	</c:if>

	<!-- A la siguiente página -->	
	<c:if test="${page < totalPages}">
		&nbsp;&nbsp;		
		<a href="${urlBase}&page=${page + 1}">
			<fmt:message key="siguiente" bundle="${messages}"/>
		</a>			
	</c:if>	
	<!-- A la ultima página -->
	<c:if test="${page != totalPages}">
		&nbsp;&nbsp;<a href="${urlBase}&page=${totalPages}"><fmt:message key="ultima" bundle="${messages}"/></a>
	</c:if>		

	</center></p>
	
</c:if>


</div>	
