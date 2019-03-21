<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<h1>Pagina Prueba Redirect</h1>

	<form action="/SeeDsWebTraining/PruebaRedirect" method="post">
	
		<a href= "/SeeDsWeb/PruebaRedirect?redirect=https://www.w3schools.com/">w3</a>
		<a href= "/SeeDsWeb/PruebaRedirect?redirect=https://www.google.com/">Google</a>
	
	</form>

<%@include file="/html/common/footer.jsp"%>