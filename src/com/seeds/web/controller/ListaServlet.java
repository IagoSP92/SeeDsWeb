package com.seeds.web.controller;

import java.io.IOException;
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
import com.isp.seeds.model.Lista;
import com.isp.seeds.model.Video;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.ListaServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.ListaService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.isp.seeds.service.util.Results;
import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.config.ConfigurationParameterNames;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.WebUtils;


@WebServlet("/lista")
public class ListaServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(ListaServlet.class);
	
	private static int pageSize = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGE_SIZE_DEFAULT)); 

	private static int pagingPageCount = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGING_PAGE_COUNT));

	private DateUtils dateUtils = null;

	private UsuarioService usuarioSvc = null;
	private VideoService videoSvc = null;
	private ListaService listaSvc = null;


	private ContenidoService contenidoSvc = null;

	private List<String> idsPais;

	public ListaServlet() {
		super();
		usuarioSvc = new UsuarioServiceImpl();
		contenidoSvc = new ContenidoServiceImpl();
		dateUtils = new DateUtils();
		videoSvc= new VideoServiceImpl();
		listaSvc= new ListaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter(ParameterNames.ACTION);

		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}

		ErrorManager errors = new ErrorManager(); 
		String target = null;
		boolean redirect = false;

		if (Actions.DETALLE.equalsIgnoreCase(action)) {
			
			Lista lista;
			try {
				lista = listaSvc.buscarId(null, Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO)) );
				request.setAttribute(AttributeNames.LISTA, lista);
				request.setAttribute(AttributeNames.NOMBRE_AUTOR, contenidoSvc.buscarId(lista.getAutor()).getNombre());
				
				int page = WebUtils.
						getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
				int startIndex= (page-1)*pageSize+1;
				int count= pageSize;
				Results<Video> videosLista= null;
				try { 
					videosLista = listaSvc.verVideosLista(lista.getId(), startIndex, count);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
				}
				
				request.setAttribute(AttributeNames.RESULTADOS, videosLista.getPage());
				request.setAttribute(AttributeNames.TOTAL, videosLista.getTotal());
				
				// Datos para paginacion (Calculos aqui, datos comodos para renderizar)
				int totalPages = (int) Math.ceil((double)videosLista.getTotal()/(double)pageSize);
				int firstPagedPage = Math.max(1, page-pagingPageCount);
				int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
				request.setAttribute(ParameterNames.PAGE, page);
				request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
				request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
				request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
				
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			} catch (NumberFormatException e) {
				logger.warn(e.getMessage(), e);
			}		
			
			target = ViewPath.DETALLE_LISTA;
			/*
			Lista lista;
			try {
				lista = listaSvc.buscarId(null, Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO)) );
				request.setAttribute(AttributeNames.LISTA, lista);
				target = ViewPath.DETALLE_LISTA;
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

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
