<%@ page import="com.isp.seeds.service.*, com.seeds.web.utils.*, com.seeds.web.model.*, com.isp.seeds.model.*, com.seeds.web.controller.*" %>
<div id="nav-menu">

	<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.GENERAL%>">
		<button class="navButton">
			<fmt:message key="nav.general" bundle="${messages}"/>
		</button>
	</a>	
	<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.MUSICA%>">
		<button class="navButton">
			<fmt:message key="nav.musica" bundle="${messages}"/>
		</button>
	</a>
	<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.SERIES%>">
		<button class="navButton">
			<fmt:message key="nav.series" bundle="${messages}"/>
		</button>
	</a>
	<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.CORTOS%>">
		<button class="navButton">
			<fmt:message key="nav.cortos" bundle="${messages}"/>
		</button>
	</a>
	<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.DOCUMENTAL%>">
		<button class="navButton">
			<fmt:message key="nav.documental" bundle="${messages}"/>
		</button>
	</a>
	<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.GUIAS%>">
		<button class="navButton">
			<fmt:message key="nav.guias" bundle="${messages}"/>
		</button>
	</a>
	<br/>
	<br/>

	<%
		if (request.getSession().getAttribute(SessionAttributeNames.USUARIO) != null) { /* Cargar opciones de usuario */
			%>
			<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.GUARDADOS%>&<%=ParameterNames.TIPO%>=2">
				<button class="navButton">
					<fmt:message key="nav.guardados" bundle="${messages}"/>
				</button>
			</a>
			<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.SEGUIDOS%>&<%=ParameterNames.TIPO%>=1">
				<button class="navButton">
					<fmt:message key="nav.seguidos" bundle="${messages}"/>
				</button>
			</a>
			<a href="/SeeDsWeb/<%=ControllerPath.CONTENIDO%>?<%=ParameterNames.ACTION%>=<%=Actions.SUBIDOS%>&<%=ParameterNames.TIPO%>=2">
				<button class="navButton">
					<fmt:message key="nav.subidos" bundle="${messages}"/>
				</button>
			</a>

			<%
		}
	%>	
</div>