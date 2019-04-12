<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<fmt:setBundle basename = "resources.Messages" var="messages" scope="session"/>
<fmt:setLocale value = "${sessionScope['user-locale']}" scope="session"/>
<fmt:setLocale value = "es_ES"/>
    
<%@ page import="com.seeds.web.utils.*, com.seeds.web.model.*,com.isp.seeds.model.*, com.seeds.web.controller.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 <title>See D's</title>
	<script src="/SeeDsWeb/js/main.js"></script>

	<link rel="stylesheet" type="text/css" media="screen" href="/SeeDsWeb/css/main.css">
</head>

<%
	ErrorManager errors = (ErrorManager) request.getAttribute(AttributeNames.ERRORS);
	if (errors == null) errors = new ErrorManager();
%>
<body>
		
		<div id="mainLogoDiv">
			<a href="/SeeDsWeb/html/index.jsp">
				<img id="mainLogo" src="/SeeDsWeb/img/seedsLogo.JPG" alt="SeeDs Logo"/>
			</a>
		</div>
		<div class="flagsDiv">
			<div class="flag">
			<a href="<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.CAMBIAR_LOCALE%>&locale=es-ES">
				<img src="/SeeDsWeb/img/flags/ES.png">
			</a>
			</div>
			<div class="flag">
			<a href="<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.CAMBIAR_LOCALE%>&locale=en-GB">
				<img src="/SeeDsWeb/img/flags/EN.png">
			</a>
			</div>
		</div>
		
		<%@include file="/html/common/user-menu.jsp"%>
		<%@include file="/html/common/nav-menu.jsp"%>