<%@ page import="java.util.*, com.ejemplo.service.*, com.ejemplo.web.util.*, com.ejemplo.web.model.*, com.ejemplo.web.controller.*" %>
	
<%
	List<String> parameterErrors = errors.getErrors(ParameterNames.ACTION);
	for (String error: parameterErrors) {
			%><li><%=error%></li>
	<%
	}
%>
