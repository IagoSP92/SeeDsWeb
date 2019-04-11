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

import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;

@WebServlet("/redirect")
public class redirectServlet extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(redirectServlet.class);
	
	private ContenidoService contenidoSvc = null;
	
	ValidationUtils validationUtils = null;

    public redirectServlet() {  
    	contenidoSvc = new ContenidoServiceImpl();
    	validationUtils = new ValidationUtils();    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		else {
			idioma= "ES" ;//CHAPUZA			
		}
		
		if (Actions.DETALLE.equalsIgnoreCase(action)) {
			
			Long id = Long.parseLong(request.getParameter(ParameterNames.ID_CONTENIDO));
			Integer tipo = Integer.parseInt(request.getParameter(ParameterNames.TIPO));
			
			if(tipo!=null && tipo<=3 && tipo>0) { // Se comprueba la validez del TIPO
				if(id!=null && id>= 0) { // Se comprueba la validez del ID
					
					if(tipo==1) {
						request.getRequestDispatcher("/usuario").forward(request, response);											
					}
					if(tipo==2) {
						request.getRequestDispatcher("/video").forward(request, response);
					}
					if(tipo==3) {
						request.getRequestDispatcher("/lista").forward(request, response);
					}
					request.setAttribute(ParameterNames.ID_CONTENIDO, id);
				}
				else {//ID INVALIDO					
					target = ViewPath.ERROR500;
					logger.info("Forwarding to "+target);
					request.getRequestDispatcher(target).forward(request, response);
				}				
			}
			else {//TIPO INVALIDO				
				target = ViewPath.ERROR500;// molaria mandalo a donde estaba e que aparecese un mensaxe nesa mima paxina
				logger.info("Forwarding to "+target);
				request.getRequestDispatcher(target).forward(request, response);
			}

		} else {// LA ACTION RECIBIDA NO ESTA DEFINIDA
			
			// Mmm...
			logger.error("Action desconocida");
			// target ?
		}

		/*
		if (redirect) {
			logger.info("Redirecting to "+target);
			response.sendRedirect(target);
		} else {
			logger.info("Forwarding to "+target);
			request.getRequestDispatcher("/usuario").forward(request, response);
			//request.getRequestDispatcher(target).forward(request, response);
		}		
		*/
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
