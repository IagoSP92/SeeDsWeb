package com.seeds.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.SendFailedException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Categoria;
import com.isp.seeds.model.Pais;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.model.Video;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.VideoServiceImpl;
import com.isp.seeds.service.spi.ContenidoService;
import com.isp.seeds.service.spi.UsuarioService;
import com.isp.seeds.service.spi.VideoService;
import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.FileUtils;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;


@WebServlet("/video")
public class VideoServlet extends HttpServlet {
	
	private static final String UPLOAD_DIRECTORY = ConfigurationManager.getInstance().getParameter("upload.directory");
	// upload settings
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 30;  // 30MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

	private static Logger logger = LogManager.getLogger(VideoServlet.class);
	
	
	
	private List<Long> idsCategoria;
	public VideoServlet() {
		super();			
		try {
			idsCategoria = WebUtils.categoriaSvc.findAll("ES").stream().map(Categoria::getIdCategoria).collect(Collectors.toList());
		} catch (DataException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = null;
		List<FileItem> formItems=null;
		
		
        
        if(ServletFileUpload.isMultipartContent(request)) {
        	 formItems = new ArrayList<FileItem>();
        	 
        	 // configures upload settings
		        DiskFileItemFactory factory = new DiskFileItemFactory();
		        // sets memory threshold - beyond which files are stored in disk
		        factory.setSizeThreshold(MEMORY_THRESHOLD);
		        // sets temporary location to store files
		        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));	 
		        ServletFileUpload upload = new ServletFileUpload(factory);	         
		        // sets maximum size of upload file
		        upload.setFileSizeMax(MAX_FILE_SIZE);	         
		        // sets maximum size of request (include file + form data)
		        upload.setSizeMax(MAX_REQUEST_SIZE);
        	 
    	        try {
    	        	
    				formItems = upload.parseRequest(request);
    				action = formItems.get(0).getString();
    	        }catch (FileUploadException e) {
    				logger.warn(e.getMessage(), e);
    			}
        	
        }else {
        	action = request.getParameter(ParameterNames.ACTION);
        }

		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}

		ErrorManager errors = new ErrorManager(); 
		String target = null;
		boolean redirect = false;
		
		String idContenidoStr = request.getParameter(ParameterNames.ID_CONTENIDO);
		String tipoStr = request.getParameter(ParameterNames.TIPO);
		Long idContenido=null;
		Integer tipo=null;
		if(idContenidoStr!=null) {idContenido =ValidationUtils.validLong(errors, idContenidoStr, ParameterNames.ID_CONTENIDO, true);}
		if(tipoStr!=null) {tipo = ValidationUtils.validInt(errors, tipoStr , ParameterNames.TIPO, true);}
		
		if (Actions.DETALLE.equalsIgnoreCase(action)) {
			
			if (logger.isDebugEnabled()) {
				logger.info("DETALLE PERFIL -> id:{} tipo:{} ", idContenido, tipo);
			}
						
			Video video=null;
			/*Long*/ idContenido = Long.parseLong( request.getParameter(ParameterNames.ID_CONTENIDO));
			request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);
			if(SessionManager.get(request, SessionAttributeNames.USUARIO)!=null) {
				Long idSesion= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
				try {
					video = WebUtils.videoSvc.buscarId(idSesion, idContenido );
					video.setValoracion(WebUtils.contenidoSvc.getValoracion(idContenido));
					request.setAttribute(ParameterNames.NOTA_DEL_VIDEO, video.getValoracion());
					request.setAttribute(ParameterNames.COMENTARIOS, video.getComentarios());
					request.setAttribute(ParameterNames.DENUNCIADO, video.getDenunciado());
					request.setAttribute(ParameterNames.GUARDADO, video.getGuardado());
					if(video.getComentado()==null) {
						request.setAttribute(ParameterNames.COMENTADO, "Null");
					}else {
						request.setAttribute(ParameterNames.COMENTADO, video.getComentado());
					}
					request.setAttribute(ParameterNames.VALORACION, video.getValorado());
					request.setAttribute(ParameterNames.AUTENTICADO, true);
					request.setAttribute(ParameterNames.ID_SESION, idSesion);					
				} catch (DataException | NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			} else {
				try {
					video = WebUtils.videoSvc.buscarId(null, idContenido );
					video.setValoracion(WebUtils.contenidoSvc.getValoracion(idContenido));
					request.setAttribute(ParameterNames.AUTENTICADO, false);
				} catch (DataException | NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
			}			
			try {
				request.setAttribute(AttributeNames.VIDEO, video);
				request.setAttribute(AttributeNames.NOMBRE_AUTOR, WebUtils.contenidoSvc.buscarId(video.getAutor()).getNombre());
//				Long idSesion = ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
//				request.setAttribute(ParameterNames.ID_SESION, idSesion);
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
			} catch (NumberFormatException e) {
				logger.warn(e.getMessage(), e);
				errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
			}			
			target = ViewPath.DETALLE_VIDEO;

		} else if (Actions.PRE_SUBIR_VIDEO.equalsIgnoreCase(action)) {
			
			List<Categoria> categorias = null;			
			try {
				categorias =WebUtils.categoriaSvc.findAll(WebUtils.getIdioma(request)).stream().map(Categoria::getCategoria).collect(Collectors.toList());
			} catch (DataException e) {
				logger.warn(e.getMessage(), e);
				errors.add(AttributeNames.CATEGORIAS, ErrorCodes.PRE_SUBIR_VIDEO);
			}
			request.setAttribute(AttributeNames.CATEGORIAS, categorias);
			target = ViewPath.SUBIR_VIDEO;
			
		} else if (Actions.SUBIR_VIDEO.equalsIgnoreCase(action)){
						
			Long idAutor= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
			String nombre = null;
			String descripcion =  null;
			String ruta = null;
			String categoria = null;

	        // parses the request's content to extract file data
			nombre = formItems.get(1).getString();
			descripcion = formItems.get(2).getString();
			categoria = formItems.get(3).getString();
	        	        
	        nombre = ValidationUtils.validString(errors, nombre, ParameterNames.NOMBRE, true);
	        descripcion = ValidationUtils.validString(errors, descripcion, ParameterNames.DESCRIPCION, true);
	        Long idCategoria = ValidationUtils.validCategoria(errors, categoria, ParameterNames.CATEGORIA, true, idsCategoria);
	        
	        Video video = new Video();

			if (!errors.hasErrors()) {
				
				video.setTipo(2); // TIPO VIDEO
				video.setFechaAlta(new Date());
				video.setFechaMod(new Date()); // FECHAS DE ALTA Y MODIFICACION SON LA ACTUAL
				video.setAutor(idAutor);
				try {
					video.setCategoria(WebUtils.categoriaSvc.findById(idCategoria, "ES"));
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(Actions.SUBIR_VIDEO, ErrorCodes.RECOVERY_ERROR);
				}
				video.setNombre(nombre);
				video.setDescripcion(descripcion);
				
				if (!errors.hasErrors()) {
					try {
						video = WebUtils.videoSvc.crearVideo(video);
					} catch (DataException e) {
						logger.warn(e.getMessage(), e);
						//errors
					}
				}
				
				if (!formItems.get(4).isFormField()) {
                    ruta= FileUtils.loadVideo(idAutor, video.getId(), formItems.get(4));
                }
				video.setUrl(ruta);
				try {
					WebUtils.videoSvc.editarVideo(video);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					// errors
				}
			}

			if (!errors.hasErrors()) {
				
				// Podriamos enviar a video detalle
				//request.setAttribute(ParameterNames.ACTION, Actions.DETALLE);
//				request.setAttribute(ParameterNames.ACTION, Actions.DETALLE);
//				request.setAttribute(ParameterNames.ID_CONTENIDO, video.getId());
//				request.setAttribute(ParameterNames.TIPO, video.getTipo());
				/*
				target = ControllerPaths.NO_CONTEXT_PRODUCTO.concat("?").concat(ParameterNames.ACTION).concat("=").concat(Actions.SEARCH_PRODUCTS)
						.concat("&").concat(ParameterNames.DEFAULT).concat("=").concat(defaultSearch);
						*/
				//RedirectOrForward.send(request, response, false, target, true);
				target= ViewPath.DETALLE_VIDEO;
				
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Subir Video Fallido: {}", errors);
				}				
				request.setAttribute(AttributeNames.ERRORS, errors);
				
				target= ViewPath.SUBIR_VIDEO;
			}

//			request.setAttribute(ParameterNames.ACTION, Actions.DETALLE_VIDEO );
//			request.setAttribute(ParameterNames.ID_CONTENIDO, video.getId());	
//			request.setAttribute(ParameterNames.TIPO, video.getTipo());	
//			target = ControllerPath.VIDEO;
			
			//target = ViewPath.HOME;
			
			
		} else   if (Actions.REPRODUCIR_VIDEO.equalsIgnoreCase(action)) {// LA ACTION RECIBIDA NO ESTA DEFINIDA
			
			Long idVideo= ValidationUtils.validLong(errors, request.getParameter(ParameterNames.ID_VIDEO), ParameterNames.ID_VIDEO, true);
			String urlBase= request.getParameter(ParameterNames.RUTA_VIDEO);
			FileUtils.readVideo(response,urlBase, idVideo);
			return;			
			
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
