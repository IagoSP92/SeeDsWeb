package com.seeds.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Contenido;
import com.isp.seeds.model.Lista;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.model.Video;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.ListaServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.ListaService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.config.ConfigurationParameterNames;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.WebUtils;


@WebServlet("/lista")
public class ListaServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(ListaServlet.class);
	
	private static int pageSize = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGE_SIZE_DEFAULT)); 

	private static int pagingPageCount = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGING_PAGE_COUNT));


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter(ParameterNames.ACTION);
		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}
		ErrorManager errors = new ErrorManager(); 
		String target = null;
		boolean redirect = false;

		if (Actions.DETALLE.equalsIgnoreCase(action)) {
			
			Lista lista=null;
			Long idContenido = Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO));			
			if(SessionManager.get(request, SessionAttributeNames.USUARIO)!=null) {
				Long idSesion= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
				try {
					lista = WebUtils.listaSvc.buscarId(idSesion, idContenido );
					request.setAttribute(ParameterNames.COMENTARIOS, lista.getComentarios());
					request.setAttribute(ParameterNames.DENUNCIADO, lista.getDenunciado());
					request.setAttribute(ParameterNames.SIGUIENDO, lista.getSiguiendo());
					request.setAttribute(ParameterNames.COMENTADO, lista.getComentado());
					request.setAttribute(ParameterNames.VALORACION, lista.getValorado());
					request.setAttribute(ParameterNames.GUARDADO, lista.getGuardado());
					
					request.setAttribute(ParameterNames.AUTENTICADO, true);
					request.setAttribute(ParameterNames.ID_SESION, idSesion);	
				} catch (DataException | NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			} else {
				try {
					lista = WebUtils.listaSvc.buscarId(null, idContenido );
					request.setAttribute(ParameterNames.AUTENTICADO, false);
				} catch (DataException | NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			}
			try {
				request.setAttribute(AttributeNames.LISTA, lista);
				request.setAttribute(AttributeNames.NOMBRE_AUTOR, WebUtils.contenidoSvc.buscarId(lista.getAutor()).getNombre());	
				request.setAttribute(ParameterNames.ID_CONTENIDO, lista.getId());
				request.setAttribute(ParameterNames.TIPO, lista.getTipo());
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			} catch (NumberFormatException e) {
				logger.warn(e.getMessage(), e);
				errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
			}
			try {

				int page = WebUtils.
						getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
				int startIndex= (page-1)*pageSize+1;
				int count= pageSize;
				Results<Video> videosLista= null;
				try { 
					videosLista = WebUtils.listaSvc.verVideosLista(lista.getId(), startIndex, count);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
				
				request.setAttribute(AttributeNames.RESULTADOS, videosLista.getPage());
				request.setAttribute(AttributeNames.TOTAL, videosLista.getTotal());
				
				// Datos para paginacion (Calculos aqui, datos comodos para renderizar)
				int totalPages = (int) Math.ceil((double)videosLista.getTotal()/(double)pageSize);
				int firstPagedPage = Math.max(1, page-pagingPageCount);
				int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
				request.setAttribute(ParameterNames.PAGE, page);
				request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
				request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
				request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
				
			} catch (NumberFormatException e) {
				logger.warn(e.getMessage(), e);
			}		
			
			target = ViewPath.DETALLE_LISTA;

		} else {// LA ACTION RECIBIDA NO ESTA DEFINIDA
			
			// Mmm...
			logger.error("Action desconocida");
			// target ?
		}
		
		logger.info("Forwarding to "+target);
		request.getRequestDispatcher(target).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
