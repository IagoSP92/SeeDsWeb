
<%@ page import="com.seeds.web.utils.*, com.seeds.web.controller.*" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">

	<h2 class="w3-text-blue"><b><fmt:message key="interfaz.subirVideo" bundle="${messages}"/></b></h2>

	<div id="subir-video-form">
		
			<form action="<%=ControllerPath.LISTA%>" method="post" >	
				<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.CREAR_LISTA%>"/>
								
				<div id="campoNombre">
				<span class="w3-text-blue"><b><fmt:message key="form.titulo" bundle="${messages}"/></b></span>
				<input type="text" class="w3-input w3-border" 
						name="<%=ParameterNames.NOMBRE%>" 
						value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE) %>"/>	
				</div>
				
				<div id="campoDescripcionLista">
				<span class="w3-text-blue"><b><fmt:message key="form.descripcion" bundle="${messages}"/></b></span>
				<input type="text" class="w3-input w3-border" 
						name="<%=ParameterNames.DESCRIPCION%>" 
						value="<%=ParameterUtils.getParameter(request, ParameterNames.DESCRIPCION) %>"/>
				</div>
				
				
				<div id="campoCategoriaLista">
				<span  class="w3-text-blue"><b><fmt:message key="form.categoria" bundle="${messages}"/></b></span>				
					<select id="select_categoria" name="<%=ParameterNames.ID_CATEGORIA%>" class="w3-select w3-border">
						<option value="0"><fmt:message key="form.elegir" bundle="${messages}"/></option>
							<c:forEach items="${categorias}" var="categoria">
								<option value="${categoria.idCategoria}">${categoria.nombreCategoria}</option>		
							</c:forEach>
					</select>		
				</div>
				
				<br/>
				<br/>		

				<br/>
				<input type="submit" class="w3-btn w3-blue" id="submit" name="crear_lista" value="<fmt:message key="form.crear" bundle="${messages}"/>"/>
			</form>
			
	</div>

</div>

<%@include file="/html/common/footer.jsp"%>