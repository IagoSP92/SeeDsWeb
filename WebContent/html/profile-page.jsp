<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
	<h1>Perfil de Usuario</h1>
	
	<div id="recuadro_usuario">
	
	<span> Nombre: </span> ${usuario.nombre}
	<br/>
	<span> fechaAlta: </span> ${usuario.fechaAlta}
	<br/>
	<span> fechaMod: </span> ${usuario.fechaMod}
	<br/>
	<span> autor: </span> ${usuario.getAutor()}
	<br/>
	<span> tipo: </span> ${usuario.tipo}
	<br/>
	<br/>	
	<span> email: </span> ${usuario.email}
	<br/>
	<span> contrasena: </span> ${usuario.contrasena}
	<br/>
	<span> descripcion: </span> ${usuario.descripcion}
	<br/>
	<span> avatarUrl: </span> ${usuario.avatarUrl}
	<br/>
	<span> nombreReal: </span> ${usuario.nombreReal}
	<br/>
	<span> apellidos: </span> ${usuario.apellidos}
	<br/>
	<span> idPais: </span> ${usuario.getPais()}
	<br/>
	<span> fechaNac: </span> ${usuario.fechaNac}
	<br/>
	
	<a href="<%=ControllerPath.RELACIONES%>?<%=ParameterNames.ACTION%>=<%=Actions.DENUNCIAR%>">
		<button class="userButton">
			<fmt:message key="detalle.denunciar" bundle="${messages}"/>
		</button>
	</a>
	<a href="<%=ControllerPath.RELACIONES%>?<%=ParameterNames.ACTION%>=<%=Actions.SEGUIR%>">
		<button class="userButton">
			<fmt:message key="detalle.comentar" bundle="${messages}"/>
		</button>
	</a>
	<br/>
	
	<fmt:message key="detalle.subidos" bundle="${messages}"></fmt:message>
	<ul>
		<c:forEach items="${usuario.videosSubidos}" var="video">
			<div class="videoPerfil">
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
	</ul>
	<ul>
		<c:forEach items="${usuario.listasSubidas}" var="lista">
			<div class="listaPerfil">
				<c:url var="urlDetalle" scope="page" value="/redirect">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${lista.id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${lista.getTipo()}"/>
				</c:url>			
				<li><a class="a_sinsub" href="${urlDetalle}">
				${urlDetalle}
				${lista.nombre}<br>
				${lista.fechaAlta} - ${lista.fechaMod}
				</a></li>
			</div>		
		</c:forEach>
	</ul>
	<br/>
	<br/>	
	<fmt:message key="detalle.seguidos" bundle="${messages}"></fmt:message>
	<ul>
		<c:forEach items="${usuariosSeguidos}" var="usuario">
			<div class="videoPerfil">
				<c:url var="urlDetalle" scope="page" value="/redirect">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${usuario.id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${usuario.tipo}"/>
				</c:url>			
				<li><a class="a_sinsub" href="${urlDetalle}">
				${urlDetalle}
				${usuario.nombre}<br>
				${usuario.fechaAlta} - ${video.fechaMod}
				</a></li>
			</div>		
		</c:forEach>
	</ul>
	<ul>
		<c:forEach items="${listasSeguidas}" var="lista">
			<div class="listaPerfil">
				<c:url var="urlDetalle" scope="page" value="/redirect">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${lista.id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${lista.tipo}"/>
				</c:url>			
				<li><a class="a_sinsub" href="${urlDetalle}">
				${urlDetalle}
				${lista.nombre}<br>
				${lista.fechaAlta} - ${lista.fechaMod}
				</a></li>
			</div>		
		</c:forEach>
	</ul>
	<br/>
	<br/>
	<fmt:message key="detalle.guardados" bundle="${messages}"></fmt:message>
	<ul>
		<c:forEach items="${videosGuardados}" var="video">
			<div class="videoPerfil">
				<c:url var="urlDetalle" scope="page" value="/redirect">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${video.id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${video.tipo}"/>
				</c:url>			
				<li><a class="a_sinsub" href="${urlDetalle}">
				${urlDetalle}
				${video.nombre}<br>
				${video.fechaAlta} - ${video.fechaMod}
				</a></li>
			</div>		
		</c:forEach>
	</ul>
	<br/>
	<br/>
	<ul>
		<c:forEach items="${listasGuardadas}" var="lista">
			<div class="listaPerfil">
				<c:url var="urlDetalle" scope="page" value="/redirect">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${lista.id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${lista.tipo}"/>
				</c:url>			
				<li><a class="a_sinsub" href="${urlDetalle}">
				${urlDetalle}
				${lista.nombre}<br>
				${lista.fechaAlta} - ${lista.fechaMod}
				</a></li>
			</div>		
		</c:forEach>
	</ul>

	</div>	
	
	
</div>
<%@include file="/html/common/footer.jsp"%>