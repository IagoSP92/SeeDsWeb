<%@ page import="com.isp.seeds.service.*, com.seeds.web.utils.*, com.seeds.web.model.*, com.isp.seeds.model.*, com.seeds.web.controller.*" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="user-menu">

	<%
		Usuario u = (Usuario) request.getSession().getAttribute(SessionAttributeNames.USUARIO);
		if (u == null) {	
	%>
	
	<a href="/SeeDsWeb<%=ViewPath.ENTRAR%>">
		<button class="userButton">
			<fmt:message key="usermenu.entrar" bundle="${messages}"/>
		</button>
	</a>
			
	<a href="/SeeDsWeb/<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.PRERREGISTRO%>">
		<button class="userButton">
			<fmt:message key="usermenu.registro" bundle="${messages}"/>
		</button>
	</a>
			
	<%		
		} else {
		%>	<!-- usuario autenticado -->			
			<div id="usuario">
				<span><%=u.getNombre()%></span>
				
				<a href="/SeeDsWeb/<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.MI_PERFIL%>">
					<button class="userButton">
						<fmt:message key="usermenu.perfil" bundle="${messages}"/>
					</button>
				</a>
				<a href="/SeeDsWeb/<%=ControllerPath.VIDEO%>?<%=ParameterNames.ACTION%>=<%=Actions.PRE_SUBIR_VIDEO%>">
					<button class="userButton">
						<fmt:message key="usermenu.subir" bundle="${messages}"/>
					</button>
				</a>
				<a href="/SeeDsWeb/<%=ControllerPath.USUARIO%>?<%=ParameterNames.ACTION%>=<%=Actions.SALIR%>">
					<button class="userButton">
						<fmt:message key="usermenu.salir" bundle="${messages}"/>
					</button>
				</a>
			</div>		
		<%
		}
	%>	
</div>