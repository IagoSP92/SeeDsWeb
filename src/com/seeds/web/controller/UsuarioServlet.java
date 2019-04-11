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
import com.isp.seeds.model.Pais;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.ListaServiceImpl;
import com.isp.seeds.service.PaisServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.ListaService;
import com.isp.seeds.service.spi.PaisService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.CookieManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.LocaleManager;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;


@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(UsuarioServlet.class);

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
		
		Object locale =  SessionManager.get(request, ConstantValues.USER_LOCALE);
		String idioma=null;		
		if(locale!=null) {
			String rawIdioma = locale.toString();
			idioma=rawIdioma.substring(0, 2);
		}
		else {
			idioma= "ES" ;//CHAPUZA			
		}	

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
					e.printStackTrace();    //CAMBIAR POR LOGGER
				}
			}

			if (usuario == null) {
				errors.add(ParameterNames.ACTION,ErrorCodes.AUTHENTICATION_ERROR);
			}
			
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("Autenticacion fallida: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.ENTRAR;				
			} else {
				if (logger.isDebugEnabled()) {
					logger.info("Usuario "+usuario.getEmail()+" autenticado.");
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
				logger.info("Nombre:{} email:{} pass:{} fnac:{} nombrereal:{} apeliidos:{} pais:{} ", nombre, email, password, fNac, nombreReal, apellidos, pais);
			}
			
			Usuario usuario= new Usuario();
			usuario.setTipo(1); // TIPO USUARIO
			
			usuario.setFechaAlta(new Date());
			usuario.setFechaMod(new Date()); // FECHAS DE ALTA Y MODIFICACION SON LA ACTUAL
						
			nombre = ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, true);
			email = ValidationUtils.validString(errors, email, ParameterNames.EMAIL, true);
			password = ValidationUtils.validPass(errors, password, ParameterNames.PASSWORD, true);
			Date fechaNacimiento = ValidationUtils.validDate(errors, fNac, ParameterNames.FECHA_NAC, true, dateUtils);
			nombreReal = ValidationUtils.validString(errors, nombreReal, ParameterNames.NOMBRE_REAL, true);
			apellidos = ValidationUtils.validString(errors, apellidos, ParameterNames.APELLIDOS, true);
			System.out.println(pais);
			System.out.println(idsPais.size());
			for(String p: idsPais) {
				System.out.println(p);
				
			}
			
			pais = ValidationUtils.validPais(errors, pais, ParameterNames.ID_PAIS, true, idsPais);
			
			if (!errors.hasErrors()) {
				usuario.setNombre(nombre);
				usuario.setEmail(email);
				usuario.setContrasena(password);
				usuario.setFechaNac(fechaNacimiento);
				usuario.setNombreReal(nombreReal);
				usuario.setApellidos(apellidos);
				usuario.setPais("ES");
			}

			if (!errors.hasErrors()) {
				try {
					usuario = usuarioSvc.crearCuenta(usuario);
				} catch (DataException e) {
					e.printStackTrace();
				}
			}
			
			if (!errors.hasErrors()) {
				try { 
					usuario = usuarioSvc.logIn(email, password); 
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION,ErrorCodes.ERROR_DABAIXO);
				}
			}
			
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("Registro 	Fallido: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.REGISTRO;				
			}

			target = ViewPath.HOME;

		} else if (Actions.SALIR.equalsIgnoreCase(action)) {
			
			request.getSession(true).setAttribute(SessionAttributeNames.USUARIO, null);
			//response.sendRedirect(ViewsPaths.ROOT_CONTEXT);
			//SessionManager.set(request, SessionAttributeNames.USUARIO, null);
			target = ViewPath.HOME;
			
		} else if (Actions.EDITAR_PERFIL.equalsIgnoreCase(action)) {
			Usuario usuario= (Usuario) SessionManager.get(request, SessionAttributeNames.USUARIO);
			
			target = ViewPath.DETALLE_PERFIL;

		} else if (Actions.DETALLE.equalsIgnoreCase(action)) {
			Usuario usuario;
			try {
				usuario = usuarioSvc.buscarId(Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO)) );
				Long id = usuario.getId();
				request.setAttribute(AttributeNames.USUARIO, usuario);
				
				request.setAttribute(AttributeNames.VIDEOS_SUBIDOS, videoSvc.buscarPorAutor(id));
				request.setAttribute(AttributeNames.LISTAS_SUBIDAS, listaSvc.buscarPorAutor(id));
				
//				request.setAttribute(AttributeNames.USUARIOS_SEGUIDOS, usuarioSvc.);
//				request.setAttribute(AttributeNames.VIDEOS_SUBIDOS, videoSvc.buscarPorAutor(id));
//				
//				request.setAttribute(AttributeNames.VIDEOS_SUBIDOS, videoSvc.buscarPorAutor(id));
//				request.setAttribute(AttributeNames.VIDEOS_SUBIDOS, videoSvc.buscarPorAutor(id));
				
				target = ViewPath.DETALLE_PERFIL;
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}	else if (Actions.CAMBIAR_LOCALE.equalsIgnoreCase(action)) {
			
			logger.debug("Estamos en usuario/change-locale");
			
			
			
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

			idioma = LocaleManager.getDefault().getDisplayName();			

			SessionManager.set(request, ConstantValues.IDIOMA, idioma);
			
			SessionManager.set(request, ConstantValues.USER_LOCALE, newLocale);
			
			CookieManager.addCookie(response, ConstantValues.IDIOMA, idioma, "/", 365*24*60*60);
			
			CookieManager.addCookie(response, ConstantValues.USER_LOCALE, newLocale.toString(), "/", 365*24*60*60);
			

			if (logger.isDebugEnabled()) {
				logger.debug("Locale changed to "+newLocale);
			}

			response.sendRedirect(request.getHeader("referer"));


		} else if (Actions.SALIR.equalsIgnoreCase(action)) {
			
			SessionManager.set(request, SessionAttributeNames.USUARIO, null);
			
			target = ViewPath.HOME;
			redirect = true;
			
		} else {// LA ACTION RECIBIDA NO ESTA DEFINIDA
			
			// Mmm...
			logger.error("Action desconocida");
			// target ?
		}
		
		// POR ULTIMO SE ENVIA A DONDE/COMO CORRESPONDA:
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

}
