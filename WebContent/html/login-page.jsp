<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
	
	<div id="login-form">
		<h1 class="w3-text-blue"><fmt:message key="interfaz.entrar" bundle="${messages}"/></h1>
		
			
			<form action="<%=ControllerPath.USUARIO%>" method="post">			
				<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.ENTRAR%>"/>				
				<%@include file="/html/common/action-errors.jsp"%> <!--  !!!!!!!!!!!!!!!!!!! -->
			
				<label class="w3-text-blue"><b><fmt:message key="form.email" bundle="${messages}"/></b></label>
				<input  class="w3-input w3-border" type="email" name="<%=ParameterNames.EMAIL%>" 
							placeholder="usuario@ejemplo.com"
							value="<%=ParameterUtils.getParameter(request, ParameterNames.EMAIL)%>" />
							<%
								parameterErrors = errors.getErrors(ParameterNames.EMAIL);
								for (String error: parameterErrors) {
									%><li><%=error%></li><%
								}
							%>
				
				<label class="w3-text-blue"><b><fmt:message key="form.pass" bundle="${messages}"/></b></label>
				<input class="w3-input w3-border" type="password" name="<%=ParameterNames.PASSWORD%>"/>
							<%
								parameterErrors = errors.getErrors(ParameterNames.PASSWORD);
								for (String error: parameterErrors) {									
									%><li><%=error%></li><%
								}
							%>
				
				<input class="w3-btn w3-blue" id="submit" type="submit" value=<fmt:message key="form.entrar" bundle="${messages}"/> /> 
			
			</form>
			<br/><br/>
			
			<a class="w3-btn w3-blue" id="submitAlt" href="<%=ControllerPath.USUARIO%>?action=<%=Actions.PRERREGISTRO%>"><b><fmt:message key="form.registrate" bundle="${messages}"/></b></a>
	</div>
</div>
<%@include file="/html/common/footer.jsp"%>

