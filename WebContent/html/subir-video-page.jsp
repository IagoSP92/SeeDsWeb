
<%@ page import="com.seeds.web.utils.*, com.seeds.web.controller.*" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">

	<h3><fmt:message key="interfaz.subirVideo" bundle="${messages}"/></h3>

	<div id="subir-video-form">
		
			<form action="<%=ControllerPath.VIDEO%>" method="post">	
				<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.SUBIR_VIDEO%>"/>
				
				<input name="ParameterNames.TIPO"   type="hidden" value="2">
				<input name="ParameterNames.AUTOR"   type="hidden" value="null">
				
				<div id="campoNombre">
				<span class="rotuloCampo"><%=ParameterNames.NOMBRE%>:</span>
				<input type="text"
						name="<%=ParameterNames.NOMBRE%>" 
						value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE) %>"/>	
				</div>
				
				<div id="campoDescripcionVideo">
				<span class="rotuloCampo"><%=ParameterNames.DESCRIPCION%>:</span>
				<input type="text"
						name="<%=ParameterNames.DESCRIPCION%>" 
						value="<%=ParameterUtils.getParameter(request, ParameterNames.DESCRIPCION) %>"/>
				</div>
				
				<div id="campoRuta">
				<span class="rotuloCampo"><%=ParameterNames.RUTA_VIDEO%>:</span>
				<input type="file" name="<%=ParameterNames.RUTA_VIDEO%>"/>
				</div>
	
				<input type="submit" name="subir_video" value="Subir"/>
			</form>
			
	</div>

</div>

<%@include file="/html/common/footer.jsp"%>