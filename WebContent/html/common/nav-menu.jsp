<%@ page import="com.isp.seeds.service.*, com.seeds.web.utils.*, com.seeds.web.model.*, com.isp.seeds.model.*, com.seeds.web.controller.*" %>
<div id="nav-menu">

	<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">General</button></a>
	<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">Videoclips</button></a>
	<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">Series</button></a>
	<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">Cortos</button></a>
	<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">Documental</button></a>
	<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">Guias</button></a>
	<br/>
	<br/>

	<%
		if (request.getSession().getAttribute(SessionAttributeNames.USUARIO) != null) { /* Cargar opciones de usuario */
			%>
			<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">Biblioteca</button></a>
			<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">Subidos</button></a>
			<a href="/SeeDsWebTraining<%=ViewPath.HOME%>"><button class="navButton">Seguidos</button></a>
			<%
		}
	%>	
</div>