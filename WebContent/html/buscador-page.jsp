<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<!--  Que diferencia deduces (o porqu�) entre buscador.jsp y buscador-page.jsp ? -->
<!--  Falta algo aqu�? -->
<div class="mainWindow">
	<fmt:message key="busqueda.titulo1" bundle="${messages}"></fmt:message>
	<%@include file="/html/common/buscador.jsp"%>
</div>
<%@include file="/html/common/footer.jsp"%>
