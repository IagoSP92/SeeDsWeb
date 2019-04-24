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
	
		<span id="checkTodosDiv">
		<input type="checkbox" name="<%=ParameterNames.CHECK_TODOS%>" id="checkTodos" onchange="checkCompatible()">
			<fmt:message key="buscador.todo" bundle="${messages}"/>
		</span>
		<span id="checkVideosDiv">
		<input type="checkbox" name="<%=ParameterNames.CHECK_VIDEO%>" id="checkVideos" onchange="checkCompatible()">
			<fmt:message key="buscador.videos" bundle="${messages}"/>
		</span>
		<span id="checkListassDiv">	
		<input type="checkbox" name="<%=ParameterNames.CHECK_LISTA%>" id="checkListas" onchange="checkCompatible()">
			<fmt:message key="buscador.listas" bundle="${messages}"/>
		</span>
		<span id="checkUsuariosDiv">
		<input type="checkbox" name="<%=ParameterNames.CHECK_USUARIO%>" id="checkUsuarios" onchange="checkCompatible()">
			<fmt:message key="buscador.usuarios" bundle="${messages}"/>
		</span>
		<br/>			
		<input type="text" size=80
				name="<%=ParameterNames.NOMBRE%>" 
				value="${requestScope.nombre}" 
				placeholder='<fmt:message key="buscador.nombre" bundle="${messages}"/>'/>
		<input id="buscarButton" type="submit" value='<fmt:message key="buscador.buscar" bundle="${messages}"/>'>
		<br id="br-buscador"/>			
					
		<span class="buscador-label"><fmt:message key="buscador.valoracion" bundle="${messages}"/></span>
		<input name="<%=ParameterNames.VALORACION_MIN%>" type="text" size=2
				value="<%=ParameterUtils.getParameter(request, ParameterNames.VALORACION_MIN) %>"
				placeholder="<fmt:message key="buscador.desde" bundle="${messages}"/>"/>
				
		<input name="<%=ParameterNames.VALORACION_MAX%>" type="text" size=2
				value="<%=ParameterUtils.getParameter(request, ParameterNames.VALORACION_MAX) %>"
				placeholder="<fmt:message key="buscador.hasta" bundle="${messages}"/>"/>
				
		<span class="buscador-label"><fmt:message key="buscador.reproducciones" bundle="${messages}"/></span>
		<input name="<%=ParameterNames.REPRODUCCIONES_MIN%>" type="text" size=3
				value="<%=ParameterUtils.getParameter(request, ParameterNames.REPRODUCCIONES_MIN) %>"
				placeholder="<fmt:message key="buscador.desde" bundle="${messages}"/>"/>
				
		<input name="<%=ParameterNames.REPRODUCCIONES_MAX%>" type="text" size=3
				value="<%=ParameterUtils.getParameter(request, ParameterNames.REPRODUCCIONES_MAX) %>"
				placeholder="<fmt:message key="buscador.hasta" bundle="${messages}"/>"/>

		<span class="buscador-label"><fmt:message key="buscador.fecha" bundle="${messages}"/></span>
		<input name="<%=ParameterNames.FECHA_MIN%>" type="text" size=6
				value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MIN) %>"
				placeholder="<fmt:message key="buscador.desde" bundle="${messages}"/>"/>
				
		<input name="<%=ParameterNames.FECHA_MAX%>" type="text" size=6
				value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MAX) %>"
				placeholder="<fmt:message key="buscador.hasta" bundle="${messages}"/>"/>			
	</form>
</div>
<div id="buscador-results">

<!-- Total de resultados  -->
<p>
	<c:if test="${not empty total}">
		<h3>
			<fmt:message key="busqueda.titulo2" bundle="${messages}">
				<fmt:param value="${total}"></fmt:param>
			</fmt:message>
		</h3>
	</c:if>
</p>

<!-- Resultados -->
<c:if test="${not empty resultados}">
	<!-- Listado -->	
	<ul>
		<c:forEach items="${resultados}" var="contenido">					
			<div class="thumbDiv">
				<c:url var="urlDetalle" scope="page" value="/redirect">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${contenido.id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${contenido.tipo}"/>
				</c:url>		
				<li>
					<a class="a_sinsub" href="${urlDetalle}">
						${contenido.nombre}<br>
						${contenido.getTipo()}<br>
						${contenido.fechaAlta}
						 - 
						${contenido.fechaMod}
					</a>
				</li>
			</div>
		</c:forEach>
	</ul>

<p>todos <%=ParameterUtils.getParameter(request, ParameterNames.CHECK_TODOS) %> video ${check_video} ${check_lista}${check_usuario}</p>
	<!-- Paginacion  -->
	<p><center>
		
	<c:url var="urlBase" value="contenido" scope="page">
		<c:param name="action" value="<%=Actions.BUSCAR%>"/>


				
		<c:param name="<%=ParameterNames.NOMBRE%>" value="${nombre}"/>		
		<c:param name="<%=ParameterNames.VALORACION_MIN%>" value="${valoracion_min}"/>
		<c:param name="<%=ParameterNames.VALORACION_MAX%>" value="${valoracion_max}"/>
		<c:param name="<%=ParameterNames.REPRODUCCIONES_MIN%>" value="${reproducciones_min}"/>
		<c:param name="<%=ParameterNames.REPRODUCCIONES_MAX%>" value="${reproduccione_max}"/>
		<c:param name="<%=ParameterNames.FECHA_MIN%>" value="${fecha_min}"/>
		<c:param name="<%=ParameterNames.FECHA_MAX%>" value="${fecha_max}"/>
		
				<c:param name="<%=ParameterNames.CHECK_TODOS%>" value="<%=ParameterUtils.getParameter(request, ParameterNames.CHECK_TODOS) %>"/>
		<c:param name="<%=ParameterNames.CHECK_VIDEO%>" value="<%=ParameterUtils.getParameter(request, ParameterNames.CHECK_VIDEO) %>"/>
		<c:param name="<%=ParameterNames.CHECK_LISTA%>" value="${check_lista}"/>
		<c:param name="<%=ParameterNames.CHECK_USUARIO%>" value="${check_usuario}"/>
	</c:url>
	
	<c:if test="${page > 1}"><!-- Primera Página -->
		<a href="${urlBase}&page=1">
			<fmt:message key="pag.primera" bundle="${messages}"/>
		</a>
		&nbsp;&nbsp;
	</c:if>
	
	<c:if test="${page > 1}"><!-- Página Anterior -->
		<a href="${urlBase}&page=${page - 1}">
			<fmt:message key="pag.anterior" bundle="${messages}"/>
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
	
	<c:if test="${page < totalPages}"><!-- Siguiente Página -->	
		&nbsp;&nbsp;		
		<a href="${urlBase}&page=${page + 1}">
			<fmt:message key="pag.siguiente" bundle="${messages}"/>
		</a>			
	</c:if>	
	<c:if test="${page != totalPages}"><!-- Ultima Página -->
		&nbsp;&nbsp;
		<a href="${urlBase}&page=${totalPages}">
			<fmt:message key="pag.ultima" bundle="${messages}"/>
		</a>
	</c:if>		

	</center></p>
	
</c:if>


</div>	
