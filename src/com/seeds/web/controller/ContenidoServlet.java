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
import com.isp.seeds.model.Usuario;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.ListaServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.ListaService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.config.ConfigurationParameterNames;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.ConstantsInterface;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;


@WebServlet("/contenido")
public class ContenidoServlet extends HttpServlet  implements ConstantsInterface  {	

	private static Logger logger = LogManager.getLogger(ContenidoServlet.class);
	private static ContenidoService contenidoSvc = null;
	private static VideoService videoSvc = null;
	private static ListaService listaSvc = null;
	private static UsuarioService usuarioSvc = null;
	private static DateUtils dateUtils = null;
	
	public  ContenidoServlet () {
		contenidoSvc = new ContenidoServiceImpl();
		videoSvc = new VideoServiceImpl();
		listaSvc = new ListaServiceImpl();
		usuarioSvc = new UsuarioServiceImpl();
		dateUtils = new DateUtils();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter(ParameterNames.ACTION);

		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}

		ErrorManager errors = new ErrorManager(); 
		String target = null;
		boolean redirect = false;
		
		String idioma=SessionManager.get(request, ConstantValues.USER_LOCALE).toString().substring(0, 2).toUpperCase();	
		
		int page = 
				WebUtils.getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
		int startIndex= (page-1)*pageSize+1;
		int count= pageSize;
		
		String idContenidoStr = request.getParameter(ParameterNames.ID_CONTENIDO);
		String tipoStr = request.getParameter(ParameterNames.TIPO);
		Long idContenido=null;
		Integer tipo=null;
		if(idContenidoStr!=null) {idContenido =ValidationUtils.validLong(errors, idContenidoStr, ParameterNames.ID_CONTENIDO, true);}
		if(tipoStr!=null) {tipo = ValidationUtils.validInt(errors, tipoStr , ParameterNames.TIPO, true);}
		
		Results<Contenido> listado = null;
		ContenidoCriteria criteria = new ContenidoCriteria();

		if (Actions.BUSCAR.equalsIgnoreCase(action)) {
			
			request.setAttribute(ParameterNames.ACTION, Actions.BUSCAR);			
			Boolean aceptarTodo= null;
			Boolean aceptarVideo= null;
			Boolean aceptarLista= null;
			Boolean aceptarUsuario= null;
			
			if( request.getParameter(ParameterNames.ACEPTAR_TODOS)==null ) {			
				String checkTodos = request.getParameter(ParameterNames.CHECK_TODOS);
				String checkVideos = request.getParameter(ParameterNames.CHECK_VIDEO);
				String checkListas = request.getParameter(ParameterNames.CHECK_LISTA);
				String checkUsuarios = request.getParameter(ParameterNames.CHECK_USUARIO);				
				
				aceptarTodo= (ValidationUtils.validCheck(errors, checkTodos, ParameterNames.CHECK_TODOS, false));
				aceptarVideo= (ValidationUtils.validCheck(errors, checkVideos, ParameterNames.CHECK_VIDEO, false));
				aceptarLista= (ValidationUtils.validCheck(errors, checkListas, ParameterNames.CHECK_LISTA, false));
				aceptarUsuario= (ValidationUtils.validCheck(errors, checkUsuarios, ParameterNames.CHECK_USUARIO, false));
			} else {				
				aceptarTodo= ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.ACEPTAR_TODOS), ParameterNames.ACEPTAR_TODOS, true);
				aceptarVideo= ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.ACEPTAR_VIDEO), ParameterNames.ACEPTAR_VIDEO, true);
				aceptarLista= ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.ACEPTAR_LISTA), ParameterNames.ACEPTAR_LISTA, true);
				aceptarUsuario= ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.ACEPTAR_USUARIO), ParameterNames.ACEPTAR_USUARIO, true);				
			}
			request.setAttribute(ParameterNames.ACEPTAR_TODOS, aceptarTodo);
			request.setAttribute(ParameterNames.ACEPTAR_VIDEO, aceptarVideo);
			request.setAttribute(ParameterNames.ACEPTAR_LISTA, aceptarLista);
			request.setAttribute(ParameterNames.ACEPTAR_USUARIO, aceptarUsuario);
			
			String nombre = request.getParameter(ParameterNames.NOMBRE);			
			String valoracionMin = request.getParameter(ParameterNames.VALORACION_MIN);
			String valoracionMax = request.getParameter(ParameterNames.VALORACION_MAX);
			String reproduccionesMin = request.getParameter(ParameterNames.REPRODUCCIONES_MIN);
			String reproduccionesMax = request.getParameter(ParameterNames.REPRODUCCIONES_MAX);
			String fechaMin = request.getParameter(ParameterNames.FECHA_MIN);
			String fechaMax = request.getParameter(ParameterNames.FECHA_MAX);				

			if(aceptarTodo && !(aceptarVideo&&aceptarLista&&aceptarUsuario)) {
				errors.add(ParameterNames.CHECK_TODOS, ErrorCodes.INVALID_CHECK);
			} else {
				if(aceptarTodo) {
					aceptarVideo= false;
					aceptarLista= false;
					aceptarUsuario= false;
				}				
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
				// PARAMETROS DE BUSQUEDA
				request.setAttribute(ParameterNames.NOMBRE, nombre);				
				request.setAttribute(ParameterNames.VALORACION_MIN, valoracionMin);
				request.setAttribute(ParameterNames.VALORACION_MAX, valoracionMax);
				request.setAttribute(ParameterNames.REPRODUCCIONES_MIN, reproduccionesMin);
				request.setAttribute(ParameterNames.REPRODUCCIONES_MAX, reproduccionesMax);				
				request.setAttribute(ParameterNames.FECHA_MIN, fechaMin);
				request.setAttribute(ParameterNames.FECHA_MAX, fechaMax);				
				// PARAMETROS PAGINACION
				WebUtils.pagParams ( request,  listado,  page);
						
			}			
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("Búsqueda 	Fallida: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);			
			}
			target = ViewPath.BUSCADOR;

		} else if (Actions.GENERAL.equalsIgnoreCase(action)) {
			
			request.setAttribute(ParameterNames.ACTION, Actions.GENERAL);
			criteria.setAceptarVideo(true);
			criteria.setAceptarLista(false);
			criteria.setAceptarUsuario(false);			
			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			}
			WebUtils.pagParams ( request,  listado,  page);			
			target = ViewPath.BUSCADOR;
			
		}  else if (Actions.MUSICA.equalsIgnoreCase(action)){
			
			request.setAttribute(ParameterNames.ACTION, Actions.MUSICA);	
			criteria.setAceptarVideo(true);
			criteria.setAceptarLista(false);
			criteria.setAceptarUsuario(false);
			criteria.setCategoria(1l);
			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			}			
			WebUtils.pagParams ( request,  listado,  page);
			target = ViewPath.BUSCADOR;
			
		}  else if (Actions.SERIES.equalsIgnoreCase(action)){
			
			request.setAttribute(ParameterNames.ACTION, Actions.SERIES);
			criteria.setAceptarVideo(true);
			criteria.setAceptarLista(false);
			criteria.setAceptarUsuario(false);
			criteria.setCategoria(2l);
			request.setAttribute(ParameterNames.CATEGORIA, criteria.getCategoria());			
			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			}			
			WebUtils.pagParams ( request,  listado,  page);
			target = ViewPath.BUSCADOR;

		}  else if (Actions.CORTOS.equalsIgnoreCase(action)){
			
			request.setAttribute(ParameterNames.ACTION, Actions.CORTOS);
			criteria.setAceptarVideo(true);
			criteria.setAceptarLista(false);
			criteria.setAceptarUsuario(false);
			criteria.setCategoria(3l);			
			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			}
			WebUtils.pagParams ( request,  listado,  page);
			target = ViewPath.BUSCADOR;

		}  else if (Actions.DOCUMENTAL.equalsIgnoreCase(action)){
			
			request.setAttribute(ParameterNames.ACTION, Actions.DOCUMENTAL);
			criteria.setAceptarVideo(true);
			criteria.setAceptarLista(false);
			criteria.setAceptarUsuario(false);
			criteria.setCategoria(4l);			
			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			}
			WebUtils.pagParams ( request,  listado,  page);			
			target = ViewPath.BUSCADOR;

		}  else if (Actions.GUIAS.equalsIgnoreCase(action)){
			
			request.setAttribute(ParameterNames.ACTION, Actions.GUIAS);
			criteria.setAceptarVideo(true);
			criteria.setAceptarLista(false);
			criteria.setAceptarUsuario(false);
			criteria.setCategoria(5l);			
			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			}			
			WebUtils.pagParams ( request,  listado,  page);			
			target = ViewPath.BUSCADOR;
			
		}  else if (Actions.GUARDADOS.equalsIgnoreCase(action)){
			
			request.setAttribute(ParameterNames.ACTION, Actions.GUARDADOS);				
			Long idSesion= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
			tipo = ValidationUtils.validInt(errors, request.getParameter(ParameterNames.TIPO), ParameterNames.TIPO, true);
			if(!errors.hasErrors()) {
			try {
				if(tipo==2) {
					listado = videoSvc.cargarGuardados(idSesion, startIndex, count);
				} else if(tipo==3) {
					listado= listaSvc.cargarGuardados(idSesion, startIndex, count);
				}
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
			}
			request.setAttribute(ParameterNames.TIPO, tipo);
			WebUtils.pagParams ( request,  listado,  page);
			
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Operacion fallida: {}", errors);
				}
				request.setAttribute(AttributeNames.ERRORS, errors);
			}			
			target = ViewPath.GUARDADOS;
			
		}  else if (Actions.SEGUIDOS.equalsIgnoreCase(action)){
			
			request.setAttribute(ParameterNames.ACTION, Actions.SEGUIDOS);
			Long idSesion= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
			 tipo = ValidationUtils.validInt(errors, request.getParameter(ParameterNames.TIPO), ParameterNames.TIPO, true);
			if(!errors.hasErrors()) {
			try {
				if(tipo==1) {
					listado = usuarioSvc.cargarSeguidos(idSesion, startIndex, count);
				} else if(tipo==3) {
					listado= listaSvc.cargarSeguidos(idSesion, startIndex, count);
				}
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
			}
			request.setAttribute(ParameterNames.TIPO, tipo);			
			WebUtils.pagParams ( request,  listado,  page);
			
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Operacion fallida: {}", errors);
				}
				request.setAttribute(AttributeNames.ERRORS, errors);
			}			
			target = ViewPath.SEGUIDOS;
			
		}  else if (Actions.SUBIDOS.equalsIgnoreCase(action)){
			
			request.setAttribute(ParameterNames.ACTION, Actions.SUBIDOS);
			Long idSesion= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
			 tipo = ValidationUtils.validInt(errors, request.getParameter(ParameterNames.TIPO), ParameterNames.TIPO, true);
			if(!errors.hasErrors()) {
				if(tipo==2) {
					criteria.setAceptarVideo(true);
					criteria.setAceptarLista(false);
					criteria.setAceptarUsuario(false);
				} else if(tipo==3) {
					criteria.setAceptarVideo(false);
					criteria.setAceptarLista(true);
					criteria.setAceptarUsuario(false);
				}
				try {
					criteria.setAutor(idSesion);
					listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			request.setAttribute(ParameterNames.TIPO, tipo);			
			WebUtils.pagParams ( request,  listado,  page);	
			
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Operacion fallida: {}", errors);
				}
				request.setAttribute(AttributeNames.ERRORS, errors);
			}			
			target = ViewPath.SUBIDOS;
			
		} else if (Actions.DETALLE.equalsIgnoreCase(action)) {	

			if(tipo!=null && tipo<=3 && tipo>0) { // Se comprueba la validez del TIPO
				request.setAttribute(ParameterNames.TIPO, tipo);
				if(idContenido!=null && idContenido>= 0) { // Se comprueba la validez del ID
					request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);
					
					if(tipo==1) {
						target = ControllerPath.USUARIO;
					}
					if(tipo==2) {					
						target = ControllerPath.VIDEO;
					}
					if(tipo==3) {
						target = ControllerPath.LISTA;
					}
					
				} else {//ID INVALIDO					
					target = ViewPath.ERROR500;
					request.getRequestDispatcher(target).forward(request, response);
				}
			} else {//TIPO INVALIDO
				target = ViewPath.ERROR500;
				logger.info("INVALID CONTENT TYPE -> Forwarding to "+target);
			}

		} else {
			// LA ACTION RECIBIDA NO ESTA DEFINIDA
			errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_ACTION);
			logger.error("Action desconocida");
			target = ViewPath.HOME;
		}
	
		logger.info("Forwarding from ContenidoServlet to "+target);
		request.getRequestDispatcher(target).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
	
}
