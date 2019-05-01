<%@ page import="java.util.List" %>

<%@include file="/html/common/header.jsp"%>

<div class="mainWindow" data-idContenido="${lista.id}" data-tipo="${lista.tipo}" >

	<div id="recuadro_lista" class="w3-border2">
		<div class="cabeceraListaDiv">
			<span class="detalle-titulo"> ${lista.nombre} </span>
			<span class="detalle-autor">  ${nombre_autor} </span>	
		</div>		

		<div class="datosListaDiv">
			<span><b><fmt:message key="detalle.valoracion" bundle="${messages}"/></b></span> ${lista.valoracion}
			<span><b><fmt:message key="detalle.reproducciones" bundle="${messages}"/></b></span> ${lista.reproducciones}
			<span><b><fmt:message key="detalle.fecha" bundle="${messages}"/></b></span> ${lista.fechaAlta}		
			<span><b><fmt:message key="detalle.visitas" bundle="${messages}"/></b></span> ${lista.reproducciones}	
			
			<div class="descripcionVideoDiv">
			<br/>	
				<span class="inline-block"><b><fmt:message key="detalle.descripcion" bundle="${messages}"/></b></span>
				<div class="inline-block">${lista.descripcion}</div>
			</div>		
		</div>
	</div>	

	<div id="perfilAutenticadosDiv">
		<c:if test = "${autenticado==true}">
			<c:choose>
				<c:when test="${guardado == true}">
					<button class="w3-btn w3-border2 w3-text-blue2" id="guardarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-guardado="true">
						<fmt:message key="detalle.guardado" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="w3-btn w3-border2 w3-text-blue2" id="guardarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-guardado="false">
						<fmt:message key="detalle.noguardado" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>	
			<c:choose>
				<c:when test="${denunciado == true}">
					<button class="w3-btn w3-border2 w3-text-blue2" id="denunciarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-denunciado="true">
						<fmt:message key="detalle.nodenunciar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise> 
					<button class="w3-btn w3-border2 w3-text-blue2" id="denunciarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-denunciado="false">
						<fmt:message key="detalle.denunciar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>
			<c:choose>
				<c:when test="${siguiendo == true}">
					<button class="w3-btn w3-border2 w3-text-blue2" id="seguirButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-siguiendo="true">
						<fmt:message key="detalle.noseguir" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="w3-btn w3-border2 w3-text-blue2" id="seguirButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-siguiendo="false">
						<fmt:message key="detalle.seguir" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>			
			<c:choose>
				<c:when test="${comentado != 'Null'}">
					<button class="w3-btn w3-border2 w3-text-blue2" id="noComentarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-comentado="Null">
						<fmt:message key="detalle.noComentar" bundle="${messages}"/>
					</button>
				</c:when>
				<c:otherwise>
					<button class="w3-btn w3-border2 w3-text-blue2" id="comentarButton" data-tipo="${lista.tipo}" data-idContenido="${lista.id}" data-comentado="comentario">
						<fmt:message key="detalle.comentar" bundle="${messages}"/>
					</button>
	  			</c:otherwise>		
			</c:choose>

			<div class="textAreaDiv" hidden=true id="commentarioTextDiv">
				<textarea id="comentarioText" rows="4" cols="80"></textarea>
				<button class="w3-btn w3-border2 w3-text-blue2"
					id="comentarButtonEnviar" data-tipo="${lista.tipo}"
					data-idContenido="${lista.id}" data-comentado="comentario">
					<fmt:message key="detalle.comentarEnviar" bundle="${messages}" />
				</button>
			</div>


			<c:if test="${lista.autor==id_sesion}">
				<div class="menuAutor">
					<h4><b><fmt:message key="menuAutor.menuAutor" bundle="${messages}"/></b></h4>
					
					<button class=" autorButton w3-btn w3-border" id="editarContenidoButton">
						<fmt:message key="menuAutor.editar" bundle="${messages}"/>
					</button>
					
					<button hidden=true class="autorButton w3-btn w3-border"  id="botonEditarListaCancelar" onclick=window.location.reload()>
						<fmt:message key="editar.editarPerfilCancelar" bundle="${messages}"/>
					</button>
					
					<button class=" autorButton w3-btn w3-border" id="editarVideosListaButton">
						<fmt:message key="menuAutor.videosLista" bundle="${messages}"/>
					</button>				
				</div><!-- MENU AUTOR -->
				<!-- Funciones para mover los videos de la lista -->
				<script src="/SeeDsWeb/javascript/list-functions.js"></script>
			</c:if><!-- IF AUTOR -->
			
			<div id="edicionLista" hidden=true>
			<br/>		
				<form action="/SeeDsWeb/<%=ControllerPath.LISTA%>" method="post">	
					<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.EDITAR_LISTA%>"/>
					<input type="hidden" name="<%=ParameterNames.ID_CONTENIDO%>" value="${lista.id}"/>
	
					<div id="campoNombre">
					<span class="w3-text-blue"><b><fmt:message key="form.titulo" bundle="${messages}"/></b></span>
					<input type="text" class="w3-input w3-border" 
							name="<%=ParameterNames.NOMBRE%>" 
							value="${lista.nombre}"/>
					</div>
					
					<div id="campoDescripcionLista">
					<span class="w3-text-blue"><b><fmt:message key="form.descripcion" bundle="${messages}"/></b></span>
					<input type="text" class="w3-input w3-border" 
							name="<%=ParameterNames.DESCRIPCION%>" 
							value="${lista.descripcion}"/>
					</div>
					<br/>
	
					<input id="botonEditarListaSalvar" class=" autorButton w3-btn w3-border2" type="submit"
						 name="lista" value="<fmt:message key="editar.guardarEdicion" bundle="${messages}"/>"/>
				</form>
								
				<div id="eliminarDiv"><!-- BOTONES PARA ELIMINACION -->
				<br/>
					<button class=" autorButton w3-btn w3-border" id="eliminarButton">
						<fmt:message key="menuAutor.eliminar" bundle="${messages}"/>
					</button>
					<button class=" autorButton w3-btn w3-border" id="cancelarEliminarButton" hidden=true>
						<fmt:message key="menuAutor.cancelar" bundle="${messages}"/>
					</button>
					
					<h5 id="textConfirm" hidden=true><fmt:message key="menuAutor.confirmEliminar" bundle="${messages}"/></h5>
					<a href="<%=ControllerPath.LISTA%>?<%=ParameterNames.ACTION%>=<%=Actions.ELIMINAR%>
							&<%=ParameterNames.ID_CONTENIDO%>=${lista.id}" hidden=true  id="confirmButtonA">
						<button class=" autorButton w3-btn w3-border"  id="confirmButton">
							<fmt:message key="menuAutor.eliminar" bundle="${messages}"/>
						</button>
					</a>
				</div><!-- FIN BOTONES ELIMINACION -->		
			</div><!-- EDICION DETALLES/ELIMINAR -->
		
			<div id="operacionesLista" hidden=true> <!-- MANIPULAR VIDEOS CONTENIDOS -->		
				
				<div id="moverVideosDiv">
				<br/>
					<form action="/SeeDsWeb<%=ControllerPath.LISTA%>" method="post">
						<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.INCLUIR%>"/>
						<input type="hidden" name="<%=ParameterNames.ID_CONTENIDO%>" value="${lista.id}"/>
						<input type="hidden" name="<%=ParameterNames.LISTA_LISTA%>" value="${lista_lista}"/>
						
					<div id="recuadrosMagicos" class="inline-block">
						<select name="possible" class="possible" multiple>
							<c:forEach items="${lista_usuario}" var="contenido">
								<option value="${contenido.id}">${contenido.nombre}</option>
							</c:forEach>
					    </select>
						<script type="text/javascript"> var i = 0;</script>
					    <select name="wishlist" class="wishlist" multiple>
					    	<c:forEach items="${lista_lista}" var="contenido">
								<option class="actuallyIn" data-item="${contenido.id.toString()}"
								  value="${contenido.id}" onload="MyMoveItem2()">${contenido.nombre}</option>
							</c:forEach>
					    </select>
				    </div>
				    <div id="botonesMagicos" class="inline-block">
				    
				    
				    	<input hidden type="hidden"  onload="MyMoveItem();">	
				    	    
					    <input type="button" value="<fmt:message key="editar.incluirLista" bundle="${messages}"/>" onclick="MyMoveItem();">					    
					    <br>
					    <input type="button" value="<fmt:message key="editar.excluirLista" bundle="${messages}"/>" onclick="RemoveItem();">
				    </div>

				    <input id="incluirButton" class=" autorButton w3-btn w3-border2" type="submit"
						 name="incluir" value="<fmt:message key="menuAutor.incluir" bundle="${messages}"/>"/>
					</form>
			    </div><!-- moverVideosDiv -->
						
			</div> <!-- MANIPULAR VIDEOS CONTENIDOS -->	
		</c:if><!-- IF USUARIO AUTENTICADO -->	
	</div>
	
	
	<div id="videosEnListaDiv"><!-- SECCION PUBLICA -->	
		<h3><fmt:message key="detalle.videos-lista" bundle="${messages}"></fmt:message></h3>
		<c:if test="${not empty resultados}">		
			<c:forEach items="${resultados}" var="video">
				<div class="thumbDiv">
					<c:url var="urlDetalle" scope="page" value="<%=ControllerPath.CONTENIDO%>">
						<c:param name="action" value="<%=Actions.DETALLE%>"/>
						<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${video.getId()}"/>
						<c:param name="<%=ParameterNames.TIPO%>" value="${video.getTipo()}"/>
					</c:url>			
					<li><a class="a_sinsub" href="${urlDetalle}">
						<b>${video.nombre}</b><br>
						<fmt:message key="detalle.fecha" bundle="${messages}"/>
						${video.fechaAlta}
					</a></li>
				</div>		
			</c:forEach>		
			<!-- Paginacion  -->
			<p><center>			
				<c:url var="urlBase" value="lista" scope="page">
					<c:param name="action" value="<%=Actions.DETALLE%>"/>
					<c:param name="<%=ParameterNames.ID_CONTENIDO%>" value="${id}"/>
					<c:param name="<%=ParameterNames.TIPO%>" value="${tipo}"/>		
				</c:url>		
				<%@include file="/html/common/paginacion.jsp"%>	
		
			</center></p>
		</c:if>
	</div><!-- SECCION PUBLICA -->	
	
	<div class="comentariosDv">
		<h3><fmt:message key="detalle.comentarios" bundle="${messages}"/></h3>
		<c:forEach items="${comentarios}" var="comentario">
			<div class="comentario">
				<b>${comentario.autor}</b>
				<br>
				${comentario.texto}
			</div>
		</c:forEach>
	</div>

</div>
<script src="/SeeDsWeb/javascript/countVisits.js"></script>
<%@include file="/html/common/footer.jsp"%>.jsp"%>