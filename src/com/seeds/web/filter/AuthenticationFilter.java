package com.seeds.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seeds.web.controller.Actions;
import com.seeds.web.controller.ParameterNames;
import com.seeds.web.controller.ViewPath;
import com.seeds.web.utils.SessionAttributeNames;
import com.seeds.web.utils.SessionManager;

@WebFilter("/usuario/*")
public class AuthenticationFilter implements Filter {
	
	private static Logger logger = LogManager.getLogger(AuthenticationFilter.class);

    public AuthenticationFilter() {
    }

	public void doFilter(ServletRequest request, 
						 ServletResponse response, 
						 FilterChain chain) throws IOException, ServletException {				
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String action = httpRequest.getParameter(ParameterNames.ACTION);
		
		if ((SessionManager.get(httpRequest, SessionAttributeNames.USUARIO)==null)
			&& !Actions.ENTRAR.equals(action)
			&& !Actions.DETALLE.equals(action)
			&& !Actions.DETALLE_PERFIL.equals(action)
			&& !Actions.DETALLE_VIDEO.equals(action)
			&& !Actions.DETALLE_LISTA.equals(action)
			&& !Actions.BUSCAR.equals(action)
			&& !Actions.CAMBIAR_LOCALE.equals(action)
			&& !Actions.PRERREGISTRO.equals(action)
			&& !Actions.REGISTRO.equals(action)) {
			// Usuario no autenticado
			logger.info("Usuario deben autenticarse para "+action+"...");
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(httpRequest.getContextPath() + ViewPath.REGISTRO);
		} else {
			// Usuario autenticado o accion no protegida
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public void destroy() {
	}
}
