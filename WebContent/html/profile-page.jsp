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
	
	<div class="perfilVideosSubidosDiv">
		<fmt:message key="detalle.videosSubidos" bundle="${messages}"></fmt:message>
		<c:if test="${not empty videosSubidos}">		
			<ul>
				<c:forEach items="${videosSubidos}" var="video">
					<div class="videoPerfil">
						<c:url var="urlDetalle" scope="page" value="/redirect">
							<c:param name="action" value="<%=Actions.DETALLE%>"/>
							<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${video.id}"/>
							<c:param name="<%=ParameterNames.TIPO%>" value="${video.getTipo()}"/>
						</c:url>			
						<li><a class="a_sinsub" href="${urlDetalle}">
						<div>${video.id}</div>
						
						${video.nombre}<br>
						${video.fechaAlta} - ${video.fechaMod} 
						</a></li>
					</div>		
				</c:forEach>
			</ul>
			<!-- Paginacion  -->
			<p><center>		
				<c:url var="urlBase" value="video" scope="page">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${tipo}"/>
				</c:url>
				<%@include file="/html/common/paginacionV.jsp"%>	
			</center></p>	
		</c:if>
	</div>
	
	<div class="perfilListasSubidasDiv">	
		<fmt:message key="detalle.listasSubidas" bundle="${messages}"></fmt:message>
		<c:if test="${not empty listasSubidas}">
			<ul>
				<c:forEach items="${listasSubidas}" var="lista">
					<div class="listaPerfil">
						<c:url var="urlDetalle" scope="page" value="/redirect">
							<c:param name="action" value="<%=Actions.DETALLE%>"/>
							<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${lista.id}"/>
							<c:param name="<%=ParameterNames.TIPO%>" value="${lista.getTipo()}"/>
						</c:url>			
						<li><a class="a_sinsub" href="${urlDetalle}">
						${lista.nombre}<br>
						${lista.fechaAlta} - ${lista.fechaMod}
						</a></li>
					</div>		
				</c:forEach>
			</ul>
			<!-- Paginacion  -->
			<p><center>		
				<c:url var="urlBase" value="lista" scope="page">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${tipo}"/>
				</c:url>
				<%@include file="/html/common/paginacionL.jsp"%>	
			</center></p>	
		</c:if>
	</div>

</div>
<%@include file="/html/common/footer.jsp"%>