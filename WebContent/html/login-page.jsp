<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
<div id="login-form"
<h1><fmt:message key="interfaz.entrar" bundle="${messages}"/></h1>

<form action="<%=ControllerPath.USUARIO%>" method="post">
<h1>iago</h1>

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
	<input id="#loginSubmit" type="submit" value="Entrar" /> 

</form>

<a href="<%=ControllerPath.USUARIO%>?action=<%=Actions.PRERREGISTRO%>">Registrarse</a>
 ></div>
</div>
<%@include file="/html/common/footer.jsp"%>

