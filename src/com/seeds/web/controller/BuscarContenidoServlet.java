package com.seeds.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.dao.impl.ContenidoDAOImpl;
import com.isp.seeds.model.Contenido;
import com.isp.seeds.service.ContenidoServiceImpl;
import com.isp.seeds.service.criteria.ContenidoCriteria;
import com.isp.seeds.service.spi.ContenidoService;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.ValidationUtils;


@WebServlet("/Contenido")
public class BuscarContenidoServlet extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
	
	private ContenidoService contenidoSvc = null;
	ValidationUtils validationUtils = null;
	private DateUtils dateUtils = null;

    public BuscarContenidoServlet() {
  
    	contenidoSvc = new ContenidoServiceImpl();
    	validationUtils = new ValidationUtils();
    	dateUtils = new DateUtils();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		logger.debug((request.getParameterMap()).toString());
			
		Writer out = response.getWriter();
		String devolvemos= null;

		String nombre = request.getParameter("nombre");
		String fechaMin = request.getParameter("fechaMin");
		String fechaMax = request.getParameter("fechaMax");
		String id = request.getParameter("id");
		
		List<Contenido> listado = new ArrayList<Contenido>();

		
			
			ContenidoCriteria criteria = new ContenidoCriteria();
			

			criteria.setNombre(validationUtils.validateString(nombre));
	
			
			criteria.setFechaAlta(dateUtils.dateFormat(fechaMin));


			criteria.setFechaAltaHasta(dateUtils.dateFormat(fechaMax));
	
			
			criteria.setIdContenido(validationUtils.validateLong(id));

			
			
			try {
				listado = contenidoSvc.buscarCriteria(criteria);
			} catch (DataException e) {
				e.printStackTrace();
			}
			
//			devolvemos="CONTENIDOS COINCIDENTES: <br>";
//			for(Contenido c: listado) {
//				devolvemos+= c.toString();
//				devolvemos+="\n<br><br>";
//			}
//			
//			out.append(devolvemos);
			
		
		request.setAttribute("resultados", listado);
		
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
