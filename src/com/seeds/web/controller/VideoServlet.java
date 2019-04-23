package com.seeds.web.controller;

import java.io.IOException;
import java.util.Date;
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
import com.isp.seeds.model.Usuario;
import com.isp.seeds.model.Video;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.ValidationUtils;


@WebServlet("/video")
public class VideoServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(VideoServlet.class);

	private DateUtils dateUtils = null;

	private UsuarioService usuarioSvc = null;
	private VideoService videoSvc = null;

	private ContenidoService contenidoSvc = null;

	private List<String> idsPais;

	public VideoServlet() {
		super();
		usuarioSvc = new UsuarioServiceImpl();
		contenidoSvc = new ContenidoServiceImpl();
		dateUtils = new DateUtils();
		videoSvc= new VideoServiceImpl();		

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
			Video video;
			try {
				video = videoSvc.buscarId(null, Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO)) );
				request.setAttribute(AttributeNames.VIDEO, video);
				request.setAttribute(AttributeNames.NOMBRE_AUTOR, contenidoSvc.buscarId(video.getAutor()).getNombre());		
				
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			} catch (NumberFormatException e) {
				logger.warn(e.getMessage(), e);
			}		
			
			target = ViewPath.DETALLE_VIDEO;

		} else if (Actions.SUBIR_VIDEO.equalsIgnoreCase(action)){
			
			String nombre = request.getParameter(ParameterNames.NOMBRE);
			String descripcion = request.getParameter(ParameterNames.DESCRIPCION);
			String ruta = request.getParameter(ParameterNames.RUTA_VIDEO);
			
			if (logger.isDebugEnabled()) {
				logger.info("Nombre:{} descripcion:{} pass:{} ruta:{} nombrereal:{} apeliidos:{} pais:{} ", nombre, descripcion, ruta);
			}
			
			Video video= new Video();
			video.setTipo(2); // TIPO VIDEO
			video.setFechaAlta(new Date());
			video.setFechaMod(new Date()); // FECHAS DE ALTA Y MODIFICACION SON LA ACTUAL
						
			nombre = ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, true);
			descripcion = ValidationUtils.validString(errors, descripcion, ParameterNames.EMAIL, true);			
			
			if (!errors.hasErrors()) {
				video.setNombre(nombre);
				video.setDescripcion(descripcion);
			}

			if (!errors.hasErrors()) {
				try {
					video = videoSvc.crearVideo(video);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
				}
			}
			
			
			
			if (errors.hasErrors()) {	
				if (logger.isDebugEnabled()) {
					logger.debug("Subir Video Fallido: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);				
				target = ViewPath.HOME;				
			}

			target = ViewPath.HOME;
			
			
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
