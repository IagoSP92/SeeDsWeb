<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">

	<h1><fmt:message key="miPerfil" bundle="${messages}"/></h1>
	
	<button class="w3-btn w3-border2 w3-text-blue2" id="botonEditarPerfil">
			<fmt:message key="editar.editarPerfil" bundle="${messages}"/>
	</button>
	
	<c:url var="urlCancel" scope="page" value="/usuario">
		<c:param name="action" value="<%=Actions.MI_PERFIL%>"/>
	</c:url>
	<a class="a_sinsub" href="${urlCancel}" id="botonEditarPerilCancelar" hidden=true>
		<button class="w3-btn w3-border2 w3-text-blue2"  id="botonEditarPerfilCancelar">
			<fmt:message key="editar.editarPerfilCancelar" bundle="${messages}"/>
		</button>
	</a>
	
	<div id="recuadro_usuario">
		<form action="/SeeDsWeb/<%=ControllerPath.USUARIO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.EDITAR_PERFIL%>"/>
			<input type="hidden" name="<%=ParameterNames.ID_CONTENIDO%>" value="${usuario.id}"/>
			<br/>
			
			<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.fechaAlta" bundle="${messages}"/> </strong></span> ${usuario.fechaAlta} </div>
			<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.email" bundle="${messages}"/> </strong></span> ${usuario.email} </div>
			<br/>			
			
			<div class="editarCampoDiv" > <span> <strong><fmt:message key="editar.nombre" bundle="${messages}"/></strong> </span> ${usuario.nombre}			
				<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/> 
				<input type="text" name="<%=ParameterNames.NOMBRE%>" value="${usuario.nombre}"/>	
			</div></div><br/>
			
			<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.descripcion" bundle="${messages}"/> </strong></span> ${usuario.descripcion} 
				<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/> 
				<input type="text" name="<%=ParameterNames.DESCRIPCION%>" value="${usuario.descripcion}"> 
			</div></div><br/>
			

			
			<div class="editarCampoDiv">  <span><strong> <fmt:message key="editar.real" bundle="${messages}"/> </strong></span> ${usuario.nombreReal} 
				<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/>
				<input type="text" name="<%=ParameterNames.NOMBRE_REAL%>" value="${usuario.nombreReal}">
			</div></div><br/>
			
			<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.apellidos" bundle="${messages}"/> </strong></span> ${usuario.apellidos} 
				<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/>
				<input type="text" name="<%=ParameterNames.APELLIDOS%>" value="${usuario.apellidos}">
			</div></div><br/>
			
			<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.fechaNac" bundle="${messages}"/></strong> </span> ${usuario.fechaNac} 
				<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/>
				<input type="date" name="<%=ParameterNames.FECHA_NAC%>" value="${usuario.fechaNac}">
			</div></div><br/>
			
			<input id="botonEditarPerilSalvar" class="w3-btn w3-border2 w3-text-blue2" hidden=true type="submit" name="usuario" value="<fmt:message key="editar.guardarEdicion" bundle="${messages}"/>"/>
		</form>
		
	</div>
	
</div>
<%@include file="/html/common/footer.jsp"%>