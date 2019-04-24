	
	<c:if test="${page > 1}"><!-- Primera Página -->
		<a href="${urlBase}&page=1">
			<fmt:message key="pag.primera" bundle="${messages}"/>
		</a>
		&nbsp;&nbsp;
	</c:if>
	
	<c:if test="${page > 1}"><!-- Página Anterior -->
		<a href="${urlBase}&page=${page - 1}">
			<fmt:message key="pag.anterior" bundle="${messages}"/>
		</a>
		&nbsp;&nbsp;
	</c:if>

	<c:if test="${totalPages > 1}">		
		<c:forEach begin="${firstPagedPage}" end="${lastPagedPage}" var="i">
			<c:choose>
			  <c:when test="${page != i}">
					&nbsp;<a href="${urlBase}&page=${i}"><b>${i}</b></a>&nbsp;
			  </c:when>
			  <c:otherwise>
					&nbsp;<b>${i}</b>&nbsp;
			  </c:otherwise>
			</c:choose>
		</c:forEach>
	</c:if>
	
	<c:if test="${page < totalPages}"><!-- Siguiente Página -->	
		&nbsp;&nbsp;		
		<a href="${urlBase}&page=${page + 1}">
			<fmt:message key="pag.siguiente" bundle="${messages}"/>
		</a>			
	</c:if>	
	<c:if test="${page != totalPages}"><!-- Ultima Página -->
		&nbsp;&nbsp;
		<a href="${urlBase}&page=${totalPages}">
			<fmt:message key="pag.ultima" bundle="${messages}"/>
		</a>
	</c:if>		
