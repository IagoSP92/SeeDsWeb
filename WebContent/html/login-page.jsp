<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
<h3>LOG IN</h3>

<form action="<%=ControllerPath.USUARIO%>" method="post">

	<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.ENTRAR%>"/>
	
	<%@include file="/html/common/action-errors.jsp"%> <!--  !!!!!!!!!!!!!!!!!!! -->

	<input type="email" name="<%=ParameterNames.EMAIL%>" 
				placeholder="usuario@ejemplo.com"
				value="<%=ParameterUtils.getParameter(request, ParameterNames.EMAIL)%>" />
				<%
					parameterErrors = errors.getErrors(ParameterNames.EMAIL);
					for (String error: parameterErrors) {
						%><li><%=error%></li><%
					}
				%>
	<br/>		
	<input type="password" name="<%=ParameterNames.PASSWORD%>"/>
	<input type="submit" value="Entrar" /> 

</form>

<a href="<%=ControllerPath.USUARIO%>?action=<%=Actions.PRERREGISTRO%>">Registrarse</a>
 
</div>
<%@include file="/html/common/footer.jsp"%>

