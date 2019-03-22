package com.seeds.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Contenido;
import com.isp.seeds.model.Pais;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.PaisServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.PaisService;
import com.isp.seeds.service.spi.UsuarioService;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.Errors;
import com.seeds.web.utils.DateUtils;
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


	public UsuarioServlet() {
		super();
		usuarioSvc = new UsuarioServiceImpl();
		contenidoSvc = new ContenidoServiceImpl();
		dateUtils = new DateUtils();
		paisSvc= new PaisServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter(ParameterNames.ACTION);

		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}

		Errors errors = new Errors(); 
		String target = null;
		boolean redirect = false;

		if (Actions.ENTRAR.equalsIgnoreCase(action)) {			
			// Recuperacion
			String email = request.getParameter(ParameterNames.EMAIL);
			String password = request.getParameter(ParameterNames.PASSWORD);
			
			// Limpieza
			// ...
			if (logger.isDebugEnabled()) {
				logger.debug("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
			}
			
			// Validacion 
			// ...			
			if (StringUtils.isEmpty(email)) {
				errors.add(ParameterNames.EMAIL,ErrorCodes.MANDATORY_PARAMETER);
			}
			
			Usuario usuario = null;
			if (!errors.hasErrors()) {
				try {
					usuario = usuarioSvc.logIn(email, password);
				} catch (DataException e) {
					e.printStackTrace();
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
		} else if (Actions.PREREGISTRO.equalsIgnoreCase(action)) { 
			List<Pais> paises = null;
			try {
				paises = paisSvc.findAll("ESP");
			} catch (DataException e) {
				e.printStackTrace();
			}// ojo, con idioma de usuario en sesion
			request.setAttribute(AttributeNames.PAISES, paises);
			target = ViewPath.REGISTRO;
			
		} else if (Actions.REGISTRO.equalsIgnoreCase(action)) {

			// Recuperacion
			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String email = request.getParameter(ParameterNames.EMAIL);
			String password = request.getParameter(ParameterNames.PASSWORD);
			String fNac = request.getParameter(ParameterNames.FECHA_NAC);
			String nombreReal = request.getParameter(ParameterNames.NOMBRE_REAL);
			String apellidos = request.getParameter(ParameterNames.APELLIDOS);
			String pais = request.getParameter(ParameterNames.NOMBRE_PAIS);
			
			logger.warn("Nombre:{} email:{} pass:{} fnac:{} nombrereal:{} apeliidos:{} pais:{} ", nombre, email, password, fNac, nombreReal, apellidos, pais);
			Usuario usuario= new Usuario();
			usuario.setNombre(nombre);
			usuario.setEmail(email);
			usuario.setContrasena(password);
			usuario.setFechaNac(dateUtils.dateFormat(fNac));
			usuario.setNombreReal(nombreReal);
			usuario.setApellidos(apellidos);
			usuario.setPais("ES");
			usuario.setTipo(1);
			
			usuario.setFechaAlta(dateUtils.dateFormat(fNac));
			usuario.setFechaMod(dateUtils.dateFormat(fNac));

			
			try {
				usuario = usuarioSvc.crearCuenta(usuario);
			} catch (DataException e) {
				e.printStackTrace();
			}
			
			if (!errors.hasErrors()) {
				try { 
					usuario = usuarioSvc.logIn(email, password); 
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION,ErrorCodes.ERROR_DABAIXO);
				}
			}

			target = ViewPath.HOME;
			// Limpieza
			// ...

			// Validacion 
			// ...
			/*
			if (StringUtils.isEmpty(email)) {
				errors.add(ParameterNames.EMAIL,ErrorCodes.MANDATORY_PARAMETER);
			}

			Usuario usuario = null;
			if (!errors.hasErrors()) {
				try { 
					usuario = usuarioSvc.logIn(email, password); 
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION,ErrorCodes.ERROR_DABAIXO);
				}
			}

			if(usuario==null) {
				errors.add(ParameterNames.ACTION,ErrorCodes.AUTHENTICATION_ERROR);
			}

			if (errors.hasErrors()) {

				if (logger.isDebugEnabled()) {
					logger.debug("Autenticacion fallida: {}", errors);
				}

				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.ENTRAR;

			} else {				
				SessionManager.set(request, SessionAttributeNames.USUARIO, usuario);	
				target = ViewPath.HOME;
			}
*/
		} else if (Actions.SALIR.equalsIgnoreCase(action)) {
			
			request.getSession(true).setAttribute(SessionAttributeNames.USUARIO, null);
			//response.sendRedirect(ViewsPaths.ROOT_CONTEXT);
			//SessionManager.set(request, SessionAttributeNames.USUARIO, null);
			target = ViewPath.HOME;

		} else if (Actions.BUSCAR.equalsIgnoreCase(action)) {


			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String fechaMin = request.getParameter(ParameterNames.FECHA_MIN);
			String fechaMax = request.getParameter(ParameterNames.FECHA_MAX);
			String id = request.getParameter(ParameterNames.ID_CONTENIDO);

			List<Contenido> listado = new ArrayList<Contenido>();

			ContenidoCriteria criteria = new ContenidoCriteria();

			criteria.setNombre(ValidationUtils.validateString(nombre));				
			criteria.setFechaAlta(dateUtils.dateFormat(fechaMin));
			criteria.setFechaAltaHasta(dateUtils.dateFormat(fechaMax));				
			criteria.setIdContenido(ValidationUtils.validateLong(id));				

			try {
				listado = contenidoSvc.buscarCriteria(criteria);
			} catch (DataException e) {
				e.printStackTrace();
			}				

			List<String> resultados = new ArrayList<String>();

			for(Contenido c: listado) {
				resultados.add(c.toString());					
			}
			// Limpiar
			// ...

			// Validar
			//..

			// if hasErrors

			// else

			request.setAttribute(AttributeNames.RESULTADOS, resultados);

			target = ViewPath.BUSCADOR;

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
