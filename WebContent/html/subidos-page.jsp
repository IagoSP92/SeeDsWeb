<%@ page import="java.util.List" %>
<%@ page import="com.isp.seeds.model.Contenido, com.seeds.web.utils.*, com.seeds.web.controller.*" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
	<h1><fmt:message key="nav.subidos" bundle="${messages}"/></h1>
	
    <%
    Map<String, String> valores = new HashMap<String, String>();
    valores.put(ParameterNames.ACTION, Actions.DETALLE_PERFIL);
    %>


<div id="subidos-results">
	<div id="subidos-videos">

		<c:if test="${not empty resultados_v}">
	
			<fmt:message key="subidos.videos}"></fmt:message>
			<c:forEach items="${resultados_v}" var="video">
				<div class="thumbDiv">
					<c:url var="urlDetalle" scope="page" value="/redirect">
						<c:param name="action" value="<%=Actions.DETALLE%>"/>
						<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${video.getId()}"/>
						<c:param name="<%=ParameterNames.TIPO%>" value="${video.getTipo()}"/>
					</c:url>
					<li><a class="a_sinsub" href="${urlDetalle}">
				<div>${video.id}</div>
					
					${video.nombre}<br>
					${video.fechaAlta} - ${video.fechaMod}
					</a></li>
				</div>		
			</c:forEach>
		
			<!-- Paginacion  -->
			<p><center>				
				<c:url var="urlBase" value="lista" scope="page">
					<c:param name="action" value="<%=Actions.SUBIDOS%>"/>
				</c:url>				
				<%@include file="/html/common/paginacionV.jsp"%>
			</center></p>
		</c:if>	

</div><!-- guardados-resultados	-->	
</div><!-- MainWindow -->

<%@include file="/html/common/footer.jsp"%>