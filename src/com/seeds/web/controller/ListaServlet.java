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
import com.isp.seeds.model.Lista;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.ListaServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.ListaService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;


@WebServlet("/lista")
public class ListaServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(ListaServlet.class);

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
				lista = listaSvc.buscarId(Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO)) );
				request.setAttribute(AttributeNames.LISTA, lista);
				target = ViewPath.DETALLE_LISTA;
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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