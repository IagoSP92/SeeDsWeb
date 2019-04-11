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
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.config.ConfigurationParameterNames;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;


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
	
	private static int pageSize = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGE_SIZE_DEFAULT)); 

	private static int pagingPageCount = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGING_PAGE_COUNT));


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

		if (Actions.BUSCAR.equalsIgnoreCase(action)) {
			
			int page = WebUtils.
					getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
			int startIndex= (page-1)*pageSize+1;
			int count= pageSize;

			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String fechaMin = request.getParameter(ParameterNames.FECHA_MIN);
			String fechaMax = request.getParameter(ParameterNames.FECHA_MAX);
			String id = request.getParameter(ParameterNames.ID_CONTENIDO);

			Results<Contenido> listado = null;
			ContenidoCriteria criteria = new ContenidoCriteria();
			
			criteria.setNombre(ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, false));				
			criteria.setFechaAlta(ValidationUtils.validDate(errors, fechaMin, ParameterNames.FECHA_MIN, false, dateUtils));
			criteria.setFechaAlta(ValidationUtils.validDate(errors, fechaMax, ParameterNames.FECHA_MAX, false, dateUtils));
			criteria.setId(ValidationUtils.validLong(id));

			try { 
				listado = contenidoSvc.buscarCriteria(criteria, startIndex, count, idioma);
			} catch (DataException e) {
				e.printStackTrace();
			}
			
			request.setAttribute(AttributeNames.RESULTADOS, listado.getPage());
			request.setAttribute(AttributeNames.TOTAL, listado.getTotal());
			
			// Datos para paginacion															
			// (Calculos aqui, datos comodos para renderizar)
			int totalPages = (int) Math.ceil((double)listado.getTotal()/(double)pageSize);
			int firstPagedPage = Math.max(1, page-pagingPageCount);
			int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
			request.setAttribute(ParameterNames.PAGE, page);
			request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
			request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
			request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
						
			// PARAMETROS DE BUSQUEDA
			request.setAttribute(ParameterNames.NOMBRE, nombre);
			request.setAttribute(ParameterNames.FECHA_MIN, fechaMin);
			request.setAttribute(ParameterNames.FECHA_MAX, fechaMax);
			request.setAttribute(ParameterNames.ID_CONTENIDO, id);

			target = ViewPath.BUSCADOR;

		} else {// LA ACTION RECIBIDA NO ESTA DEFINIDA
			
			// Mmm...
			logger.error("Action desconocida");
			// target ?
		}
		
		// POR ULTIMO SE ENVIA A DONDE/COMO CORRESPONDA:
		if (redirect) {
			logger.info("Redirecting from ContenidoServlet to "+target);
			response.sendRedirect(target);
		} else {
			logger.info("Forwarding from ContenidoServlet to "+target);
			request.getRequestDispatcher(target).forward(request, response);
		}		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
