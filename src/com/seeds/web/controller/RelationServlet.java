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
import com.isp.seeds.model.Usuario;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;

/**
 * Servlet implementation class RelationService
 */
@WebServlet("/relaciones")
public class RelationServlet extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(RelationServlet.class);
	private ContenidoService contenidoSvc = null;

    public RelationServlet() {
        super();
		contenidoSvc = new ContenidoServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ParameterNames.ACTION);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}
		
		ErrorManager errors = new ErrorManager(); 
		String target = null;
		boolean redirect = false;
		
		Long idSesion= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
		String id= request.getParameter(ParameterNames.ID_CONTENIDO);
		Long idContenido =ValidationUtils.validLong(errors, id, ParameterNames.ID_CONTENIDO, false);
		
		if (Actions.SEGUIR.equalsIgnoreCase(action)) {
			Boolean nuevoValor=null;
			if(ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.SIGUIENDO), ParameterNames.SIGUIENDO, true)) {
				nuevoValor=false;
			} else {nuevoValor=true;}
			if (logger.isDebugEnabled()) {
				logger.debug("Action={}: nuevoValor={}", action, nuevoValor);
			}
			try {
				contenidoSvc.seguirContenido(idSesion, idContenido, nuevoValor);
				
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				//erros
			}
			//response.getOutputStream().write();
			
		} else if (Actions.GUARDAR.equalsIgnoreCase(action)) {			
			Boolean nuevoValor=null;
			if(ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.GUARDADO), ParameterNames.GUARDADO, true)) {
				nuevoValor=false;
			} else {nuevoValor=true;}
			if (logger.isDebugEnabled()) {
				logger.debug("Action={}: nuevoValor={}", action, nuevoValor);
			}
			try {
				contenidoSvc.guardarContenido(idSesion, idContenido, nuevoValor);
				
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				//erros
			}
			
		} else if (Actions.DENUNCIAR.equalsIgnoreCase(action)) {
			
			String valorRecibido = request.getParameter(ParameterNames.DENUNCIADO);
			if(valorRecibido!=null) {
				String nuevoValor = ValidationUtils.validString(errors, request.getParameter(ParameterNames.DENUNCIADO), ParameterNames.DENUNCIADO, true);
				try {
					contenidoSvc.denunciarContenido(idSesion, idContenido, nuevoValor);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					//erros
				}
			} else {
				try {
					contenidoSvc.cancelarDenuncia(idSesion, idContenido);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					//erros
				}
			}	
			
		} else if (Actions.COMENTAR.equalsIgnoreCase(action)) {
			
			String valorRecibido = request.getParameter(ParameterNames.COMENTADO);
			if(valorRecibido!=null) {
				String nuevoValor = ValidationUtils.validString(errors, request.getParameter(ParameterNames.COMENTADO), ParameterNames.COMENTADO, true);
				try {
					contenidoSvc.comentarContenido(idSesion, idContenido, nuevoValor);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					//erros
				}
			} else {
				try {
					contenidoSvc.borrarComentario(idSesion, idContenido);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					//erros
				}
			}
			
		} else if (Actions.VALORAR.equalsIgnoreCase(action)) {
			Integer nuevoValor= ValidationUtils.validInt(errors, request.getParameter(ParameterNames.VALORACION), ParameterNames.VALORACION, true);			
			try {
				contenidoSvc.valorarContenido(idSesion, idContenido, nuevoValor);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				//erros
			}
			
		}
		else {
			logger.error("Action desconocida");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
