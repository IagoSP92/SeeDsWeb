package com.seeds.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.isp.seeds.model.Contenido;
import com.isp.seeds.model.Pais;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.ListaServiceImpl;
import com.isp.seeds.service.PaisServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.ListaService;
import com.isp.seeds.service.spi.PaisService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.config.ConfigurationParameterNames;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.CookieManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.LocaleManager;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;


@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(UsuarioServlet.class);
	
	private static int pageSize = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGE_SIZE_DEFAULT)); 

	private static int pagingPageCount = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGING_PAGE_COUNT));

	private DateUtils dateUtils = null;

	private UsuarioService usuarioSvc = null;
	private ContenidoService contenidoSvc = null;
	private PaisService paisSvc = null;
	private VideoService videoSvc = null;
	private ListaService listaSvc = null;

	private List<String> idsPais;
	public UsuarioServlet() {
		super();
		usuarioSvc = new UsuarioServiceImpl();
		contenidoSvc = new ContenidoServiceImpl();
		videoSvc = new VideoServiceImpl();
		listaSvc = new ListaServiceImpl();
		paisSvc= new PaisServiceImpl();
		dateUtils = new DateUtils();				
		try {
			idsPais = paisSvc.findAll("ES").stream().map(Pais::getIdPais).collect(Collectors.toList());			
		} catch (DataException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter(ParameterNames.ACTION);

		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}
		
		String idioma=SessionManager.get(request, ConstantValues.USER_LOCALE).toString().substring(0, 2).toUpperCase();		

		ErrorManager errors = new ErrorManager(); 
		String target = null;
		boolean redirect = false;

		if (Actions.ENTRAR.equalsIgnoreCase(action)) {

			String email = request.getParameter(ParameterNames.EMAIL);
			String password = request.getParameter(ParameterNames.PASSWORD);						
			email= ValidationUtils.validMail(errors, email, ParameterNames.EMAIL, true);
			password= ValidationUtils.validPass(errors, password, ParameterNames.PASSWORD, true);
			Usuario usuario = null;			
			if (!errors.hasErrors()) {
				try {
					usuario = usuarioSvc.logIn(email, password);
					SessionManager.set(request, SessionAttributeNames.USUARIO , usuario);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION,ErrorCodes.AUTHENTICATION_ERROR);
				}
			}			
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("Autenticacion fallida: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.ENTRAR;	
			} else {
				if (logger.isDebugEnabled()) {
					logger.info("Usuario "+email+" autenticado.");
				}				
				SessionManager.set(request, SessionAttributeNames.USUARIO, usuario);						
				target = request.getContextPath()+ViewPath.HOME;				
				redirect = true;
			}
			
		} else if (Actions.PRERREGISTRO.equalsIgnoreCase(action)) { //CHAPUZA
			
			List<Pais> paises = null;
			try {
				paises = paisSvc.findAll(idioma);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				errors.add(AttributeNames.PAISES, ErrorCodes.PRERREGISTRO_ERROR);
			}
			request.setAttribute(AttributeNames.PAISES, paises);
			target = ViewPath.REGISTRO;
			
		} else if (Actions.REGISTRO.equalsIgnoreCase(action)) {

			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String email = request.getParameter(ParameterNames.EMAIL);
			String password = request.getParameter(ParameterNames.PASSWORD);
			String fNac = request.getParameter(ParameterNames.FECHA_NAC);
			String nombreReal = request.getParameter(ParameterNames.NOMBRE_REAL);
			String apellidos = request.getParameter(ParameterNames.APELLIDOS);
			String pais = request.getParameter(ParameterNames.ID_PAIS);			
			if (logger.isDebugEnabled()) {
				logger.info("REGISTRO -> Nombre:{} email:{} pass:{} fnac:{} nombrereal:{} apeliidos:{} pais:{} ", nombre, email, password, fNac, nombreReal, apellidos, pais);
			}
			
			Usuario usuario= new Usuario();
			usuario.setTipo(1); // USUARIO
			// FECHAS DE ALTA Y MODIFICACION SIEMPRE SERAN LA ACTUAL
			usuario.setFechaAlta(new Date());
			usuario.setFechaMod(new Date()); 						
			nombre = ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, true);
			email = ValidationUtils.validString(errors, email, ParameterNames.EMAIL, true);
			password = ValidationUtils.validPass(errors, password, ParameterNames.PASSWORD, true);
			Date fechaNacimiento = ValidationUtils.validDate(errors, fNac, ParameterNames.FECHA_NAC, true, dateUtils);
			nombreReal = ValidationUtils.validString(errors, nombreReal, ParameterNames.NOMBRE_REAL, true);
			apellidos = ValidationUtils.validString(errors, apellidos, ParameterNames.APELLIDOS, true);
			pais = ValidationUtils.validPais(errors, pais, ParameterNames.ID_PAIS, true, idsPais);
			if (!errors.hasErrors()) {
				usuario.setNombre(nombre);
				usuario.setEmail(email);
				usuario.setContrasena(password);
				usuario.setFechaNac(fechaNacimiento);
				usuario.setNombreReal(nombreReal);
				usuario.setApellidos(apellidos);
				usuario.setPais(pais);
				try {
					usuario = usuarioSvc.crearCuenta(usuario);
					SessionManager.set(request, SessionAttributeNames.USUARIO , usuario);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
				}
			}
			if (!errors.hasErrors()) {
				try { 
					usuario = usuarioSvc.logIn(email, password); 
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION,ErrorCodes.LOGIN_SERVICE_ERROR);
				}
			}			
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("El registro no ha podido realizarse: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.REGISTRO;			
			}
			target = ViewPath.HOME;

		} else if (Actions.SALIR.equalsIgnoreCase(action)) {
			
			request.getSession(true).setAttribute(SessionAttributeNames.USUARIO, null);
			target = ViewPath.HOME;
			
		} else if (Actions.MI_PERFIL.equalsIgnoreCase(action)) {
			
			Usuario usuario= (Usuario) SessionManager.get(request, SessionAttributeNames.USUARIO);
			
			System.out.println("MI PERFIL");
			
			target = ViewPath.MI_PERFIL;

		} else if (Actions.EDITAR_PERFIL.equalsIgnoreCase(action)) {
			Usuario usuario= (Usuario) SessionManager.get(request, SessionAttributeNames.USUARIO);
			System.out.println("EDITAR PERFIL");
			
			target = ViewPath.MI_PERFIL;

		} else if (Actions.DETALLE.equalsIgnoreCase(action)) {
			
			Usuario usuario=null;
			Long idContenido = Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO));			
			if(SessionManager.get(request, SessionAttributeNames.USUARIO)!=null) {
				Long idSesion= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
				try {
					usuario = usuarioSvc.buscarId(idSesion, idContenido );
					request.setAttribute(ParameterNames.DENUNCIADO, usuario.getDenunciado());
					request.setAttribute(ParameterNames.SIGUIENDO, usuario.getSiguiendo());
					request.setAttribute(ParameterNames.AUTENTICADO, true);
					request.setAttribute(ParameterNames.ID_SESION, idSesion);					
				} catch (DataException | NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			} else {
				try {
					usuario = usuarioSvc.buscarId(null, idContenido );
					request.setAttribute(ParameterNames.AUTENTICADO, false);
				} catch (DataException | NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			}
			request.setAttribute(AttributeNames.USUARIO, usuario);
			
			ContenidoCriteria criteria = new ContenidoCriteria();
			int page = WebUtils.
					getPageNumber(request.getParameter(ParameterNames.PAGE_V), 1);
			int startIndex= (page-1)*pageSize+1;
			int count= pageSize;
			criteria.setAutor(idContenido);
			criteria.setTipo(2);			
			List<Contenido> listaVideos = null;
			try {
				listaVideos = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma).getPage();
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
			}
			request.setAttribute(AttributeNames.VIDEOS_SUBIDOS, listaVideos);
			
			criteria.setTipo(3);
			int page_l = WebUtils.
					getPageNumber(request.getParameter(ParameterNames.PAGE_L), 1);
			int startIndex_l= (page-1)*pageSize+1;
			int count_l= pageSize;
			List<Contenido> listaListas = null;
			try {
				listaListas = contenidoSvc.buscarCriteria(criteria, startIndex_l, count_l, idioma).getPage();
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
			}
			request.setAttribute(AttributeNames.LISTAS_SUBIDAS,listaListas);
									
			target = ViewPath.DETALLE_PERFIL;

		}	else if (Actions.CAMBIAR_LOCALE.equalsIgnoreCase(action)) {
						
			String localeName = request.getParameter(ParameterNames.LOCALE);
			// Recordar que hay que validar... lo que nos envian, incluso en algo como esto.
			// Buscamos entre los Locale soportados por la web:
			List<Locale> results = LocaleManager.getMatchedLocales(localeName);
			Locale newLocale = null;
			if (results.size()>0) {
				newLocale = results.get(0);
			} else {
				logger.warn("Request locale not supported: "+localeName);
				newLocale = LocaleManager.getDefault();
			}			
			SessionManager.set(request, ConstantValues.USER_LOCALE, newLocale);			
			CookieManager.addCookie(response, ConstantValues.USER_LOCALE, newLocale.toString(), "/", 365*24*60*60);			

			if (logger.isDebugEnabled()) {
				logger.debug("Locale changed to "+newLocale);
			}
			
			response.sendRedirect(request.getHeader("referer"));

		} else if (Actions.SALIR.equalsIgnoreCase(action)) {
			
			SessionManager.set(request, SessionAttributeNames.USUARIO, null);			
			target = ViewPath.HOME;
			
		} else {// LA ACTION RECIBIDA NO ESTA DEFINIDA
			
			// Mmm...
			logger.error("Action desconocida");
			// target ?
		}
		
		// POR ULTIMO SE ENVIA A DONDE/COMO CORRESPONDA:
		if(!Actions.CAMBIAR_LOCALE.equalsIgnoreCase(action)) {
			if (redirect) {
				logger.info("Redirecting to "+target);
				response.sendRedirect(target);
			} else {
				logger.info("Forwarding to "+target);
				request.getRequestDispatcher(target).forward(request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
