<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
	<div id="recuadro_lista">
	
		<div class="cabeceraListaDiv">
			<span class="detalle-titulo"> ${lista.nombre} </span>
			<span class="detalle-autor"> ${nombre_autor} </span>	
		</div>		

		<div class="datosListaDiv">
			<span><fmt:message key="detalle.valoracion" bundle="${messages}"/></span> ${lista.valoracion}
			<span><fmt:message key="detalle.reproducciones" bundle="${messages}"/></span> ${lista.reproducciones}
			<span><fmt:message key="detalle.fecha" bundle="${messages}"/></span> ${lista.fechaAlta}			
			<div class="descripcionVideoDiv">
				<span><fmt:message key="detalle.descripcion" bundle="${messages}"/></span> ${lista.descripcion}
			</div>		
		</div>
	</div>	

	<div id="perfilAutenticadosDiv">
		<c:if test = "${autenticado==true}">
			<c:choose>
				<c:when test="${guardado == true}">
					<button class="userButton guardarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-guardado="true">
						<fmt:message key="detalle.guardado" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton guardarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-guardado="false">
						<fmt:message key="detalle.noguardado" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>	
			<c:choose>
				<c:when test="${denunciado != null}">
					<button class="userButton denunciarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-denunciando="Null">
						<fmt:message key="detalle.nodenunciar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton denunciarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-denunciado="denuncia">
						<fmt:message key="detalle.denunciar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>
			<c:choose>
				<c:when test="${comentado != 'Null'}">
					<button class="userButton comentarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-comentado="Null">
						<fmt:message key="detalle.nocomentar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton comentarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-comentado="comentario">
						<fmt:message key="detalle.comentar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>
			<c:choose>
				<c:when test="${siguiendo == true}">
					<button class="userButton seguirButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-siguiendo="true">
						<fmt:message key="detalle.noseguir" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton seguirButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-siguiendo="false">
						<fmt:message key="detalle.seguir" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>		
	     </c:if>
	</div>
	
	<div id="videosEnListaDiv">
	<fmt:message key="detalle.videos-lista" bundle="${messages}"></fmt:message>
		<c:if test="${not empty resultados}">		
			<c:forEach items="${resultados}" var="video">
				<div class="thumbDiv">
					<c:url var="urlDetalle" scope="page" value="/redirect">
						<c:param name="action" value="<%=Actions.DETALLE%>"/>
						<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${video.getId()}"/>
						<c:param name="<%=ParameterNames.TIPO%>" value="${video.getTipo()}"/>
					</c:url>			
					<li><a class="a_sinsub" href="${urlDetalle}">
						${video.nombre}<br>
						<fmt:message key="detalle.fecha" bundle="${messages}"/>
						${video.fechaAlta}
					</a></li>
				</div>		
			</c:forEach>		
			<!-- Paginacion  -->
			<p><center>			
				<c:url var="urlBase" value="lista" scope="page">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${tipo}"/>		
				</c:url>		
				<%@include file="/html/common/paginacion.jsp"%>	
		
			</center></p>
		</c:if>
	</div>
	
	<div class="comentariosDv">
		<fmt:message key="detalle.comentarios" bundle="${messages}"/>	
		<c:forEach items="${comentarios}" var="comentario">
			<div class="comentario">
				${comentario.autor}
				<br>
				${comentario.texto}
			</div>
		</c:forEach>
	</div>

</div>
<%@include file="/html/common/footer.jsp"%>.jsp"%>