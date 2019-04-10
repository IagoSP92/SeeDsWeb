<%@ page import="com.seeds.web.utils.*, com.seeds.web.controller.*" %>
<%@ page import="java.util.*" %>



<div id="registro-form">
	
		<form action="<%=ControllerPath.USUARIO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.REGISTRO%>"/>
			
			<input name="ParameterNames.TIPO"   type="hidden" value="1">
			<input name="ParameterNames.AUTOR"   type="hidden" value="null">
			
			<div id="campoNombre">
			<span class="rotuloCampo"><%=ParameterNames.NOMBRE%>:</span>
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
			<div id="campoFechaNac">
			<span class="rotuloCampo"><%=ParameterNames.FECHA_NAC%>:</span>
			<input type="text"
					name="<%=ParameterNames.FECHA_NAC%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_NAC) %>"/>
			</div>
			
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
			<input name="<%=ParameterNames.ID_PAIS%>" type="select" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.ID_PAIS) %>"/>		
			</div>
					

			<input type="submit" name="registro" value="Registro"/>
		</form>
		
</div>