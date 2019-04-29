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

import com.google.gson.JsonArray;
import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.model.Video;
import com.isp.seeds.service.util.Results;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;
import com.seeds.web.utils.ValidationUtils;
import com.seeds.web.utils.WebUtils;

/**
 * Servlet implementation class RelationService
 */
@WebServlet("/ayaxlist")
public class ListEdition extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(ListEdition.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ParameterNames.ACTION);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Action {}: {}", action, ToStringBuilder.reflectionToString(request.getParameterMap()));
		}
		ErrorManager errors = new ErrorManager(); 
		
		Long idSesion= ((Usuario)SessionManager.get(request, SessionAttributeNames.USUARIO)).getId();
		idSesion = ValidationUtils.validLong(errors, idSesion.toString(), ParameterNames.ID_SESION, true);
		// Se ha hecho esta conversion para validar el parámetro utilizando los métodos ya existentes
		Long idContenido =ValidationUtils.validLong(errors, request.getParameter
				(ParameterNames.ID_CONTENIDO), ParameterNames.ID_CONTENIDO, true);
		
		if(errors.hasErrors()) {
			logger.warn(" -- Invalid Parameters: BASIC -- ");
			errors.add(ParameterNames.ACTION, ErrorCodes.INVALID_PARAMETER);
			
		} else {
			if (Actions.INCLUIR.equalsIgnoreCase(action)) {
				
				String incluir = request.getParameter(ParameterNames.ID_OPERAR);
				Long idIncluir =ValidationUtils.validLong(errors, incluir, ParameterNames.ID_OPERAR, true);
				
				try {
					WebUtils.listaSvc.meterVideo(idContenido, idIncluir, null);
				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.UPDATE_ERROR);
				}
				Results<Video> videosLista = null;
				JsonArray videosListaJson = new JsonArray();
				JsonArray videosUsuarioJson = new JsonArray();

				try {
					Double infinito = Double.POSITIVE_INFINITY;
					// Workaround para aprobechar funcion que devuelve resultados paginados
					// Podrá ser añadida una sin paginación o controlarlo ene sta con un IF y un Boolean
					videosLista = WebUtils.listaSvc.verVideosLista(idContenido, 0, infinito.intValue());
//					for(Video v: videosLista) {
//						videosListaJson.add(number);;
//						videosListaJson.add(string);
//					}

				} catch (DataException e) {
					logger.warn(e.getMessage(), e);
					errors.add(ParameterNames.ACTION, ErrorCodes.RECOVERY_ERROR);
				}
				request.setAttribute(ParameterNames.AUTENTICADO, true);
//				void .complete(ViewPath.DETALLE_LISTA);
//				JsonObject jsonObject = new JsonObject();
//				jsonObject.addProperty("videosListaJson", videosListaJson);
//				jsonObject.addProperty("videosUsuarioJson", videosUsuarioJson);
				
				/*
				List<String> foo = new ArrayList<String>();
				foo.add("A");
				foo.add("B");
				foo.add("C");

				String json = new Gson().toJson(videosLista );
				
				response.getWriter().write(json);

				response.setContentType("application/json;charset=ISO-8859-1");
				//response.getOutputStream().write(usuarioJson.toString().getBytes());
*/
				
			} else if (Actions.EXCLUIR.equalsIgnoreCase(action)) {		
				


			} else if (Actions.CAMBIAR_POSICION.equalsIgnoreCase(action)) {

			} 
			else {
				logger.error("Action desconocida");
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
