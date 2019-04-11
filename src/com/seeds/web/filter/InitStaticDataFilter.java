package com.seeds.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Pais;
import com.isp.seeds.service.PaisServiceImpl;
import com.isp.seeds.service.spi.PaisService;
import com.seeds.web.controller.ConstantValues;
import com.seeds.web.utils.SessionManager;


@WebFilter("/*")
public class InitStaticDataFilter implements Filter {
	
	private static Logger logger = LogManager.getLogger(InitStaticDataFilter.class.getName());
	
	private PaisService paisSvc = null;
 
    public InitStaticDataFilter() {
    	paisSvc = new PaisServiceImpl();
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String idioma = (String) SessionManager.get(httpRequest, ConstantValues.IDIOMA);
		
		logger.debug("Idioma {}", idioma);
				
		try {
			List<Pais> paises = paisSvc.findAll(idioma);
			
			request.setAttribute("paises", paises);	
			logger.debug("Paises {}", paises);			
			
		} catch (DataException e) {
			e.printStackTrace();
		}

		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}
