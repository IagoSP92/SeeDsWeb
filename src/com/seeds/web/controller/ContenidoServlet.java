package com.seeds.web.controller;

import java.io.IOException;
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
import com.isp.seeds.model.Contenido;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;


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



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter(ParameterNames.ACTION);

		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}

		ErrorManager errors = new ErrorManager(); 
		String target = null;
		boolean redirect = false;
		int startIndex= 1;
		int count= 4;
		//String idioma= SessionManager.get(request, attName);
		String idioma= "ESP" ;

		if (Actions.BUSCAR.equalsIgnoreCase(action)) {

			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String fechaMin = request.getParameter(ParameterNames.FECHA_MIN);
			String fechaMax = request.getParameter(ParameterNames.FECHA_MAX);
			String id = request.getParameter(ParameterNames.ID_CONTENIDO);

			Results<Contenido> listado = null;

			ContenidoCriteria criteria = new ContenidoCriteria();
			
			criteria.setNombre(ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, false));				
			criteria.setFechaAlta(ValidationUtils.validDate(errors, fechaMin, ParameterNames.FECHA_MIN, false, dateUtils));
			criteria.setFechaAlta(ValidationUtils.validDate(errors, fechaMax, ParameterNames.FECHA_MAX, false, dateUtils));

			//criteria.setFechaAltaHasta(dateUtils.dateFormat(fechaMax));				
			criteria.setIdContenido(ValidationUtils.validLong(id));

			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				e.printStackTrace();
			}				

			List<Contenido> resultados = new ArrayList<Contenido>();

			for(Contenido c: listado.getPage()) {// HAY QUE MIRAR AQUI COMO SE FAI COS PARAMETROS OUTROS
				resultados.add(c);					
			}
			// Limpiar
			// ...

			// Validar
			//..

			// if hasErrors

			// else

			request.setAttribute(AttributeNames.RESULTADOS, resultados);

			target = ViewPath.BUSCADOR;

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
