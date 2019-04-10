package com.seeds.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.isp.seeds.dao.impl.ContenidoDAOImpl;
import com.isp.seeds.model.Contenido;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;


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
		
		//String idioma= SessionManager.get(request, attName);
		String idioma= "ESP" ;

		if (Actions.DETALLE.equalsIgnoreCase(action)) {
			
			String id = request.getParameter(ParameterNames.ID_CONTENIDO);
			
			Contenido contenido;
			try {
				contenido = contenidoSvc.buscarId(ValidationUtils.validLong(id), idioma);
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Integer tipo= contenido.getTipo();
			
			

			target = ViewPath.BUSCADOR;

		} else {// LA ACTION RECIBIDA NO ESTA DEFINIDA
			
			// Mmm...
			logger.error("Action desconocida");
			// target ?
		}
		
		if (redirect) {
			logger.info("Redirecting to "+target);
			response.sendRedirect(target);
		} else {
			logger.info("Forwarding to "+target);
			request.getRequestDispatcher(target).forward(request, response);
		}		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
