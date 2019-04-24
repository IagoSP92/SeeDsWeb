<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">

	<h1>Perfil de Usuario</h1>
	<a href="<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.EDITAR_PERFIL%>">
		<button class="userButton">
			<fmt:message key="detalle.editarPerfil" bundle="${messages}"></fmt:message>
		</button>
	</a>
	
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
	<br/>
	
</div>
<%@include file="/html/common/footer.jsp"%>