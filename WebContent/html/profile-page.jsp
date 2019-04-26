<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
	<h1>Perfil de Usuario</h1>
	
	<div id="recuadro_usuario">	
		<div id="nombrePerfilDiv"><h1> ${usuario.nombre} </h1></div>
		<div id="avatarPerfilDiv">  ${usuario.avatarUrl}   </div>
		<div id="descripcionPerfilDiv"> ${usuario.descripcion} </div>
		<div id="fechaPerfilDiv"> <span><fmt:message key="detalle.miembroDesde" bundle="${messages}"/></span>${usuario.fechaAlta} </div>
	</div>	
	
	<div id="perfilAutenticadosDiv">
		<c:if test = "${autenticado==true}">
			<c:choose>
				<c:when test="${siguiendo == true}">
					<button class="userButton seguirButton" data-tipo="${usuario.tipo}" data-idContenido="${usuario.id}" data-siguiendo="true">
						<fmt:message key="detalle.noseguir" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton seguirButton" data-tipo="${usuario.tipo}" data-idContenido="${usuario.id}" data-siguiendo="false">
						<fmt:message key="detalle.seguir" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>			
			<c:choose>
				<c:when test="${denunciado != null}">
					<button class="userButton denunciarButton" data-tipo="${usuario.tipo}" data-idContenido="${usuario.id}" data-denunciando="Null">
						<fmt:message key="detalle.nodenunciar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton denunciarButton" data-tipo="${usuario.tipo}" data-idContenido="${usuario.id}" data-denunciado="denuncia">
						<fmt:message key="detalle.denunciar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>	
	     </c:if>
	</div>
	
	<span>
		<a href="<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.DETALLE%>&<%=ParameterNames.ID_CONTENIDO%>=${id}&<%=ParameterNames.TIPO%>=2">
			<button class="dualButton">
				<fmt:message key="dual.videos" bundle="${messages}"/>
			</button>
		</a>
	</span>
	<span>
		<a href="<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.DETALLE%>&<%=ParameterNames.ID_CONTENIDO%>=${id}&<%=ParameterNames.TIPO%>=3">
			<button class="dualButton">
				<fmt:message key="dual.listas" bundle="${messages}"/>
			</button>
		</a>
	</span>	
	
	<div id="subidosPerfilDiv">
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
					<c:url var="urlDetalle" scope="page" value="/redirect">
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
				<c:url var="urlBase" value="usuario" scope="page">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${tipo}"/>
				</c:url>	
				<%@include file="/html/common/paginacion.jsp"%>	
		
			</center></p>
		</c:if>
	</div>

</div>


<%@include file="/html/common/footer.jsp"%>