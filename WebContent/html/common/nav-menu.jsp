<%@ page import="com.isp.seeds.service.*, com.seeds.web.utils.*, com.seeds.web.model.*, com.isp.seeds.model.*, com.seeds.web.controller.*" %>
<div id="nav-menu">

	<a href="<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.GENERAL%>">
		<button class="navButton">
			<fmt:message key="nav.general" bundle="${messages}"/>
		</button>
	</a>	
	<a href="<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.MUSICA%>">
		<button class="navButton">
			<fmt:message key="nav.musica" bundle="${messages}"/>
		</button>
	</a>
	<a href="<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.SERIES%>">
		<button class="navButton">
			<fmt:message key="nav.series" bundle="${messages}"/>
		</button>
	</a>
	<a href="<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.CORTOS%>">
		<button class="navButton">
			<fmt:message key="nav.cortos" bundle="${messages}"/>
		</button>
	</a>
	<a href="<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.DOCUMENTAL%>">
		<button class="navButton">
			<fmt:message key="nav.documental" bundle="${messages}"/>
		</button>
	</a>
	<a href="<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.GUIAS%>">
		<button class="navButton">
			<fmt:message key="nav.guias" bundle="${messages}"/>
		</button>
	</a>
	<br/>
	<br/>

	<%
		if (request.getSession().getAttribute(SessionAttributeNames.USUARIO) != null) { /* Cargar opciones de usuario */
			%>
			<a href="/SeeDsWeb<%=ViewPath.HOME%>"><button class="navButton">Biblioteca</button></a>
			<a href="/SeeDsWeb<%=ViewPath.HOME%>"><button class="navButton">Subidos</button></a>
			<a href="/SeeDsWeb<%=ViewPath.HOME%>"><button class="navButton">Seguidos</button></a>
			<%
		}
	%>	
</div>