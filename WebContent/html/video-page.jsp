<%@page import="java.util.HashMap"%>
<%@ page import="java.util.List, java.util.Map" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow" data-idContenido="${video.id}" data-tipo="${video.tipo}" data-minota="${mi_valoracion}">

	<div id=recuadroVideoDiv >
		<div class="cabeceraVideoDiv" >
			<div class="detalle-titulo"> ${video.nombre} </div>
			<div class="detalle-autor">  ${nombre_autor} </div>	
		</div><!-- cabeceraVideoDiv -->
		<br/>
		<div class="reproductorDiv">
			<c:url var="urlVideo" scope="page" value="/video">
				<c:param name="action" value="<%=Actions.REPRODUCIR_VIDEO%>"/>
				<c:param name="<%=ParameterNames.ID_VIDEO%>"   value="${video.id}"/>
				<c:param name="<%=ParameterNames.RUTA_VIDEO%>" value="${video.url}"/>					
			</c:url>			
			<video src="${urlVideo}" controls autoplay></video>
		</div><!-- reproductorDiv -->
		<br/>
		
		<div class="datosVideoDiv w3-border2">
			<span class="detalleSpan"><b><fmt:message key="detalle.valoracion" bundle="${messages}"/></b></span> 
			
			<div id="detalleValoracion" class="inline-block"> ${video.valoracion} </div>			
			<input id="insertarNota" hidden=true name="<%=ParameterNames.MI_VALORACION%>" type="text" size=2
				value="<%=ParameterUtils.getParameter(request, ParameterNames.VALORACION_MIN) %>"/>

			<button hidden=true class="insertarNotaButton" >
				<fmt:message key="detalle.valorar" bundle="${messages}"/>
			</button>

			<span id="detalleVisitas" class="detalleSpan"><b><fmt:message key="detalle.reproducciones" bundle="${messages}"/></b></span> ${video.reproducciones}
			<span class="detalleSpan"><b><fmt:message key="detalle.fecha" bundle="${messages}"/></b></span> ${video.fechaAlta}			
			<div class="descripcionVideoDiv">
				<span><b><fmt:message key="detalle.descripcion" bundle="${messages}"/></b></span><p id="pDescripcion>"> ${usuario.descripcion}</p>
			</div>
		</div><!-- datosVideoDiv -->
	</div><!-- recuadroVideoDiv -->
	
	<div id="perfilAutenticadosDiv">
		<c:if test = "${autenticado==true}">
			<c:choose>
				<c:when test="${guardado == true}">
					<button class="w3-btn w3-border2 w3-text-blue2" id="guardarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-guardado="true">
						<fmt:message key="detalle.guardado" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="w3-btn w3-border2 w3-text-blue2" id="guardarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-guardado="false">
						<fmt:message key="detalle.noguardado" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>
			<c:choose>
				<c:when test="${denunciado == true}">
					<button class="w3-btn w3-border2 w3-text-blue2" id="denunciarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-denunciado="true">
						<fmt:message key="detalle.nodenunciar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise> 
					<button class="w3-btn w3-border2 w3-text-blue2" id="denunciarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-denunciado="false">
						<fmt:message key="detalle.denunciar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>
			<c:choose>
				<c:when test="${comentado != 'Null'}">
					<button class="w3-btn w3-border2 w3-text-blue2" id="noComentarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-comentado="Null">
						<fmt:message key="detalle.noComentar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="w3-btn w3-border2 w3-text-blue2" id="comentarButton" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-comentado="comentario">
						<fmt:message key="detalle.comentar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>			
	     </c:if>
	    
	      <div class="textAreaDiv" hidden=true id="commentarioTextDiv">
	      	<textarea id="comentarioText"rows="4" cols="80"></textarea>
	      	<button class="w3-btn w3-border2 w3-text-blue2" id="comentarButtonEnviar" data-tipo="${video.tipo}" data-idContenido="${video.id}" data-comentado="comentario">
				<fmt:message key="detalle.comentarEnviar" bundle="${messages}"/>
			</button>
	      </div>
	      
	</div><!-- perfilAutenticadoDiv -->
	
	
	<div class="comentariosDiv">
		<p class="labelDetalle"><b><fmt:message key="detalle.comentarios" bundle="${messages}"/></b></p>
		<c:forEach items="${comentarios}" var="comentario">
			<div class="comentario">
				<b>${comentario.autor}</b>
				<br>
				${comentario.texto}
			</div>
		</c:forEach>
	</div><!-- comentariosDiv -->

</div>
<script src="/SeeDsWeb/javascript/countVisits.js"></script>
<%@include file="/html/common/footer.jsp"%>