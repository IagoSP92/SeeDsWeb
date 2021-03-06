<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
	<h1><fmt:message key="nav.subidos" bundle="${messages}"/></h1>

	<span>
		<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.SUBIDOS%>&<%=ParameterNames.TIPO%>=2">
			<button class="dualButton  w3-btn w3-border2 w3-text-blue2">
				<fmt:message key="dual.videos" bundle="${messages}"/>
			</button>
		</a>
	</span>
	<span>
		<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.SUBIDOS%>&<%=ParameterNames.TIPO%>=3">
			<button class="dualButton  w3-btn w3-border2 w3-text-blue2">
				<fmt:message key="dual.listas" bundle="${messages}"/>
			</button>
		</a>
	</span>	
	
	<span id="crearListaButtonDiv">
		<a href="/SeeDsWeb/<%=ControllerPath.LISTA%>?<%=ParameterNames.ACTION%>=<%=Actions.PRE_CREAR_LISTA%>">
			<button class="  w3-btn w3-border2 w3-text-blue2">
				<fmt:message key="usermenu.crearLista" bundle="${messages}"/>
			</button>
		</a>
	</span>
	
<div id="subidosDiv">
		<c:if test="${not empty resultados}">
			<c:choose>
				<c:when test="${tipo==2}">
					<h3><fmt:message key="dual.videos" bundle="${messages}"></fmt:message></h3>
				</c:when>
				<c:when test="${tipo==3}">
					<h3><fmt:message key="dual.listas" bundle="${messages}"></fmt:message></h3>
				</c:when>	
			</c:choose>
			<c:forEach items="${resultados}" var="contenido">
				<div class="thumbDiv">
					<c:url var="urlDetalle" scope="page" value="<%=ControllerPath.CONTENIDO%>">
						<c:param name="action" value="<%=Actions.DETALLE%>"/>
						<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${contenido.getId()}"/>
						<c:param name="<%=ParameterNames.TIPO%>" value="${contenido.getTipo()}"/>
					</c:url>			
					<li><a class="a_sinsub" href="${urlDetalle}">
						${contenido.nombre}<br>
						<fmt:message key="detalle.fecha" bundle="${messages}"/>
						${contenido.fechaAlta}
					</a></li>
				</div>		
			</c:forEach>		
			<!-- Paginacion  -->
			<p><center>			
				<c:url var="urlBase" value="contenido" scope="page">
					<c:param name="action" value="${action}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${tipo}"/>
				</c:url>		
				<%@include file="/html/common/paginacion.jsp"%>	
		
			</center></p>
		</c:if>
	</div>

</div>

<%@include file="/html/common/footer.jsp"%>