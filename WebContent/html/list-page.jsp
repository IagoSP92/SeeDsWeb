<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">

	<span class="detalle-titulo"> ${lista.nombre} </span>
	<span class="detalle-autor"> ${nombre_autor} </span>
	<br/>
	<span><fmt:message key="detalle.descripcion" bundle="${messages}"/></span> ${usuario.descripcion}
	<br/>
	<br/>
	

	<br/>
	<span><fmt:message key="detalle.valoracion" bundle="${messages}"/></span> ${video.valoracion}
	<span><fmt:message key="detalle.reproducciones" bundle="${messages}"/></span> ${video.reproducciones}
	<span><fmt:message key="detalle.fecha" bundle="${messages}"/></span> ${video.fechaAlta}
	
	<br/>

	
	
	<a href="<%=ControllerPath.RELACIONES%>?<%=ParameterNames.ACTION%>=<%=Actions.DENUNCIAR%>">
		<button class="userButton">
			<fmt:message key="detalle.denunciar" bundle="${messages}"/>
		</button>
	</a>
	
	<a href="<%=ControllerPath.RELACIONES%>?<%=ParameterNames.ACTION%>=<%=Actions.GUARDAR%>">
		<button class="userButton">
			<fmt:message key="detalle.guardar" bundle="${messages}"/>
		</button>
	</a>
	
	<a href="<%=ControllerPath.RELACIONES%>?<%=ParameterNames.ACTION%>=<%=Actions.COMENTAR%>">
		<button class="userButton">
			<fmt:message key="detalle.comentar" bundle="${messages}"/>
		</button>
	</a>
	<a href="<%=ControllerPath.RELACIONES%>?<%=ParameterNames.ACTION%>=<%=Actions.SEGUIR%>">
		<button class="userButton">
			<fmt:message key="detalle.comentar" bundle="${messages}"/>
		</button>
	</a>
	<br/>
	
	
		<fmt:message key="detalle.videos-lista" bundle="${messages}"></fmt:message>
		<c:forEach items="${videosLista}" var="video">
			<div class="videoDetalle">
				<c:url var="urlDetalle" scope="page" value="/redirect">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${video.id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${video.getTipo()}"/>
				</c:url>			
				<li><a class="a_sinsub" href="${urlDetalle}">
				<div>${video.id}</div>
				
				${urlDetalle}
				${video.nombre}<br>
				${video.fechaAlta} - ${video.fechaMod}
				</a></li>
			</div>		
		</c:forEach>
	
	<br/>
	<fmt:message key="detalle.comentarios" bundle="${messages}"/>	
	<c:forEach items="${comentarios}" var="comentario">
		<div class="comentario">
			${comentario.autor}
			${comentario.texto}
		</div>
	</c:forEach>

</div>
<%@include file="/html/common/footer.jsp"%>.jsp"%>