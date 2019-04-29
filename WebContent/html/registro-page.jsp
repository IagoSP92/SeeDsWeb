<%@include file="/html/common/header.jsp"%>
<%@ page import="com.seeds.web.utils.*, com.seeds.web.controller.*" %>

<div class="mainWindow">
	

<div id="registro-form">
		<h1 class="w3-text-blue"><fmt:message key="interfaz.registro" bundle="${messages}"/></h1>
	
		<form action="/SeeDsWeb/<%=ControllerPath.USUARIO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.REGISTRO%>"/>
			
			<input name="ParameterNames.TIPO"   type="hidden" value="1">
			<input name="ParameterNames.AUTOR"   type="hidden" value="null">
			
			<div id="campoNombre">
			<span  class="w3-text-blue"><b><fmt:message key="form.nombre" bundle="${messages}"/></b></span>
			<input type="text" class="w3-input w3-border"
					name="<%=ParameterNames.NOMBRE%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE) %>"/>
					<%
						parameterErrors = errors.getErrors(ParameterNames.NOMBRE);
							for (String error: parameterErrors) {
								if(error.equals(ErrorCodes.MANDATORY_PARAMETER)){
									%><li><fmt:message key="form.campoObligatorio" bundle="${messages}"/></li><%
								}
							}
					%>
			</div>
			
			<div id="campoEmail">
			<span  class="w3-text-blue"><b><fmt:message key="form.email" bundle="${messages}"/></b></span>
			<input type="text" class="w3-input w3-border"
					name="<%=ParameterNames.EMAIL%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.EMAIL) %>"/>
					<%
						parameterErrors = errors.getErrors(ParameterNames.EMAIL);
							for (String error: parameterErrors) {
								if(error.equals(ErrorCodes.MANDATORY_PARAMETER)){
									%><li><fmt:message key="form.campoObligatorio" bundle="${messages}"/></li><%
								}
							}
					%>
			</div>
			
			<div id="campoPass">
			<span  class="w3-text-blue"><b><fmt:message key="form.pass" bundle="${messages}"/></b></span>	
			<input type="password" class="w3-input w3-border"
					name="<%=ParameterNames.PASSWORD%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.PASSWORD) %>"/>
					<%
						parameterErrors = errors.getErrors(ParameterNames.PASSWORD);
							for (String error: parameterErrors) {
								if(error.equals(ErrorCodes.MANDATORY_PARAMETER)){
									%><li><fmt:message key="form.campoObligatorio" bundle="${messages}"/></li><%
								}
							}
					%>
			</div>
			<br/>
			<div id="campoFechaNac">
			<span  class="w3-text-blue"><b><fmt:message key="form.nacimiento" bundle="${messages}"/></b></span>
			<input type="date" class="w3-input w3-border"
					name="<%=ParameterNames.FECHA_NAC%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_NAC) %>"/>
					<%
						parameterErrors = errors.getErrors(ParameterNames.FECHA_NAC);
							for (String error: parameterErrors) {
								if(error.equals(ErrorCodes.MANDATORY_PARAMETER)){
									%><li><fmt:message key="form.campoObligatorio" bundle="${messages}"/></li><%
								}
							}
					%>
			</div>
			<br/>
			<div id="campoNombreReal">
			<span  class="w3-text-blue"><b><fmt:message key="form.real" bundle="${messages}"/></b></span>
			<input name="<%=ParameterNames.NOMBRE_REAL%>" type="text"  class="w3-input w3-border"
					value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE_REAL) %>"/>
					<%
						parameterErrors = errors.getErrors(ParameterNames.NOMBRE_REAL);
							for (String error: parameterErrors) {
								if(error.equals(ErrorCodes.MANDATORY_PARAMETER)){
									%><li><fmt:message key="form.campoObligatorio" bundle="${messages}"/></li><%
								}
							}
					%>
			</div>
			
			<div id="campoApellidos">
			<span  class="w3-text-blue"><b><fmt:message key="form.apellidos" bundle="${messages}"/></b></span>
			<input name="<%=ParameterNames.APELLIDOS%>" type="text"  class="w3-input w3-border"
					value="<%=ParameterUtils.getParameter(request, ParameterNames.APELLIDOS) %>"/>
					<%
						parameterErrors = errors.getErrors(ParameterNames.APELLIDOS);
							for (String error: parameterErrors) {
								if(error.equals(ErrorCodes.MANDATORY_PARAMETER)){
									%><li><fmt:message key="form.campoObligatorio" bundle="${messages}"/></li><%
								}
							}
					%>				
			</div>
			
			<div id="campoPais">
				<span  class="w3-text-blue"><b><fmt:message key="form.pais" bundle="${messages}"/></b></span>
				<%
					parameterErrors = errors.getErrors(ParameterNames.ID_PAIS);
						for (String error: parameterErrors) {
							if(error.equals(ErrorCodes.MANDATORY_PARAMETER)){
								%><li><fmt:message key="form.campoObligatorio" bundle="${messages}"/></li><%
							}
						}
				%>		
				<select id="select_pais" name="<%=ParameterNames.ID_PAIS%>" class="w3-select w3-border">
					<option value="0">Select contry:</option>
						<c:forEach items="${paises}" var="pais">
							<option value="${pais.idPais}">${pais.nombrePais}</option>		
						</c:forEach>
				</select>
				<%
					parameterErrors = errors.getErrors(ParameterNames.ID_PAIS);
						for (String error: parameterErrors) {
							if(error.equals(ErrorCodes.MANDATORY_PARAMETER)){
								%><li><fmt:message key="form.campoObligatorio" bundle="${messages}"/></li><%
							}
						}
				%>
			</div>
			<br/>
			<input  class="w3-btn w3-blue" id="submit"  type="submit" name="registro" value="<fmt:message key="form.registro" bundle="${messages}"/>"/>
		</form>
		<br/>
		<a class="w3-btn w3-blue" id="submitAlt" href="/SeeDsWeb<%=ViewPath.ENTRAR%>"/>
		<b><fmt:message key="form.entrar" bundle="${messages}"/></b></a>
		
</div>

</div>

<%@include file="/html/common/footer.jsp"%>