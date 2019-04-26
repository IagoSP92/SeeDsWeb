<%@page import="java.util.HashMap"%>
<%@ page import="java.util.List, java.util.Map" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
	<div id="recuadro_video">
		<div class="cabeceraVideoDiv">
			<span class="detalle-titulo"> ${video.nombre} </span>
			<span class="detalle-autor"> ${nombre_autor} </span>	
		</div>
		<div class="reproductorDiv">
			<c:url var="urlVideo" scope="page" value="/video">
				<c:param name="action" value="<%=Actions.REPRODUCIR_VIDEO%>"/>
				<c:param name="<%=ParameterNames.ID_VIDEO%>" value="${video.id}"/>
				<c:param name="<%=ParameterNames.RUTA_VIDEO%>" value="${video.url}"/>					
			</c:url>			
			<video src="${urlVideo}" width="854" height="480"  controls autoplay></video>
		</div>
		<div class="datosVideoDiv">
			<span><fmt:message key="detalle.valoracion" bundle="${messages}"/></span> ${video.valoracion}
			<span><fmt:message key="detalle.reproducciones" bundle="${messages}"/></span> ${video.reproducciones}
			<span><fmt:message key="detalle.fecha" bundle="${messages}"/></span> ${video.fechaAlta}			
			<div class="descripcionVideoDiv">
				<span><fmt:message key="detalle.descripcion" bundle="${messages}"/></span> ${usuario.descripcion}
			</div>		
		</div>
	</div>
	
	<div id="perfilAutenticadosDiv">
		<c:if test = "${autenticado==true}">
			<c:choose>
				<c:when test="${guardado == true}">
					<button class="userButton guardarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-guardado="true">
						<fmt:message key="detalle.guardado" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton guardarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-guardado="false">
						<fmt:message key="detalle.noguardado" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>			
			<c:choose>
				<c:when test="${denunciado != null}">
					<button class="userButton denunciarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-denunciando="Null">
						<fmt:message key="detalle.nodenunciar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton denunciarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-denunciado="denuncia">
						<fmt:message key="detalle.denunciar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>	
			<c:choose>
				<c:when test="${comentado != 'Null'}">
					<button class="userButton comentarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-comentado="Null">
						<fmt:message key="detalle.nocomentar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="userButton comentarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-comentado="comentario">
						<fmt:message key="detalle.comentar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>			
	     </c:if>
	    
	      <div class="textAreaDiv" hidden><textarea rows="4" cols="80"></textarea></div>
	</div>
	
	<div class="comentariosDiv">
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
<%@include file="/html/common/footer.jsp"%>