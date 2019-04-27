package com.seeds.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Categoria;
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
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;


@WebServlet("/lista")
public class ListaServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(ListaServlet.class);
	
	private List<Long> idsCategoria;
	public ListaServlet() {
		super();			
		try {
			idsCategoria = WebUtils.categoriaSvc.findAll("ES").stream().map(Categoria::getIdCategoria).collect(Collectors.toList());
		} catch (DataException e) {
			logger.warn(e.getMessage(), e);
		}
	}

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
				int startIndex= (page-1)*WebUtils.pageSize+1;
				int count= WebUtils.pageSize;
				Results<Video> videosLista= null;
				try { 
					videosLista = WebUtils.listaSvc.verVideosLista(lista.getId(), startIndex, count);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
				
				// Datos para paginacion
				int totalPages = (int) Math.ceil((double)videosLista.getTotal()/(double)WebUtils.pageSize);
				int firstPagedPage = Math.max(1, page-WebUtils.pagingPageCount);
				int lastPagedPage = Math.min(totalPages, page+WebUtils.pagingPageCount);
				request.setAttribute(ParameterNames.PAGE, page);
				request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
				request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
				request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
				request.setAttribute(AttributeNames.RESULTADOS, videosLista.getPage());
				request.setAttribute(AttributeNames.TOTAL, videosLista.getTotal());
				
			} catch (NumberFormatException e) {
				logger.warn(e.getMessage(), e);
			}					
			target = ViewPath.DETALLE_LISTA;

		} else if (Actions.PRE_CREAR_LISTA.equalsIgnoreCase(action)) {
			
			List<Categoria> categorias = null;			
			try {
				categorias =WebUtils.categoriaSvc.findAll(WebUtils.getIdioma(request)).stream().map(Categoria::getCategoria).collect(Collectors.toList());
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				errors.add(AttributeNames.CATEGORIAS, ErrorCodes.PRE_SUBIR_VIDEO);
			}
			request.setAttribute(AttributeNames.CATEGORIAS, categorias);
			target = ViewPath.CREAR_LISTA;
			
		} else if (Actions.CREAR_LISTA.equalsIgnoreCase(action)) {

			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String descripcion = request.getParameter(ParameterNames.DESCRIPCION);
			String categoria = request.getParameter(ParameterNames.ID_CATEGORIA);
			Long idCategoria = ValidationUtils.validCategoria(errors, categoria, ParameterNames.CATEGORIA, true, idsCategoria);
			if (logger.isDebugEnabled()) {
				logger.info("CREAR LISTA -> Nombre:{} descripcion:{} categoria:{}"
												+ nombre, descripcion, idCategoria);
			}
			Lista lista= new Lista();
			if (!errors.hasErrors()) {				
				lista.setTipo(3); // LISTA
				// FECHAS DE ALTA Y MODIFICACION SIEMPRE SERAN LA ACTUAL
				lista.setFechaAlta(new Date());
				lista.setFechaMod(new Date());
				nombre = ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, true);
				descripcion = ValidationUtils.validString(errors, descripcion, ParameterNames.DESCRIPCION, true);
				// Recuperamos el autor de sesión
				lista.setAutor(((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId());
				lista.setNombre(nombre);
				lista.setDescripcion(descripcion);
				lista.setPublica(true);
				try {
					lista.setCategoria(WebUtils.categoriaSvc.findById(idCategoria, "ES"));
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(Actions.CREAR_LISTA, ErrorCodes.RECOVERY_ERROR);
				}			
			}
			if (!errors.hasErrors()) {
				try {
					lista = WebUtils.listaSvc.crearLista(lista);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(Actions.CREAR_LISTA, ErrorCodes.UNABLE_CREATE);
				}
			}		
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("La creación no ha podido realizarse: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.HOME;	
			}
			target = ViewPath.HOME;

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
