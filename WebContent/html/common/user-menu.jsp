<%@ page import="com.isp.seeds.service.*, com.seeds.web.utils.*, com.seeds.web.model.*, com.isp.seeds.model.*, com.seeds.web.controller.*" %>
<div id="user-menu">


	<%
		Usuario u = (Usuario) request.getSession().getAttribute(SessionAttributeNames.USUARIO);
		if (u == null) {
		
			%><a href="/SeeDsWeb<%=ViewPath.ENTRAR%>"><button class="userButton">Entrar</button></a><%	
			%><a href="/SeeDsWeb<%=ViewPath.REGISTRO%>"><button class="userButton">Registrarse</button></a><%	
		
		} else {
			%>	
			<!-- usuario autenticado -->
			<div id="usuario">
				<span><%=u.getNombre()%></span>
				<a href="/SeeDsWeb<%=ViewPath.SALIR%>"><button class="userButton">Mi Perfil</button></a>
				<a href="/SeeDsWeb<%=ViewPath.SALIR%>"><button class="userButton">Subir Video</button></a>
				<a href="/SeeDsWeb<%=ViewPath.SALIR%>"><button class="userButton">Opciones</button></a>
				<a href="<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.SALIR%>"><button class="userButton">Salir</button></a>
			</div>		
			<%
		}
	%>	
</div>