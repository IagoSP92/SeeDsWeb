<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.seeds.web.utils.*, com.seeds.web.model.*, com.seeds.web.controller.*" %>    
<%@ page import="java.util.List" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value = "${sessionScope['user-locale']}" scope="session"/>
<fmt:setBundle basename = "resources.Messages" var="messages" scope="session"/>

<fmt:setLocale value = "es_ES"/>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>See D's Share your Show</title>
	 
	<script src="<%=request.getContextPath()%>/javascript/jquery-3.3.1.min.js" type="text/javascript"></script> 
	<script src="/SeeDsWeb/javascript/web.js"></script>

	<link rel="stylesheet" type="text/css" media="screen" href="/SeeDsWeb/css/w3css.css">
	<link rel="stylesheet" type="text/css" media="screen" href="/SeeDsWeb/css/main.css">
	
	<link rel="icon" href="<%=ViewPath.ROOT%>img/icon/icono.png" type="image/png" size="32x32">
	
</head>

<%
	ErrorManager errors = (ErrorManager) request.getAttribute(AttributeNames.ERRORS);
	if (errors == null) errors = new ErrorManager();
	List<String> parameterErrors = errors.getErrors(ParameterNames.ACTION);
%>
<body>
		
		<div id="mainLogoDiv">
			<a href="/SeeDsWeb/html/index.jsp">
				<img id="mainLogo" src="/SeeDsWeb/img/seedsLogo.png" alt="SeeDs Logo"/>
			</a>
		</div>
		<div class="flagsDiv">
			<div class="flag">
			<a href="<%=ViewPath.ROOT%><%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.CAMBIAR_LOCALE%>&locale=es-ES">
				<img src="<%=ViewPath.ROOT%>img/flags/ES.png">
			</a>
			</div>
			<div class="flag">
			<a href="<%=ViewPath.ROOT%><%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.CAMBIAR_LOCALE%>&locale=en-GB">
				<img src="<%=ViewPath.ROOT%>img/flags/EN.png">
			</a>
			</div>
		</div>
		
		<%@include file="/html/common/user-menu.jsp"%>
		<%@include file="/html/common/nav-menu.jsp"%>