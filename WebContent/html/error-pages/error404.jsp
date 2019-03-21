<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.seeds.web.utils.*, com.seeds.web.controller.*" %>

<%@ page import="java.util.*" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERROR 404</title>
</head>
<body>
	<h1>ERROR 404:</h1>
	<h2>Página no encontrada</h2>
	<h3>La ruta especificada no existe o no está disponible</h3>	
	
	<%=ControllerPath.ERROR%>
	
		<%	
		String ip =(String) request.getAttribute(AttributeNames.IP);	
		%>
		<p>Ip: <%=ip%></p>

</body>
</html>