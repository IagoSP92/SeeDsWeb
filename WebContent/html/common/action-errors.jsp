<%@ page import="java.util.*, com.seeds.web.model.*, com.seeds.web.controller.*" %>


<%
	
	for (String error: parameterErrors) {
			%><li><%=error%></li>
	<%
	}
%>
