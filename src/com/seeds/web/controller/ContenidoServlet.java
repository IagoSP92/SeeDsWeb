package com.seeds.web.controller;

import java.io.IOException;

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
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.config.ConfigurationParameterNames;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;


@WebServlet("/contenido")
public class ContenidoServlet extends HttpServlet {	

	private static Logger logger = LogManager.getLogger(ContenidoServlet.class);

	private DateUtils dateUtils = null;
	private ContenidoService contenidoSvc = null;

	public ContenidoServlet() {
		super();
		contenidoSvc = new ContenidoServiceImpl();
		dateUtils = new DateUtils();
	}
	
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
		
		Object locale =  SessionManager.get(request, ConstantValues.USER_LOCALE);
		String idioma=null;		
		if(locale!=null) {
			String rawIdioma = locale.toString();
			idioma=rawIdioma.substring(0, 2);
		}
		
		int page = WebUtils.
				getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
		int startIndex= (page-1)*pageSize+1;
		int count= pageSize;
		
		Results<Contenido> listado = null;
		ContenidoCriteria criteria = new ContenidoCriteria();

		if (Actions.BUSCAR.equalsIgnoreCase(action)) {
			
			String checkTodos = request.getParameter(ParameterNames.CHECK_TODOS);
			String checkVideos = request.getParameter(ParameterNames.CHECK_VIDEO);
			String checkListas = request.getParameter(ParameterNames.CHECK_LISTA);
			String checkUsuarios = request.getParameter(ParameterNames.CHECK_USUARIO);
			String nombre = request.getParameter(ParameterNames.NOMBRE);			
			String valoracionMin = request.getParameter(ParameterNames.VALORACION_MIN);
			String valoracionMax = request.getParameter(ParameterNames.VALORACION_MAX);
			String reproduccionesMin = request.getParameter(ParameterNames.REPRODUCCIONES_MIN);
			String reproduccionesMax = request.getParameter(ParameterNames.REPRODUCCIONES_MAX);
			String fechaMin = request.getParameter(ParameterNames.FECHA_MIN);
			String fechaMax = request.getParameter(ParameterNames.FECHA_MAX);
			
			Boolean aceptarTodo= (ValidationUtils.validCheck(errors, checkTodos, ParameterNames.CHECK_TODOS, false));
			Boolean aceptarVideo= (ValidationUtils.validCheck(errors, checkVideos, ParameterNames.CHECK_VIDEO, false));
			Boolean aceptarLista= (ValidationUtils.validCheck(errors, checkListas, ParameterNames.CHECK_LISTA, false));
			Boolean aceptarUsuario= (ValidationUtils.validCheck(errors, checkUsuarios, ParameterNames.CHECK_USUARIO, false));			
			
			if(aceptarTodo && !(aceptarVideo&&aceptarLista&&aceptarUsuario)) {
				errors.add(ParameterNames.CHECK_TODOS, ErrorCodes.INVALID_CHECK);
			} else {
				
				criteria.setAceptarVideo(aceptarVideo);
				criteria.setAceptarLista(aceptarLista);
				criteria.setAceptarUsuario(aceptarUsuario);				
				criteria.setNombre(ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, false));
				criteria.setValoracionMin(ValidationUtils.validDouble(errors, valoracionMin, ParameterNames.VALORACION_MIN, false));
				criteria.setValoracionMax(ValidationUtils.validDouble(errors, valoracionMax, ParameterNames.VALORACION_MAX, false));
				criteria.setReproduccionesMin(ValidationUtils.validInt(errors, reproduccionesMin, ParameterNames.REPRODUCCIONES_MIN, false));
				criteria.setReproduccionesMax(ValidationUtils.validInt(errors, reproduccionesMax, ParameterNames.REPRODUCCIONES_MAX, false));			
				criteria.setFechaAlta(ValidationUtils.validDate(errors, fechaMin, ParameterNames.FECHA_MIN, false, dateUtils));
				criteria.setFechaAltaHasta(ValidationUtils.validDate(errors, fechaMax, ParameterNames.FECHA_MAX, false, dateUtils));			

				try { 
					listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
				}
				
				request.setAttribute(AttributeNames.RESULTADOS, listado.getPage());
				request.setAttribute(AttributeNames.TOTAL, listado.getTotal());
				
				// Datos para paginacion (Calculos aqui, datos comodos para renderizar)
				int totalPages = (int) Math.ceil((double)listado.getTotal()/(double)pageSize);
				int firstPagedPage = Math.max(1, page-pagingPageCount);
				int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
				request.setAttribute(ParameterNames.PAGE, page);
				request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
				request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
				request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
							
				// PARAMETROS DE BUSQUEDA
				request.setAttribute(ParameterNames.CHECK_TODOS, checkTodos);
				request.setAttribute(ParameterNames.CHECK_VIDEO, checkVideos);
				request.setAttribute(ParameterNames.CHECK_LISTA, checkListas);
				request.setAttribute(ParameterNames.CHECK_USUARIO, checkUsuarios);				
				request.setAttribute(ParameterNames.NOMBRE, nombre);				
				request.setAttribute(ParameterNames.VALORACION_MIN, valoracionMin);
				request.setAttribute(ParameterNames.VALORACION_MAX, valoracionMax);
				request.setAttribute(ParameterNames.REPRODUCCIONES_MIN, reproduccionesMin);
				request.setAttribute(ParameterNames.REPRODUCCIONES_MAX, reproduccionesMax);				
				request.setAttribute(ParameterNames.FECHA_MIN, fechaMin);
				request.setAttribute(ParameterNames.FECHA_MAX, fechaMax);			
			}
			
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("Búsqueda 	Fallida: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);			
			}
			target = ViewPath.BUSCADOR;

		} else if (Actions.GENERAL.equalsIgnoreCase(action)) {
			criteria.setAceptarVideo(true);
			criteria.setAceptarLista(false);
			criteria.setAceptarUsuario(false);
			
			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			}
			
			request.setAttribute(AttributeNames.RESULTADOS, listado.getPage());
			request.setAttribute(AttributeNames.TOTAL, listado.getTotal());
			
			int totalPages = (int) Math.ceil((double)listado.getTotal()/(double)pageSize);
			int firstPagedPage = Math.max(1, page-pagingPageCount);
			int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
			request.setAttribute(ParameterNames.PAGE, page);
			request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
			request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
			request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
			
			target = ViewPath.HOME;
			
		}  else if (Actions.MUSICA.equalsIgnoreCase(action)){
	
			criteria.setAceptarVideo(true);
			criteria.setAceptarLista(false);
			criteria.setAceptarUsuario(false);
			criteria.setCategoria(2l);
			//criteria.setCategoria(1l);
			
			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			}
			
			request.setAttribute(AttributeNames.RESULTADOS, listado.getPage());
			request.setAttribute(AttributeNames.TOTAL, listado.getTotal());
			
			int totalPages = (int) Math.ceil((double)listado.getTotal()/(double)pageSize);
			int firstPagedPage = Math.max(1, page-pagingPageCount);
			int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
			request.setAttribute(ParameterNames.PAGE, page);
			request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
			request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
			request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
			
			target = ViewPath.HOME;
			
		}  else if (Actions.SERIES.equalsIgnoreCase(action)){	


		}  else if (Actions.CORTOS.equalsIgnoreCase(action)){


		}  else if (Actions.DOCUMENTAL.equalsIgnoreCase(action)){


		}  else if (Actions.GUIAS.equalsIgnoreCase(action)){	


			
		}  else {// LA ACTION RECIBIDA NO ESTA DEFINIDA			
			// Mmm...
			logger.error("Action desconocida");
			// target ?
		} 
		
		
		logger.info("Forwarding from ContenidoServlet to "+target);
		request.getRequestDispatcher(target).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
