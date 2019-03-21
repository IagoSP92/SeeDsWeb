<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.seeds.web.utils.*, com.seeds.web.model.*,com.isp.seeds.model.*, com.seeds.web.controller.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 <title>See D's</title>
	<!--  .js .css -->
	<link rel="stylesheet" type="text/css" media="screen" href="/SeeDsWeb/css/main.css">
</head>
<%
	Errors errors = (Errors) request.getAttribute(AttributeNames.ERRORS);
	if (errors == null) errors = new Errors();
%>
<body>
		
		<div id="mainLogoDiv">
			<a href="/SeeDsWebTraining/html/index.jsp">
				<img id="mainLogo" src="/SeeDsWebTraining/img/seedsLogo.JPG" alt="SeeDs Logo"/>
			</a>
		</div>
		
		<%@include file="/html/common/user-menu.jsp"%>
		<%@include file="/html/common/nav-menu.jsp"%>