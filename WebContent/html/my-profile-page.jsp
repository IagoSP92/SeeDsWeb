<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">

	<h1><fmt:message key="miPerfil" bundle="${messages}"/></h1>
	
	<button class="userButton" id="botonEditarPerfil">
			<fmt:message key="editar.editarPerfil" bundle="${messages}"/>
	</button>
	
	<c:url var="urlCancel" scope="page" value="/usuario">
		<c:param name="action" value="<%=Actions.MI_PERFIL%>"/>
	</c:url>
	<a class="a_sinsub" href="${urlCancel}" id="botonEditarPerilCancelar" hidden=true>
		<button class="userButton"  id="botonEditarPerfilCancelar">
			<fmt:message key="editar.editarPerfilCancelar" bundle="${messages}"/>
		</button>
	</a>
	
	<div id="recuadro_usuario">
		<br/>
		<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.fechaAlta" bundle="${messages}"/> </strong></span> ${usuario.fechaAlta} </div>
		<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.email" bundle="${messages}"/> </strong></span> ${usuario.email} </div>
		<br/>
		<div class="editarCampoDiv" > <span> <strong><fmt:message key="editar.nombre" bundle="${messages}"/></strong> </span> ${usuario.nombre}
			<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/> <input type="text" name="inputNombre" id="inputNombre"> </div>
		</div><br/>
		<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.descripcion" bundle="${messages}"/> </strong></span> ${usuario.descripcion} 
			<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/> <input type="text" name="inputDescripcion" id="inputDescripcion"> </div>
		</div><br/>
		<div class="editarCampoDiv">  <span><strong> <fmt:message key="editar.avatar" bundle="${messages}"/> </strong></span> ${usuario.avatarUrl} 
			<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/> <input type="text" name="inputAvatar" id="inputAvatar"> </div>
		</div><br/>
		<div class="editarCampoDiv">  <span><strong> <fmt:message key="editar.real" bundle="${messages}"/> </strong></span> ${usuario.nombreReal} 
			<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/> <input type="text" name="inputReal" id="inputReal"> </div>
		</div><br/>
		<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.apellidos" bundle="${messages}"/> </strong></span> ${usuario.apellidos} 
			<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/> <input type="text" name="inputApellidos" id="inputApellidos"> </div>
		</div><br/>
		<div class="editarCampoDiv"> <span><strong> <fmt:message key="editar.fechaNac" bundle="${messages}"/></strong> </span> ${usuario.fechaNac} 
			<div class="edicionSpan" hidden=true> <fmt:message key="editar.nuevo" bundle="${messages}"/> <input type="text" name="inputFecha" id="inputFecha"> </div>
		</div><br/>
	</div>	
		
	<c:url var="urlGo" scope="page" value="/usuario">
		<c:param name="action" value="<%=Actions.EDITAR_PERFIL%>"/>
		<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${usuario.id}"/>
		<c:if test="${param.inputNombre!=null}">
			<c:param name="<%=ParameterNames.NOMBRE%>" value="${param.inputNombre}"/>
		</c:if>
		<c:if test="${param.inputDescripcion!=null}">
			<c:param name="<%=ParameterNames.DESCRIPCION%>" value="${param.inputDescripcion}"/>
		</c:if>
		<c:if test="${param.inputAvatar!=null}">
			<c:param name="<%=ParameterNames.AVATAR%>" value="${param.inputAvatar}"/>
		</c:if>
		<c:if test="${param.inputReal!=null}">
			<c:param name="<%=ParameterNames.NOMBRE_REAL%>" value="${uparam.inputReal}"/>
		</c:if>
		<c:if test="${param.inputApellidos!=null}">
			<c:param name="<%=ParameterNames.APELLIDOS%>" value="${param.inputApellidos}"/>
		</c:if>
		<c:if test="${param.inputFecha!=null}">
			<c:param name="<%=ParameterNames.FECHA_NAC%>" value="${param.inputFecha}"/>
		</c:if>
		
	</c:url>	
	<a class="a_sinsub" href="${urlGo}" id="botonEditarPerilSalvar" hidden=true>
		<button class="userButton">
			<fmt:message key="editar.guardarEdicion" bundle="${messages}"/>
		</button>
	</a>
	
	
</div>
<%@include file="/html/common/footer.jsp"%>