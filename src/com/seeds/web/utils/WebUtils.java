package com.seeds.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.model.Contenido;
import com.isp.seeds.service.CategoriaServiceImpl;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.ListaServiceImpl;
import com.isp.seeds.service.PaisServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.spi.CategoriaService;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.ListaService;
import com.isp.seeds.service.spi.PaisService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.config.ConfigurationParameterNames;
import com.seeds.web.controller.AttributeNames;
import com.seeds.web.controller.ConstantValues;
import com.seeds.web.controller.ParameterNames;

/** 
 * Commodity method para facilitar la implementacion de la paginacion, etc.
 *
 */
public class WebUtils {

	private static Logger logger = LogManager.getLogger(WebUtils.class.getName());
		
	public static int pageSize = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGE_SIZE_DEFAULT)); 

	private static int pagingPageCount = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGING_PAGE_COUNT));
	
	public static DateUtils dateUtils = null;
	public static ContenidoService contenidoSvc = null;
	public static VideoService videoSvc = null;
	public static ListaService listaSvc = null;
	public static UsuarioService usuarioSvc = null;
	public static CategoriaService categoriaSvc = null;
	public static ValidationUtils validationUtils = null;
	public static PaisService paisSvc = null;
	
	static {		
		contenidoSvc = new ContenidoServiceImpl();
		videoSvc = new VideoServiceImpl();
		listaSvc = new ListaServiceImpl();
		usuarioSvc = new UsuarioServiceImpl();
		dateUtils = new DateUtils();
		categoriaSvc = new CategoriaServiceImpl();
		validationUtils = new ValidationUtils();
		paisSvc = new PaisServiceImpl();
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
	


}
