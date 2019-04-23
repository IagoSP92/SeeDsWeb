<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">

	<span class="detalle-titulo"> ${video.nombre} </span>
	<span class="detalle-autor"> ${nombre_autor} </span>
	
	<br/>
	<video></video>
	<br/>
	<span><fmt:message key="detalle.valoracion" bundle="${messages}"/></span> ${video.valoracion}
	<span><fmt:message key="detalle.reproducciones" bundle="${messages}"/></span> ${video.reproducciones}
	<span><fmt:message key="detalle.fecha" bundle="${messages}"/></span> ${video.fechaAlta}

	<br/>
	<span><fmt:message key="detalle.descripcion" bundle="${messages}"/></span> ${usuario.descripcion}
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
	<br/>	
	<fmt:message key="detalle.comentarios" bundle="${messages}"/>	
	<c:forEach items="${comentarios}" var="comentario">
		<div class="comentario">
			${comentario.autor}
			${comentario.texto}
		</div>
	</c:forEach>

</div>
<%@include file="/html/common/footer.jsp"%>