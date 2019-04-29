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
		<input type="checkbox" name="<%=ParameterNames.CHECK_TODOS%>" id="checkTodos" onchange="checkCompatible()"  class="hover">
			<fmt:message key="buscador.todo" bundle="${messages}"/>
		</span>
		<span id="checkVideosDiv">
		<input type="checkbox" name="<%=ParameterNames.CHECK_VIDEO%>" id="checkVideos" onchange="checkCompatible()"  class="hover">
			<fmt:message key="buscador.videos" bundle="${messages}"/>
		</span>
		<span id="checkListassDiv">	
		<input type="checkbox" name="<%=ParameterNames.CHECK_LISTA%>" id="checkListas" onchange="checkCompatible()"  class="hover">
			<fmt:message key="buscador.listas" bundle="${messages}"/>
		</span>
		<span id="checkUsuariosDiv">
		<input type="checkbox" name="<%=ParameterNames.CHECK_USUARIO%>" id="checkUsuarios" onchange="checkCompatible()"  class="hover">
			<fmt:message key="buscador.usuarios" bundle="${messages}"/>
		</span>
		<br/>			
		<input type="text" size=80 name="<%=ParameterNames.NOMBRE%>" value="${requestScope.nombre}"  class="hover"
				placeholder='<fmt:message key="buscador.nombre" bundle="${messages}"/>'/>
		<input id="buscarButton" class="w3-blue inline-block w3-border2 w3-text-blue2 hover" type="submit" value='<fmt:message key="buscador.buscar" bundle="${messages}"/>'>
		<br id="br-buscador"/>			
					
		<span class="buscador-label"><fmt:message key="buscador.valoracion" bundle="${messages}"/></span>
		<input name="<%=ParameterNames.VALORACION_MIN%>" type="text" size=2 class="hover"
				value="<%=ParameterUtils.getParameter(request, ParameterNames.VALORACION_MIN) %>"
				placeholder="<fmt:message key="buscador.desde" bundle="${messages}"/>"/>
				
		<input name="<%=ParameterNames.VALORACION_MAX%>" type="text" size=2 class="hover"
				value="<%=ParameterUtils.getParameter(request, ParameterNames.VALORACION_MAX) %>"
				placeholder="<fmt:message key="buscador.hasta" bundle="${messages}"/>"/>
				
		<span class="buscador-label"><fmt:message key="buscador.reproducciones" bundle="${messages}"/></span>
		<input name="<%=ParameterNames.REPRODUCCIONES_MIN%>" type="text" size=3 class="hover"
				value="<%=ParameterUtils.getParameter(request, ParameterNames.REPRODUCCIONES_MIN) %>"
				placeholder="<fmt:message key="buscador.desde" bundle="${messages}"/>"/>
				
		<input name="<%=ParameterNames.REPRODUCCIONES_MAX%>" type="text" size=3 class="hover"
				value="<%=ParameterUtils.getParameter(request, ParameterNames.REPRODUCCIONES_MAX) %>"
				placeholder="<fmt:message key="buscador.hasta" bundle="${messages}"/>"/>

		<span class="buscador-label"><fmt:message key="buscador.fecha" bundle="${messages}"/></span>
		<input name="<%=ParameterNames.FECHA_MIN%>" type="text" size=6 class="hover"
				value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MIN) %>"
				placeholder="<fmt:message key="buscador.desde" bundle="${messages}"/>"/>
				
		<input name="<%=ParameterNames.FECHA_MAX%>" type="text" size=6  class="hover"
				value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MAX) %>"
				placeholder="<fmt:message key="buscador.hasta" bundle="${messages}"/>"/>			
	</form>
</div>
<div id="buscador-results">

<!-- Total de resultados  -->
<p>
	<c:if test="${not empty total}">
		<h3><%
			if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("BUSCAR") ){
				%>
				<fmt:message key="busqueda.titulo2" bundle="${messages}">
					<fmt:param value="${total}"></fmt:param>
				</fmt:message>
				<%   
			}
			if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("GENERAL") ){
				%>
				<fmt:message key="busqueda.general" bundle="${messages}">
					<fmt:param value="${total}"></fmt:param>
				</fmt:message>
				<%   
			}
			if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("MUSICA") ){
				%>
				<fmt:message key="busqueda.general" bundle="${messages}">
					<fmt:param value="${total}"></fmt:param>
				</fmt:message>
				<%   
			}
			if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("SERIES") ){
				%>
				<fmt:message key="busqueda.general" bundle="${messages}">
					<fmt:param value="${total}"></fmt:param>
				</fmt:message>
				<%     
			}
			if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("CORTOS") ){
				%>
				<fmt:message key="busqueda.general" bundle="${messages}">
					<fmt:param value="${total}"></fmt:param>
				</fmt:message>
				<%  
			}
			if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("DOCUMENTAL") ){
				%>
				<fmt:message key="busqueda.general" bundle="${messages}">
					<fmt:param value="${total}"></fmt:param>
				</fmt:message>
				<%  
			}
			if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("GUIAS") ){
				%>
				<fmt:message key="busqueda.general" bundle="${messages}">
					<fmt:param value="${total}"></fmt:param>
				</fmt:message>
				<%  
			}
		
		%></h3>
	</c:if>
	<c:if test="${empty total}">
		<h3>
			<fmt:message key="busqueda.sinresultados" bundle="${messages}">
				<fmt:param value="${total}"></fmt:param>
			</fmt:message> 
		</h3>
	</c:if>	
</p>

<!-- Resultados -->
<c:if test="${not empty resultados}">
	<!-- Listado -->	
	<div id="resultadosDiv">
		<c:forEach items="${resultados}" var="contenido">					
			<div class="thumbDiv hover">
				<c:url var="urlDetalle" scope="page" value="/redirect">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${contenido.id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${contenido.tipo}"/>
				</c:url>		

				<a class="a_sinsub" href="${urlDetalle}">
					${contenido.nombre}<br>
					${contenido.getTipo()}<br>
					${contenido.fechaAlta}
				</a>

			</div>
		</c:forEach>
	</div>

	<!-- Paginacion  -->
	<p><center>
		
		<c:url var="urlBase" value="contenido" scope="page">
			<c:param name="action" value="${action}"/>
	
			<c:param name="<%=ParameterNames.NOMBRE%>" value="${nombre}"/>		
			<c:param name="<%=ParameterNames.VALORACION_MIN%>" value="${valoracion_min}"/>
			<c:param name="<%=ParameterNames.VALORACION_MAX%>" value="${valoracion_max}"/>
			<c:param name="<%=ParameterNames.REPRODUCCIONES_MIN%>" value="${reproducciones_min}"/>
			<c:param name="<%=ParameterNames.REPRODUCCIONES_MAX%>" value="${reproduccione_max}"/>
			<c:param name="<%=ParameterNames.FECHA_MIN%>" value="${fecha_min}"/>
			<c:param name="<%=ParameterNames.FECHA_MAX%>" value="${fecha_max}"/>
			
			<c:param name="<%=ParameterNames.ACEPTAR_TODOS%>" value="${aceptar_todos}"/>
			<c:param name="<%=ParameterNames.ACEPTAR_VIDEO%>" value="${aceptar_video}"/>
			<c:param name="<%=ParameterNames.ACEPTAR_LISTA%>" value="${aceptar_lista}"/>
			<c:param name="<%=ParameterNames.ACEPTAR_USUARIO%>" value="${aceptar_usuario}"/>
			
			<c:param name="<%=ParameterNames.CATEGORIA%>" value="${categoria}"/>
			
		</c:url>
		<%@include file="/html/common/paginacion.jsp"%>	
	</center></p>
	
</c:if>


</div>	
