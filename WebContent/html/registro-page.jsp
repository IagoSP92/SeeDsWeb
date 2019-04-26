<%@include file="/html/common/header.jsp"%>
<%@ page import="com.seeds.web.utils.*, com.seeds.web.controller.*" %>

<div class="mainWindow">
	

<div id="registro-form">
		<h1><fmt:message key="interfaz.registro" bundle="${messages}"/></h1>
	
		<form action="<%=ControllerPath.USUARIO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.REGISTRO%>"/>
			
			<input name="ParameterNames.TIPO"   type="hidden" value="1">
			<input name="ParameterNames.AUTOR"   type="hidden" value="null">
			
			<div id="campoNombre">
			<span class="rotuloCampo "><%=ParameterNames.NOMBRE%>:</span>
			<input type="text"
					name="<%=ParameterNames.NOMBRE%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE) %>"/>	
			</div>
			
			<div id="campoEmail">
			<span class="rotuloCampo"><%=ParameterNames.EMAIL%>:</span>
			<input type="text"
					name="<%=ParameterNames.EMAIL%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.EMAIL) %>"/>
			</div>
			
			<div id="campoPass">
			<span class="rotuloCampo"><%=ParameterNames.PASSWORD%>:</span>	
			<input type="text"
					name="<%=ParameterNames.PASSWORD%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.PASSWORD) %>"/>
			</div>
			<br/>
			<div id="campoFechaNac">
			<span class="rotuloCampo"><%=ParameterNames.FECHA_NAC%>:</span>
			<input type="date"
					name="<%=ParameterNames.FECHA_NAC%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_NAC) %>"/>
			</div>
			<br/>
			<div id="campoNombreReal">
			<span class="rotuloCampo"><%=ParameterNames.NOMBRE_REAL%>:</span>
			<input name="<%=ParameterNames.NOMBRE_REAL%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE_REAL) %>"/>
			</div>
			
			<div id="campoApellidos">
			<span class="rotuloCampo"><%=ParameterNames.APELLIDOS%>:</span>
			<input name="<%=ParameterNames.APELLIDOS%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.APELLIDOS) %>"/>					
			</div>
			
			<div id="campoPais">
				<span class="rotuloCampo">Pais</span>					
				
				<span class="rotuloCampo custom-select" style="width:200px;">
					<select id="select_pais" name="<%=ParameterNames.ID_PAIS%>"  >
						<option value="0">Select contry:</option>
							<c:forEach items="${paises}" var="pais">
								<option value="${pais.idPais}">${pais.nombrePais}</option>		
							</c:forEach>
					</select>
				</span>
			</div>
			<br/>
			<input id="registroSubmit" type="submit" name="registro" value="Registro"/>
		</form>
		
</div>

</div>

<%@include file="/html/common/footer.jsp"%>