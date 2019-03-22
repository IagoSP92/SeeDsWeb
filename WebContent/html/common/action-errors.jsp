<%@ page import="java.util.*, com.seeds.web.model.*, com.seeds.web.controller.*" %>


<%
	List<String> parameterErrors = errors.getErrors(ParameterNames.ACTION);
	for (String error: parameterErrors) {
			%><li><%=error%></li>
	<%
	}
%>
