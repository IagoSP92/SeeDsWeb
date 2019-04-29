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

import com.google.gson.JsonObject;
import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Contenido;
import com.isp.seeds.model.Lista;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.model.Video;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;

/**
 * Servlet implementation class RelationService
 */
@WebServlet("/relaciones")
public class RelationServlet extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(RelationServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ParameterNames.ACTION);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}
		
		Object locale =  SessionManager.get(request, ConstantValues.USER_LOCALE);
		String idioma=null;
		
		if (locale!=null) {
			String rawIdioma = locale.toString();
			idioma=rawIdioma.substring(0, 2);
		}
		ErrorManager errors = new ErrorManager(); 
//		String target = null;
//		boolean redirect = false;
		Long idSesion= null;
		Usuario u = ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO));
		if(u!=null){
			idSesion= u.getId();
			idSesion = ValidationUtils.validLong(errors, idSesion.toString(), ParameterNames.ID_SESION, true);
		}
		String idContenidoStr = request.getParameter(ParameterNames.ID_CONTENIDO);
		String tipoStr = request.getParameter(ParameterNames.TIPO);
			
		Long idContenido =ValidationUtils.validLong(errors, idContenidoStr, ParameterNames.ID_CONTENIDO, true);
		Integer tipo = ValidationUtils.validInt(errors, tipoStr , ParameterNames.TIPO, true);
		
		 if (Actions.SUMAR_VISITA.equalsIgnoreCase(action)) {
				if (logger.isDebugEnabled()) {					
					logger.debug(" Action={}:  Contenido={} Tipo={}",action, idContenido, tipo );
				}
				if(tipo==1||tipo==2||tipo==3 ) {
					Integer nuevoValor = null;
					try {
						Contenido contenido = WebUtils.contenidoSvc.buscarId(idContenido);
						 nuevoValor = contenido.getReproducciones()+1;
						contenido.setReproducciones(nuevoValor);
						WebUtils.contenidoSvc.update(contenido);

					} catch (DataException e) {
						errorManagement ( errors, e, ErrorCodes.UNABLE_CHANGE_RELATION );
					}
					
					if(!errors.hasErrors()) {
						JsonObject objetoJson = new JsonObject();
						objetoJson.addProperty("visitas", nuevoValor);
						
						response.setContentType("application/json;charset=ISO-8859-1");
						response.getOutputStream().write(objetoJson.toString().getBytes());
					}
					
				} else {
					logger.warn(" -- Invalid Parameters: TYPE -- ");
					errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_PARAMETER);
				}
				
			}  
		
		if(errors.hasErrors()) {
			logger.warn(" -- Invalid Parameters: BASIC -- ");
			errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_PARAMETER);
		} else {
			if (Actions.SEGUIR.equalsIgnoreCase(action)) {
				
				if((tipo==1 || tipo==3)) {
					Boolean nuevoValor=null;
					if(ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.SIGUIENDO), ParameterNames.SIGUIENDO, true)) {
						nuevoValor=false;
					} else {
						nuevoValor=true;
					}
					definedLogger (action, idSesion, idContenido, tipo, nuevoValor.toString(), 1);
					try {
						WebUtils.contenidoSvc.seguirContenido(idSesion, idContenido, nuevoValor);					
					} catch (DataException e) {
						errorManagement ( errors, e, ErrorCodes.UNABLE_CHANGE_RELATION );
					}				
					//request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);	
					try {
						JsonObject usuarioJson = new JsonObject();
						if(tipo==1) {
							Usuario usuario=null;
							usuario = WebUtils.usuarioSvc.buscarId(idSesion, idContenido );
							usuarioJson.addProperty("siguiendo", usuario.getSiguiendo());
						}
						if(tipo==3) {
							Lista lista=null;
							lista = WebUtils.listaSvc.buscarId(idSesion, idContenido );
							usuarioJson.addProperty("siguiendo", lista.getSiguiendo());
						}
						String mensaje="Default";
						if(nuevoValor) {
							if(idioma.equals("en")==true) {
								mensaje="Unfollow";
							}
							if(idioma.equals("es")) {
								mensaje="Dejar de Seguir";
							}
						}
						if(!nuevoValor) {
							if(idioma.equals("en")) {
								mensaje="Follow";
							}
							if(idioma.equals("es")) {
								mensaje="Seguir";
							}
						}					
						usuarioJson.addProperty("mensaje", mensaje);	
						response.setContentType("application/json;charset=ISO-8859-1");
						response.getOutputStream().write(usuarioJson.toString().getBytes());
						
					} catch (DataException | NumberFormatException e) {
						errorManagement ( errors, e, ErrorCodes.RECOVERY_ERROR );
					}
				} else {
					logger.warn(" -- Invalid Parameters: TYPE -- ");
					errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_PARAMETER);
				}
				
			} else if (Actions.GUARDAR.equalsIgnoreCase(action)) {		
				
				if(tipo==2||tipo==3) {
					Boolean nuevoValor=null;
					if(ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.GUARDADO), ParameterNames.GUARDADO, true)) {
						nuevoValor=false;
					} else {
						nuevoValor=true;
					}
					definedLogger (action, idSesion, idContenido, tipo, nuevoValor.toString(), 1);
					try {
						WebUtils.contenidoSvc.guardarContenido(idSesion, idContenido, nuevoValor);						
					} catch (DataException e) {
						errorManagement ( errors, e, ErrorCodes.UNABLE_CHANGE_RELATION );
					}
					
					//request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);	
					if(SessionManager.get(request, SessionAttributeNames.USUARIO)!=null) {
						try {						
							JsonObject usuarioJson = new JsonObject();
							if(tipo==2) {
								Video video=null;
								video = WebUtils.videoSvc.buscarId(idSesion, idContenido );
								usuarioJson.addProperty("guardado", video.getGuardado());
							}
							if(tipo==3) {
								Lista lista=null;
								lista = WebUtils.listaSvc.buscarId(idSesion, idContenido );
								usuarioJson.addProperty("guardado", lista.getGuardado());
							}
							String mensaje="Default";
							if(nuevoValor) {
								if(idioma.equals("en")==true) {
									mensaje="Delete";
								}
								if(idioma.equals("es")) {
									mensaje="Guardado";
								}
							}
							if(!nuevoValor) {
								if(idioma.equals("en")) {
									mensaje="Save";
								}
								if(idioma.equals("es")) {
									mensaje="Guardar";
								}
							}					
							usuarioJson.addProperty("mensaje", mensaje);
		
							response.setContentType("application/json;charset=ISO-8859-1");
							response.getOutputStream().write(usuarioJson.toString().getBytes());
							
						} catch (DataException | NumberFormatException e) {
							errorManagement ( errors, e, ErrorCodes.RECOVERY_ERROR );
						}
					}
				} else {
					logger.warn(" -- Invalid Parameters: TYPE -- ");
					errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_PARAMETER);
				}
				
				
			} else if (Actions.DENUNCIAR.equalsIgnoreCase(action)) {
				if((tipo==1 || tipo==2 || tipo==3)) {
					Boolean nuevoValor=null;
					if(ValidationUtils.validBoolean(errors, request.getParameter(ParameterNames.DENUNCIADO), ParameterNames.DENUNCIADO, true)) {
						nuevoValor=false;
					} else {
						nuevoValor=true;
					}
					definedLogger (action, idSesion, idContenido, tipo, nuevoValor.toString(), 1);
					try {
						WebUtils.contenidoSvc.denunciarContenido(idSesion, idContenido, nuevoValor);					
					} catch (DataException e) {
						errorManagement ( errors, e, ErrorCodes.UNABLE_CHANGE_RELATION );
					}				
					//request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);	
					try {
						JsonObject usuarioJson = new JsonObject();
						if(tipo==1) {
							Usuario usuario=null;
							usuario = WebUtils.usuarioSvc.buscarId(idSesion, idContenido );
							usuarioJson.addProperty("denunciado", usuario.getDenunciado());
						}
						if(tipo==2) {
							Video video=null;
							video = WebUtils.videoSvc.buscarId(idSesion, idContenido );
							usuarioJson.addProperty("denunciado", video.getDenunciado());
						}
						if(tipo==3) {
							Lista lista=null;
							lista = WebUtils.listaSvc.buscarId(idSesion, idContenido );
							usuarioJson.addProperty("denunciado", lista.getDenunciado());
						}
						String mensaje="Default";
						if(nuevoValor) {
							if(idioma.equals("en")==true) {
								mensaje="Cancel Report";
							}
							if(idioma.equals("es")) {
								mensaje="Cancelar Denuncia";
							}
						}
						if(!nuevoValor) {
							if(idioma.equals("en")) {
								mensaje="Report";
							}
							if(idioma.equals("es")) {
								mensaje="Denunciar";
							}
						}					
						usuarioJson.addProperty("mensaje", mensaje);	
						response.setContentType("application/json;charset=ISO-8859-1");
						response.getOutputStream().write(usuarioJson.toString().getBytes());
						
					} catch (DataException | NumberFormatException e) {
						errorManagement ( errors, e, ErrorCodes.RECOVERY_ERROR );
					}
				} else {
					logger.warn(" -- Invalid Parameters: TYPE -- ");
					errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_PARAMETER);
				}
				
			} else if (Actions.COMENTAR.equalsIgnoreCase(action)) {
				if(tipo==2||tipo==3) {				
					String valorRecibido = request.getParameter(ParameterNames.COMENTADO);
					if(valorRecibido!=null) {
						String nuevoValor = ValidationUtils.validString(errors, request.getParameter(ParameterNames.COMENTADO), ParameterNames.COMENTADO, true);
						definedLogger (action, idSesion, idContenido, tipo, nuevoValor.toString(), 1);
						try {
							WebUtils.contenidoSvc.comentarContenido(idSesion, idContenido, nuevoValor);
						} catch (DataException e) {
							errorManagement ( errors, e, ErrorCodes.UNABLE_CHANGE_RELATION );
						}
					} else {
						try {
							WebUtils.contenidoSvc.comentarContenido(idSesion, idContenido, null);
						} catch (DataException e) {
							errorManagement ( errors, e, ErrorCodes.UNABLE_CHANGE_RELATION );
						}
					}
					//request.setAttribute(ParameterNames.ID_CONTENIDO, idContenido);
					try {						
						JsonObject usuarioJson = new JsonObject();
						if(tipo==2) {
							Video video=null;
							video = WebUtils.videoSvc.buscarId(idSesion, idContenido );
							usuarioJson.addProperty("guardado", video.getGuardado());
						}
						if(tipo==3) {
							Lista lista=null;
							lista = WebUtils.listaSvc.buscarId(idSesion, idContenido );
							usuarioJson.addProperty("guardado", lista.getGuardado());
						}

						String mensaje="Default";
						if(valorRecibido!=null) {
							if(idioma.equals("en")==true) {
								mensaje="Comment";
							}
							if(idioma.equals("es")) {
								mensaje="Comentar";
							}
						} else {
							if(idioma.equals("en")) {
								mensaje="Delete Comment";
							}
							if(idioma.equals("es")) {
								mensaje="Borrar Comentario";
							}
						}					
						usuarioJson.addProperty("mensaje", mensaje);

						response.setContentType("application/json;charset=ISO-8859-1");
						response.getOutputStream().write(usuarioJson.toString().getBytes());

					} catch (DataException | NumberFormatException e) {
						errorManagement ( errors, e, ErrorCodes.RECOVERY_ERROR );
					}
				} else {
					logger.warn(" -- Invalid Parameters: TYPE -- ");
					errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_PARAMETER);
				}
				
			} else if (Actions.VALORAR.equalsIgnoreCase(action)) {
				
				if(tipo==1||tipo==2||tipo==3) {			
				Integer nuevoValor= ValidationUtils.validInt(errors, request.getParameter(ParameterNames.MI_VALORACION), ParameterNames.MI_VALORACION, true);			
					try {
						WebUtils.contenidoSvc.valorarContenido(idSesion, idContenido, nuevoValor);
					} catch (DataException e) {					
						 errorManagement ( errors, e, ErrorCodes.UNABLE_CHANGE_RELATION );
					}
					try {						
						JsonObject objetoJson = new JsonObject();
						objetoJson.addProperty("valor", WebUtils.contenidoSvc.getValoracion(idContenido));
						response.setContentType("application/json;charset=ISO-8859-1");
						response.getOutputStream().write(objetoJson.toString().getBytes());
						
					} catch (DataException | NumberFormatException e) {
						errorManagement ( errors, e, ErrorCodes.RECOVERY_ERROR );
					}					
				} else {
					logger.warn(" -- Invalid Parameters: TYPE -- ");
					errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_PARAMETER);
				}
			} else {
				logger.error("Action desconocida");
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static void definedLogger (String action, Long User, Long Content, int tipo, String nV, int choose) {
		
		if(choose==1) {
			if (logger.isDebugEnabled()) {					
				logger.debug(" Action={}: Usuario={} Contenido={} Tipo={} Nuevo Valor={}",
									action, User, Content, tipo,  nV);
			}
		}
	}
	
	private static void errorManagement (ErrorManager errors, Exception e, String errorCode) {
		
		logger.warn(e.getMessage(), e);
		errors.add(ParameterNames.ACTION, errorCode);

	}
	


}
