package com.seeds.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.model.Contenido;
import com.isp.seeds.service.CategoriaServiceImpl;
import com.isp.seeds.service.spi.CategoriaService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.controller.AttributeNames;
import com.seeds.web.controller.ConstantValues;
import com.seeds.web.controller.ParameterNames;

/** 
 * Commodity method para facilitar la implementacion de la paginacion, etc.
 *
 */
public class WebUtils implements ConstantsInterface {

	private static Logger logger = LogManager.getLogger(WebUtils.class.getName());
		
	
	private static CategoriaService categoriaSvc = null;
	
	public WebUtils () {
		categoriaSvc = new CategoriaServiceImpl();
	}

	
	public static HttpServletRequest pagParams (HttpServletRequest request, Results<Contenido> listado, int page) {
		// PARAMETROS PAGINACION
		int totalPages = (int) Math.ceil((double)listado.getTotal()/(double)pageSize);
		int firstPagedPage = Math.max(1, page-pagingPageCount);
		int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
		request.setAttribute(ParameterNames.PAGE, page);
		request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
		request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
		request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
		request.setAttribute(AttributeNames.RESULTADOS, listado.getPage());
		request.setAttribute(AttributeNames.TOTAL, listado.getTotal());
		return request;
	}
	
	
	
	
	/**
	 * Obtiene el valor entero de un valor de parametro currentPageValue
	 */
	public static final int getPageNumber(String pageValue, int defaultValue) {
		int pageNumber = defaultValue;
		if (pageValue!=null) {
			try {
				pageNumber = Integer.valueOf(pageValue);
			} catch (NumberFormatException e) {
				logger.warn("Parece que hay un usuario navegando que se considera muy listo: "+pageValue);		
			}
		}
		return pageNumber;
	}
	
	public static String getIdioma (HttpServletRequest request) {
		Object locale =  SessionManager.get(request, ConstantValues.USER_LOCALE);
		String idioma=null;		
		if (locale!=null) {
			String rawIdioma = locale.toString();
			idioma=rawIdioma.substring(0, 2);
		}
		return idioma;
	}
	
	/*
	public class RedirectOrForward {
		
		public static final void send(HttpServletRequest request, HttpServletResponse response,
				Boolean redirect, String target, Boolean send) throws ServletException, IOException {
			System.out.println(redirect+","+target+" "+send);
			if(send) {
				StringBuilder s = new StringBuilder();
				s.append(request.getContextPath()).append(target);
				
				if (redirect) {
					response.sendRedirect(s.toString());
				} else {
					request.getRequestDispatcher(target).forward(request, response);
				}
			} else {
				target = request.getHeader("referer");
				System.out.println(target);
				response.sendRedirect(target);
			}
		}
	}
*/


}
