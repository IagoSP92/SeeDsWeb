<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
<%@include file="/html/common/action-errors.jsp"%>

	<h1 class="titulo"><%
		if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("BUSCAR") ){
			%> <fmt:message key="busqueda.titulo1" bundle="${messages}"></fmt:message>  <%   
		}
		if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("GENERAL") ){
			%> <fmt:message key="nav.general" bundle="${messages}"></fmt:message>  <%   
		}
		if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("MUSICA") ){
			%> <fmt:message key="nav.musica" bundle="${messages}"></fmt:message> <%   
		}
		if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("SERIES") ){
			%> <fmt:message key="nav.series" bundle="${messages}"></fmt:message> <%   
		}
		if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("CORTOS") ){
			%> <fmt:message key="nav.cortos" bundle="${messages}"></fmt:message>  <%   
		}
		if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("DOCUMENTAL") ){
			%> <fmt:message key="nav.documental" bundle="${messages}"></fmt:message> <%   
		}
		if(ParameterUtils.getParameter(request, ParameterNames.ACTION).equalsIgnoreCase("GUIAS") ){
			%> <fmt:message key="nav.guias" bundle="${messages}"></fmt:message>  <%   
		}
	%></h1>
	
	<%@include file="/html/common/buscador.jsp"%>

</div>
<%@include file="/html/common/footer.jsp"%>
