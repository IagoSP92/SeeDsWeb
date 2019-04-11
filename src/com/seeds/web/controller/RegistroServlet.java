/*package com.seeds.web.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.isp.seeds.Exceptions.DataException;
import com.isp.seeds.model.Usuario;
import com.isp.seeds.service.UsuarioServiceImpl;
import com.isp.seeds.service.spi.UsuarioService;
import com.seeds.web.utils.DateUtils;
import com.seeds.web.utils.ValidationUtils;



@WebServlet("/Registro")
public class RegistroServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger();

	UsuarioService usuarioSvc = null;
	ValidationUtils validationUtils = null;
	DateUtils dateUtils = null;

	public RegistroServlet() {

		usuarioSvc = new UsuarioServiceImpl();
		validationUtils = new ValidationUtils();
		dateUtils = new DateUtils();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Writer out = response.getWriter();

		String nombre =  request.getParameter(ParameterNames.NOMBRE);
		String email = request.getParameter(ParameterNames.EMAIL);
		String password = request.getParameter(ParameterNames.PASSWORD);

		String nombreReal = request.getParameter(ParameterNames.NOMBRE_REAL);
		String apellidos = request.getParameter(ParameterNames.APELLIDOS);
		String fechaNacimineto = request.getParameter(ParameterNames.FECHA_NAC);

		String idioma = "ESP";

		Usuario u = new Usuario();

		u.setNombre(ValidationUtils.validateString(nombre));
		u.setEmail(ValidationUtils.validateString(email));
		u.setContrasena(ValidationUtils.validateString(password));

		u.setNombreReal(ValidationUtils.validateString(nombreReal));
		u.setApellidos(ValidationUtils.validateString(apellidos));
		u.setFechaNac(dateUtils.dateFormat(fechaNacimineto));

		u.setTipo(1);
		u.setIdAutor(null);

		try {
			u= usuarioSvc.crearCuenta(u);
		} catch (DataException e) {
			// ERROR LOG
		}

		request.setAttribute("usuairo", u);

		request.getRequestDispatcher("/IndexJSP").forward(request, response);

		out.flush();		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
*/