package com.seeds.web.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.isp.seeds.service.CategoriaServiceImpl;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.ListaServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.CategoriaService;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.ListaService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.ConstantsInterface;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;


@WebServlet("/lista")
public class ListaServlet extends HttpServlet  implements  ConstantsInterface {

	private static Logger logger = LogManager.getLogger(ListaServlet.class);
	
	private List<Long> idsCategoria;
	private static ListaService listaSvc = null;
	private static ContenidoService contenidoSvc = null;
	private static CategoriaService categoriaSvc = null;
	
	public ListaServlet() {
		super();
		contenidoSvc = new ContenidoServiceImpl();
		listaSvc = new ListaServiceImpl();
		categoriaSvc = new CategoriaServiceImpl();
		try {
			idsCategoria = categoriaSvc.findAll("ES").stream().map(Categoria::getIdCategoria).collect(Collectors.toList());
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
		String idioma=SessionManager.get(request, ConstantValues.USER_LOCALE).toString().substring(0, 2).toUpperCase();	

		if (Actions.DETALLE.equalsIgnoreCase(action)) {
			
			Lista lista=null;
			Long idContenido = Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO));	
			request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);
			if(SessionManager.get(request, SessionAttributeNames.USUARIO)!=null) {
				
				Long idSesion = ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
				request.setAttribute(ParameterNames.ID_SESION, idSesion);
				try {
					lista = listaSvc.buscarId(idSesion, idContenido );
					request.setAttribute(ParameterNames.COMENTARIOS, lista.getComentarios());
					request.setAttribute(ParameterNames.DENUNCIADO, lista.getDenunciado());
					request.setAttribute(ParameterNames.SIGUIENDO, lista.getSiguiendo());
					if(lista.getComentado()==null) {
						request.setAttribute(ParameterNames.COMENTADO, "Null");
					}else {
						request.setAttribute(ParameterNames.COMENTADO, lista.getComentado());
					}
					request.setAttribute(ParameterNames.VALORACION, lista.getValorado());
					request.setAttribute(ParameterNames.GUARDADO, lista.getGuardado());					
					request.setAttribute(ParameterNames.AUTENTICADO, true);
					request.setAttribute(ParameterNames.ID_SESION, idSesion);	
					
					// Parametros para manipulacion de listas
					if(idSesion==lista.getAutor()) {
						
						List<Contenido> listaTodos = new ArrayList<Contenido>();
						List<Contenido> listaLista = new ArrayList<Contenido>();
						List <Contenido> listaUsuario = new ArrayList<Contenido>();
						ContenidoCriteria criteria = new ContenidoCriteria();
						criteria.setAceptarVideo(true);
						criteria.setAceptarLista(false);
						criteria.setAceptarUsuario(false);
						criteria.setAutor(idSesion);
						listaTodos= contenidoSvc.verVideosAutor(idSesion);
						listaLista = listaSvc.verContenidosLista(idContenido);
						//categorias =categoriaSvc.findAll(getIdioma(request)).stream().map(Categoria::getCategoria).collect(Collectors.toList());
						
						if(listaLista.size()>0) {
							for(Contenido c: listaTodos) {
								if(!listaLista.contains(c)) {
									listaUsuario.add(c);
								}
							}
						} else {
							listaUsuario = listaTodos;
						}						
						request.setAttribute(ParameterNames.LISTA_LISTA, listaLista);
						request.setAttribute(ParameterNames.LISTA_USUARIO, listaUsuario);						
					}					
				} catch (DataException | NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			} else {
				try {
					lista = listaSvc.buscarId(null, idContenido );
					request.setAttribute(ParameterNames.AUTENTICADO, false);
				} catch (DataException | NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			}
			try {
				request.setAttribute(AttributeNames.LISTA, lista);
				request.setAttribute(AttributeNames.NOMBRE_AUTOR, contenidoSvc.buscarId(lista.getAutor()).getNombre());	
				request.setAttribute(ParameterNames.ID_CONTENIDO, lista.getId());
				request.setAttribute(ParameterNames.TIPO, lista.getTipo());
				if(lista.getValoracion()==null) {
					lista.setValoracion(0d);
				}
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			} catch (NumberFormatException e) {
				logger.warn(e.getMessage(), e);
				errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
			}
			try {

				int page = 
						WebUtils.getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
				int startIndex= (page-1)*pageSize+1;
				int count= pageSize;
				Results<Video> videosLista= null;
				try { 
					videosLista = listaSvc.verVideosLista(lista.getId(), startIndex, count);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}

				
				// Datos para paginacion
				int totalPages = (int) Math.ceil((double)videosLista.getTotal()/(double)pageSize);
				int firstPagedPage = Math.max(1, page-pagingPageCount);
				int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
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
			
			cargarCategorias(request, errors);
			
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
					lista.setCategoria(categoriaSvc.findById(idCategoria, "ES"));
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(Actions.CREAR_LISTA, ErrorCodes.RECOVERY_ERROR);
				}
			}
			if (!errors.hasErrors()) {
				try {
					lista = listaSvc.crearLista(lista);
					if(lista.getId()==null) {
						logger.warn("Create List Error");
						errors.add(ParameterNames.ACTION,ErrorCodes.UNABLE_CREATE);
					}
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
				cargarCategorias(request, errors);
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.CREAR_LISTA;	
			}
			if(!errors.hasErrors()) {
				if (logger.isDebugEnabled()) {
					logger.debug("List Created: {}", errors);
				}
				target = ViewPath.HOME;
			}



		} else if (Actions.EDITAR_LISTA.equalsIgnoreCase(action)) {
			//cargarCategorias(request, errors);
			Long idContenido = Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO));
			String[] ids = request.getParameterValues("wishlist");
			
			request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);
			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String descripcion = request.getParameter(ParameterNames.DESCRIPCION);

			Lista lista= new Lista();
			if (!errors.hasErrors()) {
				try {
					lista= listaSvc.buscarId(null, idContenido);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(Actions.EDITAR_LISTA, ErrorCodes.RECOVERY_ERROR);
				}
				lista.setFechaMod(new Date());
				nombre = ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, true);
				descripcion = ValidationUtils.validString(errors, descripcion, ParameterNames.DESCRIPCION, true);
			
			}
			if (!errors.hasErrors()) {
				try {
					lista.setNombre(nombre);
					lista.setDescripcion(descripcion);					
					listaSvc.editarLista(lista);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(Actions.CREAR_LISTA, ErrorCodes.UNABLE_CREATE);
				}
			}		
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("La edicion no ha podido realizarse: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.HOME;	
			}
			
			target = ViewPath.SUBIDOS;

			
		} else if (Actions.INCLUIR.equalsIgnoreCase(action)) {

			Long idContenido = Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO));
			request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);
			String[] ids = request.getParameterValues("wishlist");
			if (logger.isDebugEnabled()) {
				logger.debug("Inluir: Lista={}", idContenido);
			}
			
			List<Long> nuevosVideos = new ArrayList<Long>();
			Long idVideo = null;
			
			if(ids!=null){
				for(int i=0; i<ids.length;i++) {
					idVideo= ValidationUtils.validLong(errors, ids[i], ParameterNames.WISHLIST, true);
					nuevosVideos.add(idVideo);				
				}
			}
			
			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String descripcion = request.getParameter(ParameterNames.DESCRIPCION);
			request.setAttribute(ParameterNames.NOMBRE, nombre);
			request.setAttribute(ParameterNames.DESCRIPCION, descripcion);

			if (!errors.hasErrors()) {
				try {
					listaSvc.redefinirIncluidos(idContenido, nuevosVideos);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(Actions.INCLUIR, ErrorCodes.RECOVERY_ERROR);
				}			
			}
			
			if (!errors.hasErrors()) {
				logger.warn("La lista ha sido redefinida");
			}
	
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("La edicion no ha podido redefinirse: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.HOME;
			}
			target = ViewPath.SUBIDOS;
			
		} else if (Actions.ELIMINAR.equalsIgnoreCase(action)) {

			Long idContenido = Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO));
			request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);
			if (logger.isDebugEnabled()) {
				logger.debug("Eliminar: Lista={}", idContenido);
			}
			

			if (!errors.hasErrors()) {
				try {
					Lista lista = listaSvc.buscarId(null, idContenido);
					listaSvc.eliminarLista(lista);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(Actions.INCLUIR, ErrorCodes.DELETE_ERROR);
				}			
			}
			
			if (!errors.hasErrors()) {
				logger.warn("La lista ha sido eliminada");
			}
	
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("La lista no ha podido eliminarse: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.HOME;
			}
			target = ViewPath.SUBIDOS;
			
		} else  {
			// LA ACTION RECIBIDA NO ESTA DEFINIDA
			errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_ACTION);
			logger.error("Action desconocida");
			target = ViewPath.HOME;
		}
		
		if (redirect) {
			logger.info("Redirecting to "+target);
			response.sendRedirect(target);
		} else {
			logger.info("Forwarding to "+target);
			request.getRequestDispatcher(target).forward(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static void cargarCategorias (HttpServletRequest request, ErrorManager errors){
		
		List<Categoria> categorias = null;			
		try {
			categorias =categoriaSvc.findAll(WebUtils.getIdioma(request)).stream().map(Categoria::getCategoria).collect(Collectors.toList());
		} catch (DataException e) {
			logger.warn(e.getMessage(), e);
			errors.add(AttributeNames.CATEGORIAS, ErrorCodes.PRE_SUBIR_VIDEO);
		}
		request.setAttribute(AttributeNames.CATEGORIAS, categorias);
	}

}
